<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <div class="container">
      <form name='frm' method='post' action='register'>
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
                    <input type="hidden" name="prdNo" value="${cartItem.prdNo}">
                    <input type="hidden" name="quantity" value="${cartItem.quantity}">
                    <c:set var="quan" value="${cartItem.quantity}" />
                    <c:set var="pri" value="${cartItem.price}" />
                    <c:set var="onetotal" value="${quan * pri}" />

                    <tr>
                      <td class="align-middle"><img src="./../storage/${cartItem.prdImgFileName}" alt="Product 2"
                          class="product-image"></td>
                      <td class="text-left align-middle">
                        <div>${cartItem.prdName}</div>
                        <div>[&nbsp;옵션&nbsp;:&nbsp;&nbsp;${cartItem.color}&nbsp;/&nbsp;${cartItem.size}&nbsp;]</div>
                      </td>
                      <td class="align-middle text-center">
                        <div class="d-inline-block">
                          <input type="number" class="form-control text-center" id="quantity" name="quantity" min="1"
                            value="${cartItem.quantity}" style="width: 70px;" onchange="updatePrice(this)">
                        </div>
                      </td>
                      <td class="align-middle helloo" id="onetotal${vs.count}">₩<c:out value="${onetotal}"></c:out>
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
          <div class="col-md-6 text-md-center pr-5">
            <a href="${pageContext.request.contextPath}/home"
                      class="btn btn-outline-dark rounded-pill py-3 px-5">계속 쇼핑하기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="submit" class="btn btn-dark rounded-pill py-3 px-5" style="width: 190px;">구매하기</button>
          </div>
        </div>


      </form>
    </div>

    <script>
      function removeProduct(button) {
        var row = button.parentNode.parentNode;
        var price = row.querySelector('td:nth-child(4)').innerText;
        var totalAmountElement = document.getElementById('total-amount');
        var currentAmount = parseInt(totalAmountElement.innerText.slice(1));
        var productPrice = parseInt(price.slice(1));

        // Update the total amount by subtracting the product price
        var newAmount = currentAmount - productPrice;
        totalAmountElement.innerText = '₩' + newAmount.toFixed();

        // Remove the row from the table
        row.remove();

        var orderAmount = newAmount + 2500;
        document.getElementById('order-amount').innerText = '₩' + orderAmount.toFixed();

      }
    </script>

    <script>
      function updatePrice(input) {
        var row = input.parentNode.parentNode.parentNode;
        var price = row.querySelector('td:nth-child(4)').innerText;
        var productPrice = parseInt(price.slice(1));
        var quantity = input.value;
        // var price = 40000; // Replace with the actual price calculation
        var productTotal = quantity * productPrice;
        row.querySelector('td:nth-child(4)').innerText='₩' + productTotal.toFixed();
        calculateTotal();
      }
    </script>
    <script>
      // Function to calculate the sum of values
      function calculateTotal() {
        let sum = 0;

        // Get all elements with class "helloo"
        const elements = document.getElementsByClassName("helloo");

        // Iterate over the elements and calculate the sum
        for (let i = 0; i < elements.length; i++) {
          const element = elements[i];
          var cc = element.innerText.slice(1);
          
          alert(cc);
          var bb = parseInt(cc);
          const value = parseInt(bb);
          
          // Add the value to the sum
          sum += value;
        }

        return sum;
      }

      // Example usage to display the sum
      const totalSum = calculateTotal();
      console.log("Total sum:", totalSum);
    </script>

    

    <%@ include file="../footer.jsp" %>