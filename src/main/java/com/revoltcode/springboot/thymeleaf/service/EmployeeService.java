package com.revoltcode.springboot.thymeleaf.service;

import java.util.List;

import com.revoltcode.springboot.thymeleaf.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void delete(int id);
}
