package com.metaarivu;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent.KinesisEventRecord;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaarivu.model.Order;

public class OrdersConsumer implements RequestHandler<KinesisEvent, Void> {

	@Override
	public Void handleRequest(KinesisEvent event, Context context) {
		LambdaLogger logger = context.getLogger();

		logger.log("Kinesis Java Lambda Consumer Invoked: records = " + event.getRecords().size());

		for (KinesisEventRecord rec : event.getRecords()) {

			logger.log("Processed order array: " + rec.getKinesis().getData().array());
			
			try {
				Order order=  new ObjectMapper().readValue(rec.getKinesis().getData().array(), Order.class);
				logger.log("Order Data="+order.toJSON()+" PKey="+rec.getKinesis().getPartitionKey()+"Seq="+rec.getKinesis().getSequenceNumber());
			} catch (JsonParseException e) {
				
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
}
