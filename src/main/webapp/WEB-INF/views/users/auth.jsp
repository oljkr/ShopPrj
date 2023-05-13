<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- auth.jsp -->
<!-- 로그인 상태 정보 확인 -->
<%	
	Long sNo;
    String sEmail="";
    String sRoles="";
    
    if(session.getAttribute("userEmail")==null){
		//로그인 하지 않았다면
		sNo=0l;
    	sEmail="guest";
   		sRoles="guest";
	}else{
		sNo=(Long)session.getAttribute("userNo");
		sEmail=(String)session.getAttribute("userEmail");
		sRoles=(String)session.getAttribute("roles");
	}

%>