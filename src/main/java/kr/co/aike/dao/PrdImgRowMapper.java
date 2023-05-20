package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.aike.domain.PrdImg;

public class PrdImgRowMapper implements RowMapper<PrdImg>{
	@Override
	public PrdImg mapRow(ResultSet rs, int rowNum) throws SQLException {
		PrdImg prdImg = new PrdImg();
		
		prdImg.setPrdImgNo(rs.getLong("prd_img_no"));
		prdImg.setPrdNo(rs.getLong("prd_no"));
		prdImg.setFileName(rs.getString("file_name"));
		prdImg.setLocation(rs.getString("location"));

		return prdImg;
	}
}
