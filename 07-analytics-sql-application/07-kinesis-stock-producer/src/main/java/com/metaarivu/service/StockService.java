package com.metaarivu.service;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.metaarivu.AppConfig;
import com.metaarivu.model.Stock;
import com.metaarivu.model.StockDataGenerator;
import com.metaarivu.util.StockData;

@Service
public class StockService {

	private static final Logger log = LoggerFactory.getLogger(StockService.class);

	@Autowired
	private AppConfig config;

	@Autowired
	private AmazonKinesis amazonKinesis;

	public void publishStock(Stock stock) {
		
		if (stock != null) {
			String event = stock.toJSON();
			if (event != null) {

				PutRecordRequest putRecordRequest = new PutRecordRequest();
				putRecordRequest.setStreamName(config.getInputstreamname());
				putRecordRequest.setPartitionKey(stock.getStockCode());

				String key = StockData.getInstance().get(stock.getStockId());

				if (key != null) {
					//putRecordRequest.setSequenceNumberForOrdering(stock.getStockId());
				}
				putRecordRequest.withData(ByteBuffer.wrap(event.getBytes()));

				PutRecordResult putRecordResult = amazonKinesis.putRecord(putRecordRequest);

				//StockData.getInstance().put(key, stock.getStockId());

				log.info("SQNO=" + putRecordResult.getSequenceNumber()+" Data="+event);

			} else {
				log.error("Stock Message cannot be published..");
			}
		} else {
			log.error("Stock cannot be published..");
		}

	}

	@Scheduled(fixedRate = 5000L)
	public void generateData() {
		System.out.println();
		log.info("-------------------Producing Stock Data-------------------");
		StockDataGenerator.getInstance().stocksWithRamdomPrice().forEach(this::publishStock);
	}

}
