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
			sql.append(" INSERT INTO users(id, name, email, phone, pw, zipcode, address1, address2, roles, join_date) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, now()) ");
			cnt=jdbcTemplate.update(sql.toString(), users.getUserId(), users.getUserName(), users.getUserEmail(), users.getPhone(), users.getUserPw(), users.getZipcode(), users.getAddress1(), users.getAddress2(), "member");
		}catch (Exception e) {
			System.out.println("회원 등록 실패:" + e);
		}//end
		return cnt;
	}//insertUsers() end
	
	// 상세 조회
	public Users selectUser(Users user) throws Exception {
		Users results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT id, name, email, phone, pw, zipcode, address1, address2, roles, join_date ");
			sql.append(" FROM users ");
			sql.append(" where name='"+user.getUserName()+"' and email='"+user.getUserEmail()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new UsersRowMapper());
		}catch (Exception e) {
			System.out.println("회원 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectUser() end
	
	// 상세 조회 - 아이디 찾기
	public Users selectUserForId(Users user) throws Exception {
		Users results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT id, name, email, phone, pw, zipcode, address1, address2, roles, join_date ");
			sql.append(" FROM users ");
			sql.append(" where name='"+user.getUserName()+"' and email='"+user.getUserEmail()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new UsersRowMapper());
		}catch (Exception e) {
			System.out.println("회원 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectUser() end
	
	// 상세 조회 - 비번 찾기
	public Users selectUserForPw(Users user) throws Exception {
		Users results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT id, name, email, phone, pw, zipcode, address1, address2, roles, join_date ");
			sql.append(" FROM users ");
			sql.append(" where id='"+user.getUserId()+"' and email='"+user.getUserEmail()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new UsersRowMapper());
		}catch (Exception e) {
			System.out.println("회원 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectUser() end
	
	//비밀번호 변경
	public int updateUserPw(Users findUsers, StringBuilder tempPw) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" UPDATE users ");
			sql.append(" SET pw = '"+tempPw+"' ");
			sql.append(" WHERE id = '"+findUsers.getUserId()+"' and email = '"+findUsers.getUserEmail()+"' ");
			cnt=jdbcTemplate.update(sql.toString());
		}catch (Exception e) {
			System.out.println("회원 비밀번호를 임시 비밀번호로 바꾸기 실패:" + e);
		}//end
		return cnt;		
	}//updateUserPw() end
	
	// 로그인
	public Users loginUser(Users users) throws Exception {
		Users results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT user_no, id, name, email, phone, pw, zipcode, address1, address2, roles, join_date ");
			sql.append(" FROM users ");
			sql.append(" where id='"+users.getUserId()+"' and pw='"+users.getUserPw()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new UsersRowMapper());
		}catch (Exception e) {
			System.out.println("회원 로그인 읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//loginUser() end
	
	// 탈퇴
	public int unregisterUser(Users users) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" DELETE FROM users  ");
			sql.append(" WHERE user_no = "+users.getUserNo()+" ");
			cnt=jdbcTemplate.update(sql.toString());
		}catch (Exception e) {
			System.out.println("회원 정보 삭제 실패:" + e);
		}//end
		return cnt;		
	}//updateUserPw() end
	
	//회원정보 수정
	public int updateUser(Users users) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" UPDATE users ");
			sql.append(" SET name = '"+users.getUserName()+"', email='"+users.getUserEmail()+"', phone='"+users.getPhone()+"', pw='"+users.getUserPw()+"', ");
			sql.append(" zipcode = '"+users.getZipcode()+"', address1='"+users.getAddress1()+"', address2='"+users.getAddress2()+"' ");
			sql.append(" WHERE user_no = "+users.getUserNo()+" ");
			cnt=jdbcTemplate.update(sql.toString());
		}catch (Exception e) {
			System.out.println("회원 정보 수정 실패:" + e);
		}//end
		return cnt;		
	}//updateUser() end
	
}
