package com.ticketmanaging.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ticketmanaging.dto.EmployeeDataDto;
import com.ticketmanaging.dto.TechMemberDto;

public class TechTeamRowMapper implements RowMapper<TechMemberDto>{
	@Override
	public TechMemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		TechMemberDto techMemberDto = new TechMemberDto();
		techMemberDto.setMemberId(rs.getString("member_id"));
		techMemberDto.setMemberName(rs.getString("member_name"));
		techMemberDto.setPhoneNumber(rs.getLong("phone_number"));
		return techMemberDto;
	}	
}
