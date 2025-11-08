package com.customer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.entity.Customer;
import com.customer.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> fetchAllCustomers() {
		return  ResponseEntity.ok(customerService.fetchAllCustomers());
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
		return  ResponseEntity.ok(customerService.createCustomer(newCustomer));
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer updatedCust, @PathVariable("id") Long id) {
		return  ResponseEntity.ok(customerService.updateCustomer(updatedCust,id));
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable("id") Long id) {
		return  ResponseEntity.ok(customerService.getCustomerById(id));
	}	
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		return  ResponseEntity.ok(customerService.deleteCustomer(id));
	}
}
