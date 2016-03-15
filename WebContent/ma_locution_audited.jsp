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

				<h2 class="sub-header">问答题审核</h2>
				<ul class="nav nav-tabs">
				   <li><a href="ma_locution.jsp">未审核</a></li>
				   <li class="active"><a href="ma_locution_audited.jsp">已审核</a></li>
				</ul>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>题目编号</th>
								<th>题目</th>
								<th>答案</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="abstract">
						</tbody>
						
					</table>
					
					<ul class="pager">
			 			<li><a class ="pageButton hand-point" name="sy">首页</a></li>
			 			<li><a class ="pageButton hand-point" name="syy">上一页</a></li>
			 			<li><a class ="pageButton hand-point" name="xyy">下一页</a></li>
			 			<li><a class ="pageButton hand-point" name="wy">尾页</a></li>
			 			<li><a class="hand-point">当前页码：<span id="showCurrnetPage">1</span></a></li>
					</ul>
					
				</div>
			</div>
		</div>
	</div>
	
<div style="display: none;">
	<div id="cboxLoadedContent" >
		   <br>
		   <br>
		   <br>
		<div class="panel panel-default">
		   <div class="panel-heading">
		      <h3 class="panel-title text-center">题目详细</h3>
		   </div>
		   <table class="table">
		      <tr><th>题目：</th><th id="show_pr"></th></tr>
		      <tr><td>答案：</td><td id="show_as"></td></tr>
		      <tr><td>所属知识点：</td><td id="show_kp"></td></tr>
		   </table>
		   
		   <div id="interimg">
		   
		   </div>
		</div>
	</div>
</div>
<script type="text/javascript">
var items = 10;
 /*public function*/
 var loadMessages = function(start){
		$.ajax({
			url:"getpagesinter/"+start+"/"+items+"/1",
			type:"get",
			dataType:"json",
			//data:{"start":0,"itemNums":10},
			success:function(data){
				 $("tbody.abstract").empty();
				 $.each(data,function(){
					$("tbody.abstract").append("<tr><td>"+this.number+"</td><td>"+this.problem+"</td><td>"+this.answer+"</td><td>已审核</td>"
					+"<td>"
					+"<button type='button' class='btn btn-primary showinfo' kp='"+this.keypoint+"' as='"+this.answer+"' pr='"+this.problem+"' imgurl='"+this.imgUrl+"'>查看</button>"
					+"&nbsp;&nbsp;<button type='button' name='delete' class='btn btn-danger' ky='inter' tid='"+this.id+"'>删除</button>"
					+"</td></tr>");
				 $.getScript("js/colorboxconfig.js");
				}); 
			},
			error:function(data,d1,d2){
				console.log(data,d1,d2);
			}
		});
 }
	 $(function(){
		//导航active
		$("#collapseOne").find("li").eq(2).addClass("sub-active");
		 
		/* init */
		 loadMessages(1);
		 pagebutton("getinterpages",items);
	})
</script>

</body>
</html>