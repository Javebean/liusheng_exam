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
<%@include file="public/public_1.html"%>

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
				<h2 class="sub-header">出题</h2>
				
				<div class="page-header">
					 <h4>1.选择单选题要出的知识点：（共10题）</h4>
				</div>
				<div class="allkp">
				</div>
				
				<hr>
				<div class="page-header">
					 <h4>2.选择填空题要出的知识点：（共10个空）</h4>
				</div>
				<div class="allkp">
				</div>
			
				<hr>
				<div class="page-header">
					 <h4>3.选择问答题要出的知识点：（共6题）</h4>
				</div>
				<div class="allkp">
				</div>
				<hr>
				<div class="col-xs-4 col-xs-offset-4">
					<button type="button" id="createExam" class="btn btn-danger">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出&nbsp;题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
					<img alt="loding" src="images/loading.gif" class="hidden load">
					<p class="tipmes"></p>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
		getAllkp_createExam();
		$("#createExam").click(function(){
			$(".load").removeClass("hidden");
			$(".tipmes").text("");
			//检查单选 填空 问答 一共选择的数量
			var length1 = $(".allkp").eq(0).children("input:checked").length;
			var length2 = $(".allkp").eq(1).children("input:checked").length;
			var length3 = $(".allkp").eq(2).children("input:checked").length;
			if(length1==0){
				$(".load").addClass("hidden");
				$(".tipmes").text("请勾选单选题出题知识点");
				return false;
			}
			if(length2==0){
				$(".load").addClass("hidden");
				$(".tipmes").text("请勾选填空题出题知识点");
				return false;
			}
			if(length3==0){
				$(".load").addClass("hidden");
				$(".tipmes").text("请勾选问答题出题知识点");
				return false;
			}
			
			//输入框里面填的数字之和不能大于所给的题目数量
			var a = $(".allkp").eq(0).children("input:checked").map(function(){
				return this.name;
			}).get();
			var b = $(".allkp").eq(1).children("input:checked").map(function(){
				return this.name;
			}).get();
			var c = $(".allkp").eq(2).children("input:checked").map(function(){
				return this.name;
			}).get();
			var param = {"simple":JSON.stringify(a),"fill":JSON.stringify(b),"inter":JSON.stringify(c)};
			$.get("cratexam",param,function(data){
				console.log(data);
			});
		});
	})

</script>
</body>
</html>