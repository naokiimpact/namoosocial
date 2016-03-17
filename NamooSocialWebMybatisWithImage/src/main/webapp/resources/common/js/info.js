/**
 * 
 */
$(document).ready(function() {

	$("#loadingIcon").hide();
	
	$("#infoMsg").hide();
	$("#infoMsg").css('width', '20%').css('position', 'fixed').css('bottom', ($(document).height()/2 + 50) + 'px').css('right', '41%').css('opacity', '0.8').css('text-align', 'center').css('list-style', 'none').css('border', 'none').css('margin', '0').css('padding', '1%').css('z-index', '2500').css('background', '#55acee').css('border-radius', '5px').css('color', '#ffffff');
	
	$("#modifyBtn").click(function(){
		
		var user = {};
		user.userId = $("[name=userId]").val();
		user.name = $("[name=name]").val();
		user.email = $("[name=email]").val();
		user.password = $("[name=password]").val();
	
		$.ajax({
			url : nsjs.ctx + "/user/info/modify"
			,type : "post"
			,data : user
			,dataType : "json"
			,success : function(data) {
				$("#loadingIcon").fadeIn(1000).fadeOut(1000);
				$("#infoMsg").fadeIn(1000).fadeOut(2000);
			}
		});
	});
});