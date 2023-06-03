package kr.co.aike.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.aike.domain.BuyerInfo;
import kr.co.aike.domain.Order;
import kr.co.aike.domain.OrderSheet;
import kr.co.aike.domain.Products;
import kr.co.aike.domain.SheetOrderConn;
import kr.co.aike.domain.Users;
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
	
	// 구매자 정보 상세 조회
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
	
	// 구매자 정보 상세 조회 - 구매자 번호로
	public BuyerInfo selectBuyerInfoAsNo(BuyerInfo buyerInfo) throws Exception {
		BuyerInfo results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT buyer_info_no, name, email, phone, buyer_no, buy_time ");
			sql.append(" FROM buyer_info ");
			sql.append(" where buyer_info_no='"+buyerInfo.getBuyerInfoNo()+"' ");
			
			RowMapper<BuyerInfo> rowMapper=new RowMapper<BuyerInfo>() {
				@Override
				public BuyerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					BuyerInfo buyerInfo = new BuyerInfo();
					buyerInfo.setBuyerInfoNo(rs.getLong("buyer_info_no"));
					buyerInfo.setName(rs.getString("name"));
					buyerInfo.setEmail(rs.getString("email"));
					buyerInfo.setPhone(rs.getString("phone"));
					buyerInfo.setBuyerNo(rs.getString("buyer_no"));
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
	}//selectBuyerInfoAsNo() end
	
	// 구매자 정보 상세 조회 - 구매자 번호로 같은 구매자 번호의 구매자 정보 번호들 조회
	public List<BuyerInfo> selectAllBuyerInfoNumList() throws Exception {
		List<BuyerInfo> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT buyer_info_no, name, email, phone, buyer_no, buy_time ");
			sql.append(" FROM buyer_info ");
			sql.append(" ORDER BY buyer_info_no DESC ");
			
			RowMapper<BuyerInfo> rowMapper=new RowMapper<BuyerInfo>() {
				@Override
				public BuyerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					BuyerInfo buyerInfo = new BuyerInfo();
					buyerInfo.setBuyerInfoNo(rs.getLong("buyer_info_no"));
					buyerInfo.setName(rs.getString("name"));
					buyerInfo.setEmail(rs.getString("email"));
					buyerInfo.setPhone(rs.getString("phone"));
					buyerInfo.setBuyerNo(rs.getString("buyer_no"));
					buyerInfo.setBuyTime(rs.getTimestamp("buy_time").toLocalDateTime());
					return buyerInfo;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.query(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("구매자 정보 번호 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectAllBuyerInfoNumList() end
	
	// 구매자 정보 상세 조회 - 구매자 번호로 같은 구매자 번호의 구매자 정보 번호들 조회
	public List<BuyerInfo> selectBuyerInfoNumList(BuyerInfo buyerInfo) throws Exception {
		List<BuyerInfo> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT buyer_info_no, name, email, phone, buyer_no, buy_time ");
			sql.append(" FROM buyer_info ");
			sql.append(" WHERE buyer_no='"+buyerInfo.getBuyerNo()+"' ");
			sql.append(" ORDER BY buyer_info_no DESC ");
			
			RowMapper<BuyerInfo> rowMapper=new RowMapper<BuyerInfo>() {
				@Override
				public BuyerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					BuyerInfo buyerInfo = new BuyerInfo();
					buyerInfo.setBuyerInfoNo(rs.getLong("buyer_info_no"));
					buyerInfo.setName(rs.getString("name"));
					buyerInfo.setEmail(rs.getString("email"));
					buyerInfo.setPhone(rs.getString("phone"));
					buyerInfo.setBuyerNo(rs.getString("buyer_no"));
					buyerInfo.setBuyTime(rs.getTimestamp("buy_time").toLocalDateTime());
					return buyerInfo;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.query(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("구매자 정보 번호 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectBuyerInfoNumList() end
	
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
	
	// 주문 상세 조회
	public Order selectOrder(Order order) throws Exception {
		Order results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT order_no, prd_no, quantity, ship_info_no, payment_method, status, pay_time ");
			sql.append(" FROM aike.order ");
			sql.append(" where prd_no='"+order.getPrdNo()+"' and quantity='"+order.getQuantity()+"' and ship_info_no='"+order.getShipInfoNo()+"' and status='상품준비중' ");
			sql.append(" ORDER BY order_no DESC ");
			sql.append(" LIMIT 1 ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new OrderRowMapper());
		}catch (Exception e) {
			System.out.println("주문 세부 자료읽기 실패:" +e);
			return null;
		}//end
		return results;
	}//selectOrder() end
	
	// 주문 상세 조회 - 주문 번호로
	public Order selectOrderAsNo(Order order) throws Exception {
		Order results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT order_no, prd_no, quantity, ship_info_no, payment_method, status, pay_time ");
			sql.append(" FROM aike.order ");
			sql.append(" where order_no='"+order.getOrderNo()+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new OrderRowMapper());
		}catch (Exception e) {
			System.out.println("주문 세부 자료읽기 실패:" +e);
			return null;
		}//end
		return results;
	}//selectOrderAsNo() end
	
	//운송 상태 변경
	public int updateStatus(String orderNo, String newStat) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" UPDATE aike.order ");
			sql.append(" SET status = '"+newStat+"' ");
			sql.append(" WHERE order_no = '"+orderNo+"' ");
			cnt=jdbcTemplate.update(sql.toString());
		}catch (Exception e) {
			System.out.println("운송 상태 바꾸기 실패:" + e);
		}//end
		return cnt;		
	}//updateStatus() end
	
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
	
	//주문서 상세 조회 - 구매자 정보로
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
	
	//주문서 상세 조회 - 주문서 정보로
	public OrderSheet selectOrderSheetAsNo(OrderSheet orderSheet) throws Exception {
		OrderSheet results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT order_sheet_no, buyer_info_no ");
			sql.append(" FROM order_sheet ");
			sql.append(" where order_sheet_no='"+orderSheet.getOrderSheetNo()+"' ");
			
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
	}//selectOrderSheetAsNo() end
	
	//목록 조회 - 상품 분류에 따른 전체 갯수 조회
	public int totalRowCount(OrderSheet orderSheet) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(distinct name) FROM order_sheet ");
			sql.append(" where order_sheet_no='"+orderSheet.getOrderSheetNo()+"' ");
			cnt=jdbcTemplate.queryForObject(sql.toString(), Integer.class);		
		}catch (Exception e) {
			System.out.println("카테고리의 전체 상품 갯수 조회 실패:" + e);
		}//end
		return cnt;
	}//totalRowCountAsSort() end
	
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
	
	//주문 번호 목록 조회 - 주문서 번호로
	public List<Long> selectOrderSheetConn(SheetOrderConn sheetOrderConn) throws Exception {
		List<Long> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT order_no ");
			sql.append(" FROM sheet_order_conn ");
			sql.append(" WHERE order_sheet_no='"+sheetOrderConn.getOrderSheetNo()+"' ");
			sql.append(" ORDER BY order_no DESC ");
			
			RowMapper<Long> rowMapper=new RowMapper<Long>() {
				@Override
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					Long proNo=rs.getLong("order_no");
					return proNo;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.query(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("주문서 번호 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}//selectOrderSheetConn() end

}
