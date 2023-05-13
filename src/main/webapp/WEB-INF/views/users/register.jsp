<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<!-- contents start -->
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card mt-5 mb-5">
          <div class="card-header">
            회원 등록
          </div>
          <div class="card-body">
            <form name='frm' method='post' action='register'>
              <div class="form-group">
                <label for="name">이름</label>
                <input type="text" class="form-control" id="name" name="userName" placeholder="Enter your name">
              </div>
              <div class="form-group">
                <label for="email">이메일 주소</label>
                <input type="email" class="form-control" id="email" name="userEmail" aria-describedby="emailHelp" placeholder="Enter email">
              </div>
              <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" class="form-control" id="password" name="userPw" placeholder="Password">
              </div>
              <div class="form-group">
                <label for="name">주소</label>
                <input type="text" class="form-control" id="address" name="address" placeholder="Enter your address">
              </div>
              <button type="submit" class="btn btn-primary">가입하기</button>
              <button type="reset" class="btn btn-outline-secondary">취소</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
<!-- contents end -->

<%@ include file="../footer.jsp" %>

