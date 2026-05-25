package com.vcube.crsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.crsapp.exception.CustomerNotFoundException;
import com.vcube.crsapp.model.Customer;
import com.vcube.crsapp.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) throws CustomerNotFoundException {
		return customerRepository.findById(id).orElseThrow(()->  new CustomerNotFoundException("customer not found : "));
	}

}
