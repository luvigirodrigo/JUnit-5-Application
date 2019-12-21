package com.junit.junit5Application.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.junit.junit5Application.model.Employee;
import com.junit.junit5Application.repository.EmployeeRepository;
import com.junit.junit5Application.service.EmployeeService;

@Service("employeeServiceImplementor")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	@Qualifier("employeeRepository")
	private EmployeeRepository employeeRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Employee saveEmployee(Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		int x = 2 / 0;
		System.out.println("Employee " + savedEmployee + " was saved." + " Value is " + x);
		return savedEmployee;
	}
}
