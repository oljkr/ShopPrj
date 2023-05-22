<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

  <div class="container">
    <div class="row mt-5">
      <div class="col-lg-1">
        <c:forEach var="thumbnailimagelist" items="${upperImages}" varStatus="status">
          <div class="square mb-3" style="background-image: url(./../storage/${thumbnailimagelist.fileName}); background-size: cover;" data-image="./../storage/${thumbnailimagelist.fileName}"></div>
        </c:forEach>
        
      </div>
      <div class="col-lg-6">
        <div class="big-square bg-light">
          <img id="imagePreview" src="./../storage/${upperImages[0].fileName}" alt="Image Preview" class="img-fluid">
        </div>
      </div>
      <div class="col-lg-5 pr-5">

        <c:catch>
          <c:choose>
            <c:when test="${authInfo.roles eq 'admin' }">

              <div class="d-flex justify-content-end align-items-center">
                <a href="${pageContext.request.contextPath}/products/edit?prdNo=${product.prdNo}" class="btn btn-outline-dark rounded-pill mr-2">상품 수정</a>
                <a href="${pageContext.request.contextPath}/products/predelete?prdNo=${product.prdNo}" class="btn btn-outline-danger rounded-pill">상품 삭제</a>
              </div>

            </c:when>
          </c:choose>
        </c:catch>
        
        <div class="mt-2">
          <h2>${product.name}</h2>
        </div>
        <h5>${product.price} 원</h5>
        <p class="mt-4">${product.shortDes}</p>

        <form name='cartfrm' id="cartfrm" method='post'>
          <input type="hidden" name="prdNo" value="${product.prdNo}">
          <input type="hidden" name="prdName" value="${product.name}">
          <input type="hidden" name="prdImgFileName" value="${upperImages[0].fileName}">
          <input type="hidden" name="price" value="${product.price}">

          <h4 class="mb-3">Options</h4>
          <div class="form-group">
            <label for="color">Color:</label>
            <select class="form-control" id="color" name="color">
              <c:forEach var="coloroption" items="${color}" varStatus="status">
                <option value="${coloroption}">${coloroption}</option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label for="size">Size:</label>
            <select class="form-control" id="size" name="size">
              <c:forEach var="sizeoption" items="${size}" varStatus="status">
                <option value="${sizeoption}">${sizeoption}</option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input type="number" class="form-control" id="quantity" name="quantity" min="1" value="1">
          </div>
          <div class="px-3">
            <button class="btn btn-outline-dark rounded-pill btn-block py-3" onclick="addCart();openModal(); return false;">Add to Cart</button>
          </div>
        </form>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-lg-12">
        <div class="text-center">
          <a href="#" class="btn btn-outline-dark rounded-pill px-5 py-3 mx-2" id="descriptionBtn">상품 설명</a>
          <a href="#" class="btn btn-outline-dark rounded-pill px-5 py-3 mx-2" id="reviewsBtn">리뷰</a>
          <hr>
          
          <div id="description" class="mt-4">
          <div id="contentImages" class="mb-5">
            <c:forEach var="contentimagelist" items="${lowerImages}" varStatus="status">
              <img src="./../storage/${contentimagelist.fileName}" style="max-width: 100%;">
            </c:forEach>
          </div>
            ${product.fullDes}
          </div>
          <div id="reviews" class="mt-4">
            world<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
          </div>
        </div>

      </div>
    </div>
  </div>
  
  <button id="scrollToTop" class="btn btn-light" title="Scroll to Top">
    <i class="fa fa-arrow-up"></i>
  </button>


  <!-- Modal window HTML -->
<div id="modal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">장바구니 담기</h5>
        <button type="button" class="close" onclick="closeModal()">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body d-flex align-items-center">
        <p class="m-auto">장바구니에 상품이 정상적으로 담겼습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-dark rounded-pill" onclick="closeModal()">쇼핑 계속하기</button>
        <button type="button" class="btn btn-outline-primary rounded-pill" onclick="goToLink()">장바구니 이동</button>
      </div>
    </div>
  </div>
