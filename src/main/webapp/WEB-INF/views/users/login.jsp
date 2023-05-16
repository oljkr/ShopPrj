<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>
  

    <!-- contents start -->
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card mt-5">
            <div class="card-header">
              Login
            </div>
            <div class="card-body">
              <form name='loginfrm' method='post' action='login'>
                <div class="form-group">
                  <input type="hidden" id="place" name="place" value="${place}" readonly="readonly">
                  <label for="userId">아이디</label>
                  <input type="text" class="form-control" id="userId" name="userId" aria-describedby="idHelp"
                    placeholder="Enter Id" value="${c_id}">
                </div>
                <div class="form-group">
                  <label for="userPw">비밀번호</label>
                  <input type="password" class="form-control" id="userPw" name="userPw" placeholder="Password">
                </div>
                <div class="mt-1">
                  <label><input type="checkbox" name="c_id" value="SAVE" ${checked}> 아이디 저장</label>
                </div>
			    <div class="text-primary m-2" style="font-size: 15px; background-color: #ebedf0">${msg}</div>
                <button type="submit" class="btn btn-primary btn-block">로그인하기</button>
              </form>
            </div>
          </div>
          <div class="btn-group btn-group-sm d-flex justify-content-center mt-2 mb-5 ml-3 mr-3" role="group"
            aria-label="Basic example">
            <button type="button" class="btn btn-outline-secondary"
              onclick="location.href = '${pageContext.request.contextPath}/users/findid' ">아이디 찾기</button>
            <button type="button" class="btn btn-outline-secondary"
              onclick="location.href = '${pageContext.request.contextPath}/users/findpw' ">비밀번호 찾기</button>
            <button type="button" class="btn btn-outline-secondary"
              onclick="location.href = '${pageContext.request.contextPath}/users/register' ">회원가입</button>
          </div>
        </div>
      </div>
    </div>
    <!-- contents end -->

    <script src="./../js/utils.js"></script>

    <%@ include file="../footer.jsp" %>