<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/common/js/userlist.js"></script>
<link href="${ctx}/resources/common/css/userlist.css" rel="stylesheet">
<title>MSG</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row">
			<div class="col-lg-6">

				<div class="page-header">
					<h2 id="container">회원 리스트</h2>
				</div>

				<c:forEach var="user" items="${userList}">
					<div class="panel panel-default">
						<div class="panel-heading">
							<a title="프로필보기" href="javascript:getProfile('${user.user.userId}')"><b>${user.user.name}</b></a>
						</div>
						<div class="panel-body">
							<a title="프로필보기" href="javascript:getProfile('${user.user.userId}')">
							<img class="profileImg img-responsive hidden-xs"
										src="${ctx}/user/profile/${user.user.userId}/image"/></a>
							<div class="emailDiv hidden-xs">${user.user.email}</div>
							<div class="btnDiv" id="btnDiv${user.user.userId}">
								<c:choose>
									<c:when test="${user.followed}">
										<button class="followBtn btn btn-primary" id="btnm" style="background: #55acee;"
											onmouseover="this.style.background='#ae162c'; this.innerHTML=' 언팔로우';"
											onmouseout="this.style.background='#55acee'; this.innerHTML='팔로잉'"
											onclick="unfollow('${user.user.userId}')">팔로잉</button>
									</c:when>
									<c:otherwise>
										<button class="followBtn btn btn-default" id="btnm" style="color: #444444;"
											onclick="follow('${user.user.userId}')">
											<i class="btnIcon glyphicon glyphicon-plus"></i><i
												class="btnIcon glyphicon glyphicon-user"></i>
											팔로우
										</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="loadingIcon">
			<img src="${ctx}/resources/common/img/ajax-loader.gif" />
		</div>
		<hr>

		<%@include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- /container -->

</body>
</html>