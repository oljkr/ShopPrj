<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>
  

    <!-- contents start -->
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6 mb-5">
          <div class="card mt-5">
            <div class="card-header">
              아이디 찾기
            </div>
            <div class="card-body">
              <form name='findidfrm' method='post' action='findid'>
                <div class="form-group">
                  <label for="userName">이름</label>
                  <input type="text" class="form-control" id="userName" name="userName" aria-describedby="nameHelp"
                    placeholder="Enter Name">
                </div>
                <div class="form-group">
                  <label for="email">이메일 주소</label>
                  <input type="email" class="form-control" id="email" name="userEmail" aria-describedby="emailHelp"
                    placeholder="Enter email">
                </div>
			    <div class="text-primary m-2" style="font-size: 15px; background-color: #ebedf0">${msg}</div>
                <button type="submit" class="btn btn-primary btn-block">아이디 찾기</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- contents end -->

    <script src="./../js/utils.js"></script>

    <%@ include file="../footer.jsp" %>