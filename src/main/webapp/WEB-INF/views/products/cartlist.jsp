<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <div class="container">
      <form name='frm' method='post' action='register'>
        <h1 class="text-left">Shopping Cart</h1>
        <div class="text-center">
          <div class="row justify-content-center">
            <div class="col-md-12">
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Image</th>
                    <th scope="col">Product Name</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Actions</th>
                  </tr>
                </thead>
                <tbody>
                
				<c:set var="sum" value="0"/>
				
                  <c:forEach var="cartItem" items="${cartItems}" varStatus="vs">
                  <input type="hidden" name="prdNo" value="${cartItem.prdNo}">
                  <input type="hidden" name="quantity" value="${cartItem.quantity}">
                    <c:set var="quan" value="${cartItem.quantity}" />
                    <c:set var="pri" value="${cartItem.price}" />
                    <c:set var="onetotal" value="${quan * pri}" />
                  
                    <tr>
                      <td class="align-middle"><img src="./../storage/${cartItem.prdImgFileName}" alt="Product 2" class="product-image"></td>
                      <td class="align-middle">${cartItem.prdName}</td>
                      <td class="align-middle text-center">
                        <div class="d-inline-block">
                          <input type="number" class="form-control text-center" id="quantity" name="quantity" min="1" value="${cartItem.quantity}" style="width: 70px;">
                        </div>
                      </td>
                      <td class="align-middle">₩<c:out value="${onetotal}"></c:out></td>
                      <c:set var="sum" value="${sum + onetotal}"/>
                      <td class="align-middle">
                        <button type="button" class="btn btn-danger btn-sm remove-button" onclick="removeProduct(this)">Remove</button>
                      </td>
                    </tr>                

                  </c:forEach>

                </tbody>
              </table>
            </div>
          </div>
        </div>


        <div class="row justify-content-end">
          <div class="col-md-6">
            <h3>Total Amount: <span id="total-amount">₩<c:out value="${sum}"></c:out></span></h3>
          </div>
          <div class="col-md-6 text-md-right pr-5">
            <button type="submit" class="btn btn-primary">Submit Cart</button>

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
      }
    </script>

    <%@ include file="../footer.jsp" %>