package com.junit.junit5Application.temp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TempCourse")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id")
	private Integer id;

	@Column(name = "course_name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "course_dept_id")
	private Department department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Course(String name) {
		super();
		this.name = name;
	}
	
	public Course(String name, Department department) {
		super();
		this.name = name;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", department=" + department + "]";
	}
}
