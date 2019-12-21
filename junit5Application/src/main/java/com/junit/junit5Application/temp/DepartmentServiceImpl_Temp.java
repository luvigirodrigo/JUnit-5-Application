package com.junit.junit5Application.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("departmentServiceImpl_Temp")
public class DepartmentServiceImpl_Temp {
	@Autowired
	@Qualifier("departmentRepository_Temp")
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}
}
