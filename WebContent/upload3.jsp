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

				<h2 class="sub-header">上传问答题</h2>
				<div class="alert alert-warning">
   					<strong>注意！</strong>上传问答题时，Excel表格一行为一题，每一行中请保持依次为“题目”，“答案”，“所属知识点”的顺序！
   					<br><span class="msg">请不要在excel中上传带有图片的题目，带图片请手动在下面上传</span>
				</div>
				<div class="table-responsive">
						<input  id='excel'  type="file" class="form-control" name="file" required="required"> <br />
						<button id="submit" class ="btn btn-danger" >确认上传</button>
						<img alt="loading" src="images/loading.gif" class="col-sm-1 loading hidden" id='excelloading '>
						<p class="tipmes" id='excelmsg'></p>
				</div>
				
				<hr>
				<h4 class="sub-header">手动上传</h4>
				<form class="form-horizontal">
					<div class="form-group">
				      <label for="firstname" class="col-sm-1 control-label">题目：</label>
				      <div class="col-sm-11">
				         <textarea class="form-control" rows="2" placeholder="请输入题目" id="problem"></textarea>
				      </div>
				   </div>
				   <h5 class="sub-header">图片上传（如果该题没有图片则不用上传）</h5>
    				<div id="blah" style="width: 400px;height: 400px;border: solid 1px #ccc;" class="col-sm-offset-4">
    				</div>
    				<br>
    				<label class="btn btn-primary col-sm-1 col-sm-offset-4">
    					<input type="file" id="imgInp" name="file" accept="image/png, image/jpeg, image/gif"/>
    					<span>上传图片</span>
					</label>
    				<br>
    				<br>
	    			<br>
    				
				   <div class="form-group">
				      <label for="firstname" class="col-sm-1 control-label">答案：</label>
				      <div class="col-sm-11">
				         <textarea class="form-control" rows="4" placeholder="请输入答案" id="answer"></textarea>
				      </div>
				   </div>
				    
				   <div class="form-group">
				      <label for="firstname" class="col-xs-1 control-label">所属知识点：</label>
				      
				       <select id='kpArea' style="width: 20%;" class="form-control" name='keypointId'>
				      </select>
				   </div>
				 
				   <div class="form-group">
				      <div class="col-sm-1">
				         <input type="submit" id ="submit2" class ="btn btn-danger" value="确认上传">
				      </div>
				      
				      <img alt="loading" src="images/loading.gif" class="col-sm-1 loading hidden">
				      <p class="tipmes col-sm-4"></p>
				   </div>
				</form>
				
			</div>
		</div>
	</div>
	
<script type="text/javascript">
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	        	$("#blah").append('<img id ="uploadImg" alt="your image"/>');
	            $('#uploadImg').attr('src', e.target.result).css({"width":"400px","height":"400px"});
	        }

	        reader.readAsDataURL(input.files[0]);
	    }
	}
	$(function(){
		//导航active	
		$("#collapseTwo").find("li").eq(2).addClass("sub-active");
		
		
		
		//上传excel
		$("#submit").click(function(){
			 $("#excelmsg").text("");
        	 $("#excelloading").removeClass('hidden');
			 var formData = new FormData();
			 formData.append("file", document.getElementById('excel').files[0]);
			 formData.append("type", 3);
             $.ajax({
                 url: 'upload',
                 type: 'POST',
                 data: formData,
                 cache: false,
                 contentType: false,
                 processData: false,
                 success: function (data) {
                     var res = jsonParse(data);
                    	console.log(res);
                     if(res.code!=0){
                    	 $("#excelmsg").text(res.msg);
                     } else {
                    	 $("#excelmsg").text("添加成功");
                     }
                     $("#excelloading").addClass('hidden');
                 }
             });
             return false;
		});
		
		
		
		
		//加载所属知识点
		getAllkp();
		
		$("#imgInp").change(function(){
		    readURL(this);
		});
		
		//上传
		$("#submit2").click(function(){
			var pro = $("#problem").val();
			var answer = $("#answer").val();
			if(""==$.trim(pro)){
				$("img.loading").addClass("hidden");
				$("p.tipmes").text("请填写问答题题目！");
				return false;
			}
			if(""==$.trim(answer)){
				$("img.loading").addClass("hidden");
				$("p.tipmes").text("请填写问答题答案！");
				return false;
			}
			
			 var formData = new FormData();
			 formData.append("problem", pro);
			 formData.append("answer", answer);
			 var kpArea = document.getElementById('kpArea');
			 
			 formData.append("keypoint", kpArea.options[kpArea.selectedIndex].text);
			 formData.append("keypointId", kpArea.value);
			 formData.append("file", document.getElementById('imgInp').files[0]);
             $.ajax({
                 url: 'addinter',
                 type: 'POST',
                 data: formData,
                 cache: false,
                 contentType: false,
                 processData: false,
                 success: function (data) {
                     var res = jsonParse(data);
                     if(res.code==0){
                    	 $("p.tipmes").text("添加成功");
                    	 $("#problem").val('');
             			 $("#answer").val('');
                     }
                 }
             });
             return false;
			
			
		});
		
	})
	
	
	
</script>
</body>
</html>