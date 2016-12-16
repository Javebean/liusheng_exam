<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<!-- <link rel="icon" href="../../favicon.ico"> -->

<title>计算机网路出卷系统</title>
<%@include file="public/public.html"%>
<link href="css/dashboard.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style id="style-1-cropbar-clipper">
.en-markup-crop-options {
	top: 18px !important;
	left: 50% !important;
	margin-left: -100px !important;
	width: 200px !important;
	border: 2px rgba(255, 255, 255, .38) solid !important;
	border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
	margin-left: 0px !important;
}
</style>
</head>
<body>
	<%@include file="public/navbar.html"%>
	<div class="container-fluid">
		<div class="row">

			<%@include file="public/nav.html"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">出卷系统控制台</h1>

				<h2 class="sub-header">上传单选题</h2>
				<div class="alert alert-warning">
   					<strong>注意！</strong>上传单选题时，Excel表格一行为一题，每一行中请保持依次为“题目”，“选项A”，“选项B”，“选项C”，“选项D”，"答案"，“所属知识点”的顺序！
   					<br><span class="msg">其中“答案”只需填写正确答案的字母，即A或B或C或D！</span>
				</div>
				<div class="table-responsive">
					<form method="POST" enctype="multipart/form-data" action="upload">
						<input type="file" class="form-control" name="file" required="required"> <br />
						<input type="hidden" name="type" value="1">
						<input type="submit" id ="submit" class ="btn btn-danger" value="确认上传">
					</form>
				</div>
				
				<hr>
				<h4 class="sub-header">手动上传（例：TCP/IP 的网络层含有四个重要的协议，分别为________。）</h4>
				
				<form class="form-horizontal" role="form" id="uploadSimple" method="post">
				   <div class="form-group">
				      <label for="firstname" class="col-sm-1 control-label">题目：</label>
				      <div class="col-sm-11">
				         <textarea class="form-control" rows="2" placeholder="请输入题目" name="problem"></textarea>
				      </div>
				   </div>
				   <div class="form-group">
				      <label for="optionA" class="col-sm-1 control-label">A:</label>
				      <div class="col-sm-5">
				         <input type="text" class="form-control" id="optionA" 
				            placeholder="请输入选项A" name="optionA">
				      </div>
				      <label for="optionB" class="col-sm-1 control-label">B:</label>
				      <div class="col-sm-5">
				         <input type="text" class="form-control" id="optionB" 
				            placeholder="请输入选项B" name="optionB">
				      </div>
				   </div>
				   <div class="form-group">
				      <label for="optionC" class="col-sm-1 control-label">C:</label>
				      <div class="col-sm-5">
				         <input type="text" class="form-control" id="optionC" 
				            placeholder="请输入选项C" name="optionC">
				      </div>
				      <label for="optionD" class="col-sm-1 control-label">D:</label>
				      <div class="col-sm-5">
				         <input type="text" class="form-control" id="optionD" 
				            placeholder="请输入选项D" name="optionD">
				      </div>
				   </div>
				   
				   <div class="form-group">
				      <label  class="col-sm-2 control-label">正确答案：</label>
				      <div class="col-sm-2">
				          <label class="checkbox-inline">
	      						<input type="radio" name="answer" 
	         					  value="A"> 选项 A
   						  </label>
				      </div>
				      <div class="col-sm-2">
				         <label class="checkbox-inline">
	      						<input type="radio" name="answer" 
	         					  value="B"> 选项 B
   						  </label>
				      </div>
				      <div class="col-sm-2">
				         <label class="checkbox-inline">
	      						<input type="radio" name="answer" 
	         					  value="C"> 选项 C
   						  </label>
				      </div>
				      <div class="col-sm-2">
				         <label class="checkbox-inline">
	      						<input type="radio" name="answer" 
	         					  value="D"> 选项 D
   						  </label>
				      </div>
				   </div>
				 
				   <div class="form-group">
				      <label for="firstname" class="col-xs-1 control-label">所属知识点：</label>
				      
				      <div class="allkp">
				      </div>
				   </div>
				   
				   <div>
				         <button  id ="submit2" class ="btn btn-danger">确认上传</button>
				         <img alt="loading" src="images/loading.gif" class="hidden loading">
				         <span class="msg"></span>
				   </div>
				   
				</form>
				
				
			</div>
		</div>
	</div>
<script type="text/javascript">
	//得到所有知识点
	getAllkp_upload();
	$("#submit2").click(function(){
		$("img.loading").removeClass("hidden");
		$(".msg").text("");
		
		//检查题目和选项
		var flag = false;
		$("#uploadSimple :input:not(:button)").each(function(){
			var v = $(this).val();
			if(v==""){
				$("img.loading").addClass("hidden");
				$(".msg").text("请填写题目或者选项");
				flag =true;
			}
			
		});
		if(flag){
			return false;
		}
		
		//检查答案与知识点
		var flag2 = 0;
		$("input[type=radio]").each(function(){
			var v = $(this).prop("checked");
			if(v){
				flag2++;
			}
			
		});
		if(flag2!=2){
			$("img.loading").addClass("hidden");
			$(".msg").text("请检查答案以及知识点");
			return false;
		}
		
		
		$.post("addsimpleselect",$("#uploadSimple").serializeArray(),function(data){
			if(data){
				$("img.loading").addClass("hidden");
				$(".msg").text("上传成功");
				$("input[type=text],textarea").val("");
				$("input[type=radio]").prop("checked",false);
			}else{
				alert("上传失败");
			}
		});
		return false;
	});
	
	
	//导航active
	$(function(){
		$("#collapseTwo").find("li").eq(0).addClass("sub-active");
	});
</script>
</body>
</html>