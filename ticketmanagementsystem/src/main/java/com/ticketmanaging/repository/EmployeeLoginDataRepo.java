package com.ticketmanaging.repository;

import com.ticketmanaging.service.BasicLoginData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeLoginDataRepo {
	
	@Autowired
	NamedParameterJdbcTemplate namedTemplate;

	public BasicLoginData findByUsername(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
	    String findUserQuery = "SELECT employee_id,password,role From employee_data WHERE employee_id = :username";
	    params.addValue("username", username);
	    
	    ResultSetExtractor<BasicLoginData> basicData = rs->{
	    	if(rs.next()) {
	    	 return new BasicLoginData(rs.getString("employee_id"),rs.getString("password"),rs.getString("role"));
	    	}
	    	return null;
	    };
	    return namedTemplate.query(findUserQuery, params,basicData);
	}
    
}
