<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计算机网路出卷系统</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/publicfuc.js"></script>
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
</head>
<body id="loginbody">

<div id="loginIn">
	<table>
		<tr><td><h2>管理员登入</h2></td></tr>
		<tr>
			<td>
				<input class="form-control" name="username" type="text" placeholder="用户名">
			</td>
		</tr>
		<tr>
			<td>
				<input class="form-control" name="password" type ="password" placeholder="密码">
			</td>
		</tr>
		<tr>
			<td>
				<button id="login" class="btn btn-lg btn-primary btn-block">登陆</button>
				<img alt="loading" src="images/loading.gif" class="hidden">
				<p class="hidden msg"></p>
			</td>
		</tr>
	</table>

</div>
<script type="text/javascript">
	$(function(){
		$("#login").click(function(){
			$("#loginIn img").removeClass("hidden");
			$("#login").addClass("hidden");
			var param = {"username":$("input[name=username]").val(),"password":$("input[name=password]").val()};
			$.post("login",param,function(data0){
				var data = data0.data;
				var result = data0.result;
				console.log(result);
				$(".msg").removeClass("hidden").text(result.msg);
				if(result.code==0){
					/*登陆成功*/
					setCookie("user", data.username);
					window.location.href="manager.jsp";
				}
				$("#login").removeClass("hidden");
				$("#loginIn img").addClass("hidden");
			});
		});
	})

</script>
</body>
</html>