<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/common/js/usermain.js"></script>
<link href="${ctx}/resources/common/css/usermain.css" rel="stylesheet">
<title>MSG</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>

	<div class="container">

		<div class="row row-offcanvas row-offcanvas-left">

			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<div class="sideProfile well sidebar-nav">
					<ul class="nav">
						<li><h3 class="hidden-xs">${user.name}</h3>
						<h4 class="visible-xs">${user.name}</h4></li>
						<li>NamooSocial</li>
						<li><a href="${ctx}/main/${userId}/message">메시지</a></li>
						<li><a href="${ctx}/main/${userId}/following">팔로잉</a></li>
						<li><a href="${ctx}/main/${userId}/follower">팔로워</a></li>
						<li><a href="${ctx}/main/${userId}/friend">친구</a></li>
					</ul>
				</div>
				<!--/.well -->

				<div class="well sidebar-nav">
					<ul class="nav">
						<li><c:choose>
								<c:when test="${recoList.size() eq 0}">
									<div class="noRecoDiv">
										<b>추천할 팔로워가 없습니다.</b>
									</div>
								</c:when>
								<c:otherwise>
									<div class="recoControlDiv">
										<h3 class="hidden-xs">팔로우 추천</h3>
										<h4 class="visible-xs">팔로우 추천</h4>
										<a href="javascript:getRecoMember()">새로 고침</a> /
										<a href="${ctx}/user/list">사용자 목록</a>
									</div>
									<div class="recoDiv" id="recoList">
										<c:forEach var="recoUser" items="${recoList}">
											<div class="recoGroup">
												<a title="프로필보기" href="javascript:getProfile('${recoUser.userId}')">
												<img class="recoImg img-responsive hidden-xs"
													src="${ctx}/user/profile/${recoUser.userId}/image"/></a>
												<b><a title="프로필보기" href="javascript:getProfile('${recoUser.userId}')">${recoUser.name}</a></b><br>
												<button class="followBtn btn btn-default btn-xs" id="btnm"
													onclick="follow('${recoUser.userId}')">
													<i class="followBtnIcon glyphicon glyphicon-plus"></i>
													<i class="followBtnIcon glyphicon glyphicon-user"></i>
													팔로우
												</button>
											</div>
										</c:forEach>
									</div>
								</c:otherwise>
							</c:choose>
						</li>
					</ul>
				</div>
				<!--/.well -->


			</div>
			<!--/span-->
			<div class="mainDiv col-xs-10 col-sm-6">
				<p class="pull-left visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">Toggle nav</button>
				</p>

				<div class="row">

					<div class="profileDiv jumbotron">
						<div class="container">
							<a title="프로필보기" href="javascript:getProfile('${user.userId}')">
							<img class="img-responsive hidden-xs" id="mainProfileImg"
								src="${ctx}/user/profile/${user.userId}/image"/>
							<b class="profileName">${user.name}</b>
							</a>
						</div>
						<hr class="profileHR">
						<div class="profileCount">
							<div class="countDivF">
								<div class="countTitle">메시지</div>
								<div class="countNo">${count.msgCount}</div>
							</div>
							<div class="countDivM">
								<div class="countTitle">팔로잉</div>
								<div class="countNo">${count.followingCount}</div>
							</div>
							<div class="countDivM">
								<div class="countTitle">팔로워</div>
								<div class="countNo">${count.followerCount}</div>
							</div>
						</div>
					</div>

					<div class="row">
						<c:choose>
							<c:when test="${followingList ne null}">
								<h3>팔로잉 리스트</h3>
							</c:when>
							<c:when test="${followerList ne null}">
								<h3>팔로워 리스트</h3>
							</c:when>
							<c:when test="${friendsList ne null}">
								<h3>친구 리스트</h3>
							</c:when>
						</c:choose>
					</div>
					<c:choose>
						<c:when test="${followingList.size() eq 0}">
							<div>팔로잉이 없습니다.</div>
						</c:when>
						<c:otherwise>
							<c:forEach var="user" items="${followingList}">
								<div class="userlistBody panel-body">
									<a title="프로필보기" href="javascript:getProfile('${user.userId}')">
									<img class="userlistImg img-responsive"
										src="${ctx}/user/profile/${user.userId}/image"/></a>
									<div class="userListProfile">
										<div>
											<a title="프로필보기" href="javascript:getProfile('${user.userId}')">${user.name}</a>
										</div>
										<div>${user.email}</div>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${followerList.size() eq 0}">
							<div>팔로워가 없습니다.</div>
						</c:when>
						<c:otherwise>
							<c:forEach var="user" items="${followerList}">
								<div class="userlistBody panel-body">
									<a title="프로필보기" href="javascript:getProfile('${user.userId}')">
									<img class="userlistImg img-responsive"
										src="${ctx}/user/profile/${user.userId}/image"/></a>
									<div class="userListProfile">
										<div>
											<a title="프로필보기" href="javascript:getProfile('${user.userId}')">${user.name}</a>
										</div>
										<div>${user.email}</div>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${friendsList.size() eq 0}">
							<div>친구가 없습니다.</div>
						</c:when>
						<c:otherwise>
							<c:forEach var="user" items="${friendsList}">
								<div class="userlistBody panel-body">
									<a title="프로필보기" href="javascript:getProfile('${user.userId}')">
									<img class="userlistImg img-responsive"
										src="${ctx}/user/profile/${user.userId}/image"/></a>
									<div class="userListProfile">
										<div>
											<a title="프로필보기" href="javascript:getProfile('${user.userId}')">${user.name}</a>
										</div>
										<div>${user.email}</div>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<div id="loadingIcon">
						<img src="${ctx}/resources/common/img/ajax-loader.gif" />
					</div>
				</div>
				<!--/row-->
			</div>
			<!--/span-->

		</div>
		<!--/row-->

		<hr>

		<%@include file="/WEB-INF/views/common/footer.jsp"%>

	</div>
	<!--/.container-->

</body>
</html>