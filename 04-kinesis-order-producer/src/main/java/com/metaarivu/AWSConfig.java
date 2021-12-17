package com.metaarivu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;

@Configuration
public class AWSConfig {

	@Autowired
	private AppConfig config;
	
	@Bean
	public AmazonKinesis amazonKinesis() {

		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(config.getAccessKey(), config.getSecretKey());
		
		return AmazonKinesisClientBuilder
				.standard()
				.withRegion(config.getRegion())
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
	}
}
