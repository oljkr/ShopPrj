package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.aike.domain.BuyerInfo;
import kr.co.aike.domain.Order;
import kr.co.aike.domain.OrderSheet;
import kr.co.aike.domain.SheetOrderConn;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderDao {
	private final JdbcTemplate jdbcTemplate;
	private StringBuilder sql=null;
	
	// 구매자 정보 등록 처리
	public int insertBuyerInfo(BuyerInfo buyerInfo) throws Exception {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO buyer_info(name, email, phone, buyer_no, buy_time) ");
			sql.append(" VALUES(?, ?, ?, ?, now()) ");
			cnt=jdbcTemplate.update(sql.toString(), buyerInfo.getName(), buyerInfo.getEmail(), buyerInfo.getPhone(), buyerInfo.getBuyerNo());
		}catch (Exception e) {
			System.out.println("구매자 정보 등록 실패:" + e);
		}//end
		return cnt;
	}//insertBuyerInfo() end
	
	// 상세 조회 - 상품명, 컬러, 사이즈
	public BuyerInfo selectBuyerInfo(BuyerInfo buyerInfo) throws Exception {
		BuyerInfo results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT buyer_info_no, name, email, phone, buyer_no, buy_time ");
			sql.append(" FROM buyer_info ");
			sql.append(" where name='"+buyerInfo.getName()+"' and email='"+buyerInfo.getEmail()+"' and phone='"+buyerInfo.getPhone()+"' and buyer_no='"+buyerInfo.getBuyerNo()+"' ");
			sql.append(" ORDER BY buyer_info_no DESC ");
			sql.append(" LIMIT 1 ");
			
			RowMapper<BuyerInfo> rowMapper=new RowMapper<BuyerInfo>() {
				@Override
				public BuyerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					BuyerInfo buyerInfo = new BuyerInfo();
					buyerInfo.setBuyerInfoNo(rs.getLong("buyer_info_no"));
					buyerInfo.setName(rs.getString("name"));
					buyerInfo.setEmail(rs.getString("name"));
					buyerInfo.setPhone(rs.getString("name"));
					buyerInfo.setBuyerNo(rs.getString("name"));
					buyerInfo.setBuyTime(rs.getTimestamp("buy_time").toLocalDateTime());
					return buyerInfo;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.queryForObject(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("구매자 정보 세부 자료읽기 실패:" +e);
			return null;
		}//end
		return results;
	}//selectBuyerInfo() end
	
	// 주문 등록 처리
	public int insertOrder(Order order) throws Exception {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO aike.order(prd_no, quantity, ship_info_no, payment_method, status, pay_time) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, now()) ");
			cnt=jdbcTemplate.update(sql.toString(), order.getPrdNo(), order.getQuantity(), order.getShipInfoNo(), order.getPaymentMethod(), "상품준비중");
		}catch (Exception e) {
			System.out.println("주문 등록 실패:" + e);
		}//end
		return cnt;
	}//insertOrder() end
	
	// 상세 조회 - 상품명, 컬러, 사이즈
	public Order selectOrder(Order order) throws Exception {
		Order results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT order_no, prd_no, quantity, ship_info_no, payment_method, status, pay_time ");
			sql.append(" FROM aike.order ");
			sql.append(" where prd_no='"+order.getPrdNo()+"' and quantity='"+order.getQuantity()+"' and ship_info_no='"+order.getShipInfoNo()+"' and status='상품준비중' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new OrderRowMapper());
		}catch (Exception e) {
			System.out.println("주문 세부 자료읽기 실패:" +e);
			return null;
		}//end
		return results;
	}//selectOrder() end
	

	//주문서 등록 처리
	public int insertOrderSheet(OrderSheet orderSheet) throws Exception {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO order_sheet(buyer_info_no) ");
			sql.append(" VALUES(?) ");
			cnt=jdbcTemplate.update(sql.toString(), orderSheet.getBuyerInfoNo());
		}catch (Exception e) {
			System.out.println("주문서 등록 실패:" + e);
		}//end
		return cnt;
	}//insertOrderSheet() end
	
	//주문서 상세 조회
	public OrderSheet selectOrderSheet(OrderSheet orderSheet) throws Exception {
		OrderSheet results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT order_sheet_no, buyer_info_no ");
			sql.append(" FROM order_sheet ");
			sql.append(" where buyer_info_no='"+orderSheet.getBuyerInfoNo()+"' ");
			
			RowMapper<OrderSheet> rowMapper=new RowMapper<OrderSheet>() {
				@Override
				public OrderSheet mapRow(ResultSet rs, int rowNum) throws SQLException {
					OrderSheet orderSheet = new OrderSheet();
					orderSheet.setOrderSheetNo(rs.getLong("order_sheet_no"));
					orderSheet.setBuyerInfoNo(rs.getLong("buyer_info_no"));
					return orderSheet;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.queryForObject(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("주문서 세부 자료읽기 실패:" +e);
			return null;
		}//end
		return results;
	}//selectOrderSheet() end
	
	//주문서-주문 연결 등록 처리
	public int insertOrderSheetConn(SheetOrderConn sheetOrderConn) throws Exception {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO sheet_order_conn(order_sheet_no, order_no) ");
			sql.append(" VALUES(?, ?) ");
			cnt=jdbcTemplate.update(sql.toString(), sheetOrderConn.getOrderSheetNo(), sheetOrderConn.getOrderNo());
		}catch (Exception e) {
			System.out.println("주문서-주문 연결 등록 실패:" + e);
		}//end
		return cnt;
	}//insertOrderSheetConn() end

}
