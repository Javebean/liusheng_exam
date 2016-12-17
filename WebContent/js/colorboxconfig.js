
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
