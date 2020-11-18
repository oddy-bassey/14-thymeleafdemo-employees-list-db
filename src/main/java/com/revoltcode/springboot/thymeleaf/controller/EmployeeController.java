package com.revoltcode.springboot.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revoltcode.springboot.thymeleaf.entity.Employee;
import com.revoltcode.springboot.thymeleaf.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService; 
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	//add a mapping for '/list'
	@GetMapping("/list")
	public String listEmployees(Model model) {
		
		//get employees from database
		List<Employee> employees = employeeService.findAll();
		//add to the spring model
		model.addAttribute("employees", employees);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showForm(Model model) {
		
		//create the model attribute to bind the data
		Employee employee = new Employee();
		
		//add to model
		model.addAttribute("employee", employee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showUpdateForm(@RequestParam("employeeId") int id, Model model) {
		
		//get the employee from the service
		Employee employee = employeeService.findById(id);
		
		//set employee as a model attribute
		model.addAttribute("employee", employee);
		
		//send over to update form
		
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {

		//save the employee
		employeeService.save(employee);
		
		//use a redirect to prevent duplicate submission
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int id) {
		
		//delete the employee
		employeeService.delete(id);
		
		//redirect to /employee/list
		return "redirect:/employees/list";
	}
}


















