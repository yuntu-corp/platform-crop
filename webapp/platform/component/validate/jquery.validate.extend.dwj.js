/**
 * jquery.validate 拓展  作者（邓文杰）
 */

/*
 * 保留两位小数以内的数字
 */
jQuery.validator.addMethod("decimal2", function(value, element) {
		var decimal2 = /^(([1-9][0-9]{1,10})|([1-9]{1,10}\.[0-9]{1,2})|[0-9])$/;
		return this.optional(element) || (decimal2.test(value));
}, "请输入正确的数字（小数点后最多保留两位数字）");

jQuery.validator.addMethod("ennum", function(value, element){
	return this.optional(element) ||/^[a-zA-Z0-9]+$/.test(value);
}, "只能包括英文字母和数字");

//判断中文字符 
jQuery.validator.addMethod("isChinese", function(value, element) {       
     return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);
}, "只能包含中文字符。"); 

// 手机号码验证    
jQuery.validator.addMethod("isMobile", function(value, element) {    
  var length = value.length;    
  return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(14[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));    
}, "请正确填写您的手机号码。");