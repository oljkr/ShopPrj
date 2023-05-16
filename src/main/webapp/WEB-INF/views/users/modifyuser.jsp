<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <!-- contents start -->
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-9">
          <div class="card mt-5 mb-5">
            <div class="card-header d-flex justify-content-between">
              <span>회원 정보 수정</span><span class="text-danger">*는 필수 입력 사항입니다.</span>
            </div>
            <div class="card-body">
              <form name='modifyfrm' method='post' action='modifyuser'>
              	<div>
                  <div>
                    <input type="hidden" id="userNo" name="userNo" value="${authInfo.userNo}" readonly="readonly">
                    <input type="hidden" id="roles" name="roles" value="${authInfo.roles}" readonly="readonly">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="id" class="col-md-2 col-form-label">*아이디</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" id="userId" name="userId"
                      value="${authInfo.userId}" readonly>
                  </div>
                  <div class="col-md-4 align-self-center">
                    <span class="text-danger">*아이디는 변경 불가능합니다.</span>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="password" class="col-md-2 col-form-label">*비밀번호</label>
                  <div class="col-md-4">
                    <input type="password" class="form-control" id="userPw" name="userPw"
                      value="${authInfo.userPw}" required>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="password" class="col-md-2 col-form-label">*비밀번호 확인</label>
                  <div class="col-md-4">
                    <input type="password" class="form-control" id="userPwcheck" name="userPwcheck"
                      value="${authInfo.userPw}" required>
                      <div id="pwcheck-message" class="text-danger"></div>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="name" class="col-md-2 col-form-label">*이름</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" id="userName" name="userName"
                      value="${authInfo.userName}" required>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="email" class="col-md-2 col-form-label">*이메일</label>
                  <div class="col-md-6">
                    <input type="email" class="form-control" id="userEmail" name="userEmail"
                      value="${authInfo.userEmail}" aria-describedby="emailHelp" required>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="zipcode" class="col-md-2 col-form-label">우편번호</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" id="zipcode" name="zipcode" value="${authInfo.zipcode}" readonly>
                  </div>
                  <div class="col-md-3">
                    <input type="button" value="주소찾기" onclick="DaumPostcode()" class="btn btn-primary"
                      data-toggle="modal" data-target="#myModal2">
                  </div>

                </div>
                <div class="form-group row">
                  <label for="address1" class="col-md-2 col-form-label">주소</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="address1" name="address1" value="${authInfo.address1}">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="address2" class="col-md-2 col-form-label">나머지주소</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="address2" name="address2" value="${authInfo.address2}">
                  </div>
                </div>

                <div class="form-group row">
                  <div class="col-md-12 text-right">
                    <button type="submit" id="btn_modify" class="btn btn-primary mr-2">회원 정보 수정하기</button>
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-secondary">취소</a>
                  </div>
                </div>

                <!-- PostCode Modal -->
                <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                  aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="myModalLabel">Daum Postcode</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <!-- Embed the Daum Postcode widget here -->
                        <div id="wrap"></div>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- Modal -->

              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- contents end -->

    <!-- 우편번호 입력 관련 함수 -->
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script>
      var element_wrap = document.getElementById('wrap');
      function DaumPostcode() {
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
          oncomplete: function (data) {
            var fullAddr = data.address;
            var extraAddr = '';
            if (data.addressType === 'R') {
              if (data.bname !== '') {
                extraAddr += data.bname;
              }
              if (data.buildingName !== '') {
                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
              }
              fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
            }
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById('address1').value = fullAddr;
            $('#address2').focus();
            $('#myModal2').modal('hide');
            document.body.scrollTop = currentScroll;
          },
          onresize: function (size) {
            element_wrap.style.height = size.height + 'px';
          },
          width: '100%',
          height: '100%'
        }).embed(element_wrap);
        $('#myModal2').modal('show');
      }
    </script>
	
	<!-- 비밀번호-비밀번호 확인 일치 여부 함수 -->
	<script>
	$(document).ready(function() {
	  $('#userPwcheck').keyup(function() {
	    var pw = $('#userPw').val();
	    var pwcheck = $('#userPwcheck').val();
	    if (pw !== pwcheck) {
	      $('#pwcheck-message').text('비밀번호가 일치하지 않습니다');
	    } else {
	      $('#pwcheck-message').text('');
	    }
	  });
	});
	</script>

    <%@ include file="../footer.jsp" %>