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
				users.setUserId(rs.getString("id"));
				users.setUserName(rs.getString("name"));
				users.setUserEmail(rs.getString("email"));
				users.setUserPw(rs.getString("pw"));
				users.setZipcode(rs.getString("zipcode"));
				users.setAddress1(rs.getString("address1"));
				users.setAddress2(rs.getString("address2"));
				users.setRoles(rs.getString("roles"));
				
				users.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());
			}
		}
		
		return users;
	}

}
