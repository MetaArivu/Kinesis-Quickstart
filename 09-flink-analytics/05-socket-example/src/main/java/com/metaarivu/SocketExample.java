package com.metaarivu;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SocketExample {
	public static void main(String[] args) throws Exception {
		System.out.println("STARTING WORDCOUNT JOB ==>");
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		ParameterTool params = ParameterTool.fromArgs(args);

		env.getConfig().setGlobalJobParameters(params);

		DataStream<String> employees = env.socketTextStream("localhost", 9991);

		DataStream<Tuple2<String, Integer>> counts = employees
				 //.filter(val -> val.startsWith("N"))
				 .map(new Tokenizer())
				 .keyBy(0)
				 .sum(1);
		
		counts.print();
		
		env.execute("Stream Wordcount Example");

	}

	public static final class Tokenizer implements MapFunction<String, Tuple2<String, Integer>> {
		public Tuple2<String, Integer> map(String value) {
			return new Tuple2<String, Integer>(value, Integer.valueOf(1));
		}
	}
}