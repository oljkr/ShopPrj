<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Sports shopping mall</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
      crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <style>
      .fakeimg {
        height: 200px;
        background: #aaa;
      }
    </style>


    <script src="./../js/check.js"></script>

    <link rel="stylesheet" href="./../css/style.css" />
    <link rel="stylesheet" as="style" crossorigin
      href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.6/dist/web/static/pretendard.css" />
</head>
<body>

<!-- navbar start -->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/navlink1">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/navlink2">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/navlink3">Link</a>
      </li>    
    </ul>
    
    <ul class='navbar-nav ms-auto'>
       <li class='nav-item m-1'>
       
	<c:catch>
	    <c:choose>
	        <c:when test="${empty authInfo }">
                <a type='button' class='btn btn-outline-light' href='${pageContext.request.contextPath}/users/login'>Sign in</a>
	        </c:when>
	        <c:otherwise>
	            <c:choose>
	           		<c:when test="${authInfo.roles eq 'guest' }">
	           		<a type='button' class='btn btn-outline-light' href='${pageContext.request.contextPath}/users/login'>Sign in</a>
	           		</c:when>
	           		<c:otherwise>
	           			<c:choose>
			                <c:when test="${authInfo.roles eq 'admin' }">
		                       <a type='buttone' class="text-white mr-2">관리자 ${authInfo.userName }님, 환영합니다.</a>
		                       <a type='button' class='btn btn-outline-light' href='${pageContext.request.contextPath}/users/logout'>Logout</a>
			                </c:when>
			                <c:otherwise>
			                   <a type='buttone' class="text-white mr-2">${authInfo.userName }님, 반갑습니다!</a>
		                       <a type='button' class='btn btn-outline-light' href='${pageContext.request.contextPath}/users/logout'>Logout</a>
			                </c:otherwise>
		                </c:choose>
	                </c:otherwise>
	            </c:choose>
	        </c:otherwise>
	    </c:choose>
	</c:catch>
       
       




       </li>
    </ul>
   
    



  </div>  
</nav>
<!-- navbar end -->