<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="../header.jsp" %>

    <!-- contents start -->
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card mt-5">
            <div class="card-header">
              Login
            </div>
            <div class="card-body">
              <form>
                <div class="form-group">
                  <label for="email">이메일 주소</label>
                  <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                    placeholder="Enter email">
                </div>
                <div class="form-group">
                  <label for="password">비밀번호</label>
                  <input type="password" class="form-control" id="password" placeholder="Password">
                </div>
                <div class="mt-1">
                	<label><input type="checkbox" name="c_id" value="SAVE"> 아이디 저장</label>
                </div>
                <button type="submit" class="btn btn-primary btn-block">로그인하기</button>	
              </form>
            </div>
          </div>
          <div class="btn-group btn-group-sm d-flex justify-content-center mt-2 mb-5 ml-3 mr-3" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-outline-secondary">비밀번호 찾기</button>
            <button type="button" class="btn btn-outline-secondary">아이디 찾기</button>
            <button type="button" class="btn btn-outline-secondary">회원가입</button>
          </div>
        </div>
      </div>
    </div>
    <!-- contents end -->

    <%@ include file="../footer.jsp" %>