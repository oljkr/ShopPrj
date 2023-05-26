<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>
  

    <!-- contents start -->
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card mt-5 mb-5">
            <div class="card-header">
              주문 조회
            </div>
            <div class="card-body">
              <form name='orderSheetNofrm' method='post' action='getdetail'>
                <div class="form-group">
                  <label for="orderSheetNo">주문서 번호</label>
                  <input type="text" class="form-control" id="orderSheetNo" name="orderSheetNo" aria-describedby="orderSheetNoHelp"
                    placeholder="Enter Order Sheet Number">
                </div>
			    <div class="text-primary m-2" style="font-size: 15px; background-color: #ebedf0">${msg}</div>
                <button type="submit" class="btn btn-primary btn-block py-3 px-5">주문 조회</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- contents end -->

    <script src="./../js/utils.js"></script>

    <%@ include file="../footer.jsp" %>