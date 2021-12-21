package com.metaarivu;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

public class TableAPIExample {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		System.out.println("Starting Table API Examples ==>");

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStream<Tuple5<String, String, String, Double, Double>> stocks = env.socketTextStream("localhost", 9991)
				.map(new StockSplitter());

		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		Table tableStream = tableEnv.fromDataStream(stocks, "stockId, stockName, sector, price");

		tableEnv.registerTable("stocktable", tableStream);

		Table result = tableEnv.scan("stocktable").select("stockId, stockName, sector, price");

		result.printSchema();

		DataStream<Row> resultSet = tableEnv.toAppendStream(result, Row.class);

		resultSet.print();

		env.execute("Table API Example ");

	}

	public static class StockSplitter implements MapFunction<String, Tuple5<String, String, String, Double, Double>> {
		public Tuple5<String, String, String, Double, Double> map(String value) // 1,HDFC,BANKING,1450
		{
			String[] stock = value.split(","); // words = [{1},{HDFC},{BANKING},{1450}
			return new Tuple5<String, String, String, Double, Double>(stock[0], stock[1], stock[2],
					Double.valueOf(stock[3]), 0d);
		}
	}

}
