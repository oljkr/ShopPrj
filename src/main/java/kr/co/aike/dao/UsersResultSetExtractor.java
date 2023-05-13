package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import kr.co.aike.domain.Users;

public class UsersResultSetExtractor implements ResultSetExtractor<Users>{

	@Override
	public Users extractData(ResultSet rs) throws SQLException, DataAccessException {
		Users users = null;
		
		while(rs.next()) {
			if(users == null) {
				users = new Users();
				users.setUserNo(rs.getLong("id"));
				users.setUserEmail(rs.getString("email"));
				users.setUserName(rs.getString("name"));
				users.setUserPw(rs.getString("pw"));
				users.setAddress(rs.getString("address"));
				users.setRoles(rs.getString("roles"));
				
				users.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());
			}
		}
		
		return users;
	}

}
