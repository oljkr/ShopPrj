<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <div class="container mb-5">
      <h1 class="mt-4">Order Detail</h1>
      <hr>
      <h6 class="mt-4">주문 일시 : 
        <fmt:parseDate value="${buyerInfo.buyTime}" 
              pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <fmt:formatDate pattern="yyyy. MM. dd HH:mm" value="${ parsedDateTime }" />
        &nbsp;&nbsp; / &nbsp;&nbsp;주문서 번호 : ${orderSheetNo}</h6>


      <div class="text-center">
        <div class="row justify-content-center mt-4">
          <div class="col-md-10">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Product Info</th>
                  <th scope="col">Quantity</th>
                  <th scope="col">Price</th>
                  <th scope="col">Status</th>
                </tr>
              </thead>
              <tbody>

                <c:set var="sum" value="0" />
                <c:forEach var="productsList" items="${productsList}" varStatus="vs">
                  <c:set var="quan" value="${orderList[vs.index].quantity}" />
                  <c:set var="pri" value="${productsList.price}" />
                  <c:set var="onetotal" value="${quan * pri}" />
                  <p hidden id="prdNo${vs.count}">${orderList[vs.index].prdNo}</p>
                  <tr>
                    <td class="text-left align-middle">
                      <div>
                        ${productsList.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;옵션&nbsp;:&nbsp;&nbsp;${productsList.color}&nbsp;/&nbsp;${productsList.size}&nbsp;]
                      </div>
                    </td>
                    <td class="align-middle text-center">
                      <div class="d-inline-block">
                        <div class="text-center">
                          <span id="thisquantity${vs.count}">${orderList[vs.index].quantity}</span>
                        </div>
                      </div>
                    </td>
                    <td class="align-middle" id="onetotal${vs.count}">₩ <c:out value="${onetotal}"></c:out>
                    </td>
                    <c:set var="sum" value="${sum + onetotal}" />
                    <td class="align-middle">
                      ${orderList[vs.index].status}
                    </td>
                  </tr>

                </c:forEach>

              </tbody>
            </table>
          </div>
        </div>
      </div>

      <form name='frm' method='post' action='add'>
        
      <div class="row justify-content-center">
        <div class="col-md-12">



          <div class="card mt-1 mb-3">
            <div class="card-header d-flex justify-content-between">
              <span>구매자 정보</span>
            </div>
            <div class="card-body">

              <div class="form-group row">
                <label for="name" class="col-md-2 col-form-label">이름</label>
                <div class="col-md-4">
                  ${buyerInfo.name}
                </div>
              </div>
              <div class="form-group row">
                <label for="email" class="col-md-2 col-form-label">이메일</label>
                <div class="col-md-6">
                  ${buyerInfo.email}
                </div>
              </div>
              <div class="form-group row">
                <label for="phone" class="col-md-2 col-form-label">핸드폰 번호</label>
                <div class="col-md-6">
                  ${buyerInfo.phone}
                </div>
              </div>

            </div>
          </div>





          <div class="card mt-1 mb-2">
            <div class="card-header d-flex justify-content-between">
              <span>배송 정보</span>
            </div>
            <div class="card-body">
              
                <input type="hidden" name="authInfo" value="${authInfo.roles}">
                <input type="hidden" name="userNo" value="${authInfo.userNo}">

                <div class="form-group row">
                  <label for="zipcode" class="col-md-2 col-form-label">우편번호</label>
                  <div class="col-md-4">
                    ${shipInfo.zipcode}
                  </div>

                </div>
                <div class="form-group row">
                  <label for="address1" class="col-md-2 col-form-label">주소</label>
                  <div class="col-md-8">
                    ${shipInfo.address1}
                  </div>
                </div>
                <div class="form-group row">
                  <label for="address2" class="col-md-2 col-form-label">나머지주소</label>
                  <div class="col-md-8">
                    ${shipInfo.address2}
                  </div>
                </div>

                <div class="form-group row">
                  <label for="name" class="col-md-2 col-form-label">이름</label>
                  <div class="col-md-4">
                    ${shipInfo.name}
                  </div>
                </div>
                <div class="form-group row">
                  <label for="phone" class="col-md-2 col-form-label">핸드폰 번호</label>
                  <div class="col-md-6">
                    ${shipInfo.phone}
                  </div>
                </div>

            </div>
          </div>

          <c:set var="ordersum" value="${sum+2500}" />

          <div class="card mt-1 mb-3">
            <div class="card-header d-flex justify-content-between">
              <span>결제 정보</span>
            </div>
            <div class="card-body">

              <div class="form-group row">
                <label for="address1" class="col-md-2 col-form-label">총상품가격</label>
                <div class="col-md-8">
                  <div class="d-flex align-items-center" style="height: 100%;">
                    ${sum} 원
                  </div>
                </div>
              </div>
              <div class="form-group row">
                <label for="address2" class="col-md-2 col-form-label">배송비</label>
                <div class="col-md-8">
                  <div class="d-flex align-items-center" style="height: 100%;">
                    2500 원
                  </div>
                </div>
              </div>

              <div class="form-group row">
                <label for="name" class="col-md-2 col-form-label">총결제금액</label>
                <div class="col-md-8">
                  <div class="d-flex align-items-center" style="height: 100%;">
                    <span style="font-size: 19px;">${ordersum} 원</span>
                  </div>
                </div>
              </div>

              <div class="form-group row">
                <label for="name" class="col-md-2 col-form-label">결제 수단</label>
                <div class="col-md-8">
                  <div class="d-flex align-items-center" style="height: 100%;">
                    ${orderList[0].paymentMethod}
                  </div>
                 
                </div>
              </div>

            </div>
          </div>

          </form>




        </div>
      </div>
    </div>


    <%@ include file="../footer.jsp" %>