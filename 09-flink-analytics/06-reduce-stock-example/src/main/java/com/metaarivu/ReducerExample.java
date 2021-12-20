package com.metaarivu;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class ReducerExample {
	public static void main(String[] args) throws Exception {
		System.out.println("Starting Stream Stock Trend Example ==>");
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStream<Tuple5<String, String, String, Double, Double>> stocks = env.socketTextStream("localhost", 9991)
				.map(new StockSplitter());

		SingleOutputStreamOperator<Tuple5<String, String, String, Double, Double>> stockReduced = stocks
				.keyBy(v -> v.f1)
				.reduce(new StockReducer());

		stockReduced.print();

		env.execute("Stream Stock Trend Example");

	}

	public static class StockSplitter implements MapFunction<String, Tuple5<String, String, String, Double, Double>> {
		public Tuple5<String, String, String, Double, Double> map(String value) // 1,HDFC,BANKING,1450
		{
			String[] stock = value.split(","); // words = [{1},{HDFC},{BANKING},{1450}
			return new Tuple5<String, String, String, Double, Double>(stock[0], stock[1], stock[2],
					Double.valueOf(stock[3]), 0d);
		}
	}

	public static class StockReducer implements ReduceFunction<Tuple5<String, String, String, Double, Double>> {

		@Override
		public Tuple5<String, String, String, Double, Double> reduce(
				Tuple5<String, String, String, Double, Double> current,
				Tuple5<String, String, String, Double, Double> prev) throws Exception {

			return new Tuple5<String, String, String, Double, Double>(current.f0, current.f1, current.f2, current.f3,
					prev.f3);
		}

	}
}