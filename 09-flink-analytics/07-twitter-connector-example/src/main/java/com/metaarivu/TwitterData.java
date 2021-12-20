package com.metaarivu;

/* java imports */
import java.util.Properties;
/* flink imports */
import org.apache.flink.util.Collector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.common.functions.FlatMapFunction;
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
		System.out.println("====STARTING TWITTER DS=========");
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties twitterCredentials = new Properties();
		twitterCredentials.setProperty(TwitterSource.CONSUMER_KEY, "JTQvniDhww88cLB2EmxGG8onM");
		twitterCredentials.setProperty(TwitterSource.CONSUMER_SECRET,
				"y1Vr5mM0l73zppsBCTj1hbl37oClKYNOJvCcYTeoWYwbXIU5Es");
		twitterCredentials.setProperty(TwitterSource.TOKEN, "75206984-ON58zQYgtqsyjx3gjCZtz6IgnTFDIB0Ot02n34Hic");
		twitterCredentials.setProperty(TwitterSource.TOKEN_SECRET, "BsYG8xSSKe1uADJnXdjgEt8gPWMpuU59kFAmzL80Y0Feg");

		DataStream<String> twitterData = env.addSource(new TwitterSource(twitterCredentials));


		twitterData.flatMap(new TweetParser()).addSink(StreamingFileSink.forRowFormat(new Path(
				"/tmp/tweet"),
				new SimpleStringEncoder<Tuple2<String, Integer>>("UTF-8"))
				.withRollingPolicy(DefaultRollingPolicy.builder().build()).build());

		env.execute("Twitter Example");
	}

	public static class TweetParser implements FlatMapFunction<String, Tuple2<String, Integer>> {

		public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
			ObjectMapper jsonParser = new ObjectMapper();
			JsonNode node = jsonParser.readValue(value, JsonNode.class);

			boolean isEnglish = node.has("user") && node.get("user").has("lang")
					&& node.get("user").get("lang").asText().equals("en");

			if(!isEnglish) {
				isEnglish = node.has("retweeted_status") &&  node.get("retweeted_status").has("lang")
						&&  node.get("retweeted_status").get("lang").asText().equals("en");
			}
			boolean hasText = node.has("text");
			System.out.println(isEnglish +"<==>"+ hasText);
			if (isEnglish && hasText) {
				String tweet = node.get("text").asText();
				out.collect(new Tuple2<String, Integer>(tweet, 1));
			}
		}
	}
}