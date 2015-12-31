/** ******************** 浏览器相关操作 ************** */
// 进入全屏模式, 判断各种浏览器，找到正确的方法
var launchFullScreen = function(element) {
	if (element.requestFullscreen) {
		element.requestFullscreen();
	} else if (element.mozRequestFullScreen) {
		element.mozRequestFullScreen();
	} else if (element.webkitRequestFullscreen) {
		element.webkitRequestFullscreen();
	} else if (element.msRequestFullscreen) {
		element.msRequestFullscreen();
	}
	return true;
}
// 退出全屏模式
var exitFullScreen = function() {
	if (document.exitFullscreen) {
		document.exitFullscreen();
	} else if (document.mozCancelFullScreen) {
		document.mozCancelFullScreen();
	} else if (document.webkitExitFullscreen) {
		document.webkitExitFullscreen();
	}
	return false;
}
// cookie操作
var CookieUtil = {
	path : "/",
	domain : 'demo.j2ee.com',
	add : function(name, val) {
		$.cookie(name, val, {
			expires : 7,
			path : this.path,
			domain : this.domain,
			secure : true
		});
	},
	remove : function(name) {
		$.cookie(name, null, {
			path : this.path,
			domain : this.domain
		});
	},
	get : function(name) {
		$.cookie(name, {
			path : this.path,
			domain : this.domain
		});
	}
}
// error
var error = {
	e_404 : function() {
		alertMessage("404", "未找到改页面！", "warning");
	},
	e_500 : function() {
		alertMessage("500", "服务器内部错误！", "error");
	},
	e_403 : function() {
		alertMessage("403", "权限不足！", "warning");
	}
}
