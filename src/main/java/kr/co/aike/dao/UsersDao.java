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
	
	// 아이디 조회
	public int idExist(String id) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM users ");
			sql.append(" where id='"+id+"' ");
			cnt=jdbcTemplate.queryForObject(sql.toString(), Integer.class);		
		}catch (Exception e) {
			System.out.println("아이디 조회 실패:" + e);
		}//end
		return cnt;
	}//idExist() end

	// 등록 처리
	public int insertUsers(Users users) throws Exception {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO users(id, name, email, pw, zipcode, address1, address2, roles, join_date) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, now()) ");
			cnt=jdbcTemplate.update(sql.toString(), users.getUserId(), users.getUserName(), users.getUserEmail(), users.getUserPw(), users.getZipcode(), users.getAddress1(), users.getAddress2(), "member");
		}catch (Exception e) {
			System.out.println("회원 등록 실패:" + e);
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
	
	// 로그인
	public Users loginUser(Users users) throws Exception {
		Users results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT id, name, email, pw, zipcode, address1, address2, roles, join_date ");
			sql.append(" FROM users ");
			sql.append(" where id='"+users.getUserId()+"' and pw='"+users.getUserPw()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new UsersRowMapper());
		}catch (Exception e) {
			System.out.println("회원 로그인 읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}
	
}
