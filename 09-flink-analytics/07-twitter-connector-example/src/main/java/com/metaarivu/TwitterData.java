package com.metaarivu;

/* java imports */
import java.util.Properties;
/* flink imports */
import org.apache.flink.util.Collector;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
/* parser imports */
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
/* flink streaming twittter imports */
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import org.apache.flink.core.fs.Path;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;

public class TwitterData {
	public static void main(String[] args) throws Exception {
		System.out.println("STARTING TWITTER CONNECTOR=========");
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty(TwitterSource.CONSUMER_KEY, "JTQvniDhww88cLB2EmxGG8onM");
		properties.setProperty(TwitterSource.CONSUMER_SECRET, "y1Vr5mM0l73zppsBCTj1hbl37oClKYNOJvCcYTeoWYwbXIU5Es");
		properties.setProperty(TwitterSource.TOKEN, "75206984-ON58zQYgtqsyjx3gjCZtz6IgnTFDIB0Ot02n34Hic");
		properties.setProperty(TwitterSource.TOKEN_SECRET, "BsYG8xSSKe1uADJnXdjgEt8gPWMpuU59kFAmzL80Y0Feg");

		env
		.addSource(new TwitterSource(properties))
		.map(new MapFunction<String, JsonNode>() {
			@Override
			public JsonNode map(String value) throws Exception {
				return new ObjectMapper().readValue(value, JsonNode.class);
			}
		})
		.filter(new FilterFunction<JsonNode>() {
			@Override
			public boolean filter(JsonNode node) throws Exception {
				boolean isEnglish = node.has("user") && node.get("user").has("lang")
						&& node.get("user").get("lang").asText().equals("en");

				if (!isEnglish) {
					isEnglish = node.has("retweeted_status") && node.get("retweeted_status").has("lang")
							&& node.get("retweeted_status").get("lang").asText().equals("en");
				}
				return isEnglish;
			}
		})
		.flatMap(new FlatMapFunction<JsonNode, Tuple2<String, Integer>>() {
			@Override
			public void flatMap(JsonNode node, Collector<Tuple2<String, Integer>> out) throws Exception {
				if(node.has("text")) {
					out.collect(new Tuple2<String, Integer>(node.get("text").asText(), 1));
				}
			}
		})
		.addSink(StreamingFileSink.forRowFormat(new Path("/tmp/tweet"), new SimpleStringEncoder("UTF-8"))
				.withRollingPolicy(DefaultRollingPolicy.builder().build()).build());

		env.execute("Twitter Example");
	}

	public static class TweetParser implements MapFunction<String, JsonNode> {

		public JsonNode map(String value) throws Exception {
			ObjectMapper jsonParser = new ObjectMapper();

			JsonNode node = jsonParser.readValue(value, JsonNode.class);
			return node;
		}
	}


}
