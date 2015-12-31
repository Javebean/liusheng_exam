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
   					<strong>注意！</strong>综合上传时，请务必保持sheet1,sheet2,sheet3分别是单选题，填空题，问答题的顺序！
				</div>
				<div class="table-responsive">
					<form method="POST" enctype="multipart/form-data" action="upload">
						<input type="file" class="form-control" name="file"> <br />
						<input type="hidden" name="type" value="2">
						<input type="submit" class ="btn btn-danger" value="确认上传">
					</form>
				</div>
				
				<hr>
				<h4 class="sub-header">手动上传</h4>
				<div class="alert alert-warning">
   					<strong>注意！</strong>题目中的填空请使用&nbsp;“#”&nbsp;替换！正确答案中多余的空不填即可。
				</div>

				<form class="form-horizontal" role="form">
				   <div class="form-group">
				      <label for="firstname" class="col-sm-1 control-label">题目：</label>
				      <div class="col-sm-11">
				         <textarea class="form-control" rows="2" placeholder="请输入题目"></textarea>
				      </div>
				   </div>
				   
				   <div class="form-group">
				      <label  class="col-sm-2 control-label">正确答案：</label>
				      <div class="col-sm-2">
					         <input type="text" class="form-control" id="lastname" 
					            placeholder="填空1">
				      </div>
				      <div class="col-sm-2">
					         <input type="text" class="form-control" id="lastname" 
					            placeholder="填空2">
				      </div>
				      <div class="col-sm-2">
					         <input type="text" class="form-control" id="lastname" 
					            placeholder="填空3">
				      </div>
				      <div class="col-sm-2">
					         <input type="text" class="form-control" id="lastname" 
					            placeholder="填空4">
				      </div>
				   </div>
				 
				   <div class="form-group">
				      <div class="col-sm-1">
				         <input type="submit" id ="submit2" class ="btn btn-danger" value="确认上传">
				      </div>
				   </div>
				</form>
				
				
				
			</div>
		</div>
	</div>

</body>
</html>