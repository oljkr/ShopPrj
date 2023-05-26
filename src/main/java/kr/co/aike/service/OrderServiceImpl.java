package kr.co.aike.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.dao.OrderDao;
import kr.co.aike.dao.ShipInfoDao;
import kr.co.aike.dao.UsersDao;
import kr.co.aike.domain.BuyerInfo;
import kr.co.aike.domain.CartItem;
import kr.co.aike.domain.Order;
import kr.co.aike.domain.OrderSheet;
import kr.co.aike.domain.SheetOrderConn;
import kr.co.aike.domain.ShipInfo;
import kr.co.aike.domain.ShoppingCart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
	private final ShoppingCartService cartService;
	private final ShipInfoDao shipInfoDao;
	private final OrderDao orderDao;
	
	public ModelAndView addMessages(int code, String imgText, String msg1Text, String msg2Text, String msg3Text, String link1Text, String link1Href , String link2Text, String link2Href) {
		ModelAndView mav=new ModelAndView();
		String msg1="<h1 class=\"mb-4\">"+msg1Text+"</h1>";
		String img=imgText;
		String msg2="<h1 class=\"mb-4\">"+msg2Text+"</h1>";
		String msg3="<h4 class=\"mb-4\">"+msg3Text+"</h4>";
		String link1="<input class=\"btn btn-dark rounded-pill py-3 px-5\" type='button' value='"+link1Text+"' onclick='"+link1Href+"'>&nbsp;&nbsp;";
		String link2="<input class=\"btn btn-outline-dark rounded-pill py-3 px-5\" type='button' value='"+link2Text+"' onclick='"+link2Href+"'>";
		if(code==0) {
			mav.addObject("msg2", msg2);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else if(code==1) {
			mav.addObject("msg2", msg2);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
		} else if(code==2) {
			mav.addObject("msg1", msg1);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else if(code==3) {
			mav.addObject("img", img);
			mav.addObject("msg2", msg2);
			mav.addObject("msg3", msg3);
			mav.addObject("link1", link1);
		}
		return mav;
	}
	
	public ModelAndView getCartList(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		ShoppingCart shoppingCart = cartService.getOrCreateShoppingCart(request);
		List<CartItem> cartItems = shoppingCart.getItems();
		mav.addObject("cartItems", cartItems);
		mav.setViewName("order/addorder");
		return mav;
	}

	@Override
	public ModelAndView addOrder(ShipInfo shipInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		ModelAndView mav = new ModelAndView();
		//구매자 정보 등록
		BuyerInfo buyerInfo = new BuyerInfo();
		String temp1=request.getParameter("buyerName");
		buyerInfo.setName(temp1);
		temp1=request.getParameter("buyerEmail");
		buyerInfo.setEmail(temp1);
		temp1=request.getParameter("buyerPhone");
		buyerInfo.setPhone(temp1);
		
		String temp2=request.getParameter("authInfo");
		System.out.println("temp2"+temp2); //로그인 하지 않을 시 null 나옴
		if(temp2 != null && !temp2.equals("")) {
			String temp3 = request.getParameter("userNo");
//			Long userNo= Long.parseLong(temp3);
			buyerInfo.setBuyerNo(temp3);
		}else {
			//회원정보에 게스트 등록
			buyerInfo.setBuyerNo("guest");
		}
		orderDao.insertBuyerInfo(buyerInfo);
		//등록한 구매자 정보 번호 가져오기
		buyerInfo = orderDao.selectBuyerInfo(buyerInfo);
		
		
		//배송 정보 등록
		System.out.println(shipInfo.toString());
		shipInfoDao.insertShipInfo(shipInfo);
			//등록한 배송 정보의 배송 정보 번호 가져오기
		ShipInfo registeredShipInfo = shipInfoDao.selectShipInfo(shipInfo);
		System.out.println("registeredShipInfo"+registeredShipInfo.toString());
		
		//주문 등록
			//쿠키의 상품 목록 가져오기
		ShoppingCart shoppingCart = cartService.getOrCreateShoppingCart(request);
		List<CartItem> cartItems = shoppingCart.getItems();
		System.out.println(cartItems.toString());
			//결제 수단 설정
		temp1=request.getParameter("paymentMethod");
		System.out.println("paymentMethod"+temp1.toString());
		
		Order order = new Order();
		order.setPaymentMethod(temp1);
			//status 설정
		order.setStatus("상품준비중");
			//연결되는 배송 정보 번호 설정
		order.setShipInfoNo(registeredShipInfo.getShipInfoNo());
			//장바구니 항목 하나씩 가져와서 주문 등록 후 주문 번호 가져와서 List에 추가
		List<Long> orderNumList = new ArrayList<Long>();		
		for(int x=0;x<cartItems.size();++x) {
			CartItem cartItem = cartItems.get(x);
			order.setPrdNo(cartItem.getPrdNo());
			order.setQuantity(cartItem.getQuantity());
			System.out.println("order"+order);
			orderDao.insertOrder(order);
			//방금 등록한 주문 번호 가져옴
			Order temp = orderDao.selectOrder(order);
			orderNumList.add(temp.getOrderNo());
		}
		
		//주문서(order_sheet) 생성
		OrderSheet orderSheet = new OrderSheet();
		orderSheet.setBuyerInfoNo(buyerInfo.getBuyerInfoNo());
		orderDao.insertOrderSheet(orderSheet);
			//주문서 번호 가져옴
		orderSheet = orderDao.selectOrderSheet(orderSheet);
		System.out.println(orderSheet.toString());
		Long orderSheetNo = orderSheet.getOrderSheetNo();
		
		int cnt=0;
		//주문서-주문 연결
		SheetOrderConn sheetOrderConn = new SheetOrderConn();
		sheetOrderConn.setOrderSheetNo(orderSheetNo);
			//주문 테이블에 등록된 주문번호 리스트 하나씩 주문서 번호와 연결해서 db에 저장
		for(int x=0;x<orderNumList.size();++x) {
			sheetOrderConn.setOrderNo(orderNumList.get(x));
			cnt = orderDao.insertOrderSheetConn(sheetOrderConn);
		}
		
		String textOrderSheetNo = "주문서 번호 : "+ Long.toString(orderSheetNo);
		
		//장바구니 비우기
		ShoppingCart cart = new ShoppingCart();
		cartService.saveShoppingCart(cart, response);
		String cartCnt="0";
		cartService.saveShoppingCartCnt(cartCnt, response);
		
		if(cnt==0){
			mav=addMessages(0,"<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>","","!! 상품 주문 실패 !!", "" ,"다시시도","javascript:history.back()","메인으로","location.href=\"../home\"");
		}else {
			mav=addMessages(3,"<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>","","* ~ 상품 주문 성공 ~ *", textOrderSheetNo,"주문 목록으로","location.href=\"./list\"","","");
		}//if end
		mav.setViewName("order/msgView");
		
		return mav;
	}
	
	

}
