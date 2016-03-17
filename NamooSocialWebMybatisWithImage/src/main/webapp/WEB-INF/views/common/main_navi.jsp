<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${ctx}/main">NamooSocial</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="${ctx}/main"><i
						class="glyphicon glyphicon-home"></i> 홈</a></li>
				<c:if test="${login ne null}">
					<li><a href="${ctx}/main/${login.userId}"><i
							class="glyphicon glyphicon-user"></i> 나</a></li>
					<li><a href="#postModal" role="button" data-toggle="modal"><i
							class="glyphicon glyphicon-plus"></i> 메시지</a></li>
				</c:if>
			</ul>
			<c:if test="${login ne null}">
				<form class="navbar-form navbar-left">
					
					<div class="input-group input-group-sm" id="navi_search">
						<input type="text" class="form-control" placeholder="검색"
							name="srch-term" id="srch-term">
						<div class="input-group-btn">
							<!-- <button class="btn btn-default" type="submit"> -->
							<button class="btn btn-default" id="searchBtn" type="button">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
						<div id="search-result">
						</div>
					</div>
				</form>
				<ul class="nav navbar-nav navbar-right">
					
					<li class="dropdown" id="friend"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><font style="letter-spacing:-3px;"><i class="glyphicon glyphicon-user"></i>
						<i class="glyphicon glyphicon-user"></i></font></a>
						<ul class="dropdown-menu" id="friendsList">
					</ul></li>
				
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><i class="glyphicon glyphicon-envelope"></i>
							NS<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/user/list">사용자 목록</a></li>
							<li><a href="${ctx}/main/${login.userId}/followingMsg">팔로잉
									메시지</a></li>
							<li><a href="${ctx}/main/${login.userId}/followerMsg">팔로워
									메시지</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">~</li>
							<li><a href="${ctx}/user/info">개인 정보</a></li>
							<li><a href="${ctx}/main/${login.userId}">나</a></li>
						</ul></li>
	
				</ul>
			</c:if>
			<form class="navbar-form navbar-right" action="javascript:login()"
				method="post">
				<c:choose>
					<c:when test="${login eq null}">
						<div class="form-group" id="navi_login_id">
							<input name="userId" type="text" placeholder="아이디"
								class="form-control">
						</div>
						<div class="form-group" id="navi_login_pass">
							<input name="password" type="password" placeholder="패스워드"
								class="form-control">
						</div>
						<button type="submit" class="btn btn-success">로그인</button>
					</c:when>
					<c:when test="${login ne null}">
						<div class="form-group" id="navi_login_user">
							<a href="${ctx}/main/${login.userId}" id="navi_login_user_anchor">
							<img id="navi_login_user_anchor_img" src="${ctx}/user/profile/${login.userId}/image"/>
							<b>${login.name}</b></a>
						</div>
						<button type="button" class="btn btn-default" onclick="logout()">로그아웃</button>
					</c:when>
				</c:choose>
			</form>
		</div>
		<!--/.navbar-collapse -->
		<!-- massage popup -->
		<div id="popup_msg"></div>
		<!-- chatting tab -->
		<div id="chat_div">
			<div class="chat" id="chat">
				<a href = javascript:chatup()>채팅 - 로그인 중인 친구</a>
				<div class="chating" id="chating">
					<div class="chat_head"><a href = javascript:chatdown()>채팅 - 로그인 중인 친구</a></div>
					<div class="chat_body" id="chat_body"><ul></ul></div>
					<div class="chat_foot" id="chat_foot">
						<textarea class="chat_text" id="chat_text" maxlength="40"></textarea></div>
				</div>
			</div>
		</div>
	</div>
</div>

	<!--profile modal-->
	<div id="profileDiv" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" id="profileModalContent">
				<div class="modal-body">
					<div class="profileBtn">
						<a href="javascript:closeProfile()" title="닫기"><i
							class="glyphicon glyphicon-remove"></i></a>
					</div>
					<div class="profileBtn">
						<a id="modify" title="수정"><i class="glyphicon glyphicon-edit"></i></a>
					</div>
					<a id="linkImage"><img class="img-responsive" id="profileImg" /></a>
					<a id="linkUsermain" title="유저페이지로"></a>
					<div id="userDesc"></div>
					<div id="userProfile">
						<form name="profileForm" id="profileForm" action="" method="post"
							enctype="multipart/form-data"></form>
					</div>
					<hr id="profileHR">
					<div class="modal-footer">
						<div id="profileCount">
							<div class="profileCountF">
								<div class="profileCountName">메시지</div>
								<div class="profileCountNo" id="msgCount"></div>
							</div>
							<div class="profileCountB">
								<div class="profileCountName">팔로잉</div>
								<div class="profileCountNo" id="followingCount"></div>
							</div>
							<div class="profileCountB">
								<div class="profileCountName">팔로워</div>
								<div class="profileCountNo" id="followerCount"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
			
	<!--post modal-->
	<div id="postModal" class="modal fade" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="clearForm()">×</button>
					메시지 작성
				</div>
				<div class="modal-body">
					<form class="form center-block" name="form_post"
						action="${ctx}/message/write" method="post">
						<div class="form-group">
							<textarea name="contents" class="form-control input-lg"
								autofocus="" placeholder="어떤 글을 팔로워들과 공유하시겠습니까?"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer" id="writeModalFooter">
					<div>
						<button class="btn btn-primary btn-sm" id="writeBtn"
							data-dismiss="modal" aria-hidden="true">보내기</button>
						<!-- <ul class="pull-left list-inline">
							<li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li>
							<li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li>
							<li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li>
						</ul> -->
					</div>
				</div>
			</div>
		</div>
	</div>