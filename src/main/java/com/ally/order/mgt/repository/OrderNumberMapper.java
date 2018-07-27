package com.ally.order.mgt.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderNumberMapper implements RowMapper<String> {
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {  
		return rs.getString("id");  
	}  
}
