package com.company.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.beans.*;
import com.company.service.CompanyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("company")
@AllArgsConstructor
public class CompanyController {

	private CompanyService companyService;

	@PostMapping("addEmployee")
	private ResponseEntity<Long> addEmployee(@RequestBody Employee employee) {
		long newId = companyService.addEmployee(employee);
		return new ResponseEntity<Long>(newId, HttpStatus.CREATED);
	}

	@GetMapping("getEmployee/{id}")
	private ResponseEntity<Employee> getEmployee(@PathVariable long id) {
		if (id == -1) {
			return new ResponseEntity<Employee>(new Employee(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Employee>(companyService.getEmployee(id), HttpStatus.OK);
	}

	@GetMapping("getEmployees")
	private ResponseEntity<EmployeeList> getEmployees() {
		EmployeeList ls = new EmployeeList(companyService.getEmployees());
		return new ResponseEntity<EmployeeList>(ls, HttpStatus.OK);
	}

	@GetMapping("getEmployeesByName")
	private ResponseEntity<EmployeeList> getEmployeesByName(@RequestParam(defaultValue = "") String name) {
		EmployeeList ls = new EmployeeList(companyService.getEmployees(name));
		return new ResponseEntity<EmployeeList>(ls, HttpStatus.OK);
	}
}
