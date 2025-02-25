package com.ticketmanaging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ticketmanaging.daoimplementation.EmployeeDaoImpl;
import com.ticketmanaging.dto.EmployeeDataDto;
import com.ticketmanaging.dto.LoginDto;
import com.ticketmanaging.exception.EmployeeNotRegisterException;
import com.ticketmanaging.repository.EmployeeRepository;
import com.ticketmanaging.responsedto.SuccessStatusDto;
import com.ticketmanaging.responsedto.TokenDto;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDaoImpl employeeDao;
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	JWTService jwtService;
	
	@Autowired
	AuthenticationManager authManager;
	
	public void createTable() {
		employeeRepo.initializeDatabase();
	}
     
	public SuccessStatusDto RegisterEmployee(EmployeeDataDto employee) {
		SuccessStatusDto status = new SuccessStatusDto();
        boolean result = employeeDao.RegisterEmployee(employee);
        if(!result) {
        	throw new EmployeeNotRegisterException("Employee Register Failed");
        }
        status.setStatusCode(1000);
    	status.setStatusMessage("Employee Registered Successfully");
    	return status;
	}
	
	public List<Object> ViewEmployee() {
	    return employeeDao.fetchAllEmployees();
	}

	public TokenDto EmployeeLogin(LoginDto credentials) {
		TokenDto status = new TokenDto();
	    Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUserName(),credentials.getPassword()));
	   
	    if(!authentication.isAuthenticated()) {
	    	throw new EmployeeNotRegisterException("Employee login Failed");
	    }
	    else {

	    	BasicUserData employeeData = employeeRepo.getEmployeeRole(credentials.getUserName()); 
	    	String token = jwtService.generateToken(credentials.getUserName(),employeeData.getRole());
	    	status.setStatusCode(1000);
	    	status.setStatusMessage("Login sucess");
	    	status.setToken(token);
	    	return status;
	    }
	}
}
