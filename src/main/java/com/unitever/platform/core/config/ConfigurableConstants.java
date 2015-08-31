package com.unitever.platform.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.unitever.platform.util.CloseUtil;
import com.unitever.platform.util.ExceptionUtil;

/**
 * 可用Properties文件配置的Contants基类
 * <p/>
 * 子类可如下编写
 * 
 * <pre>
 * public class Constants extends ConfigurableContants {
 * 	static {
 * 		init(&quot;system.properties&quot;);
 * 	}
 * 
 * 	public final static String ERROR_BUNDLE_KEY = getProperty(&quot;constant.error_bundle_key&quot;, &quot;default&quot;);
 * 
 * }
 * </pre>
 * 
 */
public class ConfigurableConstants {

	protected static Properties p = new Properties();

	protected static void init(String propertyFileName) {
		InputStream in = null;
		try {
			in = ConfigurableConstants.class.getResourceAsStream(propertyFileName);
			if (in != null) {
				p.load(in);
			}
		} catch (IOException e) {
			throw ExceptionUtil.convertExceptionToUnchecked(e);
		} finally {
			CloseUtil.close(in);
		}
	}

	protected static String getProperty(String key, String defaultValue) {
		return p.getProperty(key, defaultValue);
	}

}
