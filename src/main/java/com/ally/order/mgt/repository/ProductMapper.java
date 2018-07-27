package com.ally.order.mgt.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ally.order.mgt.domain.Product;

public class ProductMapper implements RowMapper<Product> {
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {  
		Product product = new Product(rs.getString("sku"), rs.getString("price"), rs.getString("quantity"));  
		return product;  
	}  
}
