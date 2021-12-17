package com.metaarivvu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Sink.class)
public class ConsumerService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ConsumerService.class);

	@StreamListener("input")
	public void input(String msg) {
		log.info("Msg Consumed={}", msg);
	}
}
