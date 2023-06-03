package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.aike.domain.Order;
import kr.co.aike.domain.Products;
import kr.co.aike.domain.Users;

public class OrderRowMapper implements RowMapper<Order>{
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		
		order.setOrderNo(rs.getLong("order_no"));
		order.setPrdNo(rs.getLong("prd_no"));
		order.setQuantity(rs.getInt("quantity"));
		order.setShipInfoNo(rs.getLong("ship_info_no"));
		order.setPayTime(rs.getTimestamp("pay_time").toLocalDateTime());
		order.setPaymentMethod(rs.getString("payment_method"));
		order.setStatus(rs.getString("status"));

		return order;
	}

}
