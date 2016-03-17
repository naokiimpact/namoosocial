/**
 * 
 */
$(document).ready(function() {

	$("#loadingIcon").hide();
	var keyword = getUrlParameter('keyword');
	$("#srch-term").val(keyword);
	
	
	function btnChange(state, userId) {
		//
		var html = '';
			if (state == 'follow'){
				html += '<button class="followBtn btn btn-primary" id="btnm"';
				html += 'style="background: #55acee;"';
				html += 'onmouseover="this.style.background=\'#ae162c\'; this.innerHTML=\' 언팔로우\';"';
				html += 'onmouseout="this.style.background=\'#55acee\'; this.innerHTML=\'팔로잉\'"';
				html += 'onclick="unfollow(\'' + userId + '\')">';
				html += '팔로잉</button>';
			} else if (state == 'unfollow') {
				html += '<button class="btn btn-default" id="btnm"';
				html += 'style="color: #444444;"';
				html += 'onclick="follow(\'' + userId + '\')">';
				html += '<i class="btnIcon glyphicon glyphicon-plus"></i>';
				html += '<i class="btnIcon glyphicon glyphicon-user"></i>';
				html += '팔로우</button>';
			}
			$("#btnDiv"+userId).html(html);
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
					btnChange('follow', userId);
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
					btnChange('unfollow', userId);
				}
			}
		});
	}
	
	function getUrlParameter(sParam)
	{
	    var sPageURL = window.location.search.substring(1);
	    var sURLVariables = sPageURL.split('&');
	    for (var i = 0; i < sURLVariables.length; i++) 
	    {
	        var sParameterName = sURLVariables[i].split('=');
	        if (sParameterName[0] == sParam) 
	        {
	            return sParameterName[1];
	        }
	    }
	}
	
	window.follow = follow;
	window.unfollow = unfollow;
	window.btnChange = btnChange;
});