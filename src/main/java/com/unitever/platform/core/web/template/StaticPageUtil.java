package com.unitever.platform.core.web.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;

import com.unitever.platform.core.i18n.util.I18nUtil;
import com.unitever.platform.core.spring.SpringMVCUtil;
import com.unitever.platform.core.util.TaskEngine;

import freemarker.ext.jsp.TaglibFactory;
import freemarker.ext.servlet.AllHttpScopesHashModel;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.HttpRequestParametersHashModel;
import freemarker.ext.servlet.HttpSessionHashModel;
import freemarker.ext.servlet.ServletContextHashModel;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 静态化工具类
 * @author wdw
 */
public class StaticPageUtil {
	final static Logger logger = LoggerFactory.getLogger(StaticPageUtil.class);
	private static Configuration configuration = null;

	/**
	 * 生成静态页面（注：此方法必须在HttpServletRequest时调用）
	 * @param data 模板使用的数据
	 * @param templatePath 模板路径（相对路径，如：/demo/module/user/ftl/test.ftl）
	 * @param targetHtmlPath 静态页面路径（相对路径，如：/demo/module/user/static/test.html）
	 */
	public static void createHtml(HashMap<String, Object> data, String templatePath, String targetHtmlPath) {
		HttpServletRequest request = getReauest();
		HttpServletResponse response = getResponse();
		ServletContext context = request.getSession().getServletContext();
		Configuration config = getConfig();
		config.setServletContextForTemplateLoading(context, "/");

		SimpleHash model = buildTemplateModel(data, request, response);

		String htmlPath = context.getRealPath("/") + targetHtmlPath;
		renderTemplate(templatePath, config, model, htmlPath);
	}

	/**
	 * 生成静态页面，新线程中运行（注：此方法必须在HttpServletRequest时调用）
	 * @param templatePath 模板路径（相对路径，如：/demo/module/user/ftl/test.ftl）
	 * @param targetHtmlPath 静态页面路径（相对路径，如：/demo/module/user/static/test.html）
	 * @param staticPageData 获取页面数据的接口
	 */
	public static void createHtmlWithThread(final String templatePath, String targetHtmlPath, final StaticPageData staticPageData) {
		HttpServletRequest request = getReauest();
		HttpServletResponse response = getResponse();
		ServletContext context = request.getSession().getServletContext();
		final Configuration config = getConfig();
		config.setServletContextForTemplateLoading(context, "/");

		final SimpleHash model = buildTemplateModel(null, request, response);
		model.put("rc", new RequestContext(request));
		final String htmlPath = context.getRealPath("/") + targetHtmlPath;
		final Runnable createHTML = new Runnable() {
			public void run() {
				if (staticPageData != null) {
					HashMap<String, Object> data = staticPageData.getData();
					model.putAll(data);
				}
				renderTemplate(templatePath, config, model, htmlPath);
			}

		};
		TaskEngine.getInstance().submit(createHTML);
	}

