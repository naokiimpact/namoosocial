<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<title>MSG</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>
	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row">
			<div class="col-lg-6 hidden-xs">

				<div class="page-header">
					<h2 id="container">전체 회원 리스트</h2>
				</div>

				<c:forEach var="user" items="${userList}">
					<c:set var="count" value="1" scope="page" />
					<c:set var="followingSize" value="${followingList.size()}"
						scope="page" />
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="${ctx}/main/${user.userId}"><b>${user.name}</b></a>
						</div>
						<div class="panel-body">${user.email}
							<div style="float: right;">
								<c:forEach var="following" items="${followingList}">
									<c:choose>
										<c:when test="${user.userId eq login.userId}">
										</c:when>
										<c:when test="${user.userId eq following.userId}">
											<button class="btn btn-primary" id="btnm"
												style="background: #55acee; width: 100px;"
												onmouseover="this.style.background='#ae162c'; this.innerHTML=' 언팔로우';"
												onmouseout="this.style.background='#55acee'; this.innerHTML='팔로잉'"
												onclick="location.href='${ctx}/user/unfollow/${user.userId}'">
												팔로잉</button>
										</c:when>
										<c:when test="${user.userId ne following.userId}">
											<c:set var="count" value="${count + 1}" scope="page" />
										</c:when>
										<c:when test="${count eq followingSize}">
											<button class="btn btn-default" id="btnm"
												style="color: #444444; font-weight: bold; width: 100px;"
												onclick="location.href='${ctx}/user/follow/${user.userId}'">
												<i class="glyphicon glyphicon-plus" style="color: #55acee;"></i><i
													class="glyphicon glyphicon-user" style="color: #55acee;"></i>
												팔로우
											</button>
											<c:set var="count" value="1" scope="page" />
										</c:when>
									</c:choose>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>


			<div class="col-lg-7 visible-xs">

				<div class="page-header">
					<h2 id="container">회원 리스트</h2>
				</div>

				<c:forEach var="user" items="${userList}">
					<c:forEach var="following" items="${followingList}">
						<c:choose>
							<c:when test="${user.userId eq following.userId}">
								<div
									style="padding: 10px; margin: 10px; border: 1px solid #dddddd; border-radius: 5px;">
									<div>
										<a href="${ctx}/main/${user.userId}"><b>${user.name}</b></a>
										${user.email}
									</div>
									<button class="btn btn-primary btn-xs" id="btnm"
										style="background: #55acee; width: 80px; margin-top: 5px;"
										onmouseover="this.style.background='#ae162c'; this.innerHTML=' 언팔로우';"
										onmouseout="this.style.background='#55acee'; this.innerHTML='팔로잉'"
										onclick="location.href='${ctx}/user/unfollow/${user.userId}'">
										팔로잉</button>

								</div>
							</c:when>
							<c:otherwise>
								<div
									style="padding: 10px; margin: 10px; border: 1px solid #dddddd; border-radius: 5px;">
									<div>
										<a href="${ctx}/main/${user.userId}"><b>${user.name}</b></a>
										${user.email}
									</div>
									<button class="btn btn-default btn-xs" id="btnm"
										style="color: #444444; font-weight: bold; width: 80px; margin-top: 5px;"
										onclick="location.href='${ctx}/user/follow/${user.userId}'">
										<i class="glyphicon glyphicon-plus" style="color: #55acee;"></i><i
											class="glyphicon glyphicon-user" style="color: #55acee;"></i>
										팔로우
									</button>
								</div>

							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:forEach>

			</div>
		</div>
		<hr>

		<footer>
			<p>&copy; Company 2013</p>
		</footer>
	</div>
	<!-- /container -->

</body>
</html>