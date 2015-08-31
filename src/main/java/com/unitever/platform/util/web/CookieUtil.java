package com.unitever.platform.util.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	/**
	 * Cookie有效期，一分钟
	 */
	public final static int COOKIE_AGE_1Min = 60;

	/**
	 * Cookie有效期，一小时
	 */
	public final static int COOKIE_AGE_1H = 3600;

	/**
	 * Cookie有效期，一天
	 */
	public final static int COOKIE_AGE_24H = 3600 * 24;

	/**
	 * Cookie有效期，一周
	 */
	public final static int COOKIE_AGE_1Week = COOKIE_AGE_24H * 7;

	/**
	 * Cookie有效期，一个月
	 */
	public final static int COOKIE_AGE_1Month = COOKIE_AGE_24H * 30;

	/**
	 * Cookie有效期，一个季度
	 */
	public final static int COOKIE_AGE_3Month = COOKIE_AGE_1Month * 3;

	/**
	 * Cookie有效期，一年
	 */
	public final static int COOKIE_AGE_1Year = COOKIE_AGE_24H * 365;

	/**
	 * Cookie有效期，当前访问有效
	 */
	public final static int COOKIE_AGE_SESSION = -1;

	private static final String DEFAULT_COOKIE_PATH = "/";
	private static final String DEFAULT_COOKIE_DOMAIN = null;

	/**
	 * 写入Cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void writeCookie(HttpServletResponse response, String name, String value, int maxAge) {
		writeCookie(response, name, value, DEFAULT_COOKIE_DOMAIN, DEFAULT_COOKIE_PATH, maxAge);
	}

	/**
	 * 写入Cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param path
	 * @param maxAge
	 */
	public static void writeCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
		writeCookie(response, name, value, DEFAULT_COOKIE_DOMAIN, path, maxAge);
	}

	/**
	 * 写入Cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param domain
	 * @param path
	 * @param maxAge
	 */
	public static void writeCookie(HttpServletResponse response, String name, String value, String domain, String path, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		cookie.setVersion(1);
		if (domain != null) {
			cookie.setDomain(domain);
		}
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 写入临时Cookie，浏览器关闭失效
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void writeTempCookie(HttpServletResponse response, String name, String value) {
		writeCookie(response, name, value, DEFAULT_COOKIE_DOMAIN, DEFAULT_COOKIE_PATH, COOKIE_AGE_SESSION);
	}

	/**
	 * 删除Cookie
	 * 
	 * @param response
	 * @param name
	 */
	public static void deleteCookie(HttpServletResponse response, String name) {
		deleteCookie(response, name, DEFAULT_COOKIE_DOMAIN, DEFAULT_COOKIE_PATH);
	}

	/**
	 * 删除Cookie
	 * 
	 * @param response
	 * @param name
	 * @param path
	 */
	public static void deleteCookie(HttpServletResponse response, String name, String path) {
		deleteCookie(response, name, DEFAULT_COOKIE_DOMAIN, path);
	}

	/**
	 * 删除Cookie
	 * 
	 * @param response
	 * @param name
	 * @param domain
	 * @param path
	 */
	public static void deleteCookie(HttpServletResponse response, String name, String domain, String path) {
		Cookie cookie = new Cookie(name, "");
		cookie.setPath(path);
		if (domain != null) {
			cookie.setDomain(domain);
		}
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * 读取Cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String readCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}