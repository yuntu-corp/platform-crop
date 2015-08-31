package com.unitever.platform.core.web.template;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.unitever.platform.core.i18n.util.I18nUtil;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class FreemarkerTemplateEngine {

	private static Configuration configuration = null;

	public void renderTemplate(String path, String templateName, Writer writer, Object dataModel) throws IOException, TemplateException {
		if (!templateName.endsWith(".ftl")) {
			templateName += ".ftl";
		}
		Configuration config = getConfig();
		Template template = config.getTemplate(path + "/" + templateName);
		SimpleHash model = new SimpleHash();
		model.put("parameters", dataModel);
		// 添加自定义方法
		model.put("i18n", new TemplateMethodModelEx() {
			@Override
			public Object exec(@SuppressWarnings("rawtypes") List args) throws TemplateModelException {
				if (args.size() == 0) {
					return "";
				}
				// 忽略i18n参数，flt中变参不方便传
				return I18nUtil.getI18nString(args.get(0).toString());
			}
		});

		template.process(model, writer);
	}

	private static Configuration getConfig() {
		if (configuration == null) {
			configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			configuration.setWhitespaceStripping(true);
			configuration.setTemplateUpdateDelay(0);// TODO 缓存
			configuration.setDateFormat("yyyy-MM-dd");
			configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
			configuration.setTimeFormat("HH:mm:ss");
			configuration.setNumberFormat("0.######");
			configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, "/");
		}
		return configuration;
	}

}
