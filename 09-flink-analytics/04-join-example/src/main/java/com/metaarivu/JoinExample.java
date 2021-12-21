package com.metaarivu;

import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.utils.ParameterTool;


public class JoinExample {
	public static void main(String[] args) throws Exception {
		System.out.println("STARTING JOIN JOB V2==>");
		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		ParameterTool params = ParameterTool.fromArgs(args);

		env.getConfig().setGlobalJobParameters(params);

		DataSet<String> orders = env.readTextFile(params.get("orders"));

		DataSet<Tuple2<String, Order>> orderData = orders.map(new MapFunction<String, Tuple2<String, Order>>() {

			@Override
			public Tuple2<String, Order> map(String value) throws Exception {
				//String split[] = value.split(",");
				Order order = new Order(value);
				return new Tuple2<String, Order>(order.getPrdId(),order);
			}
		});
		System.out.println("====ORDER DATA=====");
		orderData.print();
		DataSet<String> products = env.readTextFile(params.get("products"));

		DataSet<Tuple2<String, Product>> productData = products.map(new MapFunction<String, Tuple2<String, Product>>() {

			@Override
			public Tuple2<String, Product> map(String value) throws Exception {
				//String split[] = value.split(",");
				Product product = new Product(value);

				return new Tuple2<String, Product>(product.getPrdId(),product);
			}
		});

		System.out.println("====PRODUCT DATA=====");

		productData.print();

		DataSet<Tuple2<String, Order>> joined = orderData.join(productData).where(0).equalTo(0).with(
				new JoinFunction<Tuple2<String, Order>, Tuple2<String, Product>, Tuple2<String,Order>>() {

					@Override
					public Tuple2<String,Order> join(Tuple2<String, Order> ord, Tuple2<String, Product> prd)
							throws Exception {
						Order order = ord.f1;
						Product prod = prd.f1;
						return new Tuple2<String, Order>(ord.f1.getOrdId(), new Order(order.getOrdId(), order.getPrdId(), order.getQty(), prod.getPrdName(), prod.getUnitPrice()));
					}
				});


		joined.print();
		
		//env.execute("Join order and product example");

	}

	public static final class Tokenizer implements MapFunction<String, Tuple2<String, Integer>> {
		public Tuple2<String, Integer> map(String value) {
			return new Tuple2<String, Integer>(value, Integer.valueOf(1));
		}
	}
}