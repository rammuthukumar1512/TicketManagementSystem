package com.ticketmanaging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketmanaging.configuration.PermissionConfig;
import com.ticketmanaging.dto.EmployeeDataDto;
import com.ticketmanaging.dto.LoginDto;
import com.ticketmanaging.repository.EmployeeRepository;
import com.ticketmanaging.responsedto.SuccessStatusDto;
import com.ticketmanaging.responsedto.TokenDto;
import com.ticketmanaging.service.EmployeeService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value="employee")
public class EmployeeManagementController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	PermissionConfig employeePermission;
	
	@PostConstruct
	public void init() {
		try {
            employeeService.createTable();
            System.out.println("Table created successfully!");
        } catch (Exception e) {
            System.out.println("Error during table creation: " + e.getMessage());
        }
	}
	
	@PostMapping("register")
	public SuccessStatusDto RegisterEmployee(@Valid @RequestBody EmployeeDataDto employee) {
	   return employeeService.RegisterEmployee(employee);      	  
	}
	
	@GetMapping("viewEmployees")
	@PreAuthorize("@employeePermission.isAuthorize()")
	public List<Object> viewEmployees() {
	   return employeeService.ViewEmployee();
	}
	
	@GetMapping("login")
	public TokenDto EmployeeLogin(@RequestBody LoginDto credentials) {
	   return employeeService.EmployeeLogin(credentials);
	}
	
}
