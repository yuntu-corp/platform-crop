/** ******************** Arry ************** */
// 根据数据取得再数组中的索引
Array.prototype.getIndex = function(obj) {
	for ( var i = 0; i < this.length; i++) {
		if (obj == this[i]) {
			return i;
		}
	}
	return -1;
}
// 移除数组中的某元素
Array.prototype.remove = function(obj) {
	for ( var i = 0; i < this.length; i++) {
		if (obj == this[i]) {
			this.splice(i, 1);
			break;
		}
	}
	return this;
}
// 判断元素是否在数组中
Array.prototype.contains = function(obj) {
	for ( var i = 0; i < this.length; i++) {
		if (obj == this[i]) {
			return true;
		}
	}
	return false;
}
// 获得数字数组中最小项
Array.prototype.GetMin = function() {
	var oValue = 0;
	for ( var i = 0; i < this.length; i++) {
		if (this[i] < oValue) {
			oValue = this[i];
		}
	}
	return oValue;
};
// 获得数字数组中最大项
Array.prototype.GetMax = function() {
	var oValue = 0;
	for ( var i = 0; i < this.length; i++) {
		if (this[i] > oValue) {
			oValue = this[i];
		}
	}
	return oValue;
};
// 数字数组由大到小排序
Array.prototype.Max2Min = function() {
	var oValue;
	for ( var i = 0; i < this.length; i++) {
		for ( var j = 0; j <= i; j++) {
			if (this[i] > this[j]) {
				oValue = this[i];
				this[i] = this[j];
				this[j] = oValue;
			}
		}
	}
	return this;
};
// 数字数组由小到大排序
Array.prototype.Min2Max = function() {
	var oValue;
	for ( var i = 0; i < this.length; i++) {
		for ( var j = 0; j <= i; j++) {
			if (this[i] < this[j]) {
				oValue = this[i];
				this[i] = this[j];
				this[j] = oValue;
			}
		}
	}
	return this;
};