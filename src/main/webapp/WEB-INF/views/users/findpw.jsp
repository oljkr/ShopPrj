<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>
  

    <!-- contents start -->
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6 mb-5">
          <div class="card mt-5">
            <div class="card-header">
              비밀번호 찾기
            </div>
            <div class="card-body">
              <form name='findidfrm' method='post' action='findpw'>
                <div class="form-group">
                  <label for="userId">아이디</label>
                  <input type="text" class="form-control" id="userId" name="userId" aria-describedby="nameHelp"
                    placeholder="Enter Id">
                </div>
                <div class="form-group">
                  <label for="email">이메일 주소</label>
                  <input type="email" class="form-control" id="email" name="userEmail" aria-describedby="emailHelp"
                    placeholder="Enter email">
                </div>
			    <div class="text-danger m-2" style="font-size: 15px; background-color: #ebedf0">${msg}</div>
			    <div class="text-primary m-2" style="font-size: 15px; background-color: #ebedf0">비밀번호 찾기를 누르시면 등록된 이메일로 임시 비밀번호가 발급됩니다.</div>
                <button type="submit" class="btn btn-primary btn-block">비밀번호 찾기</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- contents end -->

    <script src="./../js/utils.js"></script>

    <%@ include file="../footer.jsp" %>