package com.ticketmanaging.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ticketmanaging.dto.TicketDataDto;

public class TicketRowMappers implements RowMapper<TicketDataDto>{
	@Override
	public TicketDataDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketDataDto ticket = new TicketDataDto();
		ticket.setTicketNumber(rs.getString("ticket_number"));
		ticket.setEmployeeId(rs.getString("employee_id"));
		ticket.setPhoneNumber(rs.getLong("phone_number"));
		ticket.setLabtopSerialNumber(rs.getString("laptop_serial_number"));
		ticket.setIssueDescription(rs.getString("issue_description"));
		return ticket;
	}	
}
