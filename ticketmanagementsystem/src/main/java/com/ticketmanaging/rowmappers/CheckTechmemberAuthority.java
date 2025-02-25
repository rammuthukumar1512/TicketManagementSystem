package com.ticketmanaging.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.ticketmanaging.dto.CheckTechAuthorityDto;
@Component
public class CheckTechmemberAuthority implements RowMapper{
	@Override
	public CheckTechAuthorityDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		CheckTechAuthorityDto authorityData = new CheckTechAuthorityDto();
		authorityData.setMemberName(rs.getString("member_name"));
		authorityData.setAuthority(rs.getString("authority"));
		authorityData.setRole(rs.getInt("role_id"));
		return authorityData;
	}
}
