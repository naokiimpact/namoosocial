/**
 * 
 */
$(document).ready(function() {

	$("#loadingIcon").hide();
	$("#timelineRow").hide();
	
	$("#more").hide();
	
	/*$(window).scroll(function () { 
		var scrollHeight = $(window).scrollTop() + $(window).height(); 
		var documentHeight = $(document).height(); 
		if (scrollHeight == documentHeight) { 
			getMessages();
		} 
	});*/

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
});