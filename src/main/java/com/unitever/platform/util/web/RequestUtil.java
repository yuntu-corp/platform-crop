package com.unitever.platform.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 * 封装HttpRequest相关操作的工具类 <BR>
 */
public class RequestUtil {

	private static final String HEADER_REFER = "referer";

	/**
	 * 获取表示给定HTTP GET请求的完整URL字符串(包括QueryString). <BR>
	 * 
	 * @param req
	 *            指定的request
	 * @return 表示给定HTTP GET请求的完整URL字符串(包括QueryString)
	 */
	public static String getFullGetStr(HttpServletRequest req) {
		final String qryStr = req.getQueryString();
		if (qryStr == null) {
			return req.getRequestURL().toString();
		}
		return req.getRequestURL().append('?').append(qryStr).toString();
	}

	/**
	 * 获取给定的Http请求的Referer URL, 即上一个页面.
	 * 
	 * @param req
	 *            给定的Http请求
	 * @return 给定Http请求的referer头的值. 如果不存在, 返回null.
	 */
	public static String getReferUrl(HttpServletRequest req) {
		return req.getHeader(HEADER_REFER);
	}

	/**
	 * 获取真实IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		int pos = ip.indexOf(",");
		int startPos = 0;
		while (pos > 0) {
			String ip1 = ip.substring(startPos, pos).trim();
			if (ip1.length() > 0 && !"unknown".equalsIgnoreCase(ip1)) {
				return ip1;
			}
			startPos = pos + 1;
			pos = ip.indexOf(",", startPos);
		}
		return ip;
	}

}