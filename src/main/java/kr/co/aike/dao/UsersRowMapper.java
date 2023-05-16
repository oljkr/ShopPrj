package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.aike.domain.Users;

public class UsersRowMapper implements RowMapper<Users>{
	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users users = new Users();
		
		users.setUserNo(rs.getLong("user_no"));
		users.setUserId(rs.getString("id"));
		users.setUserName(rs.getString("name"));
		users.setUserEmail(rs.getString("email"));
		users.setUserPw(rs.getString("pw"));
		users.setZipcode(rs.getString("zipcode"));
		users.setAddress1(rs.getString("address1"));
		users.setAddress2(rs.getString("address2"));
		users.setRoles(rs.getString("roles"));
		users.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());

		return users;
	}
}
