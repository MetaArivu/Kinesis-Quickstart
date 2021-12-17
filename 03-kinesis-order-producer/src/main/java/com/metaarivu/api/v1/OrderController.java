package com.metaarivu.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaarivu.model.Order;
import com.metaarivu.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private OrderService svc;

	@PostMapping("/")
	public ResponseEntity<String> creatOrder(@RequestBody Order order) {
		svc.publishOrder(order);
		return new ResponseEntity<String>("order published", HttpStatus.OK);
	}
}
