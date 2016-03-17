<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/common/js/join.js"></script>
<title>MSG</title>
<style type="text/css">
#confirm_msg_ok {
	color: #3355ff;
	margin:0;
}

#confirm_msg_false {
	color: #ff0000;
	margin:0;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">

				<div class="page-header">
					<h2 id="container">회원 가입하기</h2>
				</div>

				<div class="well">
					<p>회원가입을 위해 아래 내용들을 작성해 주세요.</p>

					<form class="form-horizontal" action="${ctx}/user/signup"
						method="post">
						<fieldset>
							<div class="form-group">

								<label class="col-lg-2 control-label">아이디</label>
								<div class="col-lg-4">
									<input type="text" name="userId" class="form-control" id="userIdInput"
										placeholder="아이디" required value="${user.userId}"/>
								</div> <label id="confirm_msg_ok">가입할 수 있는 아이디입니다.</label>
								<label id="confirm_msg_false">이미 존재하는 회원입니다.</label>
								<br>
								<br> <label class="col-lg-2 control-label">비밀번호</label>
								<div class="col-lg-4">
									<input type="password" name="password" class="form-control"
										placeholder="비밀번호" required value="${user.password}"/>
								</div>
								<br>
								<br> <label class="col-lg-2 control-label">비밀번호 확인</label>
								<!--TODO: 확인란에 입력이 끝나고 포커스가 벗어나면 자동으로 위의 비밀번호 입력과 비교해서 메시지 출력하는 스크립트 작성-->
								<div class="col-lg-4">
									<input type="password" class="form-control"
										placeholder="비밀번호 확인" required value="${user.password}"/>
								</div>
								<br>
								<br> <label class="col-lg-2 control-label">이름</label>
								<div class="col-lg-4">
									<input type="text" name="name" class="form-control"
										placeholder="이름" required value="${user.name}"/>
								</div>
								<br>
								<br> <label class="col-lg-2 control-label">이메일</label>
								<div class="col-lg-4">
									<input type="text" name="email" class="form-control"
										placeholder="이메일" required value="${user.email}"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">

									<button type="submit" class="btn btn-primary" id="submitBtn">가입</button>
									<!-- <button class="btn btn-default">다시입력</button> -->
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<hr>

		<%@include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- /container -->

</body>
</html>