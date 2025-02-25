package com.ticketmanaging.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.ticketmanaging.dto.EmployeeDataDto;
import com.ticketmanaging.dto.TicketDataDto;

public class EmployeeRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeDataDto employeeDto = new EmployeeDataDto();
		employeeDto.setEmployeeId(rs.getString("employee_id"));
		employeeDto.setEmployeeName(rs.getString("employee_name"));
		employeeDto.setPhoneNumber(rs.getLong("phoneNumber"));
		employeeDto.setLocation(rs.getString("location"));
		return employeeDto;
	}	
}
