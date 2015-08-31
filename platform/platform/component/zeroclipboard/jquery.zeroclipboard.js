/**
 * 把ZeroClipboard包装为jquery插件，调用插件中的方法。
 */
(function($){
	
	$.fn.extend({
		
		zeroclipboard:function(param,showTips){
			if(typeof showTips == "undefined"){
				showTips = true;
			}
			if($(this).size() == 0){
				return $(this);
			}
			if(param && typeof (param) == 'string'){
				$(this).attr("data-clipboard-target",param);
				var clip = new ZeroClipboard($(this)[0]);
				clip.on("complete",function(client,args){
					if(showTips){
						var dom = $("<span style='font-size: 14px'>复制成功</span>");
						$(this).after(dom);
						dom.fadeOut(3000,function(){
							dom.remove();
						});
					}
				});
			}
			return $(this);
		}
	
	});
	
	
})(jQuery);

//用法
//创建
//$("#aaa").zeroclipboard("iptId");

