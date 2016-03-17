<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="${ctx}/resources/common/js/main.js"></script>
<link href="${ctx}/resources/common/css/main.css" rel="stylesheet">
<title>MSG</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/main_navi.jsp"%>

	<div class="container">

		<div class="row row-offcanvas row-offcanvas-left">

			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<div class="well sidebar-nav">
					<ul class="nav">
						<li>
							<div class="side">
								<div class="sideProfile container">
									<a title="프로필보기" href="javascript:getProfile('${user.userId}')">
									<img class="img-responsive" id="mainProfileImg"
										src="${ctx}/user/profile/${user.userId}/image"/>
									<b class="profileName">${user.name}</b></a>
								</div>
								<hr class="sideProfileHR">
								<div class="sideProfileCount">
									<div class="sideCountDivF">
										<div class="sideCountTitle">메시지</div>
										<div class="sideCountNo">${count.msgCount}</div>
									</div>
									<div class="sideCountDivM">
										<div class="sideCountTitle">팔로잉</div>
										<div class="sideCountNo">${count.followingCount}</div>
									</div>
									<div class="sideCountDivM">
										<div class="sideCountTitle">팔로워<%-- <c class="sideCountNo visible-xs">${count.followerCount}</c> --%></div>
										<div class="sideCountNo">${count.followerCount}</div>
									</div>
								</div>
							</div>
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
					<c:choose>
						<c:when test="${recoList.size() eq 0}">
							<div class="noRecoDiv">
								<b>추천할 팔로워가 없습니다.</b>
							</div>
						</c:when>
						<c:otherwise>
							<div class="recoControlDiv">
								<h3>팔로우 추천</h3>
								<a href="javascript:getRecoMember()">새로 고침</a> / <a href="${ctx}/user/list">사용자
									목록</a>
							</div>
							<div class="recoDiv" id="recoList">
								<c:forEach var="recoUser" items="${recoList}">
									<div class="recoGroup">
										<div class="recoProfileDiv">
										<a title="프로필보기" href="javascript:getProfile('${recoUser.userId}')"><b>${recoUser.name}</b><br>
										<b class="hidden-xs">${recoUser.email}</b>
										<img class="recoImg img-responsive hidden-xs"
											src="${ctx}/user/profile/${recoUser.userId}/image"/></a>
										</div>
										<button class="followBtn btn btn-default" id="btnm"
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
				</div>


				<div class="row">
					<h3>타임라인</h3>
				</div>
				<div class="row" id="timelineRow">
					<input type="hidden" id="currentPage" value="1" /> 
					<input type="hidden" id="countPerPage" value="10" />
				</div>
				<div class="panel" id="more"><b>더 이상 메시지가 없습니다.</b>
				</div>
				<div id="loadingIcon">
					<img src="${ctx}/resources/common/img/ajax-loader.gif" />
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