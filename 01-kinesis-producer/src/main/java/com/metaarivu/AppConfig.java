package com.metaarivu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Value("${spring.twitter.access.url}")
	private String tweetUrl;

	@Value("${spring.twitter.access.token}")
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public String getTweetUrl() {
		return tweetUrl;
	}

}