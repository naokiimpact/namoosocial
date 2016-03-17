/**
 * 
 */
$(document).ready(function() {

	$("#loadingIcon").hide();
	$("#timelineRow").hide();
	
	$("#more").hide();
	
	getMessages();
	
	$(window).scroll(function () { 
		var scrollHeight = $(window).scrollTop() + $(window).height(); 
		var documentHeight = $(document).height(); 
		if (scrollHeight == documentHeight) { 
			getMessages();
		} 
	});

//	// ajax 전역 이벤트 처리
//	$(document).ajaxStart(function() {
//		$("#loadingIcon").fadeIn(1000);
//	}).ajaxStop(function() {
//		$("#loadingIcon").fadeOut(2000);
//	});
	
	function appendMessage(messages) {
		//
		var html = '';
		for (var i = 0, length = messages.length; i < length; i++) {
			//
			var message = messages[i];
			html += '<div class="panel panel-default">';
			html += '<div class="panel-heading">';
			if (message.writer.userId == nsjs.loginId) {
				html += '<a href="' + nsjs.ctx + '/main/' + message.writer.userId + '"><b>' + message.writer.name + '</b></a>';
			} else {
				html += '<a href="' + nsjs.ctx + '/main/' + message.writer.userId + '">' + message.writer.name + '</a>';
			}
			html += '<div style="float: right;">' + $.getDate('Y/m/d H:i:s', message.regDate) +'</div>';
			html += '</div>';
			html += '<div class="panel-body">' + message.contents + '</div>';
			html += '</div>';
			
		}
		$("#timelineRow").fadeIn(1000).append(html);
	}
	
	function getMessages() {
		//
		var currentPage = $("#currentPage").val();
		var countPerPage = $("#countPerPage").val();
		var userId = $("#userId").val();
		
		var nowUrl = location.href;
		var sub = nowUrl.substr(nowUrl.length-5,nowUrl.length);
		var url = '';
		
		if (sub == 'ngMsg') {
			url = nsjs.ctx + "/message/" + userId + "/followingMsg?currentPage=" + currentPage + "&countPerPage=" + countPerPage;
		} else if (sub == 'erMsg') {
			url = nsjs.ctx + "/message/" + userId + "/followerMsg?currentPage=" + currentPage + "&countPerPage=" + countPerPage;
		} else {
			url = nsjs.ctx + "/message/" + userId + "/message?currentPage=" + currentPage + "&countPerPage=" + countPerPage;
		}
		
		$.ajax({
			url : url
			,type : "get"
			,dataType : "json"
			,beforeSend : function() {
				$("#loadingIcon").fadeIn(function(){
					$("#loadingIcon").fadeOut();
				});
			}
			,success : function(data) {
				appendMessage(data.results);
				if (data.nextPage) {
					$("#currentPage").val(data.currentPage + 1);
				} else {
					$(window).off("scroll");
					$("#more").show();
				}
			}
		});
	}
	
	function follow(userId) {
		//
		$.ajax({
			url : nsjs.ctx + "/user/follow/" + userId
			,type : "get"
			,dataType : "json"
			,success : function(data) {
				if (data == true){
					$("#loadingIcon").fadeIn(function(){
						$("#loadingIcon").fadeOut();
					});
					getRecoMember();
				}
			}
		});
	}
	
	function unfollow(userId) {
		//
		$.ajax({
			url : nsjs.ctx + "/user/unfollow/" + userId
			,type : "get"
			,dataType : "json"
			,success : function(data) {
				if (data == true){
					$("#loadingIcon").fadeIn(function(){
						$("#loadingIcon").fadeOut();
					});
					getRecoMember();
				}
			}
		});
	}
	
	function getRecoMember() {
		//
		
		$.ajax({
			url : nsjs.ctx + "/user/reco"
			,type : "get"
				,dataType : "json"
					,beforeSend : function() {
						$("#loadingIcon").fadeIn(function(){
							$("#loadingIcon").fadeOut();
						});
					}
		,success : function(data) {
			$("#recoList").fadeOut(function(){
				printRecoMember(data);
			});
		}
		});
	}
	
	function printRecoMember(recoList) {
		//
		var html = '';
		for (var i = 0, length = recoList.length; i < length; i++) {
			//
			var recoMember = recoList[i];
			html += '<div class="recoGroup">';
			html += '<a title="프로필보기" href="javascript:getProfile(\'' + recoMember.userId + '\')">';
			html += '<img class="recoImg img-responsive hidden-xs" src="' + nsjs.ctx + '/user/profile/' + recoMember.userId + '/image"/></a>';
			html += '<b><a title="프로필보기" href="javascript:getProfile(\'' + recoMember.userId + '\')">' + recoMember.name + '</a></b><br>';
			html += '<button class="followBtn btn btn-default btn-xs" id="btnm"';
			html += 'onclick="follow(\''+ recoMember.userId  +'\')">';
			html += '<i class="followBtnIcon glyphicon glyphicon-plus"></i>';
			html += '<i class="followBtnIcon glyphicon glyphicon-user"></i> 팔로우</button>';
			html += '</div>';
		}
		$("#recoList").html(html).fadeIn();
	}
	
	window.getRecoMember = getRecoMember;
	window.printRecoMember = printRecoMember;
	window.follow = follow;
	window.unfollow = unfollow;
	window.getMessages = getMessages;
	window.appendMessage = appendMessage;
});