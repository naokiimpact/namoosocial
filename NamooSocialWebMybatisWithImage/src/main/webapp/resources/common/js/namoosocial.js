// 나무소셜 페이지 전체에 적용되는 자바스크립트
$(document).ready(function() {
	$('[data-toggle=offcanvas]').click(function() {
		$('.row-offcanvas').toggleClass('active');
	});
  
	$("#loadingIcon").css('bottom', ($(document).height()/2) + 'px');
//	$("#profileDiv").css('position', 'fixed').css('bottom', ($(document).height()*0.2) + 'px').css('text-align', 'center').css('list-style', 'none').css('border', 'none').css('margin', '0').css('padding', '0').css('z-index', '2500').css('width', '40%').css('right', '30%').css('height', ($(document).height()*0.7) + 'px').css('background-color','#eeeeee').css('border-radius','10px').css('font-size','20px');
//	'style="position: fixed; bottom: 300px; right: 50%; opacity: 0.5; text-align: center; list-style: none; border: none; margin: 0; padding: 0; z-index: 2500;"';
	$("#popup_msg").hide();
	$("#login_msg").hide();
	$("#chating").hide();
	
	$.ajaxSetup({
	    cache : false
	});
  
 
  
//	$("#profileDiv").hide();
  
	var preMsgSize = 0;
	var nowMsgSize = 0;
	var interval = null;
  
  
	// 처음 실행 때 현재 타임라인 글 수를 각각 이전과 현재 사이즈 변수에 같게 입력.
	$.ajax({
		url : nsjs.ctx + "/message/renew"
		,type : "get"
		,dataType : "json"
		,success : function(data) {
			nowMsgSize = data;
			preMsgSize = nowMsgSize;
		}
	});
  
  
	// 현재 화면에 표시된 타임라인 글 수와 실제 타임라인 글 수를 비교해서 최근에 업데이트 된 글을 주기적으로 확인. 팝업메시지로 알림.
	if(nsjs.loginId != '') {
		//
		interval = setInterval(function() { 
			//
			$.ajax({
				url : nsjs.ctx + "/message/renew"
				,type : "get"
				,dataType : "json"
				,success : function(data) {
					nowMsgSize = data;
				
//					var nowUrl = location.href;
//					var sub = nowUrl.substr(nowUrl.length-4,nowUrl.length);
					if( preMsgSize != 0 && preMsgSize != nowMsgSize && preMsgSize < nowMsgSize ){
						$('#popup_msg').html('새로운 메시지가 등록되었습니다  '
						+ ' <button style="background:#55acee; color:#ffffff; border:1px solid #ffffff;" ' 
						+ 'class="btn btn-default btn-xs" type="button" onclick="openMain()">보기</button>'
						).fadeIn(500).fadeOut(500).fadeIn(500).fadeOut(500).fadeIn(500).delay(7000).fadeOut(1000);
					}
//					if (sub == 'main') {
//						var url = nsjs.ctx + "/message/timeline?currentPage=1&countPerPage=" + (nowMsgSize - preMsgSize);
//						$.get(url, function(data) {
//							prependMessage(data.results);
//						});
//					}
					preMsgSize = nowMsgSize;
				}
			});
		}, 3200);
	} else {
		clearInterval(interval);
	}
  
	// 친구에게 온 인스턴트 메시지가 있는지 확인하고 데이터베이스에서 로드해와서
	// 화면에 출력.
	if(nsjs.loginId != '') {
		//  
		interval = setInterval(function() { 
			//
			$.ajax({
				url : nsjs.ctx + "/instant/load"
				,type : "get"
				,dataType : "json"
				,success : function(data) {
					for (var i = 0, length = data.length; i < length; i++) {
						var instant = data[i];
						printChatDiv(instant.sender.userId, instant.sender.name);
						printChatting(instant.sender.name, instant, false);
					}
				}
				  
			});
			  
		}, 1300);
	} else {
		clearInterval(interval);
	}
  
	// 검색창 밖에 포커스가 옮겨갔을 때 검색 결과 표시를 사라지게 함
  	$("#srch-term").blur(function(){
  		
  		$("#search-result").fadeOut();
  	});
  	
  	// 검색창에 포커싱 되었을 때 검색을 실행하도록 함
  	$("#srch-term").keyup(function(){
		var keyword = $(this).val();
		
		if(keyword.length > 0) {
			$.ajax({
				url : nsjs.ctx + '/user/search'
				,type : "get"
				,data : {
					"keyword" : keyword
				}
				,dataType : "json"
				,success : function(data) {
					printSearchedUsers(data);
					$("#search-result").fadeIn();
				}
			});
		} else {
			$("#search-result").html('');
		}
	});
  	
  	
  	// 검색창에 포커싱 되었을 때 검색을 실행하도록 함 
  	$("#srch-term").focus(function(){
  		//
  		var keyword = $(this).val();
		//
		if(keyword.length > 0) {
			$.ajax({
				url : nsjs.ctx + '/user/search'
				,type : "get"
				,data : {
					"keyword" : keyword
				}
				,dataType : "json"
				,success : function(data) {
					printSearchedUsers(data);
					$("#search-result").fadeIn();
				}
			});
		} else {
			$("#search-result").html('');
		}
  	 });
  	
  	// 검색창에서 엔터키가 입력되었을 때 검색 결과 페이지로 이동하도록 함
  	$("#srch-term").keypress(function(e){
  		var keyword = $(this).val();
  		if (e.keyCode == 13) {
  			if (keyword.length <= 0) {
  				alert("검색어를 입력하세요");
  				return false;
  			} else {
  	  			location.href = nsjs.ctx + "/user/list/search?keyword=" + keyword;
  	  			return false;
  	  		}
  		}
	});
  	
  	// 검색창 우측 버튼을 클릭했을 때 검색 결과 페이지로 이동하도록 함
  	$("#searchBtn").click(function(){
  		var keyword = $("#srch-term").val();
		if (keyword.length <= 0) {
				alert("검색어를 입력하세요");
		} else {
			location.href = nsjs.ctx + "/user/list/search?keyword=" + keyword;
		}
  	});
  	
  	// 글쓰기 버튼을 클릭 했을 때 실행 됨  		
	$("#writeBtn").click(function(){
		var message = {};
		message.contents = $("textarea[name=contents]").val();
		//
		$.ajax({
			url : nsjs.ctx + "/message/write"
			,type : "post"
			,data : message
			,dataType : "json"
			,beforeSend : function() {
				preMsgSize = preMsgSize + 1;
			}
			,success : function(data) {
				prependMessage([data]);
				clearForm("textarea[name=contents]");
			}
			,error : function() {
				preMsgSize = preMsgSize - 1;
			}
		});
	});
	
//	$('#profileForm').ajaxForm(option);
	
	//	친구목록 불러오기 메뉴를 클릭했을 때 실행함
	$('#friend').on('show.bs.dropdown', function () {
		//
		$.ajax({
			url : nsjs.ctx + "/user/friends"
			,type : "get"
			,dataType : "json"
			,beforeSend : function() {
				$("#loadingIcon").fadeIn(function(){
					$("#loadingIcon").fadeOut();
				});
			}
			,success : function(data) {
			printFriendsList(data);
			}
		});
	});

// 친구목록 불러오기를 실행함	
//	function getFriendsList(userId) {
//		//
//		$.ajax({
//			url : nsjs.ctx + "/main/" + userId + "/friend"
//			,type : "get"
//				,dataType : "json"
//					,beforeSend : function() {
//						$("#loadingIcon").fadeIn(function(){
//							$("#loadingIcon").fadeOut();
//						});
//					}
//		,success : function(data) {
//			printFriendsList(data);
//		}
//		});
//	}

	// 프로필 수정을 실행함 	
	$('#profileForm').submit(function() {
		var fileName = $('#imageFile').val();
		// 프로필 이미지를 등록하지 않고 수정을 실행하는 경우
		if (fileName == "" ) {
			var form = $('#profileForm');
			
			$.ajax({
				url : nsjs.ctx + '/user/profile/modify2'
				,data : form.serialize()
				,type : "post"
				,dataType : "json"
				,success : function(data) {
					$('#modify').html(
					'<i class="glyphicon glyphicon-edit"></i>');
					getProfile(data.userId);
				}
			});
			return false;
			// 프로필 이미지를 등록하고 수정을 실행하는 경우
		} else {
			var lastPoint = fileName.lastIndexOf('.');
	        var extension = fileName.substring(lastPoint +1, fileName.length);
	        // 확장자가 이미지 파일 형식인 경우 실행
			if (extension == 'jpg' || 'jpe' || 'jpeg' || 'png' 
					|| 'ico' || 'bmp' || 'gif') {
				// 이미지 파일의 용량을 확인해서 70KB 이상인 경우 오류 메시지를 출력
				if (document.getElementById('imageFile').files[0].size > 307200) {
					alert('용량이 70KB 미만인 이미지만 업로드할 수 있습니다.');
					return false;
				// 이미지 파일의 용량이 300KB 미만인 경우 실행
				} else {
				
				$(this).ajaxSubmit({
						url : nsjs.ctx + '/user/profile/modify'
						,data : $('#profileForm').serialize()
						,type : "post"
						,dataType : "json"
				        ,success : function(data) {
				        	$('#navi_login_user_anchor_img')
							.attr('src', nsjs.ctx + '/user/profile/' + data.userId + '/image?a=' + Math.random());
				    		$('#modify').html(
				    		'<i class="glyphicon glyphicon-edit"></i>');
				    		$('#mainProfileImg')
				    		.attr('src', nsjs.ctx + '/user/profile/' + data.userId + '/image?a=' + Math.random());
				    		getProfile(data.userId);
				    	}
				
				});
				return false;
				}
	        // 확장자를 확인해서 이미지 파일 형식이 아닌 경우 오류 메시지를 출력
			} else {
				alert('이미지 파일이 아니거나 지원하지 않는 파일 형식입니다.');
				return false;
			}
		}
		
	});
	
	$('.chat_text').keyup(function(e){
  		//
		chatting(e);
	});
	
	// 메시지를 화면에 추가로 출력해줄 때 사용
	function prependMessage(messages) {
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
		$("#loadingIcon").fadeIn(1000).delay(2000).fadeOut(1000);
		$("#timelineRow").prepend(html);
	}
	
	// 입력폼에 입력된 내용을 삭제해주는 함수
	function clearForm(element) {
		$(element).val('');
	}
	
	// 메인 페이지를 출력하는 함수
	function openMain() {
		location.href = nsjs.ctx + '/main';
	}
	
	// 로그인을 실행
	function login() {
		var user = {};
		user.userId = $("input[name=userId]").val();
		user.password = $("input[name=password]").val();
		
		if(user.userId == ''){
			$('#popup_msg').hide().html('아이디를 입력하세요')
			.fadeIn();
			$('#login_msg').hide().html('아이디를 입력하세요')
			.fadeIn();
			return false;
		} else {
			if(user.password == ''){
				$('#popup_msg').hide().html('비밀번호를 입력하세요')
				.fadeIn();
				$('#login_msg').hide().html('비밀번호를 입력하세요')
				.fadeIn();
				return false;
			}
		}
		
		$.ajax({
			url : nsjs.ctx + '/user/login'
			,data : user
			,type : "post"
			,dataType : "json"
			,success : function(data) {
				if (data) {
					location.href= nsjs.ctx;
				} else {
					$('#popup_msg').hide().html('아이디나 비밀번호가 잘못되었습니다.')
					.fadeIn();
					$('#login_msg').hide().html('아이디나 비밀번호가 잘못되었습니다.')
					.fadeIn();
				}
			}
		});
	}
	
	// 로그아웃을 실행
	function logout() {
		nsjs.loginId = '';
		location.href= nsjs.ctx + '/user/logout';
		clearInterval(interval);
	}
	
	// 검색창을 통해 검색된 유저를 출력
	function printSearchedUsers(users) {
		var html = '';
		if (users.length == 0) {
			html += '<div class="search-user" style="width:100%; padding: 6px 5px 0px 5px;">';
			html += '<span style="display:inline-block; margin-left: 10px; height: 40px; vertical-align:-60%;">';
			html += '검색된 유저가 없습니다';
			html += '</span>';
			html += '</div>';
		} else {
			for (var i = 0, length = users.length; i < length; i++) {
				var user = users[i];
				html += '<div class="search-user" style="width:100%; padding: 6px 5px 0px 5px;">';
				html += '<a style="width:100%; display: block; margin-top:5px; margin-bottom:5px;" href="' + nsjs.ctx + '/main/' + user.userId + '">';
				html += '<span style="display:inline-block;">';
				html += '<img class="searchImg img-responsive" src="' + nsjs.ctx + '/user/profile/' + user.userId + '/image"/>';
				html += '</span>';
				html += '<span style="display:inline-block; margin-left: 10px; vertical-align:70%;">';
				html += user.name + ' / ' + user.email;
				html += '</span>';
				html += '</a>';
				html += '</div>';
			}
		}
		$("#search-result").html(html);
	}
	
	// 해당 유저의 프로필을 서버로 부터 로드해옴 : 성공하면 출력하는 함수 실행
	function getProfile(userId) {
		
		$.ajax({
			url : nsjs.ctx + '/user/profile/' + userId
			,type : "get"
			,dataType : "json"
			,success : function(data) {
				printProfile(data);
			}
		});
	}
	
	// 해당 유저의 프로필을 서버로 부터 로드해옴
	// : 성공하면 출력하는 함수 실행
	function getProfileForModify(userId) {
		
		$.ajax({
			url : nsjs.ctx + '/user/profile/' + userId
			,type : "get"
			,dataType : "json"
			,success : function(data) {
				modifyProfile(data);
			}
		});
	}
	
	function printProfile(user) {
		//
//		var html = '';
		var introduction = ''; 
		var region = '';
		var homepage = '';
		if (user.profile.introduction != null) introduction = user.profile.introduction; 
		if (user.profile.region != null) region = user.profile.region; 
		if (user.profile.homepage != null) homepage = user.profile.homepage;
//		html += '<div style="float:right; width:30px;"><a href="javascript:closeProfile()"><b>X</b></a></div>';
//		html += '<img class="img-responsive"';
//		html += 'src="' + nsjs.ctx + '/user/profile/' + user.userId + '/image"';
//		html += 'style="margin: 10%; margin-left: 35%; border: 1px solid #eeeeee; border-radius: 10px; width: 30%; z-index: 2500;"/>';
//		html += '<a href="' + nsjs.ctx + '/main/' + user.userId + '">' + user.name + '<br/>' + user.email + '</a><br/>';
//		html += introduction + '<br/><br/><br/>';
//		html += region + '&nbsp&nbsp&nbsp&nbsp' + homepage;
//		html += '<hr style="margin: 5px; border: 1px solid #dddddd;">';
//		html += '<div';
//		html += 'style="font-size: 15px;">';
//		html += '<div style="float: left; width: 34%;">';
//		html += '<div style="font-size: 15px; color: #aaaaaa;">메시지</div>';
//		html += '<div style="color: #55acee; font-weight: bold;">' + user.profile.count.msgCount + '</div>';
//		html += '</div>';
//		html += '<div style="float: left; width: 32%;">';
//		html += '<div style="font-size: 15px; color: #aaaaaa;">팔로잉</div>';
//		html += '<div style="color: #55acee; font-weight: bold;">' + user.profile.count.followingCount + '</div>';
//		html += '</div>';
//		html += '<div style="float: left; width: 32%;">';
//		html += '<div style="font-size: 15px; color: #aaaaaa;">팔로워</div>';
//		html += '<div style="color: #55acee; font-weight: bold;">' + user.profile.count.followerCount + '</div>';
//		html += '</div>';
//		html += '</div>';
		if(user.userId == nsjs.loginId){
			$('#modify').attr('href', 'javascript:getProfileForModify(\'' + user.userId + '\')').show();
		} else {
			$('#modify').hide();
		}
		$('#profileForm').html('');
		$('#profileImg').attr('src', nsjs.ctx + '/user/profile/' + user.userId + '/image?a=' + Math.random());
		$('#linkUsermain').attr('href', nsjs.ctx + '/main/' + user.userId).html(
			'<b>' + user.name + '<br/>' + user.email + '</b>');
		if(homepage.substring(0, 4) == 'http') {
			$('#userDesc').html('<br/><font color="#428bca">' + introduction + '<br/>'
					+ region + '&nbsp&nbsp&nbsp&nbsp<a href="' + homepage + '">' + homepage  + '</a></font><br/><br/>');
		} else {
			$('#userDesc').html('<br/><font color="#428bca">' + introduction + '<br/>'
					+ region + '&nbsp&nbsp&nbsp&nbsp' + homepage  + '</font><br/><br/>');
		}
		$('#msgCount').text(user.profile.count.msgCount);
		$('#followingCount').text(user.profile.count.followingCount);
		$('#followerCount').text(user.profile.count.followerCount);
		
//		$("#profileDiv").html(html);
//		$("#profileDiv").fadeIn();
		$('#profileDiv').modal();
	}
	
	function modifyProfile(user){
		var html = '';
		
		var introduction = ''; 
		var region = '';
		var homepage = '';
		if (user.profile.introduction != null) introduction = user.profile.introduction; 
		if (user.profile.region != null) region = user.profile.region; 
		if (user.profile.homepage != null) homepage = user.profile.homepage;
//		html += '<form name="profileForm" id="profileForm" action="javascript:saveProfile(\'' + user.userId+ '\')" method="post" enctype="multipart/form-data">';
//		html += '<form name="profileForm" id="profileForm" action="' + nsjs.ctx + '/user/profile/modify" method="post" enctype="multipart/form-data">';
		html += '<input type="text" id="fileName" class="file_input_textbox" readonly="readonly">';
		html += '<div class="file_input_div">';
		html += '<button type="button" value="이미지" class="file_input_button">이미지</button>';
		html += '<input type="file" id="imageFile" name="imageFile" class="file_input_hidden" onchange="javascript: document.getElementById(\'fileName\').value = this.value"/>';
		html += '</div>';
//		html += '<input type="file" id="imageFile" name="imageFile" style="margin-left:20%; margin-bottom:2%;"/>';
		html += '<input type="hidden" name="userId" value="' + user.userId + '"/>';
		html += '<input type="hidden" name="name" value="' + user.name + '"/>';
		html += '<input type="hidden" name="email" value="' + user.email + '"/>';
		html += '<input type="hidden" name="password" value="' + user.password + '"/>';
		html += '<textarea placeholder="소개" name="introduction" cols=40 rows=2 style="background:none; border:dotted 2px #888888; oveflow:auto; resize:none; margin-bottom:2%; padding: 5px;">' + introduction  + '</textarea><br>';
		html += '<textarea placeholder="지역" name="region" cols=40 rows=2 style="background:none; border:dotted 2px #888888; oveflow:auto; resize:none; margin-bottom:2%; padding: 5px;">' + region  + '</textarea><br>';
		html += '<textarea placeholder="홈페이지" name="homepage" cols=40 rows=2 style="background:none; border:dotted 2px #888888; oveflow:auto; resize:none; margin-bottom:2%; padding: 5px;">' + homepage  + '</textarea><br>';
		html += '<button class="btn btn-default" type="submit">수정</button><br><br>';
//		html += '</form>';
		
//		$('#userProfile').html(html);
		$('#profileForm').attr('action', nsjs.ctx + '/user/profile/modify').html(html);
		$('#linkUsermain').html('');
		$('#userDesc').html('');
//		$('#modify').attr('href', 'javascript:profileForm.submit()').attr('title', '저장').html(
//				'<i class="glyphicon glyphicon-ok"></i>');
		$('#modify').hide();
		
	}
	
	// 프로필을 저장하는 버튼을 클릭 시 실행 : 수정된 프로필 저장
	function saveProfile(userId){
		//
		var form = $('#profileForm');
		
//		var profile = {};
//		profile.introduction = $("[name=introduction]").val();
//		profile.region = $("[name=region]").val();
//		profile.homepage = $("[name=homepage]").val();
		
		$.ajax({
			url : nsjs.ctx + '/user/profile/modify'
			,data : form.serialize()
			,type : "post"
			,dataType : "json"
			,success : function(data) {
				getProfile(data.userId);
				$('#modify').html('<i class="glyphicon glyphicon-edit"></i>');
			}
		});
	}
	
	// 프로필 닫기 버튼을 클릭했을 때 실행
	function closeProfile(){
		//
//		$("#profileDiv").html('');
//		$("#profileDiv").fadeOut();
		$('#profileDiv').modal("hide");
	}

	// 친구 목록을 클릭해서 하단에 해당 친구와의 채팅 메뉴 팝업 
	function chat(userId,name) {
		//
		if($('#chat_'+name).length){}else{
			//
			printChatDiv(userId,name);
			$('#chat_div').fadeIn();
			$('#chating_'+name).hide();
		}
	}
	
	// 팝업된 채팅탭 클릭시 실행 : 해당 채팅창 활성화
	function chatup(name) {
		//
		if(name==null){
			//
			bottomFriendsListup();
			$('#chating').show();
			$('#chat').css('padding','0').css('border','0');
		} else {
			//
			$('#chating_'+name).show();
			$('#chat_'+name).css('padding','0').css('border','0');
			$('#chat_body_'+name).scrollTop($('#chat_body_'+name).prop('scrollHeight'));
		}
	}
	
	// 활성화된 채팅창 상단 클릭시 실행 : 해당 채팅창 비활성화
	function chatdown(name) {
		//
		if(name==null){
			//
			$('#chating').hide();
			$('#chat').css('padding','3 0 0 10').css('border','1px #cccccc solid');
		}else{
			//
			$('#chating_'+name).hide();
			$('#chat_'+name).css('padding','3 0 0 10').css('border','1px #cccccc solid');
		}
	}
	
	// 하단 친구목록 클릭 시 실행 : 친구목록창 활성화 & 로그인된 친구목록
	// 로드와 출력함수 호출
	function bottomFriendsListup() {
		//
		$.ajax({
			url : nsjs.ctx + "/user/friends"
			,type : "get"
			,dataType : "json"
			,beforeSend : function() {
				$("#loadingIcon").fadeIn(function(){
					$("#loadingIcon").fadeOut();
				});
			}
		,success : function(data) {
			printLoginedFriendsList(data);
		}
		});
	}
	
	// 네비게이션 친구목록 클릭 시 실행 : 전체 친구목록을 로드해온 결과를
	// 담아서 실행되고 로딩 결과를 출력함 
	function printFriendsList(friendsList) {
		var html = '';
		//
		for (var i = 0, length = friendsList.length; i < length; i++) {
			//
			var friend = friendsList[i];
			if(friend.userStatus.userStatus == true){
				html += '<li><a title="친구와 채팅을 하려면 클릭하세요" href="javascript:chat(\''
					+ friend.userId +'\',\''+ friend.name +'\')">' + friend.name 
					+ '&nbsp&nbsp&nbsp&nbsp<font color="green" size="4">●</font></a></li>';
			} else {
				html += '<li><a title="친구와 채팅을 하려면 클릭하세요" href="javascript:chat(\''
					+ friend.userId +'\',\''+ friend.name +'\')">' + friend.name 
					+ '&nbsp&nbsp&nbsp&nbsp<font color="red" size="4">●</font>'
					+ '&nbsp<font size="1">(' + $.getDateDiff(friend.userStatus.renewDate)
					+ ')</font></a></li>';
			}
		}
		$("#friendsList").html(html);
	}
	
	// 하단 친구목록 클릭시 실행 : 로그인 상태의 친구목록을 로드해온 결과를
	// 담아서 실행되고 로딩 결과를 출력함
	function printLoginedFriendsList(friendsList) {
		var html = '';
		//
		$("#chat_body").children().html('');
		for (var i = 0, length = friendsList.length; i < length; i++) {
			//
			var friend = friendsList[i];
			if(friend.userStatus.userStatus == true){
				html += '<li class="chat"><a title="친구와 채팅을 하려면 클릭하세요" href="javascript:chat(\''+ friend.userId +'\',\''+ friend.name +'\')"><span>&nbsp&nbsp&nbsp&nbsp' + friend.name + '</span></a></li>';
			} else {}
		}
		$("#chat_body").children().append(html);
	}
	
	// 친구 목록을 클릭해서 하단에 해당 친구와의 채팅 메뉴 팝업될 떄 같이 
	// 실행되는 함수로 해당 친구와의 채팅창을 출력함
	function printChatDiv(userId,name){
		//
		if($('#chat_'+name).length){}else{
			//
			html = '<div class="chat" id="chat_' + name + '\">';
			html += '<a href = javascript:chatup(\'' + name + '\')>'+ name;
			html += '<div class="chat_off_button glyphicon glyphicon-remove-circle" id="chat_off_button_' + name + '"></div></a>';
			html += '<div class="chating" id="chating_' + name + '\">';
			html += '<div class="chat_head">';
			html += '<a href = javascript:chatdown(\'' + name + '\')>'+ name;
			html += '<div class="chat_off_button glyphicon glyphicon-remove-circle" id="chat_off_button_' + name + '"></div></a></div>';
			html += '<div class="chat_body" id="chat_body_' + name + '\"><ul></ul></div>';
			html += '<div class="chat_foot" id="chat_foot_' + name + '\" value="' + userId + '\">';
			html += '<textarea class="chat_text" id="chat_text_' + name + '\" maxlength="80"></textarea></div></div></div>';
		$('#chat_div').append(html);
		$('#chat_text_'+name).css({'height' : '23px'});
		$('#chat_foot_'+name).css({'height' : '29px'}); 
		//
		$('#chat_text_'+name).keyup(function(e){
	  		//
			chatting(e, name);
			if($('#chat_text_'+name).val() == '\n'){
				clearForm('#chat_text_'+name);
			}
		});
		$("#chat_off_button_" + name).click(function(){
	  		//
			$('#chat_' + name).remove();
			$('#chat_text_'+name).unbind();
			$("#chat_off_button_" + name).unbind();
	  	});
		}
	}
	
	// 활성화된 채팅창 하단에 채팅할 내용을 입력하고 엔터를 입력하면 실행되어
	// 내용과 발신자/수신자를 서버로 전송
	function chatting(e, name){
		//
		if(name != null){
			//
			if (e.keyCode == 13) {
				//
	  			var instant = new Array();
	  			instant[0] = $('#chat_text_'+name).val();
	  			instant[1] = nsjs.loginId;
	  			instant[2] = $('#chat_foot_'+name).attr('value');
	  			clearForm('#chat_text_'+name);
	  			//
	  			if (instant[0].length > 0) {
	  				//
	  				if(instant[0] == '\n'){
	  					//
	  					clearForm('#chat_text_'+name);
	  				} else {
	  					//
	  					$.ajax({
		  					url : nsjs.ctx + "/instant/send"
		  					,type : "post"
		  					,data : {"instant" : instant}
		  					,dataType : "json"
		  					,success : function(data) {
		  						clearForm('#chat_text_'+name);
		  						$('#chat_text_'+name).css({'height' : '23px'});
		  	  					$('#chat_foot_'+name).css({'height' : '29px'});
		  						var message = {};
		  						message.message = instant[0];
		  						message.regDate = new Date();
		  						printChatting(name, message, true);
		  					}
		  				});
		  				return false;
	  				}
	  			} else {}
	  		} else {
	  			//
	  			if($('#chat_text_'+name).val() == '\n'){
	  				//
  					clearForm('#chat_text_'+name);
  				} else {
  					//
  					$('#chat_text_'+name).css({'height' : '23px'});
  					$('#chat_foot_'+name).css({'height' : '29px'});  					
  					var scrollHeight = $('#chat_text_'+name).prop("scrollHeight");
  		  			$('#chat_text_'+name).css({'height' : scrollHeight +'px'});
  		  			$('#chat_foot_'+name).css({'height' : (scrollHeight + 6) + 'px'});
  				}
	  		}
		} else {
			//
			if (e.keyCode == 13) {
				//
				clearForm('#chat_text');
				$('#chat_text').css({'height' : '23px'});
				$('#chat_foot').css({'height' : '29px'});
			} else {
				//
				$('#chat_text').css({'height' : '23px'});
				$('#chat_foot').css({'height' : '29px'});
				var scrollHeight = $('#chat_text').prop("scrollHeight");
	  			$('#chat_text').css({'height' : scrollHeight +'px'});
	  			$('#chat_foot').css({'height' : (scrollHeight + 6) + 'px'});
			}
		}
		
	}
	
	// 사용자에게 인스턴트 메시지의 전송 요청을 받아서 해당 내용을 출력함.
	function printChatting(name, message, send){
		//
		if (send){
			//
			html = '<li class="send"> <i class="glyphicon glyphicon-arrow-right"></i>  '
			+ message.message + '  <t>[' + $.getDate('H:i:s', message.regDate) 
			+ ']</t></li>';
		} else {
			//
			html = '<li class="load"> <i class="glyphicon glyphicon-arrow-left"></i>  '
			+ message.message + '  <t>[' + $.getDate('H:i:s', message.regDate) 
			+ ']</t></li>';
		}
		$('#chat_body_'+name).children().append(html);
		$('#chat_body_'+name).scrollTop($('#chat_body_'+name).prop('scrollHeight')); 
	}
	
	window.clearForm = clearForm;
	window.printSearchedUsers = printSearchedUsers;
	window.printProfile = printProfile;
	window.closeProfile = closeProfile;
	window.getProfile = getProfile;
	window.getProfileForModify = getProfileForModify;
	window.saveProfile = saveProfile;
	window.prependMessage = prependMessage;
	window.openMain = openMain;
	window.login = login;
	window.logout = logout;
	window.chat = chat;
	window.chatup = chatup;
	window.chatdown = chatdown;
	window.chatting = chatting;
	window.printChatDiv = printChatDiv;
	window.printChatting = printChatting;
});