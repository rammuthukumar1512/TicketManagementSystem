package com.ticketmanaging.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.ticketmanaging.dto.CheckAuthorityDto;

@Component
public class CheckAuthorityRowMapper implements RowMapper{
	@Override
	public CheckAuthorityDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		CheckAuthorityDto authorityData = new CheckAuthorityDto();
		authorityData.setEmployeeName(rs.getString("employee_name"));
		authorityData.setAuthority(rs.getString("authority"));
		authorityData.setRole(rs.getInt("role_id"));
		return authorityData;
	}
}
