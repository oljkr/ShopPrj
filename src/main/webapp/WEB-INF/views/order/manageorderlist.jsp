<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <div class="container">
      <h1 class="text-center mt-4">Order Management</h1>
        <div class="text-center">
          <div class="row justify-content-center mt-4">

            <div col="col-lg-11">
              
              <nav aria-label="Page navigation" class="text-center mb-2">
                <ul class="pagination">
      
                  <c:choose> 
                    <c:when test="${pageNum eq 1}">
                    </c:when>
                    <c:otherwise>
                      <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/order/ordermanage?pageNum=${pageNum-1}" aria-label="Previous">
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
                            <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/order/ordermanage?pageNum=${index}">
                              <c:out value="${index}" /></a>
                            </li>
                          </c:when>
                          <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/ordermanage?pageNum=${index}">
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
                            <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/order/ordermanage?pageNum=${index}">
                              <c:out value="${index}" /></a>
                            </li>
                          </c:when>
                          <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/ordermanage?pageNum=${index}">
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
                        <a class="page-link" href="${pageContext.request.contextPath}/order/ordermanage?pageNum=${pageNum+1}" aria-label="Next">
                          <span aria-hidden="true">&raquo;</span>
                          <span class="sr-only">Next</span>
                        </a>
                      </li>
                    </c:otherwise> 
                  </c:choose> 
      
      
                </ul>
              </nav>
          </div>

            <div class="col-md-12 mb-4">
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">주문 일시</th>
                    <th scope="col">주문서 번호</th>
                    <th scope="col">제품 정보</th>
                    <th scope="col">수량</th>
                    <th scope="col">주문 가격</th>
                    <th scope="col">주문 상태</th>
                  </tr>
                </thead>
                <tbody>

                  
                  <c:set var="orderListPaging" value="${orderListPaging}" />
                  <c:set var="productListPaging" value="${productListPaging}" />
                  <c:set var="prdImgListPaging" value="${prdImgListPaging}" />
                  <c:set var="prdImgListPaging" value="${buyerInfoListPaging}" />
                  <c:set var="orderSheetListPaging" value="${orderSheetListPaging}" />


                  <c:forEach var="entry" items="${orderListPaging}">
                    <c:set var="key" value="${entry.key}" />
                    <c:set var="orderList" value="${entry.value}" />
                    <c:set var="productList" value="${productListPaging[key]}" />
                    <c:set var="prdImgList" value="${prdImgListPaging[key]}" />
                    <c:set var="buyerInfoList" value="${buyerInfoListPaging[key]}" />
                    <c:set var="orderSheetList" value="${orderSheetListPaging[key]}" />


                  <c:forEach var="entry1" items="${orderList}" varStatus="status1">
                    <c:if test="${status1.count <= fn:length(productList)}">
                    <tr>
                      <td class="align-middle text-center"><fmt:parseDate value="${ buyerInfoList.buyTime }" 
                        pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                      <fmt:formatDate pattern="yyyy.MM.dd HH:mm:ss" value="${ parsedDateTime }" />
                      </td>
                      <td class="align-middle text-center">
                        <c:out value="${orderSheetList.orderSheetNo}"></c:out>
                      </td>
                      <td class="text-left align-middle">
                        <div>${productList[status1.index].name}</div>
                        <div>[&nbsp;옵션&nbsp;:&nbsp;&nbsp;${productList[status1.index].color}&nbsp;/&nbsp;${productList[status1.index].size}&nbsp;]</div>
                      </td>
                      <td class="align-middle text-center">
                        ${entry1.quantity}
                      </td>
                      <td class="align-middle text-center">
                        ${productList[status1.index].price}
                      </td>
                      <td class="align-middle text-center">
                        <div class="mb-2">${entry1.status}</div>
                        <!-- <button type="button" class="btn btn-sm btn-primary" data-toggle="modal"
                        data-target="#idCheckModal">상태 변경 하기</button> -->
                        <button class="btn btn-primary open-modal" data-value1="${entry1.orderNo}">
                          상태 변경 하기</button>

                      </td>
                      


                    </tr>

                    

                      
                    </c:if>
                  </c:forEach>
                </c:forEach>



                </tbody>
              </table>
            </div>



                      <!-- Modal start -->
                      <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                      aria-labelledby="idCheckModalLabel" aria-hidden="true">
                      <div class="modal-dialog" role="document">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="idCheckModalLabel">주문 상태 변경</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                            </button>
                          </div>
                          <div class="modal-body">
          
                            <div class="container">
                              <form>
                                <input type="hidden" id="orderNo" name="orderNo" value="${entry1.orderNo}">
                                <div class="form-group row">
                                  <label for="status" class="col-md-4 col-form-label">주문 상태:</label>
                                  <div class="col-md-6">
                                    <select class="form-control" id="status" name="status">
                                      <option value="상품준비중">상품준비중</option>
                                      <option value="배송완료">배송완료</option>
                                    </select>
                                  </div>
                                </div>
                                <div class="form-group row">
                                  <label for="waybillnum" class="col-md-4 col-form-label">운송장 번호 입력:</label>
                                  <div class="col-md-8">
                                    <input type="text" id="waybillnum" name="waybillnum" maxlength="10" autofocus class="form-control">
                                  </div>
                                </div>
                                <div class="form-group row">
                                  <div class="col-md-2"></div>
                                  <div id="panel" style="display:none;" class="col-md-7"></div>
                                  <div class="col-md-2"></div>
                                </div>
                                <p>Value 1: <span id="modalValue1"></span></p>
                              </form>
                            </div>
          
                          </div>
                          <div class="modal-footer">
                            <div id="panel2" style="display:none;"></div>
                            <button type="button" class="btn btn-primary" id="statChange">사용하기</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- Modal end-->
            


            


          </div>
        </div>

    </div>


    <script>
      $(document).ready(function() {
        $('.open-modal').click(function() {
          var value1 = $(this).data('value1');
          $('#orderNo').val(value1);
          
          $('#myModal').modal('show');
        });
      });

    </script>

    <!-- 주문 상태 변경 관련 함수 -->
    <script>
      $("#statChange").click(function () {
          $.post("statchange", "status="+$("#orderNo").val()+"/" + $("#status").val() + "/ 운송장 번호:" +$("#waybillnum").val() , responseProc, "text");
          $('#myModal').modal('hide');
          location.reload();
      });

      function responseProc(data){
            alert("주문 상태가 변경되었습니다.");
          }

    </script>

    <%@ include file="../footer.jsp" %>