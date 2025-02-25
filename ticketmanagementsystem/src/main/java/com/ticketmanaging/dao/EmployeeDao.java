package com.ticketmanaging.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticketmanaging.dto.EmployeeDataDto;
import com.ticketmanaging.dto.LoginDto;

@Component
public interface EmployeeDao {
 
	boolean RegisterEmployee(EmployeeDataDto employee);
	List<Object> fetchAllEmployees();
}
