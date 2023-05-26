package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.aike.domain.ShipInfo;

public class ShipInfoRowMapper implements RowMapper<ShipInfo>{
	@Override
	public ShipInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		ShipInfo shipinfo = new ShipInfo();
		
		shipinfo.setShipInfoNo(rs.getLong("ship_info_no"));
		shipinfo.setZipcode(rs.getString("zipcode"));
		shipinfo.setAddress1(rs.getString("address1"));
		shipinfo.setAddress2(rs.getString("address2"));
		shipinfo.setName(rs.getString("name"));
		shipinfo.setPhone(rs.getString("phone"));

		return shipinfo;
	}

}
