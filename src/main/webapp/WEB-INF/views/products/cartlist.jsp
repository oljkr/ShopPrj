<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <div class="container">
      <h1 class="text-center mt-5">Shopping Cart</h1>
        <div class="text-center">
          <div class="row justify-content-center mt-4">
            <div class="col-md-12">
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col" colspan="2">Product Info</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Actions</th>
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
                        <td class="align-middle"><img src="./../storage/${cartItem.prdImgFileName}" alt="Product 2"
                          class="product-image"></td>
                        <td class="text-left align-middle">
                          <div>${cartItem.prdName}</div>
                          <div>[&nbsp;옵션&nbsp;:&nbsp;&nbsp;${cartItem.color}&nbsp;/&nbsp;${cartItem.size}&nbsp;]</div>
                        </td>
                        <td class="align-middle text-center">
                          <div class="d-inline-block">
                            <div class="text-center">
                              <button type="button" class="btn btn-outline-dark btn-sm btn-number" onclick="checkIndex(${vs.count});decrementQuantity(${vs.count})">-</button>
                              &nbsp;&nbsp;&nbsp;&nbsp;<span id="thisquantity${vs.count}">${cartItem.quantity}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                              <button type="button" class="btn btn-outline-dark btn-sm btn-number" onclick="checkIndex(${vs.count});incrementQuantity(${vs.count})">+</button>
                            </div>
                          </div>
                        </td>
                        <td class="align-middle" id="onetotal${vs.count}">₩ <c:out value="${onetotal}"></c:out>
                        </td>
                        <c:set var="sum" value="${sum + onetotal}" />
                        <td class="align-middle">
                          <button type="button" class="btn btn-danger btn-sm remove-button"
                            onclick="removeProduct(this)">Remove</button>
                        </td>
                      </tr>         

                  </c:forEach>

                </tbody>
              </table>
            </div>
          </div>
        </div>

        <c:set var="ordersum" value="${sum+2500}" />

        <div class="row justify-content-end">
          <div class="col-md-12 mb-5">
            <div class="total-amount-container">
              <div class="total-amount-box">
                <div>총 상품가격&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <span class="colored-text" id="total-amount">₩ &nbsp;<c:out value="${sum}"></c:out></span>
                  &nbsp;&nbsp;&nbsp;&nbsp;+&nbsp;&nbsp;&nbsp;&nbsp;총 배송비&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <span class="colored-text">₩ &nbsp;2500</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;=&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;총 주문금액&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <span class="colored-text" id="order-amount">₩ &nbsp;<c:out value="${ordersum}"></c:out></span>
                </div>
              </div>
            </div>
          </div>
        </div>


        <div class="row justify-content-center mb-5">
          <div class="col-md-6 text-md-center">
            <a href="${pageContext.request.contextPath}/home"
                      class="btn btn-outline-dark rounded-pill py-3 px-5">계속 쇼핑하기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="submit" class="btn btn-dark rounded-pill py-3 px-5" style="width: 190px;">구매하기</button>
          </div>
        </div>

    </div>

    <script>
      var index=0;
      function checkIndex(thisindex){
        index=thisindex;
      }

      function incrementQuantity(cnt){
        var thisquantity = document.getElementById('thisquantity'+cnt);
        var beforequantity = parseInt(thisquantity.innerText);
        var afterquantity = beforequantity+1;
        alert("after"+afterquantity);

        var getprdNo = document.getElementById('prdNo'+index);
        var prdNoText = getprdNo.innerText;
        alert(prdNoText);

        var formData = [];
        formData.push({ name: "prdNo", value: prdNoText });
        formData.push({ name: "quantity", value: afterquantity });

        var jsonData = {};
        $.each(formData, function(index, field) {
          jsonData[field.name] = field.value;
        });

        $.ajax({
          cache: false,
          url: "${pageContext.request.contextPath}/cart/update",
          type: 'POST',
          data: jsonData,
          success: function(data) {
            alert("hello");
            location.reload();
            // Handle success response
          },
          error: function(xhr, status) {
            alert(xhr + " : " + status);
          }
        });
      }

      function decrementQuantity(cnt){
        var thisquantity = document.getElementById('thisquantity'+cnt);
        var beforequantity = parseInt(thisquantity.innerText);
        var afterquantity = beforequantity-1;
        alert("after"+afterquantity);

        var getprdNo = document.getElementById('prdNo'+index);
        var prdNoText = getprdNo.innerText;
        alert(prdNoText);

        var formData = [];
        formData.push({ name: "prdNo", value: prdNoText });
        formData.push({ name: "quantity", value: afterquantity });

        var jsonData = {};
        $.each(formData, function(index, field) {
          jsonData[field.name] = field.value;
        });

        $.ajax({
          cache: false,
          url: "${pageContext.request.contextPath}/cart/update",
          type: 'POST',
          data: jsonData,
          success: function(data) {
            alert("hello");
            location.reload();
            // Handle success response
          },
          error: function(xhr, status) {
            alert(xhr + " : " + status);
          }
        });
      }
    </script>
    
    <script>
      function removeProduct(button) {
        var row = button.parentNode.parentNode;
        var price = row.querySelector('td:nth-child(3)').innerText;
        var totalAmountElement = document.getElementById('total-amount');
        var currentAmount = parseFloat(totalAmountElement.innerText.slice(1));
        var productPrice = parseFloat(price.slice(1));

        // Update the total amount by subtracting the product price
        var newAmount = currentAmount - productPrice;
        totalAmountElement.innerText = '$' + newAmount.toFixed(2);

        // Remove the row from the table
        row.remove();
      }
    </script>

    <%@ include file="../footer.jsp" %>