package com.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.model.Customer;

@FeignClient(name="customer-service", url="http://localhost:8082")
public interface CustomerClient {
	@GetMapping("/customers/{id}")
	Customer getCustomerById(@PathVariable("id") Long id);

}
