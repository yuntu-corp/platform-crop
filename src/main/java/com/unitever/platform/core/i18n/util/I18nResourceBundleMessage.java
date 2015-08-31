package com.unitever.platform.core.i18n.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.unitever.platform.core.config.SystemConstants;
import com.unitever.platform.core.i18n.constant.I18nConstant;
import com.unitever.platform.core.web.context.ServletContextMonitor;
import com.unitever.platform.util.FileUtil;
import com.unitever.platform.util.MapUtil;

/**
 * 缓存i18n信息
 * 
 * @author tianyl
 * 
 */
@Component
public class I18nResourceBundleMessage implements ServletContextMonitor {

	private static final Log log = LogFactory.getLog(I18nResourceBundleMessage.class);

	/**
	 * 缓存i18n的值<code,<language,value>>
	 */
	private static final Map<String, Map<String, String>> cacheMessage = new HashMap<String, Map<String, String>>();

	public static String getMessage(String code, Locale locale, String... args) {
		if (!SystemConstants.I18N_ENABLECACHE.equals("true")) {
			synchronized (cacheMessage) {
				cacheMessage.clear();
				reloadI18nResource();
			}
		}
		Map<String, String> map = MapUtil.getMapInMap(cacheMessage, code);
		String msg = map.get(locale.getLanguage());
		if (msg == null) {
			return code;
		}
		if (args.length == 0) {
			return msg;
		}
		Object[] objs = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			objs[i] = args[i];
		}
		// 尝试缓存MessageFormat对象增加效率
		msg = MessageFormat.format(msg, objs);
		return msg;
	}

	@Override
	public void init(ServletContext context) {
		reloadI18nResource();
	}

	private static void reloadI18nResource() {
		File fileDir = FileUtil.getFile(I18nConstant.I18N_BUNDLE_DEFAULT_PATH);
		if (fileDir == null || fileDir.listFiles() == null || fileDir.listFiles().length == 0) {
			log.warn("未找到i18n文件");
			return;
		}
		for (File file : fileDir.listFiles()) {
			String language = file.getName().replaceFirst(".*_", "").replace(".properties", "");
			Properties prop = new Properties();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				prop.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				log.error("加载i18n文件出错", e);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("加载i18n文件出错", e);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			Set<String> keys = prop.stringPropertyNames();
			for (String key : keys) {
				String val = prop.getProperty(key);
				MapUtil.getMapInMap(cacheMessage, key).put(language, val);
			}
		}
	}

	@Override
	public void destroyed(ServletContext context) {
		// do nothing
	}

}
