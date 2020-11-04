package com.company.clr;

import java.sql.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.company.beans.Employee;
import com.company.beans.Job;
import com.company.service.CompanyService;

import lombok.AllArgsConstructor;

@Component
@Order(1)
@AllArgsConstructor
public class DatabaseInitClr implements CommandLineRunner {

	private CompanyService companyService;

	@Override
	public void run(String... args) throws Exception {

		Employee emp1 = new Employee(0, "dodo", 50000, List.of(new Job(0, "didi", Date.valueOf("2021-12-04"))));
		Employee emp2 = new Employee(0, "maniak", 2100, List.of(new Job(0, "stom", Date.valueOf("2021-10-04"))));
		Employee emp3 = new Employee(0, "nigahiga", 35000, List.of(new Job(0, "ninja", Date.valueOf("2021-11-04"))));
		Employee emp4 = new Employee(0, "shamen", 42000, List.of(new Job(0, "king", Date.valueOf("2030-05-28"))));
		companyService.addEmployee(emp1);
		companyService.addEmployee(emp2);
		companyService.addEmployee(emp3);
		companyService.addEmployee(emp4);

	}

}
