package com.junit.junit5Application.service;

import java.util.List;

import com.junit.junit5Application.model.Customer;

public interface CustomerService {
	public Customer saveCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	public void deleteCustomerByCustomerId(int customerId);

	public Customer updateCustomer(Customer customer);

	public Customer searchCutomerByCustomerId(int customerId);

	public List<Customer> searchAllCustomers();
}
