$(function(){
	$("button[name=confirm]").colorbox({
        transition: "elastic", // fade,none,elastic
        width: "55%",
        height: "82%",
        inline:true, 
        href:"#cboxLoadedContent",
        opacity:0.5,
        overlayClose:false,
        close: "关闭",
	});
})