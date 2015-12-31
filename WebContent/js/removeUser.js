/**
 * 
 */
$(function(){
	var user =getCookie("user");
	if(user==null){
		window.location.href="index.jsp";
	}else{
		$("#user").text(user);
	}
	
	
	
	
	$("#removeUser").confirm({
		title : "提示",
		text:"您确认注销？",
		confirm : function(button) {
			var user =getCookie("user");
			deleteCookie("user",user);
			window.location.href="index.jsp";
		},
		/*
		cancel : function(button) {
			alert("You aborted the operation.");
		},*/
		confirmButton : "确认",
		cancelButton : "取消",
		confirmButtonClass: "btn-danger",
		cancelButtonClass: "btn-default"
	});
	
})