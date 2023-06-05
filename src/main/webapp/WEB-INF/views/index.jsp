<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!-- contents start -->
<div id="slidingPictures" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#slidingPictures" data-slide-to="0" class="active"></li>
    <li data-target="#slidingPictures" data-slide-to="1"></li>
    <li data-target="#slidingPictures" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <a href="${pageContext.request.contextPath}/products/list?sort1=m&sort2=sho">
        <img src="./storage/index1.jpg" class="d-block w-100" alt="Image 1">
      </a>
    </div>
    <div class="carousel-item">
      <a href="${pageContext.request.contextPath}/products/list?sort1=m&sort2=sho">
        <img src="./storage/index2.jpg" class="d-block w-100" alt="Image 2">
      </a>
    </div>
    <div class="carousel-item">
      <a href="${pageContext.request.contextPath}/products/list?sort1=m&sort2=sho">
        <img src="./storage/index3.jpg" class="d-block w-100" alt="Image 3">
      </a>
    </div>
  </div>
</div>

<script>
  $(document).ready(function() {
    $('#slidingPictures').carousel({
      interval: 3000 // Adjust the interval (in milliseconds) between slides
    });
  });
</script>

<!-- contents end -->

<%@ include file="footer.jsp" %>

