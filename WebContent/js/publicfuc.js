/**
 * 
 */
var setCookie = function (name,value)
{
    var exp = new Date();
    exp.setTime(exp.getTime() + 60*60*10000);
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

/**
 * Tools
 */
var isEmpty =  function(str){
	return str=="" || str==null || str=="null" || str==undefined;
}


var isNotEmpty =  function(str){
	return str!="" && str!=null && str!="null" && str!=undefined;
}


/**
 * native ajax
 */
function nativeAjax(type,url,ajaxdone,param){
	param = (typeof param === 'undefined') ? '' : param;
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onload =ajaxdone;
	xmlhttp.open(type,url,true);
	if(type=='get' || type=='GET'){
		xmlhttp.send();
		
	} else if(type=='post' || type=='POST'){
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
	}
	
}

/**
 * param progressEvent
 */
var getResult = function(e){
	return e.currentTarget.responseText;
}


/**
 * parser jsonstr to jsonobject
 */
var jsonParse = function(mystr){
	var res=eval('('+mystr+')');
	return res;
}


/*查询知识点，在colorbox 的pop up中*/
var getAllkp = function(){
	$.get("getallkpfromkp",function(data){
		var html = "";
		if(isEmpty(data)){
			return;
		}
		for(var i=0,len=data.length;i<len;i++){
			var obj = data[i];
			html += '<option value='+obj.id+'>'+obj.keypoint+'</option>';
		}
		document.getElementById('kpArea').innerHTML = html;
		
	});
}

/*出题的知识点*/
var getAllkp_createExam = function(){
	$.getJSON("getallkp",function(data){
		//checkbox+知识点+输入框
		$.each(data,function(index0){
			var html="";
			$.each(this,function(index){
				html+='<div style="width:150px;float:left;"><input type="checkbox" name="'+this.keypointId+'">'+this.keypoint+'&nbsp;&nbsp;&nbsp;</div>';
				if((index+1)%6==0){
					html+="<br/><br/>";
				}
			});
			$(".allkp").eq(index0).append(html);
		});
	});
}

/*审核通过题目*/
var agreeQues = function(url,param,ele){
	$.get(url,param,function(data){
		$('div.confirmation-modal,div.modal-backdrop').removeClass('hidden');
			$.colorbox.close();
			$.confirm({
				title : "提示",
				text:data?"审核通过":"审核失败",
				confirmButton : "确认",
				confirmButtonClass: "btn-danger",
				cancelButtonClass: "hidden",
				confirm:function(){
					if(ele){
						ele.innerHTML = '';
					}
				}
			});
			
			setTimeout(closeConfirm, 2000);
			function closeConfirm(){
				$('div.confirmation-modal,div.modal-backdrop').addClass('hidden');
				if(ele){
					ele.innerHTML = '';
				}
			}
		
	});
}


//分页按钮
var pagebutton = function(pageNumUrl,items){
	var curpage =1;
	$('#pager').on('click','a',function(e){
		var name = e.currentTarget.getAttribute('name');
		nativeAjax('get',pageNumUrl+'?items='+items,function(e){
			var pageNums = getResult(e);
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

