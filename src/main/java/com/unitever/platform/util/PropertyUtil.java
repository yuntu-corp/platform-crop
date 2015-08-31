package com.unitever.platform.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unitever.platform.core.config.ConfigurableConstants;

/**
 * Property工具类，实时读取；后期可以考虑使用缓存；建议使用缓存方式，参考ConfigurableConstants类
 * 
 * @see ConfigurableConstants
 * 
 * @author tianyl
 * 
 */
public class PropertyUtil {

	private static final Log log = LogFactory.getLog(PropertyUtil.class);

	/**
	 * 读取Properties文件
	 * 
	 * @param filePath
	 *            从根目录开始。例如："/conf/jdbc.properties"或"conf/jdbc.properties"
	 * @return
	 */
	public static Properties getProperties(String filePath) {
		filePath = checkAndModifyPath(filePath);
		Properties propertie = new Properties();
		ClassLoader cl = PropertyUtil.class.getClassLoader();
		try {
			FileInputStream inputFile = new FileInputStream(URLDecoder.decode(cl.getResource(filePath).getFile(), "utf-8"));
			propertie.load(inputFile);
			inputFile.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			log.error("读取Properties异常", ex);
			throw new RuntimeException(ex);
		}
		return propertie;
	}

	/**
	 * 得到属性文件的值
	 * 
	 * @param fileName
	 *            从根目录开始。例如："/conf/jdbc.properties"或"conf/jdbc.properties"
	 * @param name
	 *            属性文件key
	 * @return
	 */
	public static String getProperty(String fileName, String name) {
		fileName = checkAndModifyPath(fileName);
		ClassLoader cl = PropertyUtil.class.getClassLoader();
		InputStream is = null;
		String str = null;
		try {
			is = new FileInputStream(URLDecoder.decode(cl.getResource(fileName).getFile(), "utf-8"));
			if (is != null) {
				Properties props = new Properties();
				try {
					props.load(is);
				} catch (IOException e) {
					props = null;
					throw ExceptionUtil.convertExceptionToUnchecked(e);
				}
				if (props != null) {
					str = props.getProperty(name);
					if (str != null) {
						str = str.trim();
					}
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw ExceptionUtil.convertExceptionToUnchecked(e1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw ExceptionUtil.convertExceptionToUnchecked(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return str;
	}

	private static String checkAndModifyPath(String fileName) {
		if (fileName.startsWith("/")) {
			return fileName;
		}
		return "/" + fileName;
	}
}
