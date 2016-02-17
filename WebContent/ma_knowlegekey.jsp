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

				<h2 class="sub-header">知识点管理</h2>
				<form class="form-horizontal" role="form">
				   <div class="form-group">
				      <label for="firstname" class="col-sm-2 control-label">增加知识点：</label>
				      <div class="col-sm-3">
				         <input type="text" class="form-control" id="keyName" 
				            placeholder="请输入知识点">
				      </div>
				      <button type="button" id="addKey" class="btn btn-info col-sm-1">增加</button>
				      <img alt="loading" src="images/loading.gif" height="30px" width="30px" class="hidden load1">
				      <div class="col-sm -2 msg"></div>
				   </div>
				</form>
				
				
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>编号</th>
								<th>知识点名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="abstract">
						</tbody>
						
					</table>
					
					<ul class="pager">
			 			<li><a class ="pageButton" name="sy">首页</a></li>
			 			<li><a class ="pageButton" name="syy">上一页</a></li>
			 			<li><a class ="pageButton" name="xyy">下一页</a></li>
			 			<li><a class ="pageButton" name="wy">尾页</a></li>
			 			<li><a>当前页码：<span id="showCurrnetPage">1</span></a></li>
					</ul>
					
				</div>
			</div>
		</div>
	</div>
<div style="display: none;">
	<div id="cboxLoadedContent" >
		<form class="form-horizontal" role="form">
						
			<div class="form-group">
			  <div class="col-sm-3"></div>
		      <label for="firstname" class="col-sm-4 control-label">修改知识点</label>
			  <div class="col-sm-5"></div>
	   		</div>

			<div class="form-group">
		      <label for="firstname" class="col-sm-2 control-label">知识点：</label>
		      <div class="col-sm-8">
		         <input type="text" class="form-control" id="updateKp">
	     	  </div>
	   		</div>
			<div class="form-group">
		      <div class="col-sm-5">
	     	  </div>
		      <div class="col-sm-2">
		      		<button class="btn btn-danger" id="updateKey" name="">修改</button>
	     	  </div>
		      <div class="col-sm-5">
	     	  </div>
	   		</div>
		</form>
   </div>
</div>	

<script type="text/javascript">
var items = 10;	
 /*public function*/
 var loadMessages = function(start){
		$.ajax({
			url:"pageskeypoint/"+start+"/"+items,
			type:"get",
			dataType:"json",
			//data:{"start":0,"itemNums":10},
			success:function(data){
				 $("tbody.abstract").empty();
				 $.each(data,function(){
					$(".table tbody").append("<tr><td>"+this.number+"</td><td>"+this.keypoint+"</td>"
					+"<td>"
					+"<button type='button' name='updateKey' class='btn btn-primary' tid='"+this.id+"'>修改</button>&nbsp;&nbsp;"
					+"<button type='button' name='delete' class='btn btn-danger' ky='kp' tid='"+this.id+"'>删除</button>"
					+"</td></tr>");
				}); 
				 
				$.getScript("js/colorboxconfig.js");
				
			},
			error:function(data,d1,d2){
				console.log(data,d1,d2);
			}
		});
 }

 $(function(){
		/* init */
		 loadMessages(1);
		 pagebutton("getkpages",items);
		/*增加知识点*/
		 $("#addKey").click(function(){
			 var keyName = $("#keyName").val();
			 if($.trim(keyName)==""){
				 $(".msg").text("知识点不能为空");
				 return false;
			 }
			 
			 $(".load1").removeClass("hidden");
			 $.get("addkey?keyname="+keyName,function(data){
				 $(".load1").addClass("hidden");
				 $(".msg").text(data.result.msg);
				 window.location.reload();
			 });
		 });
		
		/*修改知识点*/
		$("#updateKey").click(function(){
			var itId = $(this).attr("name");
			var key = $("#updateKp").val();
			
			$.get("updatekey/"+itId+"?key="+key,function(data){
				/*关闭colorbox*/
				$.colorbox.close();
					//success
					var r = data.result;
					$.confirm({
						title : "提示",
						text:r.msg,
						confirmButton : "确认",
						confirmButtonClass: "btn-danger",
						cancelButtonClass: "hidden"
					});
					if(r.code==0){
						console.log($("button[tid="+itId+"]").parent().prev().text(data.data));
					}
					
			});
			return false;
		});
	})
</script>

</body>
</html>