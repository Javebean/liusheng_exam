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
			window.location.reload();
		},
		error:function(){
			console.log("ajax error");
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
	var html="";
	$.get("getallkp",function(data){
		$.each(data,function(){
			//checkbox+知识点+输入框
			html+='<input type="checkbox" name="'+this.id+'">'+this.keypoint+'&nbsp;×&nbsp;'+'<input value="" type="text" class="form-control" style="width:40px; height:25px;display:inline-block">&nbsp;&nbsp;';
		});
		$(".allkp").append(html);
	});
}

var getAllkp_upload = function(){
	var html="";
	$.get("getallkp",function(data){
		$.each(data,function(){
			//checkbox+知识点+输入框
			html+='<input type="radio" name="keypointId" value="'+this.id+'">'+this.keypoint+"&nbsp;&nbsp;";
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
				cancelButtonClass: "hidden"
			});
		
	});
	
}
