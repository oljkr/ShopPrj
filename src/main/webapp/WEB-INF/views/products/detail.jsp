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
      <div class="col-lg-5 pr-5 mt-3">
        <h2>${product.name}</h2>
        <h5>${product.price} 원</h5>
        <p class="mt-4">${product.shortDes}</p>
        <h4 class="mb-3">Options</h4>
        <div class="form-group">
          <label for="color">Color:</label>
          <select class="form-control" id="color">
            <c:forEach var="coloroption" items="${color}" varStatus="status">
	            <option value="${coloroption}">${coloroption}</option>
            </c:forEach>
          </select>
        </div>
        <div class="form-group">
          <label for="size">Size:</label>
          <select class="form-control" id="size">
          	<c:forEach var="sizeoption" items="${size}" varStatus="status">
	            <option value="${sizeoption}">${sizeoption}</option>
            </c:forEach>
          </select>
        </div>
        <div class="form-group">
          <label for="quantity">Quantity:</label>
          <input type="number" class="form-control" id="quantity" min="1" value="1">
        </div>
        <div class="px-3">
          <button class="btn btn-outline-dark rounded-pill btn-block py-3">Add to Cart</button>
        </div>
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
              <img src="./../storage/${contentimagelist.fileName}">
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
  
  <script>
    $(document).ready(function() {
      // Scroll to description section when description button is clicked
      $("#descriptionBtn").click(function(e) {
        e.preventDefault();
        $('html, body').animate({
          scrollTop: $("#description").offset().top
        }, 800);
      });
      
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
        $('#imagePreview').attr('src', './../storage/kuromi2.png');
      });
      
      
    });

  </script>


<%@ include file="../footer.jsp" %>

