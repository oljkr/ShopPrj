package kr.co.aike.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.aike.domain.Products;
import kr.co.aike.domain.ShipInfo;
import kr.co.aike.domain.Users;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ShipInfoDao {
	private final JdbcTemplate jdbcTemplate;
	private StringBuilder sql=null;
	
	// 등록 처리
	public int insertShipInfo(ShipInfo shipInfo) throws Exception {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO ship_info(zipcode, address1, address2, name, phone) ");
			sql.append(" VALUES(?, ?, ?, ?, ?) ");
			cnt=jdbcTemplate.update(sql.toString(), shipInfo.getZipcode(), shipInfo.getAddress1(), shipInfo.getAddress2(), shipInfo.getName(), shipInfo.getPhone());
		}catch (Exception e) {
			System.out.println("배송 정보 등록 실패:" + e);
		}//end
		return cnt;
	}//insertShipInfo() end
	
	// 상세 조회
	public ShipInfo selectShipInfo(ShipInfo shipInfo) throws Exception {
		ShipInfo results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT ship_info_no, zipcode, address1, address2, name, phone ");
			sql.append(" FROM ship_info ");
			sql.append(" where zipcode='"+shipInfo.getZipcode()+"' and address1='"+shipInfo.getAddress1()+"' and address2='"+shipInfo.getAddress2()+"' and name='"+shipInfo.getName()+"' and phone='"+shipInfo.getPhone()+"' ");
			sql.append(" ORDER BY ship_info_no DESC ");
			sql.append(" LIMIT 1 ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new ShipInfoRowMapper());
		}catch (Exception e) {
			System.out.println("배송 정보 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectShipInfo() end
	
	// 상세 조회 - 배송 정보 번호로
	public ShipInfo selectShipInfoAsNo(ShipInfo shipInfo) throws Exception {
		ShipInfo results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT ship_info_no, zipcode, address1, address2, name, phone ");
			sql.append(" FROM ship_info ");
			sql.append(" where ship_info_no='"+shipInfo.getShipInfoNo()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new ShipInfoRowMapper());
		}catch (Exception e) {
			System.out.println("배송 정보 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectShipInfoAsNo() end

}
