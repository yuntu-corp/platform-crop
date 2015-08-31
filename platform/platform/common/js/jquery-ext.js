//对jquery的扩展
$.extend({
	browser:{//jqeury1.9去除了browser属性
		mozilla : /firefox/.test(navigator.userAgent.toLowerCase()),
		webkit : /webkit/.test(navigator.userAgent.toLowerCase()),
		opera : /opera/.test(navigator.userAgent.toLowerCase()),
		msie : /msie/.test(navigator.userAgent.toLowerCase()),
		version : (/(webkit)[ \/]([\w.]+)/.exec(navigator.userAgent.toLowerCase()) || /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(navigator.userAgent.toLowerCase()) || /(msie) ([\w.]+)/.exec(navigator.userAgent.toLowerCase()) || navigator.userAgent.toLowerCase().indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+))?/.exec( navigator.userAgent.toLowerCase() ) || [])[2] || "0",
		isMobilePlatforms:(function(){
			var userAgent = navigator["userAgent"]["toLowerCase"]();
			if (userAgent["indexOf"]('iphone') > 0 
				|| userAgent["indexOf"]('ipod') > 0 
				|| userAgent["indexOf"]('ipad') > 0          
				|| userAgent["indexOf"]('Android') > 0 
				|| userAgent["indexOf"]('ios') > 0
				|| userAgent["indexOf"]('symbianos') > 0){
				return true; 
			}
			return false;
		})()
	},
	uuid:function(){
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		    var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
		    return v.toString(16);
		});
	},
	htmlEncode:function(value){
		return !value ? value : String(value).replace(/&/g, "&amp;").replace(/\"/g, "&quot;").replace(/\'/g, "&#39;").replace(/ /g, "&nbsp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
	},
	htmlDecode:function(value){
		return !value ? value : String(value).replace(/&amp;/g, "&").replace(/&quot;/g, "\"").replace(/&#39;/g, "\'").replace(/&nbsp;/g, " ").replace(/&lt;/g, "<").replace(/&gt;/g, ">");
	},
	nano:function(template,data){//模板
		return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
			var keys = key.split("."), value = data[keys.shift()];
			$.each(keys, function() {
				value = value[this];
			});
			return (value === null || value === undefined) ? "" : value;
		});
	}
});

$.hz={};