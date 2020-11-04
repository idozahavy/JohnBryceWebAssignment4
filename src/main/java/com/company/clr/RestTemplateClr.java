package com.company.clr;

import java.sql.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.company.beans.Employee;
import com.company.beans.EmployeeList;
import com.company.beans.Job;
import com.company.beauty.TablePrinter;

@Component
@Order(2)
public class RestTemplateClr implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		final String url = "http://localhost:8080/company/";
		RestTemplate tmp = new RestTemplate();

		System.out.println();
		System.out.println(padStars(" Making New Employee ", 80));
		Employee newEmp = new Employee();
		newEmp.setName("newName");
		newEmp.setSalary(2);
		newEmp.setJobs(List.of(new Job(0, "newDesc", Date.valueOf("2021-12-10"))));
		Long newId = tmp.postForEntity(url + "addEmployee", newEmp, Long.class).getBody();
		System.out.println("Got new id " + newId);

		System.out.println();
		System.out.println(padStars(" Getting Employee By Id " + newId + " ", 80));
		newEmp = tmp.getForEntity(url + "getEmployee/" + newId, Employee.class).getBody();
		TablePrinter.print(newEmp);

		System.out.println();
		System.out.println(padStars(" Getting Employees ", 80));
		EmployeeList ls = tmp.getForEntity(url + "getEmployees", EmployeeList.class).getBody();
		TablePrinter.print(ls.getEmployees());

		System.out.println();
		System.out.println(padStars(" Getting Employees By Name 'do' ", 80));
		ls = tmp.getForEntity(url + "getEmployeesByName?name=do", EmployeeList.class).getBody();
		TablePrinter.print(ls.getEmployees());

	}

	private static String padStars(String text, int length) {
		int starsCount = length - text.length();
		if (starsCount <= 0) {
			return text;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("*".repeat(starsCount / 2));
		buffer.append(text);
		buffer.append("*".repeat(starsCount / 2));
		if (starsCount % 2 == 1) {
			buffer.append('*');
		}
		return buffer.toString();
	}

}
