package com.metaarivu;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

public class TableAPIExample2 {

	public static void main(String[] args) throws Exception {

		System.out.println("Starting Table API Examples2 ==>");

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		DataStream<Tuple5<String, String, String, Double, Double>> stocks = env.socketTextStream("localhost", 9991)
				.map(new StockSplitter());

		Table stockTable = tableEnv
				.fromDataStream(stocks)
				.as("stockId", "stockName", "sector", "price");

		tableEnv.createTemporaryView("stockTable", stockTable);

		Table resultTable = tableEnv.sqlQuery("SELECT * FROM stockTable");

		resultTable.printSchema();

		DataStream<Row> resultStream = tableEnv.toDataStream(resultTable);

		resultStream.print();

		env.execute("Table API Example 2");
		

	}

	public static class StockSplitter implements MapFunction<String, Tuple5<String, String, String, Double, Double>> {
		 
		private static final long serialVersionUID = 1L;

		public Tuple5<String, String, String, Double, Double> map(String value) // 1,HDFC,BANKING,1450
		{
			String[] stock = value.split(","); // words = [{1},{HDFC},{BANKING},{1450}
			return new Tuple5<String, String, String, Double, Double>(stock[0], stock[1], stock[2],
					Double.valueOf(stock[3]), 0d);
		}
	}

}
