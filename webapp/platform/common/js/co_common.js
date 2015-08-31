$(function(){
	// 公共表格隔行变色
	$(".co_table tr:even").css("background-color", "#f3f1f1");

	// 右侧内容区最小高度
	function minHeight(){
		//var winHeight=$(window).height();
		//var otherHeight=$(".co_header").outerHeight(true)+$(".co_nav").outerHeight(true)+$("#footer").outerHeight(true)+2*(parseInt($(".co_main").css("padding-top")));
		//var mainHeight=winHeight-otherHeight;
		//if($(".co_maincontent").height()<winHeight){
		//	$(".co_main").css("min-height",mainHeight);
		//}else{
		//	$(".co_main").css("min-height","");
		//}
		var height=$(window).height()-90;
		$(".main").css("min-height",height);
	}
	minHeight();
	$(window).resize(function() {
		minHeight();
		var height=$(window).height()-160;
		$(".details").css("min-height",height);
	});

});


































