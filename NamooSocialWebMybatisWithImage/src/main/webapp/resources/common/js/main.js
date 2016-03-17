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
		if (scrollHeight >= documentHeight) { 
			getMessages();
		}
	});
	
	function appendMessage(timeline) {
		//
		var html = '';
		for (var i = 0, length = timeline.length; i < length; i++) {
			//
			var message = timeline[i];
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
		
		$.ajax({
			url : nsjs.ctx + "/message/timeline?currentPage=" + currentPage + "&countPerPage=" + countPerPage
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
			html += '<div class="recoProfileDiv">';				
			html += '<a title="프로필보기" href="javascript:getProfile(\'' + recoMember.userId + '\')"><b>' + recoMember.name + '</b><br>';
			html += '<b class="hidden-xs">' + recoMember.email + '</b><img class="recoImg img-responsive hidden-xs"';
			html += 'src="' + nsjs.ctx + '/user/profile/' + recoMember.userId + '/image"/></a>';
			html += '</div>';
			html += '<button class="followBtn btn btn-default" id="btnm"';
			html +=	'onclick="follow(\''+ recoMember.userId  +'\')">';
			html +=	'<i class="followBtnIcon glyphicon glyphicon-plus"></i>';
			html +=	'<i class="followBtnIcon glyphicon glyphicon-user"></i> 팔로우</button>';
			html +=	'</div>';
			
		}
		$("#recoList").html(html).fadeIn();
	}
	
	window.getMessages = getMessages;
	window.appendMessage = appendMessage;
	window.getRecoMember = getRecoMember;
	window.printRecoMember = printRecoMember;
	window.follow = follow;
	window.unfollow = unfollow;
});