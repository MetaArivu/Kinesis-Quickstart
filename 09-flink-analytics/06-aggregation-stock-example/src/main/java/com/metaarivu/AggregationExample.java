package com.metaarivu;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class AggregationExample {
	public static void main(String[] args) throws Exception {
		System.out.println("Starting Stream Stock Trend Example ==>");
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStream<Tuple5<String, String, String, Double, String>> stocks = env.socketTextStream("localhost", 9991)
				.map(new StockSplitter());

		SingleOutputStreamOperator<Tuple5<String, String, String, Double, String>> stockTrend = 
				 stocks.keyBy(v -> v.f2)
				.reduce(new StockReducer());

		stockTrend.print();

		env.execute("Stream Stock Trend Example");

	}

	public static class StockSplitter implements MapFunction<String, Tuple5<String, String, String, Double, String>> {
		public Tuple5<String, String, String, Double, String> map(String value) // 1,HDFC,BANKING,1450
		{
			String[] stock = value.split(","); // words = [{1},{HDFC},{BANKING},{1450}
			return new Tuple5<String, String, String, Double, String>(stock[0], stock[1], stock[2],
					Double.valueOf(stock[3]), "NA");
		}
	}

	public static class StockReducer implements ReduceFunction<Tuple5<String, String, String, Double, String>> {

		@Override
		public Tuple5<String, String, String, Double, String> reduce(
				Tuple5<String, String, String, Double, String> current,
				Tuple5<String, String, String, Double, String> prev) throws Exception {

			String trend = "NA";
			if (current.f3 != null && prev.f3 != null) {
				if (current.f3.doubleValue() == prev.f3.doubleValue()) {
					trend = "Same";
				} else
					trend = current.f3.doubleValue() > prev.f3.doubleValue() ? "Increased" : "Decreased";
			}

			return new Tuple5<String, String, String, Double, String>(current.f0, current.f1, current.f2, current.f3,
					trend);
		}

	}
}