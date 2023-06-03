<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <div class="container">
      <h1 class="mt-4">Product Order</h1>
      <hr>


      <div class="text-center">
        <div class="row justify-content-center mt-4">
          <div class="col-md-10">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Product Info</th>
                  <th scope="col">Quantity</th>
                  <th scope="col">Price</th>
                </tr>
              </thead>
              <tbody>

                <c:set var="sum" value="0" />
                <c:forEach var="cartItem" items="${cartItems}" varStatus="vs">
                  <c:set var="quan" value="${cartItem.quantity}" />
                  <c:set var="pri" value="${cartItem.price}" />
                  <c:set var="onetotal" value="${quan * pri}" />
                  <p hidden id="prdNo${vs.count}">${cartItem.prdNo}</p>
                  <tr>
                    <td class="text-left align-middle">
                      <div>
                        ${cartItem.prdName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;옵션&nbsp;:&nbsp;&nbsp;${cartItem.color}&nbsp;/&nbsp;${cartItem.size}&nbsp;]
                      </div>
                    </td>
                    <td class="align-middle text-center">
                      <div class="d-inline-block">
                        <div class="text-center">
                          <span id="thisquantity${vs.count}">${cartItem.quantity}</span>
                        </div>
                      </div>
                    </td>
                    <td class="align-middle" id="onetotal${vs.count}">₩ <c:out value="${onetotal}"></c:out>
                    </td>
                    <c:set var="sum" value="${sum + onetotal}" />
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
                  <input type="text" class="form-control" id="buyerName" name="buyerName" placeholder="Enter your name"
                    required>
                </div>
              </div>
              <div class="form-group row">
                <label for="email" class="col-md-2 col-form-label">이메일</label>
                <div class="col-md-6">
                  <input type="email" class="form-control" id="buyerEmail" name="buyerEmail"
                    aria-describedby="emailHelp" placeholder="Enter your email" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="phone" class="col-md-2 col-form-label">핸드폰 번호</label>
                <div class="col-md-6">
                  <input type="tel" class="form-control" id="buyerPhone" name="buyerPhone" placeholder="Enter your cell phone number" required>
                  <small class="form-text text-muted">예) 010-1234-4567</small>
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
                    <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="주소 찾기 버튼을 눌러 입력"
                      readonly>
                  </div>
                  <div class="col-md-3">
                    <input type="button" value="주소찾기" onclick="DaumPostcode()" class="btn btn-primary"
                      data-toggle="modal" data-target="#myModal2">
                  </div>

                </div>
                <div class="form-group row">
                  <label for="address1" class="col-md-2 col-form-label">주소</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="address1" name="address1" required>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="address2" class="col-md-2 col-form-label">나머지주소</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="address2" name="address2" required>
                  </div>
                </div>

                <div class="form-group row">
                  <label for="name" class="col-md-2 col-form-label">이름</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Enter your name"
                      required>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="phone" class="col-md-2 col-form-label">핸드폰 번호</label>
                  <div class="col-md-6">
                    <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter your cell phone number" required>
                    <small class="form-text text-muted">예) 010-1234-4567</small>
                  </div>
                </div>


                <!-- PostCode Modal -->
                <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                  aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="myModalLabel">Daum Postcode</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <!-- Embed the Daum Postcode widget here -->
                        <div id="wrap"></div>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- Modal -->


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
                <label for="name" class="col-md-2 col-form-label">결제 수단 선택</label>
                <div class="col-md-8">
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="paymentMethod" id="creditCard" value="creditCard"
                      checked>
                    <label class="form-check-label" for="creditCard">
                      신용카드
                    </label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="paymentMethod" id="paypal" value="paypal">
                    <label class="form-check-label" for="paypal">
                      간편결제
                    </label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="paymentMethod" id="bankTransfer"
                      value="bankTransfer">
                    <label class="form-check-label" for="bankTransfer">
                      계좌이체
                    </label>
                  </div>
                </div>
              </div>

            </div>
          </div>

          <div class="row justify-content-center mb-5">
            <div class="col-md-6 text-md-center">
              <button type="submit" class="btn btn-dark btn-lg rounded-pill py-3 px-5"
                style="width: 230px;">주문하기</button>
            </div>
          </div>



          </form>




        </div>
      </div>
    </div>

    <!-- 우편번호 입력 관련 함수 -->
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script>
      var element_wrap = document.getElementById('wrap');
      function DaumPostcode() {
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
          oncomplete: function (data) {
            var fullAddr = data.address;
            var extraAddr = '';
            if (data.addressType === 'R') {
              if (data.bname !== '') {
                extraAddr += data.bname;
              }
              if (data.buildingName !== '') {
                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
              }
              fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
            }
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById('address1').value = fullAddr;
            $('#address2').focus();
            $('#myModal2').modal('hide');
            document.body.scrollTop = currentScroll;
          },
          onresize: function (size) {
            element_wrap.style.height = size.height + 'px';
          },
          width: '100%',
          height: '100%'
        }).embed(element_wrap);
        $('#myModal2').modal('show');
      }
    </script>

    <%@ include file="../footer.jsp" %>