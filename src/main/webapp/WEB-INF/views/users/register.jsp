<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <!-- contents start -->
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-9">
          <div class="card mt-5 mb-5">
            <div class="card-header d-flex justify-content-between">
              <span>회원 등록</span><span class="text-danger">*는 필수 입력 사항입니다.</span>
            </div>
            <div class="card-body">
              <form name='frm' method='post' action='register'>
                <div class="form-group row">
                  <label for="id" class="col-md-2 col-form-label">*아이디</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="id 중복 확인 버튼을 눌러 입력"
                      required>
                  </div>
                  <div class="col-md-3">
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                      data-target="#idCheckModal">ID중복확인</button>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="password" class="col-md-2 col-form-label">*비밀번호</label>
                  <div class="col-md-4">
                    <input type="password" class="form-control" id="userPw" name="userPw" placeholder="Password"
                      required>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="password" class="col-md-2 col-form-label">*비밀번호 확인</label>
                  <div class="col-md-4">
                    <input type="password" class="form-control" id="userPwcheck" name="userPwcheck"
                      placeholder="Confirm Password" required>
                      <div id="pwcheck-message" class="text-danger"></div>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="name" class="col-md-2 col-form-label">*이름</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="Enter your name"
                      required>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="email" class="col-md-2 col-form-label">*이메일</label>
                  <div class="col-md-6">
                    <input type="email" class="form-control" id="userEmail" name="userEmail"
                      aria-describedby="emailHelp" placeholder="Enter your email" required>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="phone" class="col-md-2 col-form-label">핸드폰 번호</label>
                  <div class="col-md-6">
                    <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter your cell phone number">
                    <small class="form-text text-muted">예) 010-1234-4567</small>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="zipcode" class="col-md-2 col-form-label">우편번호</label>
                  <div class="col-md-4">
                    <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="주소 찾기 버튼을 눌러 입력" readonly>
                  </div>
                  <div class="col-md-3">
                    <input type="button" value="주소찾기" onclick="DaumPostcode()" class="btn btn-primary"
                      data-toggle="modal" data-target="#myModal2">
                  </div>

                </div>
                <div class="form-group row">
                  <label for="address1" class="col-md-2 col-form-label">주소</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="address1" name="address1">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="address2" class="col-md-2 col-form-label">나머지주소</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="address2" name="address2">
                  </div>
                </div>

                <div class="form-group row">
                  <div class="col-md-12 text-right">
                    <button type="submit" id="btn_register" class="btn btn-primary mr-2">가입하기</button>
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-secondary">취소</a>
                  </div>
                </div>


                <!-- Modal start -->
                <div class="modal fade" id="idCheckModal" tabindex="-1" role="dialog"
                  aria-labelledby="idCheckModalLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="idCheckModalLabel">ID 중복 확인</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">

                        <div class="container">
                          <form>
                            <div class="form-group row">
                              <label for="id" class="col-md-2 col-form-label">아이디:</label>
                              <div class="col-md-6">
                                <input type="text" id="checkedid" maxlength="10" autofocus class="form-control">
                              </div>
                              <div class="col-md-3">
                                <button type="button" id="btn_checkid" class="btn btn-primary btn-block">중복확인</button>
                              </div>
                            </div>
                            <div class="form-group row">
                              <div class="col-md-2"></div>
                              <div id="panel" style="display:none;" class="col-md-7"></div>
                              <div class="col-md-2"></div>
                            </div>
                          </form>
                        </div>

                      </div>
                      <div class="modal-footer">
                        <div id="panel2" style="display:none;"></div>
                        <button type="button" class="btn btn-primary" id="useId">사용하기</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- Modal end-->

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

    <!-- 아이디 중복확인 관련 함수 -->
    <script>
      $("#btn_checkid").click(function () {
        if ($("#checkedid").val() == "") {
          $("#panel").css("color", "red");
          result = "아이디를 입력해 주세요";
          $("#panel").html(result);
          $("#panel").show();
          $("#checkedid").focus();//커서 생성
        } else {
          $.post("idcheck", "checkedid=" + $("#checkedid").val(), responseProc, "text");
        }
      });

      function responseProc(result) {
        if (result == 0) {
          $("#panel").css("color", "green");
          result = "사용 가능한 아이디 입니다";
          $.cookie("checkID", "PASS");//아이디 중복확인을 했다는 증거
          $("#panel2").empty();
        } else {
          $("#panel").css("color", "red");
          result = "중복된 아이디 입니다";
          $.removeCookie('checkID');
        }
        $("#panel").empty();
        $("#panel").html(result);
        $("#panel").show();
      }

      //모달창을 닫으면서 폼양식에 값 전달하기
      $('#useId').click(function () {
        if ($.cookie('checkID') == 'PASS') {
          //아이디 중복확인 쿠키가 있으면 값 전달하기
          var enteredId = $('#checkedid').val();
          $('#userId').val(enteredId).val();

          // close the modal window
          $('#idCheckModal').modal('hide');
        } else {
          //아이디 중복확인 쿠키가 없으면 알림 문구 전달
          $("#panel2").css("color", "red");
          $("#panel2").html("아이디 중복확인을 해 주세요");
          $("#panel2").show();
          $("#checkedid").focus();//커서 생성
        }

      });
    </script>

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
    
    <!-- 아이디 입력을 readonly로 바꾸는 함수 -->
    <script>
    $("#userId").on('keydown paste focus mousedown', function(e){
        if(e.keyCode != 9) // ignore tab
            e.preventDefault();
    });
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