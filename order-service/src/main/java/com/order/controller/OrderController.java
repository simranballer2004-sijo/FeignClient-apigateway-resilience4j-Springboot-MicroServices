package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Orders;
import com.order.model.CustomerResponse;
import com.order.service.CustomerService;
import com.order.service.OrderService;


@RestController
public class OrderController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orders/placeorder")
	public ResponseEntity<String> postMethodName(@RequestBody Orders newOrder) {
		//fetch customer from customer service for the given cust id
		CustomerResponse customerResponse=customerService.getCustomer(newOrder.getCustomerId());
		if((customerResponse.getErrorMsg()!=null) && customerResponse.getErrorMsg().contains("Customer Not found")) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerResponse.getErrorMsg());
			
		}else if((customerResponse.getErrorMsg()!=null) && customerResponse.getErrorMsg().contains("Customer Service Temporarily Unavailable"))
		{
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Customer Service Temporarily Unavailable. Please try later.");
		}
		
		return ResponseEntity.ok("Order Placed for customer: "+orderService.createOrder(newOrder));
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Orders>> getAllOrders() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	
	

}
