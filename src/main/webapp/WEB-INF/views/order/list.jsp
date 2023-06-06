<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

  <div class="container mb-5">
    <h1 class="mt-4">Order List</h1>
    <hr>
    <div class="row justify-content-center">
      <div class="col-lg-12">
        <!-- <ul class="list-group mt-4 mb-5"> -->

          
            <c:if test="${not empty buyerInfoListPaging['1']}">
              <div class="border rounded shadow-sm p-4 mb-3">
            <h4>
              <fmt:parseDate value="${ buyerInfoListPaging['1'].buyTime }" 
              pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <fmt:formatDate pattern="yyyy. MM. dd" value="${ parsedDateTime }" />
              주문 <span style="font-size: 20px;">[주문서 번호 : <c:out value="${orderSheetListPaging[1].orderSheetNo}"></c:out>]</span><span style="font-size: 18px; margin-left: 15px;">
              <a href="${pageContext.request.contextPath}/order/getdetail?orderSheetNo=${orderSheetListPaging[1].orderSheetNo}"> 주문 상세보기 > </a>
            </span>
          </h4>

            <c:set var="orderList" value="${orderListPaging['1']}" />
            <c:set var="productList" value="${productListPaging['1']}" />
            <c:set var="prdImgList" value="${prdImgListPaging['1']}" />
            <ul class="list-group mt-3 mb-2">
            <c:forEach var="entry1" items="${orderList}" varStatus="status1">
              <c:if test="${status1.count <= fn:length(productList)}">
                <li class="list-group-item">
                  <div class="row align-items-center">

                    <div class="col-2  d-flex flex-column justify-content-center align-items-center">
                      <img src="./../storage/${prdImgList[status1.index].fileName}" alt="Product 2"
                          class="buy-product-image">
                    </div>

                    <div class="col-7">
                      <h5>${productList[status1.index].name}<span style="font-size: 20px;"> [ 옵션 : ${productList[status1.index].color} / ${productList[status1.index].size} ] </span></h5>
                      <p style="margin-bottom: 0;">${productList[status1.index].price} 원, ${entry1.quantity} 개</p>
                    </div>
                    <div class="col-3  d-flex flex-column justify-content-center align-items-center">
                      <div class="mb-2">${entry1.status}</div>
                      <c:if test="${entry1.status eq '배송 완료'}">
                      <button class="btn btn-primary">Add to Cart</button>
                    </c:if>
                    </div>
                  </div>
                </li>
              </c:if>
            </c:forEach>
          </ul>
        </div>
          </c:if>

        
        
        
            <c:if test="${not empty buyerInfoListPaging['2']}">
              <div class="border rounded shadow-sm p-4 mb-3">
            <h4><fmt:parseDate value="${ buyerInfoListPaging['2'].buyTime }" 
              pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <fmt:formatDate pattern="yyyy. MM. dd" value="${ parsedDateTime }" />
              주문 <span style="font-size: 20px;">[주문서 번호 : <c:out value="${orderSheetListPaging[2].orderSheetNo}"></c:out>]</span><span style="font-size: 18px; margin-left: 15px;">
              <a href="${pageContext.request.contextPath}/order/getdetail?orderSheetNo=${orderSheetListPaging[2].orderSheetNo}"> 주문 상세보기 > </a>
            </span></h4>

            <c:set var="orderList" value="${orderListPaging['2']}" />
            <c:set var="productList" value="${productListPaging['2']}" />
            <c:set var="prdImgList" value="${prdImgListPaging['2']}" />
            <ul class="list-group mt-3 mb-2">
            <c:forEach var="entry1" items="${orderList}" varStatus="status1">
              <c:if test="${status1.count <= fn:length(productList)}">
                
                <li class="list-group-item">
                  <div class="row align-items-center">

                    <div class="col-2  d-flex flex-column justify-content-center align-items-center">
                      <img src="./../storage/${prdImgList[status1.index].fileName}" alt="Product 2"
                          class="buy-product-image">
                    </div>

                    <div class="col-7">
                      <h5>${productList[status1.index].name}<span style="font-size: 20px;"> [ 옵션 : ${productList[status1.index].color} / ${productList[status1.index].size} ] </span></h5>
                      <p style="margin-bottom: 0;">${productList[status1.index].price} 원, ${entry1.quantity} 개</p>
                    </div>
                    <div class="col-3  d-flex flex-column justify-content-center align-items-center">
                      <div class="mb-2">${entry1.status}</div>
                      <c:if test="${entry1.status eq '배송 완료'}">
                      <button class="btn btn-primary">Add to Cart</button>
                    </c:if>
                    </div>
                  </div>
                </li>
              </c:if>
            </c:forEach>
          </ul>
        </div>
          </c:if>
        

        
            <c:if test="${not empty buyerInfoListPaging['3']}">
              <div class="border rounded shadow-sm p-4 mb-3">
            <h4><fmt:parseDate value="${ buyerInfoListPaging['3'].buyTime }" 
              pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <fmt:formatDate pattern="yyyy. MM. dd" value="${ parsedDateTime }" />
              주문 <span style="font-size: 20px;">[주문서 번호 : <c:out value="${orderSheetListPaging[3].orderSheetNo}"></c:out>]</span><span style="font-size: 18px; margin-left: 15px;">
              <a href="${pageContext.request.contextPath}/order/getdetail?orderSheetNo=${orderSheetListPaging[3].orderSheetNo}"> 주문 상세보기 > </a>
            </span></h4>

            <c:set var="orderList" value="${orderListPaging['3']}" />
            <c:set var="productList" value="${productListPaging['3']}" />
            <c:set var="prdImgList" value="${prdImgListPaging['3']}" />
            <ul class="list-group mt-3 mb-2">
            <c:forEach var="entry1" items="${orderList}" varStatus="status1">
              <c:if test="${status1.count <= fn:length(productList)}">

                <li class="list-group-item">
                  <div class="row align-items-center">

                    <div class="col-2  d-flex flex-column justify-content-center align-items-center">
                      <img src="./../storage/${prdImgList[status1.index].fileName}" alt="Product 2"
                          class="buy-product-image">
                    </div>

                    <div class="col-7">
                      <h5>${productList[status1.index].name}<span style="font-size: 20px;"> [ 옵션 : ${productList[status1.index].color} / ${productList[status1.index].size} ] </span></h5>
                      <p style="margin-bottom: 0;">${productList[status1.index].price} 원, ${entry1.quantity} 개</p>
                    </div>
                    <div class="col-3  d-flex flex-column justify-content-center align-items-center">
                      <div class="mb-2">${entry1.status}</div>
                      <c:if test="${entry1.status eq '배송 완료'}">
                      <button class="btn btn-primary">Add to Cart</button>
                    </c:if>
                    </div>
                  </div>
                </li>
              </c:if>
            </c:forEach>
          </ul>
        </div>
          </c:if>
        

        
            <c:if test="${not empty buyerInfoListPaging['4']}">
              <div class="border rounded shadow-sm p-4 mb-3">
            <h4><fmt:parseDate value="${ buyerInfoListPaging['4'].buyTime }" 
              pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <fmt:formatDate pattern="yyyy. MM. dd" value="${ parsedDateTime }" />
              주문 <span style="font-size: 20px;">[주문서 번호 : <c:out value="${orderSheetListPaging[4].orderSheetNo}"></c:out>]</span><span style="font-size: 18px; margin-left: 15px;">
              <a href="${pageContext.request.contextPath}/order/getdetail?orderSheetNo=${orderSheetListPaging[4].orderSheetNo}"> 주문 상세보기 > </a>
            </span></h4>

            <c:set var="orderList" value="${orderListPaging['4']}" />
            <c:set var="productList" value="${productListPaging['4']}" />
            <c:set var="prdImgList" value="${prdImgListPaging['4']}" />
            <ul class="list-group mt-3 mb-2">
            <c:forEach var="entry1" items="${orderList}" varStatus="status1">
              <c:if test="${status1.count <= fn:length(productList)}">

                <li class="list-group-item">
                  <div class="row align-items-center">
                    
                    <div class="col-2  d-flex flex-column justify-content-center align-items-center">
                      <img src="./../storage/${prdImgList[status1.index].fileName}" alt="Product 2"
                          class="buy-product-image">
                    </div>

                    <div class="col-7">
                      <h5>${productList[status1.index].name}<span style="font-size: 20px;"> [ 옵션 : ${productList[status1.index].color} / ${productList[status1.index].size} ] </span></h5>
                      <p style="margin-bottom: 0;">${productList[status1.index].price} 원, ${entry1.quantity} 개</p>
                    </div>
                    <div class="col-3  d-flex flex-column justify-content-center align-items-center">
                      <div class="mb-2">${entry1.status}</div>
                      <c:if test="${entry1.status eq '배송 완료'}">
                      <button class="btn btn-primary">Add to Cart</button>
                    </c:if>
                    </div>
                  </div>
                </li>
              </c:if>
            </c:forEach>
          </ul>
        </div>
          </c:if>
        


        
            <c:if test="${not empty buyerInfoListPaging['5']}">
              <div class="border rounded shadow-sm p-4 mb-3">
            <h4><fmt:parseDate value="${ buyerInfoListPaging['5'].buyTime }" 
              pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <fmt:formatDate pattern="yyyy. MM. dd" value="${ parsedDateTime }" />
              주문 <span style="font-size: 20px;">[주문서 번호 : <c:out value="${orderSheetListPaging[5].orderSheetNo}"></c:out>]</span><span style="font-size: 18px; margin-left: 15px;">
              <a href="${pageContext.request.contextPath}/order/getdetail?orderSheetNo=${orderSheetListPaging[5].orderSheetNo}"> 주문 상세보기 > </a>
            </span></h4>

            <c:set var="orderList" value="${orderListPaging['5']}" />
            <c:set var="productList" value="${productListPaging['5']}" />
            <c:set var="prdImgList" value="${prdImgListPaging['5']}" />
            <ul class="list-group mt-3 mb-2">
            <c:forEach var="entry1" items="${orderList}" varStatus="status1">
              <c:if test="${status1.count <= fn:length(productList)}">

                <li class="list-group-item">
                  <div class="row align-items-center">
                    <div class="col-2  d-flex flex-column justify-content-center align-items-center">
                      <img src="./../storage/${prdImgList[status1.index].fileName}" alt="Product 2"
                          class="buy-product-image">
                    </div>
                    <div class="col-7">                  
                      <h5>${productList[status1.index].name}<span style="font-size: 20px;"> [ 옵션 : ${productList[status1.index].color} / ${productList[status1.index].size} ] </span></h5>
                      <p style="margin-bottom: 0;">${productList[status1.index].price} 원, ${entry1.quantity} 개</p>
                    </div>
                    <div class="col-3  d-flex flex-column justify-content-center align-items-center">
                      <div class="mb-2">${entry1.status}</div>
                      <c:if test="${entry1.status eq '배송 완료'}">
                      <button class="btn btn-primary">Add to Cart</button>
                    </c:if>
                    </div>
                  </div>
                </li>
              </c:if>
            </c:forEach>
          </ul>
        </div>
          </c:if>


      </div>


      <div col="col-lg-12">
              
        <nav aria-label="Page navigation" class="text-center mt-3">
          <ul class="pagination">

            <c:choose> 
              <c:when test="${pageNum eq 1}">
              </c:when>
              <c:otherwise>
                <li class="page-item">
                  <a class="page-link" href="${pageContext.request.contextPath}/order/getlist?userNo=${authInfo.userNo}&pageNum=${pageNum-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                  </a>
                </li>
              </c:otherwise> 
            </c:choose> 

            <c:choose> 
              <c:when test="${totalPage <= endPage}">
                <c:forEach var="index" begin="${startPage}" end="${totalPage}">

                  <c:choose> 
                    <c:when test="${pageNum eq index}">
                      <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/order/getlist?userNo=${authInfo.userNo}&pageNum=${index}">
                        <c:out value="${index}" /></a>
                      </li>
                    </c:when>
                    <c:otherwise>
                      <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/getlist?userNo=${authInfo.userNo}&pageNum=${index}">
                        <c:out value="${index}" /></a>
                      </li>
                    </c:otherwise> 
                  </c:choose> 

                </c:forEach>
              </c:when> 
              <c:otherwise>
                <c:forEach var="index" begin="${startPage}" end="${endPage}">

                  <c:choose> 
                    <c:when test="${pageNum eq index}">
                      <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/order/getlist?userNo=${authInfo.userNo}&pageNum=${index}">
                        <c:out value="${index}" /></a>
                      </li>
                    </c:when>
                    <c:otherwise>
                      <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/getlist?userNo=${authInfo.userNo}&pageNum=${index}">
                        <c:out value="${index}" /></a>
                      </li>
                    </c:otherwise> 
                  </c:choose> 

                </c:forEach>
              </c:otherwise>
            </c:choose>

            <c:choose> 
              <c:when test="${pageNum eq totalPage}">
              </c:when>
              <c:otherwise>
                <li class="page-item">
                  <a class="page-link" href="${pageContext.request.contextPath}/order/getlist?userNo=${authInfo.userNo}&pageNum=${pageNum+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                  </a>
                </li>
              </c:otherwise> 
            </c:choose> 


          </ul>
        </nav>
    </div>






    </div>
  </div>
  
  



    <%@ include file="../footer.jsp" %>