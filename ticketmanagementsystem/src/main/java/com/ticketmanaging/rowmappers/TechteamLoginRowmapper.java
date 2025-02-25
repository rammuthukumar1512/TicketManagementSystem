package com.ticketmanaging.rowmappers;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.ticketmanaging.dto.TechMemberDto;
import com.ticketmanaging.service.BasicTechMemberData;

@Component
public class TechteamLoginRowmapper implements RowMapper{

	@Override
	public BasicTechMemberData mapRow(ResultSet rs, int rowNum) throws SQLException {
		BasicTechMemberData techDto = new BasicTechMemberData();
		techDto.setMember_name(rs.getString("member_name"));
		techDto.setRole(rs.getString("role"));
		return techDto;
	}
     
}
