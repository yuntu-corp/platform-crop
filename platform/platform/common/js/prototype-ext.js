//对javascript原生对象的扩展

//Namespace扩展
Namespace = new Object();
// 注册命名空间register函数，参数为命名空间全路径，如"com.hzth.dc.common"
Namespace.register = function(fullNS){
    var nsArray = fullNS.split('.');
    var sEval = "";
    var sNS = "";
    for (var i = 0; i < nsArray.length; i++){
        if (i != 0) sNS += ".";
         sNS += nsArray[i];
        // 依次创建构造命名空间对象（假如不存在的话）
         sEval += "if (typeof(" + sNS + ") == 'undefined') " + sNS + " = new Object();";
     }
    if (sEval != "") eval(sEval);
};

//string类的扩展
/**
 * 判断字符串是否以某个字符结尾
 * @param {Object} s1
 * @param {Object} s2
 * @return {TypeName} 
 */
String.prototype.endwith = function endWith(str) {
	if (this.length < str.length)
		return false;
	if (this == str)
		return true;
	if (this.substring(this.length - str.length) == str)
		return true;
	return false;
};

/** 
 * 用正则表达式将前后空格用空字符串替代
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * replaceAll
 */
String.prototype.replaceAll = function(s1, s2) {
	var r = new RegExp(s1.replace(/([\(\)\[\]\{\}\^\$\+\-\*\?\.\"\'\|\/\\])/g,"\\$1"), "ig");
	return this.replace(r, s2);
};

