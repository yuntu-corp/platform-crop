/**
 * <code>DateUtil</code>类用于封装常用的日期处理操作
 */
var DateUtil = function(year, month, day, hour, minute, second) {
	/** curDateTime 当前客户端日期时间 */
	this.curDateTime = new Date();
	/**
	 * <code>getDateRange</code>方法用于得到一天之内的时刻范围
	 * 
	 * @return range ["凌晨"|"上午"|"中午"|"下午"|"晚上"]
	 */
	this.getDateRange = function() {
		var hour = window.parseInt(this.getHour());
		var range = "凌晨"
		if (hour >= 6 && hour < 11) {
			range = "早晨";
		} else if (hour >= 11 && hour < 14) {
			range = "中午";
		} else if (hour >= 14 && hour <= 18) {
			range = "下午";
		} else if (hour > 18 && hour < 24) {
			range = "晚上";
		}
		return range;
	};
}
