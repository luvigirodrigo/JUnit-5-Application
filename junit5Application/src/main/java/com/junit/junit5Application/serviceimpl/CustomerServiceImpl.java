package com.junit.junit5Application.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.junit.junit5Application.model.Customer;
import com.junit.junit5Application.repository.CustomerRepository;
import com.junit.junit5Application.service.CustomerService;

@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	@Qualifier("customerRepository")
	private CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

	@Override
	public void deleteCustomerByCustomerId(int customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer searchCutomerByCustomerId(int customerId) {
		return customerRepository.findByCustomerId(customerId);
	}

	@Override
	public List<Customer> searchAllCustomers() {
		return customerRepository.findAll();
	}

}
