package com.hotel.service;

import java.util.List;

import com.hotel.entity.Customer;

public interface CustomerService {
	Customer save(Customer customer);
	List<Customer> getAllCustomers();
}
