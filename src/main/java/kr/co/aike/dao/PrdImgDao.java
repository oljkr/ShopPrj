package kr.co.aike.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.aike.domain.PrdImg;
import kr.co.aike.domain.Products;
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
	
	// 상세 조회 - 섬네일
	public List<PrdImg> selectImagesUpper(Products products) throws Exception {
		List<PrdImg> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT prd_img_no, prd_no, file_name, location ");
			sql.append(" FROM prd_img ");
			sql.append(" where prd_no='"+products.getPrdNo()+"' and location='upper' ");
			
			results = jdbcTemplate.query(sql.toString(), new PrdImgRowMapper());
		}catch (Exception e) {
			System.out.println("상품 이미지 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectImagesUpper() end
	
	// 상세 조회 - 본문
	public List<PrdImg> selectImagesLower(Products products) throws Exception {
		List<PrdImg> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT prd_img_no, prd_no, file_name, location ");
			sql.append(" FROM prd_img ");
			sql.append(" where prd_no='"+products.getPrdNo()+"' and location='lower' ");
			
			results = jdbcTemplate.query(sql.toString(), new PrdImgRowMapper());
		}catch (Exception e) {
			System.out.println("상품 이미지 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectImagesLower() end
	
	//이미지 번호로 이미지 파일 이름 조회
	public String getImgFileName(String prdImgNo) {
		String filename=null;
		try {
			sql=new StringBuilder();
			sql.append(" select file_name from prd_img ");
			sql.append(" where prd_img_no="+prdImgNo+" ");
			filename=jdbcTemplate.queryForObject(sql.toString(), String.class);		
		}catch (Exception e) {
			System.out.println("이미지 파일 이름 조회 실패:" + e);
		}//end
		return filename;
	}//getImgFileName() end
	
	//제품 번호 변경
	public int updatePrdNo(String prdImgNo, Long prdNo) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" UPDATE prd_img ");
			sql.append(" SET prd_no = '"+prdNo+"' ");
			sql.append(" WHERE prd_img_no = '"+prdImgNo+"' ");
			cnt=jdbcTemplate.update(sql.toString());
		}catch (Exception e) {
			System.out.println("이미지 정보의 제품 번호 바꾸기 실패:" + e);
		}//end
		return cnt;		
	}//updatePrdNo() end
	
	//이미지번호로 기존 파일 정보 삭제
	public int deleteImg(String prdImgNo) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" DELETE FROM prd_img ");
			sql.append(" WHERE prd_img_no = '"+prdImgNo+"' ");
			cnt=jdbcTemplate.update(sql.toString());
		}catch (Exception e) {
			System.out.println("이미지 정보 삭제 실패:" + e);
		}//end
		return cnt;		
	}//deleteImg() end
	
	// 상품 번호로 대표 이미지 조회(upper 중 이미지 번호 최솟값 선택)
	public PrdImg selectListImgPrdNo(Products products) throws Exception {
		PrdImg results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT min(prd_img_no) as prd_img_no, prd_no, file_name, location ");
			sql.append(" FROM prd_img ");
			sql.append(" where location = 'upper' and prd_no="+products.getPrdNo()+" group by prd_no order by prd_img_no ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new PrdImgRowMapper());
		}catch (Exception e) {
			System.out.println("상품 대표 이미지 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectListImgPrdNo() end
	
	// 상품 번호로 같은 상품 번호의 대표 상품의 대표 이미지 조회(upper 중 이미지 번호 최솟값 선택)
	public PrdImg selectImgFirstPrdNo(Products products) throws Exception {
		PrdImg results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT min(prd_img_no) as prd_img_no, prd_no, file_name, location ");
			sql.append(" FROM prd_img ");
			sql.append(" where location = 'upper' and ");
			sql.append(" prd_no = ( select prd_no ");
			sql.append(" 		   from products ");
			sql.append(" 		   where name = (select name from products where prd_no="+products.getPrdNo()+") ");
			sql.append(" 					     order by prd_no ");
			sql.append(" 					     LIMIT 1) ");
			sql.append(" group by prd_no order by prd_img_no ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new PrdImgRowMapper());
		}catch (Exception e) {
			System.out.println("대표 상품 대표 이미지 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectListImgPrdNo() end
	
}
