(function($){
	
	$.fn.tipbox = function(){
		
		$(".delx-box",$(this)).click(function(){
			$(this).parent("div").fadeTo("fast",0.0).slideUp(400);
		},function(){
			$(this).parent("div").remove();
		});
		$(".succes-big, .warning-big, .error-big, .info-big, .msg-big, .succes-in, .warning-in, .error-in, .info-in, .msg-in",$(this)).click(function(){
			$(this).fadeTo("fast",0.0).slideUp(400);
		},function(){
			$(this).remove();
		});
		$(".succes-big, .warning-big, .error-big, .info-big, .msg-big, .succes-in, .warning-in, .error-in, .info-in, .msg-in",$(this)).hover(function(){
			$(this).children(".delx").show();
		},function(){
			$(this).children(".delx").hide();
		});
		
		return $(this);
	};
	
})(jQuery);
