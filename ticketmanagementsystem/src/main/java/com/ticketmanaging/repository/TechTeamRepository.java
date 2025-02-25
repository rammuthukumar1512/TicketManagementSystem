package com.ticketmanaging.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ticketmanaging.dto.CheckAuthorityDto;
import com.ticketmanaging.dto.CheckTechAuthorityDto;
import com.ticketmanaging.dto.TechMemberDto;
import com.ticketmanaging.dto.TicketStatusUpdate;
import com.ticketmanaging.exception.EmployeeFetchException;
import com.ticketmanaging.exception.TechTeamControllException;
import com.ticketmanaging.exception.TicketControllerException;
import com.ticketmanaging.rowmappers.TechteamLoginRowmapper;
import com.ticketmanaging.rowmappers.CheckTechmemberAuthority;
import com.ticketmanaging.rowmappers.TechTeamRowMapper;
import com.ticketmanaging.service.BasicLoginData;
import com.ticketmanaging.service.BasicTechMemberData;
import com.ticketmanaging.service.BasicUserData;

@Component
public class TechTeamRepository {
	@Autowired
    JdbcTemplate template;
	@Autowired
    NamedParameterJdbcTemplate namedTemplate;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);	
	public void initializeDatabase() {
		String sql = "CREATE TABLE IF NOT EXISTS tech_team_member (id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "member_id VARCHAR(55),member_name VARCHAR(255),phone_number BIGINT,role INT DEFAULT 3,password VARCHAR(255))";
		template.execute(sql);
	}
    
	@Transactional
    public int RegisterTechMember(TechMemberDto techMember) {
    	int rows = 0;
    	try {
    	String callProcedureSQL = "CALL insertTechmemberAndAuthorities(?, ?, ?, ?, ?, ?)";	
//    	String query = "INSERT INTO tech_team_member (member_id,member_name,phone_number,password) VALUES(?,?,?)";
        rows = template.update(callProcedureSQL, techMember.getMemberId(),techMember.getMemberName(),techMember.getPhoneNumber(),passwordEncoder.encode(techMember.getPassword()),"auth.update",3);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		throw new TechTeamControllException("Techmember register failed, please check memberId");
    	}
        if(rows > 0) {
        	return 1;
        }else {
        	return -1;
        }
    }
    
    public List<?> fetchAllTechMembers(){
    	TechTeamRowMapper techMapper = new TechTeamRowMapper();
    	List<?> techMember;
    	try {
    	String query = "SELECT member_id,member_name,phone_number FROM tech_team_member";
    	techMember = template.query(query,techMapper);
    	}
    	catch(Exception e) {
    		throw new EmployeeFetchException(e.getMessage());
    	}
    	if(techMember.size() < 1) {
    		throw new EmployeeFetchException("Employee fetch failed");
    	}
    	else {
    		return techMember;
    	}
    }   
    
    @Transactional
    public boolean UpdateTicketStatus(TicketStatusUpdate updateData) {
    	int rows = 0;
        try {
        	String query = "UPDATE ticket_raising_data SET status = :status WHERE ticket_number = :ticket_number";
        	MapSqlParameterSource params = new MapSqlParameterSource();
        	params.addValue("ticket_number", updateData.getTicketNumber());
        	params.addValue("status", updateData.getStatus());
        	rows = namedTemplate.update(query, params);
        }
        catch(Exception e) {
        	throw new TicketControllerException("Ticket updation failed");
        }
        if(rows > 0) {
    		return true;
    	}
    	else {
    		return false;
    	}

    }

	public BasicTechMemberData getEmployeeRole(String userName) {
		   BasicTechMemberData techMemberData = null;
		   MapSqlParameterSource params = new MapSqlParameterSource();
		   
		try {
		    String loginQuery = "SELECT member_name,role FROM tech_team_member WHERE member_id =:member_id";	
		   
		    params.addValue("member_id",userName);
		    techMemberData = (BasicTechMemberData) namedTemplate.queryForObject(loginQuery,params,new TechteamLoginRowmapper());
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
			throw new EmployeeFetchException(e.getMessage());
	     	}
	     	if (techMemberData == null) {
            throw new EmployeeFetchException("login Failed.");
            }
	
	        else {
		    return techMemberData;
	        }
	}

	public BasicLoginData findByUsername(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
	    String findUserQuery = "SELECT member_id,password,role From tech_team_member WHERE member_id = :username";
	    params.addValue("username", username);
	    System.out.println("reach repo");
	    ResultSetExtractor<BasicLoginData> basicData = rs->{
	    	if(rs.next()) {
	    	 return new BasicLoginData(rs.getString("member_id"),rs.getString("password"),rs.getString("role"));
	    	}
	    	return null;
	    };
	    return namedTemplate.query(findUserQuery, params,basicData);	
	}

	public List<CheckTechAuthorityDto> CheckAuthority(String userName) {
		List<CheckTechAuthorityDto> authorities;
	     MapSqlParameterSource params = new MapSqlParameterSource();
	     System.out.println("here"+userName);
	     try {
	    	 String checkAuthorityQuery = "SELECT te.member_name,au.role_id,au.authority FROM "+
	                                      "tech_team_member AS te LEFT JOIN authorities AS au ON te.member_id = au.tech_member_id "+
	    			                      "LEFT JOIN roles AS r ON r.id = au.role_id "+
	    			                      "WHERE au.tech_member_id = :member_id";
	    	 params.addValue("member_id",userName);
	         authorities= namedTemplate.query(checkAuthorityQuery, params, new CheckTechmemberAuthority());
	    	 
	     }
	     catch(Exception e){
	    	 e.printStackTrace();
	    	 throw new EmployeeFetchException(e.getMessage());
	     }
	     return authorities;
	}
}    

