package com.junit.junit5Application.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("departmentRepository_Temp")
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
