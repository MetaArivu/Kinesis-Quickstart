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
		System.out.println("STARTING JOIN JOB==>");
		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		ParameterTool params = ParameterTool.fromArgs(args);

		env.getConfig().setGlobalJobParameters(params);

		DataSet<String> employees = env.readTextFile(params.get("employees"));

		DataSet<Tuple2<String, String>> empData = employees.map(new MapFunction<String, Tuple2<String, String>>() {

			@Override
			public Tuple2<String, String> map(String value) throws Exception {
				String split[] = value.split(",");

				return new Tuple2<String, String>(split[2], split[0] + "," + split[1]);
			}
		});
		System.out.println("\n\n Employees Data==>");
		empData.print();
		DataSet<String> departments = env.readTextFile(params.get("departments"));

		DataSet<Tuple2<String, String>> deptData = departments.map(new MapFunction<String, Tuple2<String, String>>() {

			@Override
			public Tuple2<String, String> map(String value) throws Exception {
				String split[] = value.split(",");

				return new Tuple2<String, String>(split[0], split[1]);
			}
		});

		System.out.println("\n\n Department Data==>");
		deptData.print();

		DataSet<Tuple3<String, String, String>> joined = empData.join(deptData).where(0).equalTo(0).with(
				new JoinFunction<Tuple2<String, String>, Tuple2<String, String>, Tuple3<String, String, String>>() {

					@Override
					public Tuple3<String, String, String> join(Tuple2<String, String> emp, Tuple2<String, String> dept)
							throws Exception {
						return new Tuple3<String, String, String>(emp.f1, emp.f0, dept.f1);
					}
				});

		System.out.println("\n\n Employee & Department Data==>");

		joined.print();

	}

	public static final class Tokenizer implements MapFunction<String, Tuple2<String, Integer>> {
		public Tuple2<String, Integer> map(String value) {
			return new Tuple2<String, Integer>(value, Integer.valueOf(1));
		}
	}
}