package kr.co.aike.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.aike.domain.Users;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UsersDao {
	private final JdbcTemplate jdbcTemplate;
	private StringBuilder sql=null;
	
	// 등록 처리
	public int insertUsers(Users users) throws Exception {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO users(email, name, pw, address, roles, join_date) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, now()) ");
			cnt=jdbcTemplate.update(sql.toString(), users.getUserEmail(), users.getUserName(), users.getUserPw(), users.getAddress(), "member");
		}catch (Exception e) {
			System.out.println("회원등록실패:" + e);
		}//end
		return cnt;
	}//insertUsers() end
	
	// 상세 조회
	public Users selectUser(int userNo) throws Exception {
		Users results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT id, email, name, pw, address, roles, join_date ");
			sql.append(" FROM users ");
			sql.append(" where id='"+userNo+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new UsersRowMapper());
		}catch (Exception e) {
			System.out.println("회원 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}
	
	// 로그인 조회
	public Users loginUser(Users users) throws Exception {
		Users results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT id, email, name, pw, address, roles, join_date ");
			sql.append(" FROM users ");
			sql.append(" where email='"+users.getUserEmail()+"' and pw='"+users.getUserPw()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new UsersRowMapper());
		}catch (Exception e) {
			System.out.println("회원 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}
	
}
