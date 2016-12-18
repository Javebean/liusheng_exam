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

				<h2 class="sub-header">单选题审核</h2>
				<ul class="nav nav-tabs">
				   <li class="active" ><a href="ma_simple.jsp">未审核</a></li>
				   <li><a href="ma_simple_audited.jsp">已审核</a></li>
				</ul>
				<div class="table-responsive">
					<table class="table table-striped" id='opera_table'>
						<thead>
							<tr>
								<th>题目编号</th>
								<th>题目</th>
								<th>答案</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id='abstract'>
						</tbody>
						
					</table>
					
					<ul id="pager" class="pager">
			 			<li><a class ="pageButton hand-point" name="sy">首页</a></li>
			 			<li><a class ="pageButton hand-point" name="syy">上一页</a></li>
			 			<li><a class ="pageButton hand-point" name="xyy">下一页</a></li>
			 			<li><a class ="pageButton hand-point" name="wy">尾页</a></li>
			 			<li><a class="hand-point">当前页码：<span id="showCurrnetPage" >1</span></a></li>
					</ul>
					
				</div>
			</div>
		</div>
	</div>
<div style="display: none;">
	<div id="cboxLoadedContent" >
		<table style="width: 80%;margin: 0 auto;" border="0">
			<thead><tr><td colspan="4">题目详细</td></tr></thead>
			<tbody>
				<tr><td>题目：</td></tr>
				<tr>
					<td colspan="4">
						<textarea id="question" class="form-control" rows="2"></textarea>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				
				<tr>
					<td colspan="4">
						<div class="alert alert-warning">
		   					<strong>注意！</strong>请慎重审核答案和所属知识点，该答案将作为该题的标准（参考）答案！
						</div>
					</td>
				</tr>
				<tr><td>选项：</td></tr>
				<tr>
					<td colspan="2"><input type="radio" class='answer' name="answer" id="A" />A：<label for="A" id='la'></label></td>
					<td colspan="2"><input type="radio"  class='answer' name="answer" id="B" />B：<label for="B" id='lb'></label></td>
				</tr>
				<tr>
					<td colspan="2"><input type="radio" class='answer' name="answer" id="C" />C：<label for="C" id='lc'></label></td>
					<td colspan="2"><input type="radio" class='answer' name="answer" id="D" />D：<label for="D" id='ld'></label></td>
				</tr>
				
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td colspan="4">所属知识点：</td>
				</tr>
				<tr>
					<td><select id="kpArea" class="form-control"></select> </td>
				</tr>
				
			</tbody>
			<tfoot>
				<tr><td>&nbsp;<hr></td></tr>
					<tr>
						<td colspan="4">
							<button type="button" class="btn btn-danger col-sm-2 col-sm-offset-5"  id="agree" agreeId="" >通     过</button>
						</td>
					</tr>
				</tfoot>
		</table>
	</div>
</div>	
<script type="text/javascript">
var items =10;
/*public function*/
var loadMessages = function(start){
	$.ajax({
		url:"getpagess/"+start+"/"+items+"/0",
		type:"get",
		dataType:"json",
		//data:{"start":0,"itemNums":10},
		success:function(data){
			if(isEmpty(data)){
				console.log('no data has has found');
				return;
			}
			
			 var html = "";
			 for(var i=0,len=data.length;i<len;i++){
				 var obj = data[i];
				 html += "<tr id='"+obj.id+"'><td>"+obj.number+"</td><td class='problem'>"+obj.problem+"</td><td>"+obj.answer+"</td><td>未审核</td>"
				 +"<td class='hidden opa'>"+obj.optionA+"</td><td class='hidden opb'>"+obj.optionB+"</td>"
				 +"<td class='hidden opc'>"+obj.optionC+"</td><td class='hidden opd'>"+obj.optionD+"</td>"
				+"<td>"
				+"<button type='button' class='btn btn-primary' aw ='"+obj.answer+"' kpId='"+obj.keypointId+"'>审核</button>&nbsp;&nbsp;"
				+"<button type='button' class='btn btn-danger'>删除</button>"
				+"</td></tr>";
				 
			 }
			 document.getElementById('abstract').innerHTML = html;
		},
		error:function(data,d1,d2){
			console.log(data,d1,d2);
		}
	});
}
	 $(function(){
		//导航active
		$("#collapseOne").find("li").eq(0).addClass("sub-active");
		
		/* init */
		 loadMessages(1);
		 pagebutton("getsimpages",items);
		
		 /*查询知识点*/
		 getAllkp();
		 /*审核通过*/
		 $("#agree").click(function(){
			 var question = $("#question").val();
			 var $check_answer = $("input.answer:checked");
			 var optionSy = $check_answer.attr("id");
			 var option = $check_answer.next().text();
			 
			 var e = document.getElementById("kpArea");
			 var keypoint = e.options[e.selectedIndex].text;
			 var keypointId = e.value;
			 var param = {"agreeId":$(this).attr("agreeId"),"question":question,"optionSy":optionSy,"option":option,"keypoint":keypoint,"keypointId":keypointId};
			 agreeQues("agreeques",param);
		 });
		 
	})
	
	
	//弹出colorbox
		$('#abstract').on('click','tr',function(e){
			var tar = e.target;
			var ele = e.currentTarget;
			var qid = ele.id;
			if(tar.className=='btn btn-primary'){//审核
				$.colorbox({
					transition : "elastic", // fade,none,elastic
					width : "55%",
					height : "88%",
					inline : true,
					href : "#cboxLoadedContent",
					opacity : 0.5,
					scrolling : true,
					overlayClose : false,
					close : "关闭",
					onComplete:function(){
						var text = $(ele).find('td.problem').text();
						$("#question").val(text);
						
						//回显答案
						var aw = $(tar).attr("aw");
						$('#'+aw).attr("checked","checked");
						
						//回显知识点 kpId在审核的按钮上，所以能获取到
						var kpId = $(tar).attr("kpId");
						document.getElementById('kpArea').value= kpId;
						
						//回显四个选项
						var opa = $(ele).find('td.opa').text();
						$('#la').text(opa);
						var opb = $(ele).find('td.opb').text();
						$('#lb').text(opb);
						var opc = $(ele).find('td.opc').text();
						$('#lc').text(opc);
						var opd = $(ele).find('td.opd').text();
						$('#ld').text(opd);
						
						//获取题目id
						$("#agree").attr("agreeId",qid);
						
					}
				});
			} else if(tar.className = 'btn btn-danger'){
				$.confirm({
					title : "提示",
					text:"确认删除？",
					confirm : function(button) {
						nativeAjax('get','deletesim/'+qid,function(e){
							var res = getResult(e);
							if(res){
								ele.innerHTML = '';
							}
						});
					},
					
					confirmButton : "确认",
					cancelButton : "取消",
					confirmButtonClass: "btn-danger",
					cancelButtonClass: "btn-default"
				});
			}
		});
</script>

</body>
</html>