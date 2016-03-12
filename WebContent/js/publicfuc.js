/**
 * 
 */
var setCookie = function (name,value)
{
    var exp = new Date();
    exp.setTime(exp.getTime() + 60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

var getCookie = function (name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

var deleteCookie = function(name,value){
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	
}

var deleteItem = function (url,dId){
	$.ajax({
		url:url+"/"+dId,
		type:"get",
		dataType:"json",
		success:function (data){
			if(data){
				window.location.reload();
			}else{
				alert("删除失败！");
			}
		},
		error:function(){
			alert("删除失败！");
		} 
		
	});
}

/*查询知识点，在colorbox 的pop up中*/
var getAllkp = function(){
	$.get("getallkp",function(data){
		var html = "";
		$.each(data,function(index){
			if(index%3==0){
				html+="<tr>";
			}
			html+='<td><input type="radio" name="keypoint" id="kp'+this.id+'" /><label for="kp'+this.id+'">'+this.keypoint+'</label></td>';
			if(index%3==2){
				html+="</tr>";
			}
			
		});
		var s = html.substring(html.length-2,html.length-1);
		if(s=="d"){
			html+="</tr>";
		}
		$("#kpArea").after(html);
	});
}

/*出题的知识点*/
var getAllkp_createExam = function(){
	$.getJSON("getallkp",function(data){
		//checkbox+知识点+输入框
		$.each(data,function(index0){
			var html="";
			$.each(this,function(index){
				html+='<input type="checkbox" name="'+this.keypointId+'">'+this.keypoint+'&nbsp;&nbsp;&nbsp;';
				if((index+1)%6==0){
					html+="<br/><br/>";
				}
			});
			$(".allkp").eq(index0).append(html);
		});
	});
}

var getAllkp_upload = function(){
	var html="";
	$.get("getallkp",function(data){
		$.each(data,function(){
			//checkbox+知识点+输入框
			html+='<input type="radio" name="keypointId" value="'+this.id+','+this.keypoint+'">'+this.keypoint+"&nbsp;&nbsp;";
		});
		$(".allkp").append(html);
	});
}





/*审核通过题目*/
var agreeQues = function(url,id){
	$.get(url+"/"+id,function(data){
			$.colorbox.close();
			$.confirm({
				title : "提示",
				text:data?"审核通过":"审核失败",
				confirmButton : "确认",
				confirmButtonClass: "btn-danger",
				cancelButtonClass: "hidden",
				confirm:function(){
					window.location.reload();
				}
			});
		
	});
	
}


//分页按钮
var pagebutton = function(pageNumUrl,items){
	var curpage =1;
	$(".pageButton").click(function(){
		//再去查一共多少页
		var name = $(this).attr("name");
		$.get(pageNumUrl,{"items":items},function(pageNums){
			if(name=="sy"){
				curpage = 1;
			}else if(name=="syy"){
				curpage = --curpage>1?curpage:1;
			}else if(name=="xyy"){
				curpage = ++curpage>pageNums?pageNums:curpage;
			}else if(name=="wy"){
				curpage=pageNums;
			}
			loadMessages(curpage);
			$("#showCurrnetPage").text(curpage+"/"+pageNums);
			
		});
	});
	
}

