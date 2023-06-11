<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

          .product {
            margin-bottom: 20px;
          }

          .product img {
            width: 100%;
            height: auto;
          }

          .product-name {
            font-weight: bold;
            margin-top: 10px;
          }

          .product-price {
            color: #888;
          }


          /* Custom styling for modal content */
          .modal-content {
            border-radius: 0;
          }

          .product-image {
            max-width: 100px;
            max-height: 100px;
          }

          /* Center the box horizontally and vertically */
          .total-amount-container {
            display: flex;
            justify-content: center;
            align-items: center;
          }

          /* Style the box */
          .total-amount-box {
            width: 80%;
            height: 100px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            /* background-color: #f8f8f8; */
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
          }
          .total-amount-box p {
            margin-bottom: 0;
            font-size: 18px;
            color: #333;
          }

          .total-amount-box div {
            margin-bottom: 0;
            font-size: 18px;
            color: #333;
          }

          /* Style the colored text */
          .colored-text {
            font-size: 19px;
            color: rgb(231, 80, 80);
          }


          .hover-list {
      display: none; /* Hide the list by default */
      position: absolute;
      background-color: #fff;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
      list-style: none; /* Remove the list-style */
      margin-top: 10px; /* Adjust the margin as needed */
      width: 70px;
    }
    
    .icon:hover + .hover-list {
      display: block; /* Show the list on hover */
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
        <nav class="navbar navbar-expand-sm navbar-light"
          style="background-color: white; border-bottom: 1px solid gray;">

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
                <a class="nav-link dropdown-toggle text-dark font-weight-bold" href="#" id="navbarDropdown"
                  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Men
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list?sort1=m&sort2=sho">신발</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list?sort1=m&sort2=clo">의류</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list?sort1=m&sort2=acc">용품</a>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-dark font-weight-bold" href="#" id="navbarDropdown"
                  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Women
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list?sort1=w&sort2=sho">신발</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list?sort1=w&sort2=clo">의류</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list?sort1=w&sort2=acc">용품</a>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-dark font-weight-bold" href="#" id="navbarDropdown"
                  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Kids
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list?sort1=k&sort2=sho">신발</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list?sort1=k&sort2=clo">의류</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/products/list?sort1=k&sort2=acc">용품</a>
                </div>
              </li>
            </ul>

            <ul class='navbar-nav'>
              <li class='nav-item m-1'>
                <div class="d-flex align-items-center">

                  <form class="form-inline my-4 my-lg-0 mx-3">
                    <div class="input-group" style="width:250px">
                      <input id="search-input" class="form-control mr-sm-2 form-rounded" type="search" placeholder="Search"
                        aria-label="Search">
                      <div class="input-group-append">
                        <button class="btn btn-outline-dark my-2 my-sm-0 form-rounded" type="submit">
                          <i class="fas fa-search"></i>
                        </button>
                      </div>
                    </div>
                  </form>

                  <c:catch>
                    <c:choose>
                      <c:when test="${empty authInfo && empty cookie.cartCnt.value}">

                      <div class="dropdown mr-3">
                        <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button"
                          id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                          aria-expanded="false">
                          <i class="fas fa-user icon"></i>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                          <a class="dropdown-item"
                            href="${pageContext.request.contextPath}/users/login">로그인</a>
                          <a class="dropdown-item"
                            href="${pageContext.request.contextPath}/order/guest">주문조회</a>
                        </div>
                      </div>


                      	<a href="${pageContext.request.contextPath}/cart/getlist" class="text-dark mr-2">
                          <i class="fas fa-shopping-cart fa-2x"></i>
                          <span class="badge badge-danger" id="cartIconNumber">0</span>
                        </a>


                      </c:when>
                      <c:when test="${empty authInfo && not empty cookie.cartCnt.value}">

                      <div class="dropdown mr-3">
                        <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button"
                          id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                          aria-expanded="false">
                          <i class="fas fa-user icon"></i>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                          <a class="dropdown-item"
                            href="${pageContext.request.contextPath}/users/login">로그인</a>
                          <a class="dropdown-item"
                            href="${pageContext.request.contextPath}/order/guest">주문조회</a>
                        </div>
                      </div>


                      	<a href="${pageContext.request.contextPath}/cart/getlist" class="text-dark mr-2">
                          <i class="fas fa-shopping-cart fa-2x"></i>
                          <span class="badge badge-danger" id="cartIconNumber">${cookie.cartCnt.value}</span>
                        </a>



                      </c:when>
                      <c:otherwise>
                        <c:choose>
                          <c:when test="${authInfo.roles eq 'guest' && empty cookie.cartCnt.value}">

                          <div class="dropdown mr-3">
                            <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button"
                              id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                              aria-expanded="false">
                              <i class="fas fa-user icon"></i>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                              <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/users/login">로그인</a>
                              <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/order/guest">주문조회</a>
                            </div>
                          </div>
    
    
                            <a href="${pageContext.request.contextPath}/cart/getlist" class="text-dark mr-2">
                              <i class="fas fa-shopping-cart fa-2x"></i>
                              <span class="badge badge-danger" id="cartIconNumber">0</span>
                            </a>

                          </c:when>
                          <c:when test="${authInfo.roles eq 'guest' && not empty cookie.cartCnt.value}">


                          <div class="dropdown mr-3">
                            <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button"
                              id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                              aria-expanded="false">
                              <i class="fas fa-user icon"></i>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                              <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/users/login">로그인</a>
                              <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/order/guest">주문조회</a>
                            </div>
                          </div>
    
    
                            <a href="${pageContext.request.contextPath}/cart/getlist" class="text-dark mr-2">
                              <i class="fas fa-shopping-cart fa-2x"></i>
                              <span class="badge badge-danger" id="cartIconNumber">${cookie.cartCnt.value}</span>
                            </a>


                          </c:when>
                          <c:otherwise>
                            <c:choose>
                              <c:when test="${authInfo.roles eq 'admin' && empty cookie.cartCnt.value}">


                                <a href="${pageContext.request.contextPath}/cart/getlist" class="text-dark mr-2">
                                  <i class="fas fa-shopping-cart fa-2x"></i>
                                  <span class="badge badge-danger" id="cartIconNumber">0</span>
                                </a>

                                <div class="dropdown">
                                  <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button"
                                    id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    관리자 ${authInfo.userName }님, 환영합니다.
                                  </a>
                                  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/order/ordermanage">주문관리</a>
                                <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/products/add">상품등록</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/logout">로그아웃</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/modifyuser">회원정보수정</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/preunregister">회원탈퇴</a>
                                  </div>
                                </div>

                              </c:when>
                              <c:when test="${authInfo.roles eq 'admin' && not empty cookie.cartCnt.value}">


                                <a href="${pageContext.request.contextPath}/cart/getlist" class="text-dark mr-2">
                                  <i class="fas fa-shopping-cart fa-2x"></i>
                                  <span class="badge badge-danger" id="cartIconNumber">${cookie.cartCnt.value}</span>
                                </a>

                                <div class="dropdown">
                                  <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button"
                                    id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    관리자 ${authInfo.userName }님, 환영합니다.
                                  </a>
                                  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/order/ordermanage">주문관리</a>
                                <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/products/add">상품등록</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/logout">로그아웃</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/modifyuser">회원정보수정</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/preunregister">회원탈퇴</a>
                                  </div>
                                </div>

                              </c:when>
                              <c:when test="${authInfo.roles eq 'member' && empty cookie.cartCnt.value}">


                                <a href="${pageContext.request.contextPath}/cart/getlist" class="text-dark mr-2">
                                  <i class="fas fa-shopping-cart fa-2x"></i>
                                  <span class="badge badge-danger" id="cartIconNumber">0</span>
                                </a>
                                <div class="dropdown">
                                  <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button"
                                    id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    ${authInfo.userName }님, 반갑습니다!
                                  </a>

                                  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/order/getlist?userNo=${authInfo.userNo}">주문조회</a>
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
                                


                                <div class="dropdown mr-3">
                                  <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button"
                                    id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    ${authInfo.userName }님, 반갑습니다!
                                  </a>

                                  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item"
                                href="${pageContext.request.contextPath}/order/getlist?userNo=${authInfo.userNo}">주문조회</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/logout">로그아웃</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/modifyuser">회원정보수정</a>
                                    <a class="dropdown-item"
                                      href="${pageContext.request.contextPath}/users/preunregister">회원탈퇴</a>
                                  </div>
                                </div>

                                <a href="${pageContext.request.contextPath}/cart/getlist" class="text-dark mr-2">
                                  <i class="fas fa-shopping-cart fa-2x"></i>
                                  <span class="badge badge-danger" id="cartIconNumber">${cookie.cartCnt.value}</span>
                                </a>
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

        <!-- Search box start -->
        <div id="justwrap">
          <div id="search-box" class="d-flex flex-wrap justify-content-center">
            <div id="search-results"></div>
          </div>
        </div>
        <!-- Search box end -->

        <script>

          function displaySearchResults() {
            var searchBox = document.getElementById('search-box');


            var searchInput = document.getElementById('search-input');
            var searchResultsDiv = document.getElementById('search-results');
            var searchTerm = searchInput.value.trim();

            // Clear previous search results
            searchResultsDiv.innerHTML = '';

            // Perform search and display results
            if (searchTerm !== '') {

              $.ajax({
                url: "searchproduct"
                , type: "post"
                , data: { "searchword": searchTerm }
                , dataType: "json"
                , error: function (request, status, error) {
                  alert("에러:" + error);
                  alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                }//error callback함수
                , success: showSearchResults//success callback함수
              });

              function showSearchResults(data) {
                $("#search-results").empty(); //검색할 때마다 추가되지 않게 결과 창 비움
                var searchResults = data.product;
                var searchResultsImg = data.img;

                // Create and append search result items to the search results div
                var temp = 0;
                searchResults.forEach(function (result) {
                  var resultItem = document.createElement('div');
                  resultItem.className = 'search-result';

                  var image = document.createElement('img');
                  image.src = "./../storage/" + searchResultsImg[temp].fileName;
                  image.alt = result.productName;

                  // Attach a link to the image
                  var link = document.createElement('a');
                  link.href = '${pageContext.request.contextPath}/products/detail?prdNo=' + result.prdNo

                  var productName = document.createElement('p');
                  productName.textContent = result.name;

                  var productPrice = document.createElement('p');
                  productPrice.textContent = result.price + " 원";

                  link.appendChild(image);
                  resultItem.appendChild(link);
                  resultItem.appendChild(productName);
                  resultItem.appendChild(productPrice);
                  searchResultsDiv.appendChild(resultItem);

                  temp++;

                  var leftt=temp*15-3;                  
              
                  resultItem.style.left = leftt+"%";
                });
              }

              // Display the search results
              searchResultsDiv.style.display = 'flex';
              searchResultsDiv.style.height = '300px';
              searchBox.style.backgroundColor='#ffffff';
              searchBox.style.border='1px solid #ccc';
            } else {
              // Hide the search results
              searchResultsDiv.style.display = 'none';
            }
          }

          // Add event listener to the search input
          var searchInput = document.getElementById('search-input');
          searchInput.addEventListener('keyup', displaySearchResults);
        </script>