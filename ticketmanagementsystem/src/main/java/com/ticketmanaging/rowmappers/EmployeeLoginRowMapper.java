package com.ticketmanaging.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.ticketmanaging.service.BasicUserData;

public class EmployeeLoginRowMapper implements RowMapper{

	@Override
	public BasicUserData mapRow(ResultSet rs, int rowNum) throws SQLException {
		BasicUserData loginData = new BasicUserData();
		loginData.setUser_name(rs.getString("employee_name"));
		loginData.setRole(rs.getString("role"));
		return loginData;
	}	
}

