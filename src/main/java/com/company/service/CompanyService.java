package com.company.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.beans.Employee;
import com.company.beans.Job;
import com.company.repo.EmployeeRepository;
import com.company.repo.JobRepository;

@Component
public class CompanyService {

	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private JobRepository jobRepo;

	public Long addEmployee(Employee e) {
		empRepo.save(e);
		return e.getId();
	}

	public Employee getEmployee(long empId) {
		if (empRepo.existsById(empId)) {
			return empRepo.getOne(empId);
		}
		return null;
	}

	public List<Employee> getEmployees(String name) {
		return empRepo.findEmployeeByNameLike("%" + name + "%");
	}

	public List<Employee> getEmployees() {
		return empRepo.findAll();
	}

	public List<Job> getJobs() {
		return jobRepo.findAll();
	}

	public List<Job> getJobs(Date endDate) {
		return jobRepo.findJobByEndDate(endDate);
	}

	public List<Job> getJobs(Date start, Date end) {
		return jobRepo.findJobByEndDateBetween(start, end);
	}
}
