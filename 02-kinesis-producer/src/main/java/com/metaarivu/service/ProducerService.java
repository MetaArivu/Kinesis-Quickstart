package com.metaarivu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.internal.eventstreaming.Message;
import com.metaarivu.model.TweetModel;

@Service
@EnableBinding(Source.class)
public class ProducerService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ProducerService.class);

	@Autowired
	private TwitterService svc;

	@Autowired
	private Source source;

	long count = 1;

	@Scheduled(fixedRate = 60000L)
	public void sendMessage() {
		TweetModel tweetModel = svc.fetchTweets("india");
		if (tweetModel != null) {
			if(tweetModel.getData()!=null) {
				tweetModel.getData().forEach(tweet->{
					
					String msg = tweet.toJSON();
					if (msg != null) {
						boolean flag = source.output().send(MessageBuilder.withPayload(msg).build());
						log.info("Msg Published={}, Count={}, Data={}", flag, count, msg);
					}
					count++;
				});
			}
		}
		
	}

}
