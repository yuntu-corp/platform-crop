package com.unitever.platform.util.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * Http与Servlet工具类.
 * 
 * @author calvin
 */
public class WebUtil {

	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	/**
	 * 设置客户端缓存过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		// Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "max-age=" + expiresSeconds);
	}

	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified
	 *            内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag
	 *            内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查浏览器客户端是否支持gzip编码.
	 */
	public static boolean checkAcceptGzip(HttpServletRequest request) {
		// Http1.1 header
		String acceptEncoding = request.getHeader("Accept-Encoding");

		if (StringUtils.contains(acceptEncoding, "gzip")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置Gzip Header并返回GZIPOutputStream.
	 */
	public static OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Vary", "Accept-Encoding");
		return new GZIPOutputStream(response.getOutputStream());
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setDownloadableHeader(HttpServletResponse response, String fileName) {
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	}

	/**
	 * 取得带相同前缀的Request Parameters.
	 * 
	 * 返回的结果Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParametersStartingWith(HttpServletRequest request, String prefix) {
		return (org.springframework.web.util.WebUtils.getParametersStartingWith(request, prefix));
	}

	/**
	 * 判断客户端是否为IE
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isIE(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		return userAgent.contains("MSIE");
	}

	/**
	 * 获取客户IP地址，此地址长度可能会超过15，长度是未知的
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteAddress(HttpServletRequest request) {
		String ip = "";
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取url响应内容
	 * 
	 * @param url
	 * @return
	 */
	public static String getUrlResponse(String url) {
		return getUrlResponse(url, true);
	}

	/**
	 * 获取url响应内容
	 * 
	 * @param url
	 * @param isGet
	 * @return
	 */
	public static String getUrlResponse(String url, boolean isGet) {
		return getUrlResponse(url, new HashMap<String, String>(), isGet);
	}

	/**
	 * 获取url响应内容
	 * 
	 * @param url
	 * @param paramMap
	 * @param isGet
	 * @return
	 */
	public static String getUrlResponse(String url, Map<String, String> paramMap, boolean isGet) {
		return getUrlResponse(url, paramMap, isGet, "utf-8");
	}

	/**
	 * 获取url响应内容
	 * 
	 * @param url
	 * @param paramMap
	 * @param isGet
	 * @return
	 */
	public static String getUrlResponse(String url, Map<String, String> paramMap, boolean isGet, String charsetName) {
		String result = "";
		if (paramMap == null) {
			paramMap = new HashMap<String, String>();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) (new URL(checkUrl(url)).openConnection());
			if (!paramMap.isEmpty()) {
				conn.setDoInput(true);
			}
			conn.setDoOutput(true);
			conn.setRequestMethod(isGet ? "GET" : "POST");
			conn.setUseCaches(false);
			// 仅对当前请求自动重定向
			conn.setInstanceFollowRedirects(true);
			// header 设置编码
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 连接
			conn.connect();
			writeParameters(conn, paramMap);
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				// throw new IOException();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charsetName));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				result += temp + "\n";
			}
			reader.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NetIOException(e.getMessage(), e);
		}
		return result;
	}

	private static String checkUrl(String url) {
		String result = url;
		if (url.startsWith("http://")) {
			url = url.replaceFirst("http://", "");
			if (url.contains("//")) {
				url = url.replaceAll("//", "/");
			}
			result = "http://" + url;
		}
		return result;
	}

	private static void writeParameters(HttpURLConnection conn, Map<String, String> map) throws IOException {
		if (map == null) {
			return;
		}
		String content = "";
		Set<String> keySet = map.keySet();
		int i = 0;
		for (String key : keySet) {
			String val = map.get(key);
			content += (i == 0 ? "" : "&") + key + "=" + URLEncoder.encode(val, "utf-8");
			i++;
		}
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(content);
		out.flush();
		out.close();
	}

}
