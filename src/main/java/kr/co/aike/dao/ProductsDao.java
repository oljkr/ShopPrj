package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
			sql.append(" INSERT INTO products(sort1, sort2, name, color, size, short_des, full_des, stock, price, order_cnt) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, 0) ");
			cnt=jdbcTemplate.update(sql.toString(), products.getSort1(), products.getSort2(), products.getName(), products.getColor(), products.getSize(), products.getShortDes(), products.getFullDes(), products.getStock(), products.getPrice());
		}catch (Exception e) {
			System.out.println("상품 등록 실패:" + e);
		}//end
		return cnt;
	}//insertProducts() end
	
	// 상세 조회 - 상품명, 컬러, 사이즈
	public Products selectProduct(Products products) throws Exception {
		Products results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT prd_no, sort1, sort2, name, color, size, short_des, full_des, stock, price, order_cnt ");
			sql.append(" FROM products ");
			sql.append(" where name='"+products.getName()+"' and color='"+products.getColor()+"' and size='"+products.getSize()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new ProductsRowMapper());
		}catch (Exception e) {
			System.out.println("상품 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectProduct() end
	
	// 상세 조회 - 상품번호로 조회
	public Products selectProductPrdNo(Products products) throws Exception {
		Products results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT prd_no, sort1, sort2, name, color, size, short_des, full_des, stock, price, order_cnt ");
			sql.append(" FROM products ");
			sql.append(" where prd_no='"+products.getPrdNo()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new ProductsRowMapper());
		}catch (Exception e) {
			System.out.println("상품 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectProductPrdNo() end
	
	// 상세 조회 - 같은 이름의 상품번호들 조회
	public List<String> selectPrdNumList(Products products) throws Exception {
		List<String> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT prd_no ");
			sql.append(" FROM products ");
			sql.append(" WHERE name='"+products.getName()+"' ");
			
			RowMapper<String> rowMapper=new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					String proNo=rs.getString("prd_no");
					return proNo;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.query(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("상품 번호 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectPrdNumList() end
	
	// 상세 조회 - 상품번호로 찾은 상품과 같은 이름의 상품번호들 조회
	public List<Long> selectPrdNumListAsNum(Long prdNo) throws Exception {
		List<Long> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT prd_no ");
			sql.append(" FROM products ");
			sql.append(" where name = ( ");
			sql.append(" 				select name from products where prd_no = "+prdNo+" ) ");
			
			RowMapper<Long> rowMapper=new RowMapper<Long>() {
				@Override
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					Long proNo=rs.getLong("prd_no");
					return proNo;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.query(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("상품 번호 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectPrdNumListAsNum() end
	
	// 상세 조회 - 상품의 컬러 옵션 조회
	public List<String> selectProductColor(Products products) throws Exception {
		List<String> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT DISTINCT color ");
			sql.append(" FROM products ");
			sql.append(" WHERE name='"+products.getName()+"' ");
			
			RowMapper<String> rowMapper=new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					String color=rs.getString("color");
					return color;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.query(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("상품 컬러 옵션 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectProductColor() end
	
	// 상세 조회 - 상품의 사이즈 옵션 조회
	public List<String> selectProductSize(Products products) throws Exception {
		List<String> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT DISTINCT size ");
			sql.append(" FROM products ");
			sql.append(" WHERE name='"+products.getName()+"' ");
			
			RowMapper<String> rowMapper=new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					String size=rs.getString("size");
					return size;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.query(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("상품 사이즈 옵션 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectProductSize() end
	
	// 상품번호로 상품 삭제
	public int deleteProduct(Long prdNo) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" DELETE FROM products  ");
			sql.append(" WHERE prd_no = "+prdNo+" ");
			cnt=jdbcTemplate.update(sql.toString());
		}catch (Exception e) {
			System.out.println("상품 삭제 실패:" + e);
		}//end
		return cnt;		
	}//deleteProduct() end
	
	//목록 조회 - 전체 갯수 조회
	public int totalRowCount() {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(*) FROM products ");
			cnt=jdbcTemplate.queryForObject(sql.toString(), Integer.class);		
		}catch (Exception e) {
			System.out.println("전체 상품 갯수 조회 실패:" + e);
		}//end
		return cnt;
	}//totalRowCount() end
	
	//목록 조회 - 상품 분류에 따른 전체 갯수 조회
	public int totalRowCountAsSort(Products products) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(distinct name) FROM products ");
			sql.append(" where sort1='"+products.getSort1()+"' and sort2='"+products.getSort2()+"' ");
			cnt=jdbcTemplate.queryForObject(sql.toString(), Integer.class);		
		}catch (Exception e) {
			System.out.println("카테고리의 전체 상품 갯수 조회 실패:" + e);
		}//end
		return cnt;
	}//totalRowCountAsSort() end
	
	
	//목록 조회 - 전체 상품 페이징 조회
	public List<Products> list(int start, int end) {
		List<Products> list=null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT * ");
			sql.append(" FROM ( SELECT *, @ROWNUM :=@ROWNUM+1 AS ROWNUM ");
			sql.append("           FROM ( SELECT prd_no, sort1, sort2, name, color, size, short_des, full_des, stock, price, order_cnt ");
			sql.append("                      FROM ( SELECT AA.prd_no, AA.sort1, AA.sort2, AA.name, AA.color, AA.size, AA.short_des, AA.full_des, AA.stock, AA.price, AA.order_cnt ");
			sql.append("                                 FROM products AA ) BB, (SELECT @ROWNUM := 0) TMP ");
			sql.append("          ORDER BY prd_no DESC ) CC ) DD ");
			sql.append(" WHERE "+start+"<=ROWNUM AND ROWNUM<="+end+" ");
			
			list=jdbcTemplate.query(sql.toString(), new ProductsRowMapper());
		}catch (Exception e) {
			System.out.println("전체 상품 페이징실패:" +e);
		}//end
		return list;
	}//list() end
	
	//목록 조회 - 카테고리 상품 페이징 조회
	public List<Products> listAsSort(Products products, int start, int numPerPage) {
		List<Products> list=null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT ROW_NUMBER() OVER (ORDER BY BB.prd_no DESC) as rownum, ");
			sql.append(" BB.prd_no, BB.sort1, BB.sort2, BB.name, BB.color, BB.size, BB.short_des, BB.full_des, BB.stock, BB.price, BB.order_cnt ");
			sql.append(" FROM (SELECT min(AA.prd_no) as prd_no, AA.sort1, AA.sort2, AA.name, AA.color, AA.size, AA.short_des, AA.full_des, AA.stock, AA.price, AA.order_cnt ");
			sql.append("                                 FROM products AA where sort1='"+products.getSort1()+"' and sort2='"+products.getSort2()+"' group by name) BB ");
			sql.append(" ORDER BY BB.prd_no DESC LIMIT "+start+","+numPerPage+" ");
			
			list=jdbcTemplate.query(sql.toString(), new ProductsRowMapper());
		}catch (Exception e) {
			System.out.println("카테고리 상품 페이징실패:" +e);
		}//end
		return list;
	}//list() end
	
	// 상세 조회 - 같은 이름의 상품번호들 조회(주문순으로 정렬해서 상위 5개 데이터만 가져옴)
	public List<Products> searchProduct(String searchWord) throws Exception {
		List<Products> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT prd_no, sort1, sort2, name, color, size, short_des, full_des, stock, price, order_cnt ");
			sql.append(" FROM products ");
			sql.append(" WHERE name LIKE '%"+searchWord+"%' ");
			sql.append(" group by name order by order_cnt desc LIMIT 5 ");
			
			results = jdbcTemplate.query(sql.toString(), new ProductsRowMapper());
		}catch (Exception e) {
			System.out.println("검색어로 상품 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//searchProduct() end
}
