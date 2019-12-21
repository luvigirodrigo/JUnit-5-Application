package com.junit.junit5Application.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.junit.junit5Application.model.Department;
import com.junit.junit5Application.model.Employee;
import com.junit.junit5Application.service.DepartmentService;
import com.junit.junit5Application.service.EmployeeService;

@Service("departmentServiceImplementor")
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	@Qualifier("departmentServiceImplementor")
	private DepartmentService departmentService;

	@Autowired
	@Qualifier("employeeServiceImplementor")
	private EmployeeService employeeServiceImplementor;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Department saveDepartment(Department department) {
		Department savedDepartment = departmentService.saveDepartment(department);
		Employee emp = new Employee();
		emp.setName("Luvigi");
		try {
			employeeServiceImplementor.saveEmployee(emp);
			System.out.println("Employee saved");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Employee transaction rolled back");
		}

		System.out.println("Department information saved");
		return savedDepartment;
	}

}
