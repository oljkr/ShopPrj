<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <div class="container my-5">
      <div class="mb-4">
        <h4>${headPhrase}</h4>
      </div>
      <div class="row" id="product-container">

        <c:forEach var="product" items="${list}" varStatus="vs">
          <div class="col-md-4">
            <div class="product">
              <a href="${pageContext.request.contextPath}/products/detail?prdNo=${product.prdNo}">
                <img src="./../storage/${imgList[vs.index].fileName}" alt="Product 1">
              </a>
              <h4 class="product-name">${product.name}</h4>
              <p class="product-price">${product.price} 원</p>
            </div>
          </div>
        </c:forEach>


        <!-- Add more col-md-4 divs for other products -->
      </div>
    </div>

    <script>
      var flag = true;
      var cnt = 7;
      var rest = 0;
      // Function to check if the user has reached the bottom of the page
      function isBottomOfPage() {
        return (window.innerHeight + window.scrollY) >= document.body.offsetHeight;
      }

      // Function to dynamically add more product divs
      function addMoreProducts(data) {
        if (flag) {
          var productContainer = document.getElementById('product-container');

          var level = Math.floor(${ productCount } / 6);

          var productList = data.product;
          var imgList = data.img;

          if (Math.floor(cnt / 6) == level) {
            rest = ${ productCount }% 6;
            //alert(rest);
            var temp = 0;
            for (var i = cnt; i < cnt + rest; i++) { // Example: Adding 6 more products
              var colDiv = document.createElement('div');
              colDiv.className = 'col-md-4';
              var productDiv = document.createElement('div');
              productDiv.className = 'product';

              var img = document.createElement('img');
              img.src = "./../storage/"+imgList[temp].fileName;
              img.alt = 'Product ' + i;
              img.setAttribute('data-link', '${pageContext.request.contextPath}/products/detail?prdNo=' + productList[temp].prdNo);

              // Attach a link to the image
              var link = document.createElement('a');
              link.href = '${pageContext.request.contextPath}/products/detail?prdNo=' + productList[temp].prdNo;

              var productName = document.createElement('h4');
              productName.className = 'product-name';
              productName.textContent = productList[temp].name;

              var productPrice = document.createElement('p');
              productPrice.className = 'product-price';
              productPrice.textContent = productList[temp].price+" 원";

              link.appendChild(img);
              productDiv.appendChild(link);
              productDiv.appendChild(productName);
              productDiv.appendChild(productPrice);
              colDiv.appendChild(productDiv);
              productContainer.appendChild(colDiv);

              temp++;
            }
          } else {
            // Add more col-md-4 divs for other products
            for (var i = 4; i <= 8; i++) { // Example: Adding 6 more products
              var colDiv = document.createElement('div');
              colDiv.className = 'col-md-4';
              var productDiv = document.createElement('div');
              productDiv.className = 'product';

              var img = document.createElement('img');
              img.src = 'product' + i + '.jpg';
              img.alt = 'Product ' + i;

              var productName = document.createElement('h4');
              productName.className = 'product-name';
              productName.textContent = 'Product ' + i;

              var productPrice = document.createElement('p');
              productPrice.className = 'product-price';
              productPrice.textContent = '$' + (10 + i) + '.99';

              productDiv.appendChild(img);
              productDiv.appendChild(productName);
              productDiv.appendChild(productPrice);
              colDiv.appendChild(productDiv);
              productContainer.appendChild(colDiv);


            }
            cnt += 7;
          }

          // Check if the desired number of divs have been added and remove the event listener
          if (productContainer.children.length == ${ productCount }) {
            flag = false;
            window.removeEventListener('scroll', scrollHandler);
          }

        }
      }

      // Event handler for scroll event
      function scrollHandler() {
        if (isBottomOfPage()) {
          $.ajax({
            url: "getlist"
            , type: "post"
            , data: { "cnt": cnt, sort1: "m", sort2: "sho" }
            , dataType: "json"
            , error: function (request, status, error) {
              alert("에러:" + error);
              alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }//error callback함수
            , success: addMoreProducts//success callback함수
          });

          addMoreProducts(data);

        }
      }

      // Add the scroll event listener
      window.addEventListener('scroll', scrollHandler);

    </script>


    <%@ include file="../footer.jsp" %>