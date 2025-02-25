package com.ticketmanaging.daoimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ticketmanaging.dao.EmployeeDao;
import com.ticketmanaging.dto.EmployeeDataDto;
import com.ticketmanaging.dto.LoginDto;
import com.ticketmanaging.repository.EmployeeRepository;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
    
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Override
	public boolean RegisterEmployee(EmployeeDataDto employee) {
		int result = employeeRepo.RegisterEmployee(employee);
		if(result > 0){
		   return true;
		}
		else {
		   return false;	
		}
	}

	@Override
	public List<Object> fetchAllEmployees() {
	    return employeeRepo.fetchAllEmployees(); 
	}    
		
}