</div>
  
  <script>
    $(document).ready(function() {
      // Scroll to description section when description button is clicked
      $("#descriptionBtn").click(function(e) {
        e.preventDefault();
        $('html, body').animate({
          scrollTop: $("#description").offset().top
        }, 800);
      });
   // Scroll to review section when description button is clicked
      $("#reviewsBtn").click(function(e) {
          e.preventDefault();
          $('html, body').animate({
            scrollTop: $("#reviews").offset().top
          }, 800);
        });
      
      
   // Show or hide the scroll-to-top button based on scroll position
      $(window).scroll(function() {
        if ($(this).scrollTop() > 200) {
          $('#scrollToTop').fadeIn();
        } else {
          $('#scrollToTop').fadeOut();
        }
      });

      // Scroll to top when the button is clicked
      $('#scrollToTop').click(function() {
        $('html, body').animate({ scrollTop: 0 }, 800);
        return false;
      });


      // Show image on hover
      $('.square').hover(function() {
        var image = $(this).data('image');
        $('#imagePreview').attr('src', image);
      }, function() {
        //아래는 hover뒤에 기본으로 바뀔 이미지
        $('#imagePreview').attr('src', './../storage/${upperImages[0].fileName}');
      });
      
      
    });

  </script>
  <script>
    function addCart(){
        var formData = $("#cartfrm").serialize();

        $.ajax({
            cache : false,
            url : "${pageContext.request.contextPath}/cart/add",
            type : 'POST', 
            data : formData, 
            success : function(data) {
                checkCartCookie();
                getSetCartCnt();
                modalCart(data);
            }, // success 
    
            error : function(xhr, status) {
                alert(xhr + " : " + status);
            }
        }); // $.ajax */

        function modalCart(){
          openModal();
        }
    }

    function getSetCartCnt(){
      $.ajax({
            cache : false,
            url : "${pageContext.request.contextPath}/cart/get",
            type : 'get', 
            success : responseProc,
            error : function(xhr, status) {
                alert(xhr + " : " + status);
            }
        }); // $.ajax */

        function responseProc(data){
          var len1=data.length;
          checkCartCookie();
          setCartCookie(data.items.length, 30);  // Sets the 'cartCnt' cookie value to 10, expiring in 30 days
          modifyCartIconNumber(data);
        }

        function modifyCartIconNumber(data){
          // document.getElementById("cartIconNumber");
          const divNode = document.getElementById("cartIconNumber");
          var cartCookie = document.cookie.match(/(^|;) ?cartCnt=([^;]*)(;|$)/);
          var cartCnt = cartCookie[2];
          divNode.innerHTML = cartCnt;
        }
    }

    function checkCartCookie(){
      // Check if the 'cartCnt' cookie exists
      var cartCookie = document.cookie.match(/(^|;) ?cartCnt=([^;]*)(;|$)/);

      if (cartCookie) {
        // Cookie exists, get the value
        var cartCnt = cartCookie[2];
      } else {
        // Cookie does not exist, create a new one
        var cartCnt = 0; // Set initial value
        document.cookie = "cartCnt=" + cartCnt + "; expires=Thu, 31 Dec 2099 23:59:59 UTC; path=/";
      }
    }

    function setCartCookie(value, expirationDays) {
      var date = new Date();
      date.setTime(date.getTime() + (expirationDays * 24 * 60 * 60 * 1000));
      var expires = "expires=" + date.toUTCString();
      document.cookie = "cartCnt=" + value + "; " + expires + "; path=/";
    }
    

	</script>
  <script>
    function openModal() {
      var modal = document.getElementById('modal');
      modal.style.display = 'block';
    }
  
    function closeModal() {
      var modal = document.getElementById('modal');
      modal.style.display = 'none';
    }
  
    function goToLink() {
      window.location.href = '${pageContext.request.contextPath}/cart/get'; // Replace with your desired link
    }
  </script>


<%@ include file="../footer.jsp" %>

