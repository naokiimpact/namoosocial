<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/common/js/info.js"></script>
<title>MSG</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row" id="infoForm">
			<div class="col-lg-12">

				<div class="page-header">
					<h2 id="container">회원 정보 수정</h2>
				</div>

				<div class="well">
					<p>변경할 정보를 수정한 후 '확인' 버튼을 클릭해주세요.</p>
					<form class="form-horizontal" method="post">
						<fieldset>
							<div class="form-group">

								<label class="col-lg-2 control-label">아이디</label>
								<div class="col-lg-10">
									<input type="text" name="userId" class="form-control"
										placeholder="아이디" readonly value="${user.userId}" />
									<!-- <input type="button" value="중복확인" /> -->
								</div>
								<label class="col-lg-2 control-label">비밀번호</label>
								<div class="col-lg-10">
									<input type="password" name="password" class="form-control"
										placeholder="비밀번호" required value="${user.password}" />
								</div>
								<label class="col-lg-2 control-label">이름</label>
								<div class="col-lg-10">
									<input type="text" name="name" class="form-control"
										placeholder="이름" required value="${user.name}" />
								</div>
								<label class="col-lg-2 control-label">이메일</label>
								<div class="col-lg-10">
									<input type="text" name="email" class="form-control"
										placeholder="이메일" required value="${user.email}" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">

									<button type="button" class="btn btn-primary" id="modifyBtn">확인</button>
									<!-- <button class="btn btn-default">다시입력</button> -->
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div id="loadingIcon">
				<img src="${ctx}/resources/common/img/ajax-loader.gif" />
			</div>
			<div id="infoMsg">회원정보가 수정되었습니다.</div>
		</div>
		<hr>

		<%@include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- /container -->

</body>
</html>