<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

          #shortDes {
            height: 100px;
          }

          .ck-editor__editable {
            min-height: 200px;
          }

          #scrollToTop {
            position: fixed;
            bottom: 20px;
            right: 20px;
            width: 50px;
            height: 50px;
            background-color: #f8f9fa;
            border-radius: 50%;
            border: none;
            color: #000;
            font-size: 20px;
            text-align: center;
            cursor: pointer;
            display: none;
          }

          #scrollToTop:hover {
            background-color: #e9ecef;
          }

          .square {
            width: 70px;
            height: 70px;
          }

          .big-square {
            width: 90%;
            height: 90%;
          }
        </style>

        <script src="./../js/check.js"></script>

        <link rel="stylesheet" href="./../css/style.css" />
        <link rel="stylesheet" as="style" crossorigin
          href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.6/dist/web/static/pretendard.css" />
          
          <script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
	  	  <script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>

      </head>

      <body>

        <!-- navbar start -->
        <nav class="navbar navbar-expand-sm navbar-light" style="background-color: white; border-bottom: 1px solid gray;">

          <!-- <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Navbar</a> -->
          <a href="${pageContext.request.contextPath}/home">
            <img src="./../storage/logo.png" width="70" alt="logo">
          </a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">
            <ul class="navbar-nav" style="margin-left:1%">
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-dark font-weight-bold" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">
                  Men
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/navlink1">신발</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/navlink2">의류</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/navlink3">용품</a>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-dark font-weight-bold" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">
                  Women
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list">신발</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/navlink2">의류</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/navlink3">용품</a>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-dark font-weight-bold" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">
                  Kids
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/navlink1">신발</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/navlink2">의류</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/navlink3">용품</a>
                </div>
              </li>
              <!-- Search form start -->
              <form class="form-inline my-4 my-lg-0 mx-2">
                <div class="input-group" style="width:250px">
                  <input class="form-control mr-sm-2 form-rounded" type="search" placeholder="Search"
                    aria-label="Search">
                  <div class="input-group-append">
                    <button class="btn btn-outline-dark my-2 my-sm-0 form-rounded" type="submit">
                      <i class="fas fa-search"></i>
                    </button>
                  </div>
                </div>
              </form>
              <!-- Search form end-->
            </ul>

            <ul class='navbar-nav'>
              <li class='nav-item m-1'>
                <div class="d-flex align-items-center">

                  <c:catch>
                    <c:choose>
                      <c:when test="${empty authInfo }">
                        <a type='button' class='btn btn-outline-dark'
                          href='${pageContext.request.contextPath}/users/login'>Login</a>
                      </c:when>
                      <c:otherwise>
                        <c:choose>
                          <c:when test="${authInfo.roles eq 'guest' }">
                            <a type='button' class='btn btn-outline-dark'
                              href='${pageContext.request.contextPath}/users/login'>Login</a>
                          </c:when>
                          <c:otherwise>
                            <c:choose>
                              <c:when test="${authInfo.roles eq 'admin' }">

                                




                                <a href="#" class="text-dark mr-2">
                                  <i class="fas fa-shopping-cart fa-2x"></i>
                                  <span class="badge badge-danger">0</span>
                                </a>

                                <div class="dropdown">
                                  <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button"
                                    id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    관리자 ${authInfo.userName }님, 환영합니다.
                                  </a>
                                  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/logout">로그아웃</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/modifyuser">회원정보수정</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/preunregister">회원탈퇴</a>
                                  </div>
                                </div>

                              </c:when>
                              <c:otherwise>
                                <a href="#" class="text-white mr-3">
                                  <i class="fas fa-search fa-lg"></i>
                                </a>
                                <a href="#" class="text-white mr-2">
                                  <i class="fas fa-shopping-cart fa-lg"></i>
                                  <span class="badge badge-danger">3</span>
                                </a>
                                <div class="dropdown">
                                  <a class="btn btn-secondary dropdown-toggle" href="#" role="button"
                                    id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    ${authInfo.userName }님, 반갑습니다!
                                  </a>

                                  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/logout">로그아웃</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/modifyuser">회원정보수정</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/preunregister">회원탈퇴</a>
                                  </div>
                                </div>
                              </c:otherwise>
                            </c:choose>
                          </c:otherwise>
                        </c:choose>
                      </c:otherwise>
                    </c:choose>
                  </c:catch>

                </div>
              </li>
            </ul>


          </div>
        </nav>

        <!-- navbar end -->