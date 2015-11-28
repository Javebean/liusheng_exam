$(function(){
	$("#submit").click(function(){
		$.ajax({
			url:"upload",
			type:"post",
			dataType:"json",
			success:function(data){
				console.log(data);
			},
			error:function(data){
				console.log(data);
			}
			
		});
		return false;
	});
})