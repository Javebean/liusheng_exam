//$(function() {
	$("button[name=confirm1]").colorbox({
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
			var text = $(this).parent().prev().prev().prev().text();
			$("[symbol=question]").val(text);
			var aw = $(this).attr("aw");
			if(aw=="A"||aw=="a"){
				$("#A").attr("checked","checked");
			}
			if(aw=="B"||aw=="b"){
				$("#B").attr("checked","checked");
			}
			if(aw=="C"||aw=="c"){
				$("#C").attr("checked","checked");
			}
			if(aw=="D"||aw=="d"){
				$("#D").attr("checked","checked");
			}
			
			/*回显知识点 kpId在审核的按钮上，所以能获取到*/
			var kpId = $(this).attr("kpId");
			$("#kp"+kpId).attr("checked","checked");
			/*回显四个选项*/
			var $option = $(this).next();
			$("#A").next().text($option.attr("option1"));
			$("#B").next().text($option.attr("option2"));
			$("#C").next().text($option.attr("option3"));
			$("#D").next().text($option.attr("option4"));
			
			/*获取题目id*/
			var qid = $(this).attr("qId");
			$("#agree").attr("agreeId",qid);
			
		}
	});
	$("button[name=confirm2]").colorbox({
		transition : "elastic", // fade,none,elastic
		width : "55%",
		height : "82%",
		inline : true,
		href : "#cboxLoadedContent",
		opacity : 0.5,
		overlayClose : false,
		close : "关闭",
		onComplete:function(){
			var text = $(this).parent().prev().prev().text();
			$("[symbol=question]").val(text);
			/*回显知识点*/
			var kpId = $(this).attr("kpId");
			$("#kp"+kpId).attr("checked","checked");
			
			
			/*获取题目id*/
			var qid = $(this).attr("qId");
			$("#agree").attr("agreeId",qid);
		}
			
			
			
	});
	$("button[name=confirm3]").colorbox({
		transition : "elastic", // fade,none,elastic
		width : "55%",
		height : "82%",
		inline : true,
		href : "#cboxLoadedContent",
		opacity : 0.5,
		overlayClose : false,
		close : "关闭",
		onComplete:function(){
			var text = $(this).parent().prev().prev().prev().text();
			$("[symbol=question]").val(text);
			
			var answer = $(this).parent().prev().prev().text();
			$("[symbol=answer]").val(answer);
			/*回显知识点*/
			var kpId = $(this).attr("kpId");
			$("#kp"+kpId).attr("checked","checked");
			
			var imgurl = $(this).attr("imgurl");
			if(imgurl!="null"&&""!=imgurl){
				$("#inter_img").empty();
				$("#inter_img").append('<img alt="pic" src='+imgurl+'>');
			}else{
				$("#inter_img").empty();
				$("#inter_img").append('该题无图');
				
			}
			
			
			
			/*获取题目id*/
			var qid = $(this).attr("qId");
			$("#agree").attr("agreeId",qid);
		}
	});

	$("button[name=updateKey]").colorbox({
		transition : "elastic", // fade,none,elastic
		width : "50%",
		height : "30%",
		inline : true,
		href : "#cboxLoadedContent",
		opacity : 0.5,
		scrolling : true,
		overlayClose : false,
		close : "关闭",
		onComplete:function(){
	        $("#updateKp").val($(this).parent().prev().text());
	        $("#updateKey").attr("name",$(this).attr("tid"));
	    }
	});
	

	
	$("button[name=delete]").confirm({
			title : "提示",
			text:"确认删除？",
			confirm : function(button) {
				/*每个删除按钮上有个ky属性，根据ky来判断是那个类型的删除*/
				var ky = $(button).attr("ky");
				var deleteId = $(button).attr("tid");
				if(ky=="kp"){
					/**知识点的删除*/
					deleteItem("deletekey", deleteId);
				}else if(ky=="sim"){
					deleteItem("deletesim", deleteId);
				}else if(ky=="fill"){
					deleteItem("deletefb", deleteId);
				}else if(ky=="inter"){
					deleteItem("deleteil", deleteId);
				}
				
			},
			
			confirmButton : "确认",
			cancelButton : "取消",
			confirmButtonClass: "btn-danger",
			cancelButtonClass: "btn-default"
		});
//})

$("button.showinfo").colorbox({
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
		var pr = $(this).attr("pr");
		var as = $(this).attr("as");
		var kp = $(this).attr("kp");
		$("#show_pr").text(pr);
		$("#show_as").text(as);
		$("#show_kp").text(kp);
		
		var imgurl = $(this).attr("imgurl");
		if(imgurl!="null"&&""!=imgurl){
			$("#interimg").empty();
			$("#interimg").append('<img alt="pic" src='+$(this).attr("imgurl")+'>');
		}
	}
	
});