	private static void renderTemplate(final String templatePath, final Configuration config, final SimpleHash model, final String htmlPath) {
		Writer out = getStaticPageWriter(htmlPath);
		//处理模版  
		try {
			Template template = config.getTemplate(templatePath, "UTF-8");
			template.process(model, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			errorLog(e, "原因：模板文件路径错误！");
		} catch (TemplateException e) {
			errorLog(e, "原因：模板解析错误！");
		}
	}
	
	private static Writer getStaticPageWriter(String htmlPath) {
		Writer out = null;
		try {
			File htmlFile = new File(htmlPath);
			if (!htmlFile.exists()) {
				htmlFile.createNewFile();
			}
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
		} catch (IOException e) {
			errorLog(e, "原因：静态页面路径错误！");
		}
		return out;
	}

	private static void errorLog(Exception e, String errorInfo) {
		logger.error(errorInfo, e);
		throw new RuntimeException(errorInfo, e);
	}

	private static ServletContext getServletContext() {
		return getReauest().getSession().getServletContext();
	}

	private static HttpServletResponse getResponse() {
		HttpServletResponse response = SpringMVCUtil.getResponse();
		if (response == null) {
			throw new RuntimeException("HttpServletResponse is null !!");
		}
		return response;
	}

	private static HttpServletRequest getReauest() {
		HttpServletRequest request = SpringMVCUtil.getRequest();
		if (request == null) {
			throw new RuntimeException("HttpServletRequest is null !!");
		}
		return request;
	}

	private static Configuration getConfig() {
		if (configuration == null) {
			configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			configuration.setWhitespaceStripping(true);
			configuration.setTemplateUpdateDelay(0);
			configuration.setDateFormat("yyyy-MM-dd");
			configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
			configuration.setTimeFormat("HH:mm:ss");
			configuration.setNumberFormat("0.######");
			configuration.setClassForTemplateLoading(StaticPageUtil.class, "/");
		}

		return configuration;
	}

	private static SimpleHash buildTemplateModel(Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) {
		ServletContext servletContext = getServletContext();
		AllHttpScopesHashModel fmModel = new AllHttpScopesHashModel(getObjectWrapper(), servletContext, request);
		fmModel.put(FreemarkerServlet.KEY_JSP_TAGLIBS, new TaglibFactory(servletContext));
		fmModel.put(FreemarkerServlet.KEY_APPLICATION, getServletContextHashModel(servletContext));
		fmModel.put(FreemarkerServlet.KEY_SESSION, buildSessionModel(request, response));
		fmModel.put(FreemarkerServlet.KEY_REQUEST, new HttpRequestHashModel(request, response, getObjectWrapper()));
		fmModel.put(FreemarkerServlet.KEY_REQUEST_PARAMETERS, new HttpRequestParametersHashModel(request));
		fmModel.put("rc", request);
		// 添加自定义方法
		fmModel.put("i18n", new TemplateMethodModelEx() {
			@Override
			public Object exec(@SuppressWarnings("rawtypes") List args) throws TemplateModelException {
				if (args.size() == 0) {
					return "";
				}
				// 忽略i18n参数，ftl中变参不方便传
				return I18nUtil.getI18nString(args.get(0).toString());
			}
		});
		if (data != null) {
			fmModel.putAll(data);
		}
		return fmModel;
	}

	private static ServletContextHashModel getServletContextHashModel(ServletContext context) {
		GenericServlet servlet = new GenericServletAdapter();
		try {
			servlet.init(new DelegatingServletConfig(context));
		} catch (ServletException ex) {
			throw new BeanInitializationException("Initialization of GenericServlet adapter failed", ex);
		}
		return new ServletContextHashModel(servlet, getObjectWrapper());
	}

	private static HttpSessionHashModel buildSessionModel(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return new HttpSessionHashModel(session, getObjectWrapper());
		} else {
			return new HttpSessionHashModel(null, request, response, getObjectWrapper());
		}
	}

	private static ObjectWrapper getObjectWrapper() {
		ObjectWrapper ow = getConfig().getObjectWrapper();
		return (ow != null ? ow : ObjectWrapper.DEFAULT_WRAPPER);
	}

	@SuppressWarnings("serial")
	private static class GenericServletAdapter extends GenericServlet {

		@Override
		public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
			// no-op
		}
	}

	/**
	 * Internal implementation of the {@link ServletConfig} interface,
	 * to be passed to the servlet adapter.
	 */
	private static class DelegatingServletConfig implements ServletConfig {
		private ServletContext context;

		public DelegatingServletConfig(ServletContext context) {
			super();
			this.context = context;
		}

		@Override
		public String getServletName() {
			return StaticPageUtil.class.getName();
		}

		@Override
		public ServletContext getServletContext() {
			return context;
		}

		@Override
		public String getInitParameter(String paramName) {
			return null;
		}

		@Override
		public Enumeration<String> getInitParameterNames() {
			return Collections.enumeration(new HashSet<String>());
		}
	}

}
