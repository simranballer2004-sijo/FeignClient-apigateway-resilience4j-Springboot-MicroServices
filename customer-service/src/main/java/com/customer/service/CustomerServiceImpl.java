package com.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entity.Customer;
import com.customer.exception.CustomerAlreadyExistsException;
import com.customer.exception.CustomerNotFoundException;
import com.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}
	
	@Override
	public List<Customer> fetchAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		Optional<Customer> optCust= customerRepository.findById(id);
		if(optCust.isEmpty()) throw new CustomerNotFoundException(id);
		return optCust;
	}

	@Override
	public Customer createCustomer(Customer newCustomer) {
		Customer customer=  customerRepository.getCustomerByEmail(newCustomer.getEmail());
		if(customer!=null) throw new CustomerAlreadyExistsException(newCustomer.getEmail());
			return customerRepository.save(newCustomer);
	}

	@Override
	public Customer updateCustomer(Customer newCustomer, Long id) {
		Optional<Customer> custOptional=customerRepository.findById(id);
		Customer custObj=null;
		if(custOptional.isPresent()) {
			custObj=custOptional.get();
			custObj.setName(newCustomer.getName());
			custObj.setEmail(newCustomer.getEmail());
			custObj.setLocation(newCustomer.getLocation());
		}
		return customerRepository.save(custObj);
	}

	@Override
	public String deleteCustomer(Long id) {
		Optional<Customer> custOptional=customerRepository.findById(id);
		Customer custObj=null;
		String deleteMsg=null;
		if(custOptional.isPresent()) {
			custObj=custOptional.get();
			customerRepository.delete(custObj);
			deleteMsg="Customer Successfully Deleted for id:"+id;
		}
		return deleteMsg;
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.getCustomerByEmail(email);
	}

}
