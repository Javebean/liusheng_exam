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

				<h2 class="sub-header">上传填空题</h2>
				<div class="alert alert-warning">
   					<strong>注意！</strong>上传填空题时，Excel表格一行为一题，每一行中请保持依次为“题目”，“所属知识点”的顺序！
   					<br><span class="msg">请在题目中的要填空的词两边使用&nbsp;“&lt;&gt;”&nbsp;标记！</span>
				</div>
				<div class="table-responsive">
					<form method="POST" enctype="multipart/form-data" action="upload">
						<input type="file" class="form-control" name="file" required="required"> <br />
						<input type="hidden" name="type" value="2">
						<input type="submit" class ="btn btn-danger" value="确认上传">
					</form>
				</div>
				
				<hr>
				<h4 class="sub-header">手动上传</h4>
				<div class="alert alert-warning">
   					<strong>注意！</strong>请在题目中的要填空的词两边使用&nbsp;“&lt;&gt;”&nbsp;标记！
				</div>

				<form class="form-horizontal" role="form" id="uploadFillBlank">
				   <div class="form-group">
				      <label for="firstname" class="col-sm-1 control-label">题目：</label>
				      <div class="col-sm-11">
				         <textarea class="form-control" rows="2" placeholder="请输入题目" name="problem"></textarea>
				      </div>
				   </div>
				   
				   <div class="form-group">
				      <label for="firstname" class="col-xs-1 control-label">所属知识点：</label>
				      
				      <div class="allkp">
				      </div>
				   </div>
				 
				   <div class="form-group">
				      <div class="col-sm-1">
				         <button id ="submit2" class ="btn btn-danger">确认上传</button>
				      </div>
				      <img alt="loading" src="images/loading.gif" class="col-sm-1 loading hidden">
				      <p class="tipmes col-sm-2"></p>
				   </div>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
	getAllkp_upload();
	
	$(function(){
		$("#submit2").click(function(){
			$(".loading").removeClass("hidden");
			$.post("addfb",$("#uploadFillBlank").serializeArray(),function(data){
				if(data){
					//添加填空题成功
					$(".loading").addClass("hidden");
					$(".tipmes").text("上传成功");
				}else{
					$(".tipmes").text("上传失败");
				}
			});
			return false;
		});
	})
	
</script>
</body>
</html>