package com.metaarivu.service;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.metaarivu.AppConfig;
import com.metaarivu.util.OrderData;

@Service
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private AppConfig config;

	@Autowired
	private AmazonKinesis amazonKinesis;

	public void publishOrder(com.metaarivu.model.Order order) {
		if (order != null) {
			String event = order.toJSON();
			if (event != null) {

				PutRecordRequest putRecordRequest = new PutRecordRequest();
				putRecordRequest.setStreamName(config.getInputstreamname());
				putRecordRequest.setPartitionKey(order.getCustomerId());
				
				String key = OrderData.getInstance().get(order.getId());
				
				if(key!=null) {
					putRecordRequest.setSequenceNumberForOrdering(order.getId());
				}
				putRecordRequest.withData(ByteBuffer.wrap(event.getBytes()));

				PutRecordResult putRecordResult = amazonKinesis.putRecord(putRecordRequest);

				OrderData.getInstance().put(key, order.getId());

				log.info("Order event Published, Record sequence number: " + putRecordResult.getSequenceNumber());
		
			}else {
				log.error("Order Message cannot be published..");
			}
		}else {
			log.error("Order cannot be published..");
		}

	}

}
