(function($){
	
	var methods = {
		init:function(){
			$(this).each(function(){
	        	if($(this).find(".box1-header").size() <= 0){//防止多次执行
	            	var box_content = [];
	            	box_content.push('<div class="box1-header">');
		            box_content.push('	<h2></h2>');
	            	box_content.push('	<div class="box1-header-ctrls">');
	            	box_content.push('		<span class="spin" alt=""></span>');
	            	box_content.push('		<a class="close" title="" href="javascript:void(null);"></a>');
	            	box_content.push('	</div>');
	            	box_content.push('</div>');
	            	
	            	$(this).wrapInner('<div class="box1-content"></div>');
	            	if ($(this).attr("angle") != null && $(this).attr("angle") == "corner") {
	            		$(this).addClass("corners shadow");
	            	}
	            	
	            	$(this).prepend($(box_content.join("")));
	            	
	            	
	            	if ($(this).attr("boxTitle") != null) {
	                	$(this).find(".box1-header h2").html($(this).attr("boxTitle"));
	            	}
	            	
		            if ($(this).attr("boxHeight") != null) {
		                $(this).find(".box1-content").height($(this).attr("boxHeight"));
		            }
	            	
	            	if ($(this).attr("boxState") != null && $(this).attr("boxState") == "close") {
		                $(this).find(".box1-content").slideToggle(200);
		                $(this).find(".box1-header-ctrls a").removeClass("close").addClass("open");
		            }
	            	
	            	if ($(this).attr("boxState") != null && $(this).attr("boxState") == "false") {
	            		$(this).find(".box1-header-ctrls").addClass("none");
	            	}
	            	
	            	$(this).box1("control");
	            	$(this).box1("resetWidth");
	            	
	            	return $(this);
	        	}
			});
		},
		resetWidth:function(){
			$(this).each(function(){
				if ($(this).attr("boxWidth") != null) {
		            var C = $(this).attr("boxWidth");
		            var J = C.substr(C.length - 1, 1);
		            if (J == "%") {
		                $(this).width(C);
		                //百分百时减去边距宽度
		                //var S = Number($(this).width()-($(this).outerWidth()-$(this).width()));
		                //$(this).width(S);
		            } else {
		                var e = Number($(this).attr("boxWidth"));
		                $(this).width(e);
		            }
		        }else{
		        	$(this).width("100%");
		        	var P = Number($(this).width()-($(this).outerWidth()-$(this).width()));
		        	$(this).width(P);
		        	 
		        }
			});
			return $(this);
		},
		control:function(){
			$(this).each(function(){
				//添加点击展开合并时间
				$(this).find(".box1-header-ctrls a").unbind('click').bind("click",function(){
					
					$(this).parents(".box1-header").next(".box1-content").slideToggle(200);
					
					var btnclass = $(this).attr("class");
					
					if(btnclass=="open"){
						$(this).removeClass("open").addClass("close");
					}else{
						$(this).removeClass("close").addClass("open");
					}
					
					$(this).parent(".box1-header-ctrls").find("span.spin").fadeIn(400).show(500,function(){
						$(this).fadeOut(350);
					});
					
				});
			});
			return $(this);
		}
	};
	
	$.fn.extend({
		
		box1:function(method){
			
			if(!method){
				return $(this).box1("init");
			}
			
			if(method && typeof (method) == 'string' && methods[method]){
				return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
			}
			
		}
	
	});
	
})(jQuery);