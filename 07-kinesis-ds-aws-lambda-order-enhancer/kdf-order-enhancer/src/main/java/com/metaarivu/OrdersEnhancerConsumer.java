package com.metaarivu;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.KinesisAnalyticsInputPreprocessingResponse;
import com.amazonaws.services.lambda.runtime.events.KinesisAnalyticsInputPreprocessingResponse.Record;
import com.amazonaws.services.lambda.runtime.events.KinesisFirehoseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaarivu.model.Order;

public class OrdersEnhancerConsumer {

	public KinesisAnalyticsInputPreprocessingResponse transformRecords(KinesisFirehoseEvent event, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("Processing Records.."+event.getRecords());
		KinesisAnalyticsInputPreprocessingResponse response = new KinesisAnalyticsInputPreprocessingResponse();
		List<Record> transformed = new ArrayList<Record>();
		if (event != null && event.getRecords() != null) {

			logger.log("Record Count="+event.getRecords().size());

			for (KinesisFirehoseEvent.Record rec : event.getRecords()) {

				logger.log("Processed order array: " + rec.getData().array());
				logger.log("Record Metadata= " + rec.getKinesisRecordMetadata());

				Record record = new Record();

				try {
					
					Order order = new ObjectMapper().readValue(rec.getData().array(), Order.class);

					logger.log("Order Data=" + order.toJSON());
					record.setData(ByteBuffer.wrap(order.toJSON().getBytes()));
					record.setRecordId(rec.getRecordId());
					record.setResult(KinesisAnalyticsInputPreprocessingResponse.Result.Ok);
					// return json;
				} catch (Exception e) {

					e.printStackTrace();
					record.setData(ByteBuffer.wrap(e.getMessage().getBytes()));
					record.setRecordId(rec.getRecordId());
					record.setResult(KinesisAnalyticsInputPreprocessingResponse.Result.ProcessingFailed);

				}
				transformed.add(record);

			}
			
			response.setRecords(transformed);
		}
		
		logger.log("Records Returned="+transformed.size());
		return response;
	}


}
