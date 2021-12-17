package com.metaarivu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metaarivu.AppConfig;
import com.metaarivu.model.TweetModel;

@Service
public class TwitterService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(TwitterService.class);

	@Autowired
	private AppConfig config;

	@Autowired
	private RestTemplate restTemplate;

	public TweetModel fetchTweets(final String hashTag) {

		try {
			String tweetUrl = config.getTweetUrl() + hashTag;
			log.info("URL={}", tweetUrl);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", config.getAccessToken());
			HttpEntity httpEntity = new HttpEntity<>(headers);

			ResponseEntity<TweetModel> responseEntity = this.restTemplate.exchange(tweetUrl, HttpMethod.GET, httpEntity,
					TweetModel.class);

			TweetModel data = responseEntity.getBody();
			log.info("Tweets={}", data);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Tweet  Exception={}", e.getMessage());
		}
		return null;
	}
}
