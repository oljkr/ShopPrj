package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.aike.domain.Products;
import kr.co.aike.domain.Users;

public class ProductsRowMapper implements RowMapper<Products>{
	@Override
	public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
		Products products = new Products();
		
		products.setPrdNo(rs.getLong("prd_no"));
		products.setSort1(rs.getString("sort1"));
		products.setSort2(rs.getString("sort2"));
		products.setName(rs.getString("name"));
		products.setColor(rs.getString("color"));
		products.setSize(rs.getString("size"));
		products.setShortDes(rs.getString("short_des"));
		products.setFullDes(rs.getString("full_des"));
		products.setStock(rs.getInt("stock"));
		products.setPrice(rs.getInt("price"));
		products.setOrderCnt(rs.getInt("order_cnt"));

		return products;
	}
}
