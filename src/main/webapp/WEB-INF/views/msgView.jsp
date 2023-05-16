<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <!-- 404 Start -->
    <div class="container-fluid pt-4 px-4">
      <div class="row vh-100 rounded mt-5 justify-content-center mx-0">
        <div class="col-md-6 text-center p-4 mt-5">
          ${msg1 != null? msg1 : img}
          ${msg2}
          ${msg3 != null? img : ""} ${msg3}
          ${link1} ${link2} ${link3}
        </div>
      </div>
    </div>
    <!-- 404 End -->


  <%@ include file="footer.jsp" %>