<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<title>MSG</title>
<style type="text/css">
.mainDiv {
	left:15%
}

.titleH1 {
	margin-top:32%;
	margin-left:10%;
}

#left_well {
	min-height:350px;
    height:auto !important;
    height:350px;
}
#right_well {
	min-height:350px;
    height:auto !important;
    height:350px;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>

    <div class="container">
      <div class="row hidden-xs">	
      		<div class="col-lg-4" class="mainDiv">
	      		<div class="well" id="left_well">
	      			<h1 class="titleH1">NamooSocial</h1>
	      		</div>
      		</div>
			<div class="col-lg-5" class="mainDiv">
				<div class="well" id="right_well">
					<p>처음 오신 분은 회원가입을 해주세요.</p>
					<form class="form-horizontal" action="${ctx}/user/join"
						method="post">
						<fieldset>
							<div class="form-group">

								<label class="col-lg-3 control-label">아이디</label>
								<div class="col-lg-8">
									<input type="text" name="userId" class="form-control" id="userIdInput"
										placeholder="아이디" required />
								</div>
								<br>
								<br> <label class="col-lg-3 control-label">비밀번호</label>
								<div class="col-lg-8">
									<input type="password" name="password" class="form-control"
										placeholder="비밀번호" required />
								</div>
								<br>
								<br> <label class="col-lg-3 control-label">확인</label>
								<!--TODO: 확인란에 입력이 끝나고 포커스가 벗어나면 자동으로 위의 비밀번호 입력과 비교해서 메시지 출력하는 스크립트 작성-->
								<div class="col-lg-8">
									<input type="password" class="form-control"
										placeholder="비밀번호 확인" required />
								</div>
								<br>
								<br> <label class="col-lg-3 control-label">이름</label>
								<div class="col-lg-8">
									<input type="text" name="name" class="form-control"
										placeholder="이름" required />
								</div>
								<br>
								<br> <label class="col-lg-3 control-label">이메일</label>
								<div class="col-lg-8">
									<input type="text" name="email" class="form-control"
										placeholder="이메일" required />
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-4 col-lg-offset-3">

									<button type="submit" class="btn btn-primary" id="submitBtn">가입</button>
									<!-- <button class="btn btn-default">다시입력</button> -->
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<div class="row visible-xs">	
			<div class="col-lg-5">
			
				<div class="well" id="right_well">
				
				<h2>NamooSocial</h2>
					<p><b>처음 오신 분</b>은 <b>회원가입</b>을 해주세요. 
					<br><b>로그인</b>은 <b>상단 메뉴</b>를 이용해주세요.</p>

					<form class="form-horizontal" action="${ctx}/user/join"
						method="post">
						<fieldset>
							<div class="form-group">

								<div class="col-lg-8">
									<input type="text" name="userId" class="form-control" id="userIdInput"
										placeholder="아이디" required />
								</div>
								<br>
								<div class="col-lg-8">
									<input type="password" name="password" class="form-control"
										placeholder="비밀번호" required />
								</div>
								<br>
								<div class="col-lg-8">
									<input type="password" class="form-control"
										placeholder="비밀번호 확인" required />
								</div>
								<br>
								<div class="col-lg-8">
									<input type="text" name="name" class="form-control"
										placeholder="이름" required />
								</div>
								<br>
								<div class="col-lg-8">
									<input type="text" name="email" class="form-control"
										placeholder="이메일" required />
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-4 col-lg-offset-3">

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
    </div> <!-- /container -->

</body>
</html>