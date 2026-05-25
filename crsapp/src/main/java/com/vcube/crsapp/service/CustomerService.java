package com.vcube.crsapp.service;

import java.util.List;

import com.vcube.crsapp.exception.CustomerNotFoundException;
import com.vcube.crsapp.model.Customer;

public interface CustomerService {
	Customer addCustomer(Customer customer);

	List<Customer> getAllCustomers();

	Customer getCustomerById(Long id) throws CustomerNotFoundException;
}
