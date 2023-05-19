package kr.co.aike.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.aike.domain.PrdImg;
import kr.co.aike.domain.Users;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PrdImgDao {
	private final JdbcTemplate jdbcTemplate;
	private StringBuilder sql=null;
	
	// 등록 처리
	public int insertProductImage(PrdImg prdImg) throws Exception {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO prd_img(prd_no, file_name, location) ");
			sql.append(" VALUES(?, ?, ?) ");
			cnt=jdbcTemplate.update(sql.toString(), prdImg.getPrdNo(), prdImg.getFileName(), prdImg.getLocation());
		}catch (Exception e) {
			System.out.println("상품 이미지 등록 실패:" + e);
		}//end
		return cnt;
	}//insertProductImage() end
	
}
