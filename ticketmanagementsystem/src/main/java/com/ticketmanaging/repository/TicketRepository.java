package com.ticketmanaging.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmanaging.dto.AddAuthorityDto;
import com.ticketmanaging.dto.RemoveAuthorityDto;
import com.ticketmanaging.dto.TicketDataDto;
import com.ticketmanaging.dto.TicketUpdateDto;
import com.ticketmanaging.exception.FetchTicketException;
import com.ticketmanaging.exception.TicketControllerException;
import com.ticketmanaging.rowmappers.TicketRowMappers;

import jakarta.validation.Valid;

@Component
public class TicketRepository {
	   @Autowired
       JdbcTemplate template;
	   @Autowired
       NamedParameterJdbcTemplate namedTemplate;
	   LocalDateTime datetime = LocalDateTime.now();
       
	   public void initializeDatabase(){
			String sql = "CREATE TABLE IF NOT EXISTS ticket_raising_data (id INT AUTO_INCREMENT PRIMARY KEY,"
					+ "ticket_number VARCHAR(55),employee_id VARCHAR(255),phone_number BIGINT,laptop_serial_number VARCHAR(255),created_at DATETIME,"
					+ "issue_description VARCHAR(30),status VARCHAR(30) DEFAULT 'open')";
			template.execute(sql);
		}
	    
	   @Transactional
	    public int RaiseTicket(TicketDataDto ticket) {
	    	int rows = 0;
	    	try {
	    	String query = "INSERT INTO ticket_raising_data (ticket_number,employee_id,phone_number,laptop_serial_number,created_at,issue_description) VALUES(?,?,?,?,?,?)";
	        rows = template.update(query, ticket.getTicketNumber(),ticket.getEmployeeId(),ticket.getPhoneNumber(),ticket.getLabtopSerialNumber(),datetime,ticket.getIssueDescription());
	    	}
	    	catch(Exception e) {
	    		 throw new TicketControllerException("Ticket raising failed, Check ticket number");
	    	}
	        if(rows > 0) {
	        	return 1;
	        }
	        else {
	        	return -1;
	        }
	    }
	    
	   @Transactional
	    public int UpdateTicket(TicketUpdateDto updateData) {
	   
	    	MapSqlParameterSource params = new MapSqlParameterSource();
	    	String updateQuery = "UPDATE ticket_raising_data SET issue_description = :issueDescription WHERE ticket_number = :ticket_number";
	    	params.addValue("ticket_number", updateData.getTicketNumber());
	    	params.addValue("issueDescription", updateData.getIssueDescription());
	    	int rows = namedTemplate.update(updateQuery,params);
	        if(rows > 0) {
	        	return 1;
	        }
	        else {
	        	return -1;
	        }
	    }

		public int AddAuthority(AddAuthorityDto authorityData) {
			int rows = 0;
			String employeeQuery = "SELECT CASE WHEN EXISTS(SELECT 1 FROM authorities WHERE employee_id=:employee_id AND role_id=:role_id)THEN 0 ELSE("
					             + "UPADTE authorities SET role_id=:role_id WHERE employee_id=:employee_id)";
			String memberQuery = "SELECT CASE WHEN EXISTS(SELECT 1 FROM authorities WHERE tech_member_id=:member_id_id AND role_id=:role_id)THEN 0 ELSE("
							     + "UPADTE authorities SET role_id=:role_id WHERE tech_member_id=:member_id)";
			MapSqlParameterSource params = new MapSqlParameterSource();
			
			if(authorityData.getUserId().startsWith("tech")) {
				params.addValue("member_id", authorityData.getUserId());
				params.addValue("role_id", authorityData.getRole_id());
				rows = namedTemplate.update(memberQuery, params);
			}
			else {
				params.addValue("employee_id", authorityData.getUserId());
				params.addValue("role_id", authorityData.getRole_id());
				rows = namedTemplate.update(employeeQuery, params);
			}
			
	        if(rows > 0) {
	        	return 1;
	        }
	        else {
	        	return -1;
	        }
		}

		public int RemoveAuthority(@Valid RemoveAuthorityDto removeAuthorityData) {
			int rows = 0;
			String employeeQuery = "UPDATE authorities SET available = 0 WHERE employee_id = :employee_id AND authority = :authority AND role_id = :role_id";
			String memberQuery = "UPADTE authorities SET available = 0 WHERE tech_member_id = :member_id AND authority = :authority AND role_id = :role_id";
			MapSqlParameterSource params = new MapSqlParameterSource();
			if(removeAuthorityData.getUserId().startsWith("tech")) {
				params.addValue("member_id", removeAuthorityData.getUserId());
				params.addValue("authority", removeAuthorityData.getRemoveAuthority());
				params.addValue("role_id", removeAuthorityData.getRoleId());
				rows = namedTemplate.update(memberQuery, params);
			}
			else {
				params.addValue("employee_id", removeAuthorityData.getUserId());
				params.addValue("authority", removeAuthorityData.getRemoveAuthority());
				params.addValue("role_id", removeAuthorityData.getRoleId());
				rows = namedTemplate.update(employeeQuery, params);
			}

	        if(rows > 0) {
	        	return 1;
	        }
	        else {
	        	return -1;
	        }
		}

		public List<TicketDataDto> FetchTickets() {
			TicketRowMappers ticketMapper = new TicketRowMappers();
			String query = "SELECT ticket_number,employee_id,phone_number,laptop_serial_number,issue_description FROM ticket_raising_data";
			MapSqlParameterSource params = new MapSqlParameterSource();
			try {
				List<TicketDataDto> tickets = namedTemplate.query(query,ticketMapper);
				return tickets;
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new FetchTicketException("Fetching ticket failed");
			}
		}
             
}
