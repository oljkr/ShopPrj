package kr.co.aike.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.aike.domain.Products;
import kr.co.aike.domain.Users;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductsDao {
	private final JdbcTemplate jdbcTemplate;
	private StringBuilder sql=null;
	
	// 등록 처리
	public int insertProducts(Products products) throws Exception {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO products(sort1, sort2, name, color, size, short_des, full_des, stock, price, read_cnt) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, 0) ");
			cnt=jdbcTemplate.update(sql.toString(), products.getSort1(), products.getSort2(), products.getName(), products.getColor(), products.getSize(), products.getShortDes(), products.getFullDes(), products.getStock(), products.getPrice());
		}catch (Exception e) {
			System.out.println("상품 등록 실패:" + e);
		}//end
		return cnt;
	}//insertProducts() end
	
	// 상세 조회
	public Products selectProduct(Products products) throws Exception {
		Products results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT prd_no, sort1, sort2, name, color, size, short_des, full_des, stock, price, read_cnt ");
			sql.append(" FROM products ");
			sql.append(" where name='"+products.getName()+"' and color='"+products.getColor()+"' and size='"+products.getSize()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new ProductsRowMapper());
		}catch (Exception e) {
			System.out.println("상품 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectProduct() end
}
