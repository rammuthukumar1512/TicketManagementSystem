package com.ticketmanaging.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmanaging.dto.CheckAuthorityDto;
import com.ticketmanaging.dto.EmployeeDataDto;
import com.ticketmanaging.exception.EmployeeFetchException;
import com.ticketmanaging.exception.EmployeeNotRegisterException;
import com.ticketmanaging.rowmappers.CheckAuthorityRowMapper;
import com.ticketmanaging.rowmappers.EmployeeLoginRowMapper;
import com.ticketmanaging.rowmappers.EmployeeRowMapper;
import com.ticketmanaging.service.BasicUserData;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@Component
public class EmployeeRepository {
	@Autowired
    JdbcTemplate template;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
		
	public void initializeDatabase() {
		String sql = "CREATE TABLE IF NOT EXISTS employee_data (id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "employee_id VARCHAR(55),employee_name VARCHAR(255),phoneNumber BIGINT,location VARCHAR(255),"
				+ "role int DEFAULT 3,password VARCHAR(255))";
		template.execute(sql);
	}
    
	@Transactional
    public int RegisterEmployee(EmployeeDataDto employee) {
    	int rows = 0;
    	try {
        String callProcedureSQL = "CALL insertEmployeeAndAuthorities(?, ?, ?, ?, ?, ?)";
//    	String query = "INSERT INTO employee_data (employee_id,employee_name,phoneNumber,location,password) VALUES(?,?,?,?,?)";
        rows = template.update(callProcedureSQL, employee.getEmployeeId(), employee.getEmployeeName(), employee.getPhoneNumber(), employee.getLocation(), passwordEncoder.encode(employee.getPassword()), 3);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		throw new EmployeeNotRegisterException("Employee register failed, Please check employeeId.");
    	}
        if(rows > 0) {
        	return 1;
        }else {
        	return -1;
        }
    }
    
	public List<Object> fetchAllEmployees(){
    	System.out.println("repo reached");
    	List<Object> employees;
    	try {
    	String query = "SELECT employee_id,employee_name,phoneNumber,location FROM employee_data";
    	employees = template.query(query,employeeRowMapper);
    	}
    	catch(Exception e) {
    		throw new EmployeeFetchException(e.getMessage());
    	}
    	
    	 if (employees == null || employees.isEmpty()) {
             throw new EmployeeFetchException("Employee fetch failed: No employees found.");
         }
    	
    	else {
    		return employees;
    	}
    }   
	
	public BasicUserData getEmployeeRole(String userName) {
		   BasicUserData employeeData = null;
		   MapSqlParameterSource params = new MapSqlParameterSource();
		   
		try {
		    String loginQuery = "SELECT employee_name,role FROM employee_data WHERE employee_id =:employee_id";	
		   
		    params.addValue("employee_id",userName);
		    employeeData = (BasicUserData) namedParameterJdbcTemplate.queryForObject(loginQuery,params,new EmployeeLoginRowMapper());
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
			throw new EmployeeFetchException("Employee fetch failed");
	     	}
	     	if (employeeData == null) {
            throw new EmployeeFetchException("login Failed.");
            }
   	
   	        else {
   		    return employeeData;
   	        }
	}

	public List<CheckAuthorityDto> CheckAuthority(String userName) {
		     List<CheckAuthorityDto> authorities;
		     MapSqlParameterSource params = new MapSqlParameterSource();
		     System.out.println("here"+userName);
		     try {
		    	 String checkAuthorityQuery = "SELECT em.employee_name,au.role_id,r.authority FROM employee_data AS em LEFT JOIN authorities AS au ON em.employee_id = au.employee_id"
		    	 		+ "	LEFT JOIN roles AS r ON r.role_id = au.role_id WHERE au.employee_id = :employee_id";
		    	 params.addValue("employee_id",userName);
		         authorities= namedParameterJdbcTemplate.query(checkAuthorityQuery, params, new CheckAuthorityRowMapper());
		    	 
		     }
		     catch(Exception e){
		    	 e.printStackTrace();
		    	 throw new EmployeeFetchException(e.getMessage());
		     }
		     return authorities;
	}
}
