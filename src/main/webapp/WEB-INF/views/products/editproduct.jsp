<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="header.jsp" %>

    <!-- contents start -->
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-9">
          <div class="card mt-5 mb-5">
            <div class="card-header d-flex justify-content-between">
              <span style="font-size:20px">상품 수정</span>
            </div>
            <div class="card-body">
              <form role="form" method="post" action="edit" enctype="multipart/form-data">


                <div class="row">
                  <div class="form-group col-md-6">
                    <div class="row">
                      <label for="sort1" class="col-form-label col-md-3">1차 분류</label>
                      <div class="col-md-9">
                        <select class="form-control" id="sort1" name="sort1">
                          <option value="m">Men</option>
                          <option value="w">Women</option>
                          <option value="k">Kids</option>
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="form-group col-md-6">
                    <div class="row">
                      <label for="sort2" class="col-form-label col-md-3">2차 분류</label>
                      <div class="col-md-9">
                        <select class="form-control" id="sort2" name="sort2">
                          <option value="sho">신발</option>
                          <option value="clo">의류</option>
                          <option value="acc">용품</option>
                        </select>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-group col-md-12">
                  <div class="row">
                    <label for="name" class="col-md-2">상품명</label>
                    <div class="col-md-9">
                      <input type="text" class="form-control" id="name" name="name" />
                    </div>
                  </div>
                </div>

                <div class="form-group col-md-12">
                  <div class="row">
                    <label for="color" class="col-md-2">컬러</label>
                    <div class="col-md-9">
                      <input type="text" class="form-control" id="color" name="color" />
                      <small class="form-text text-muted">예) Black,White,Blue,Red</small>
                    </div>
                  </div>
                </div>

                <div class="form-group col-md-12">
                  <div class="row">
                    <label for="size" class="col-md-2">사이즈</label>
                    <div class="col-md-9">
                      <input type="text" class="form-control" id="size" name="size" />
                      <small class="form-text text-muted">예) 240,245,250,255</small>
                    </div>
                  </div>
                </div>


                <div class="row">
                  <div class="form-group col-md-6">
                    <div class="row">
                      <label for="price" class="col-form-label col-md-3">상품가격</label>
                      <div class="col-md-9">
                        <input type="text" class="form-control" id="price" name="price" />
                      </div>
                    </div>
                  </div>
                  <div class="form-group col-md-6">
                    <div class="row">
                      <label for="stock" class="col-form-label col-md-3">상품수량</label>
                      <div class="col-md-9">
                        <input type="text" class="form-control" id="stock" name="stock" />
                      </div>
                    </div>
                  </div>
                </div>




                <div class="form-group col-md-12">
                  <div class="row">
                    <label for="productImg" class="col-md-2">섬네일 이미지1</label>
                    <div class="col-md-9">
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" id="productImg1" name="thumbnailimg"
                          onchange="updateFileName(this)">
                        <label class="custom-file-label" for="productImg1">Choose file</label>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-group col-md-12">
                  <div class="row">
                    <label for="productImg" class="col-md-2">섬네일 이미지2</label>
                    <div class="col-md-9">
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" id="productImg2" name="thumbnailimg"
                          onchange="updateFileName(this)">
                        <label class="custom-file-label" for="productImg2">Choose file</label>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-group col-md-12">
                  <div class="row">
                    <label for="productImg" class="col-md-2">섬네일 이미지3</label>
                    <div class="col-md-9">
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" id="productImg3" name="thumbnailimg"
                          onchange="updateFileName(this)">
                        <label class="custom-file-label" for="productImg3">Choose file</label>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-group col-md-12">
                  <div class="row">
                    <label for="productImg" class="col-md-2">섬네일 이미지4</label>
                    <div class="col-md-9">
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" id="productImg4" name="thumbnailimg"
                          onchange="updateFileName(this)">
                        <label class="custom-file-label" for="productImg4">Choose file</label>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-group">
                  <label for="shortDes">간단 상품소개</label>
                  <textarea class="form-control" rows="5" cols="50" id="shortDes" name="shortDes"></textarea>
                </div>


                <div class="form-group col-md-12">
                  <div class="row">
                    <label for="productImg" class="col-md-2">본문 이미지</label>
                    <div class="col-md-9">
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" id="productImg5" name="contentimg"
                          onchange="updateFileName(this)">
                        <label class="custom-file-label" for="productImg5">Choose file</label>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-group">
                  <label for="fullDes">본문 상품소개</label>
                  <textarea class="form-control" rows="5" cols="50" id="fullDes" name="fullDes" ></textarea>
                </div>


                <div class="form-group row">
                  <div class="col-md-12 text-right">
                    <button type="submit" id="addproduct_Btn" class="btn btn-primary rounded-pill mr-2 py-2 px-5">등록</button>
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-secondary rounded-pill py-2 px-5">취소</a>
                  </div>
                </div>

              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- contents end -->

    <!--파일 업로드 관련 부트스트랩 함수-->
    <script>
      function updateFileName(input) {
        var fileName = input.value.split("\\").pop();
        var label = input.nextElementSibling;
        label.innerHTML = fileName;
      }
    </script>

    <!-- Ck Editor 관련 함수-->
    <script>
      let aa = ClassicEditor.builtinPlugins.map(plugin => plugin.pluginName);
      console.log(aa);

      ClassicEditor.create(document.querySelector('#fullDes'), {
        language: "ko"

      }).catch(error => {
        console.log(error);
      });
    </script>

    <%@ include file="../footer.jsp" %>