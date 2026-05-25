package com.vcube.crsapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.crsapp.model.Customer;
import com.vcube.crsapp.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		return ResponseEntity.ok(service.addCustomer(customer));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(service.getAllCustomers());
	}
	 @GetMapping("/{id}")
	    public String getCustomer(@PathVariable Long id) {
	        return "Customer id : " + id;
	    }
}