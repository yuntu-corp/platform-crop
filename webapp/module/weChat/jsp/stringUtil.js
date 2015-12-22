/**
 * 将一个字符串用给定的字符变成数组
 * @param tag
 * @returns 如果不包含此字符，返回只包含自己的数组；如果是空则返回一个空的数组
 */
String.prototype.toArray = function(tag) {
    if (this.indexOf(tag) != -1) {
        return this.split(tag);
    }else {
        if (this != '') {
            return [this.toString()];
        }else {
            return [];
        }
    }
}
//只留下数字(0123456789)
String.prototype.toNumber= function() { 
    return this.replace(/\D/g, "");
}
//保留中文  
String.prototype.toCN= function() {  
    var regEx = /[^\u4e00-\u9fa5\uf900-\ufa2d]/g;  
    return this.replace(regEx, '');  
}