package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.aike.domain.Users;

public class UsersRowMapper implements RowMapper<Users>{
	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users schedule = new Users();
		
		schedule.setUserNo(rs.getLong("id"));
		schedule.setUserEmail(rs.getString("email"));
		schedule.setUserName(rs.getString("name"));
		schedule.setUserPw(rs.getString("pw"));
		schedule.setAddress(rs.getString("address"));
		schedule.setRoles(rs.getString("roles"));
		schedule.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());

		return schedule;
	}
}
