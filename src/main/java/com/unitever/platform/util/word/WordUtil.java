package com.unitever.platform.util.word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.core.spring.SpringMVCUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WordUtil {

	/**
	 * 创建Wrod文档(基于ftl模板)
	 * 
	 * @param templatePath
	 *            模板文件所在文件夹地址
	 * @param templateName
	 *            模板文件名称
	 * @param dataMap
	 *            数据模型
	 * @param filePath
	 *            生成的文件地址
	 */
	public static void createDocWithTemplate(String templatePath, String templateName, Map<String, Object> dataMap, String filePath) {
		if (StringUtils.isBlank(filePath)) {
			throw new RuntimeException("Word生成路径Null异常！");
		}
		Template template = getTemplate(templatePath, templateName);
		try {
			File file = new File(filePath);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			// 输出文件
			template.process(dataMap, out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			throw new RuntimeException("文件生成异常");
		}
	}

	/**
	 * 导出Word文档(基于ftl模板)
	 * 
	 * @param templatePath
	 *            模板文件所在文件夹地址
	 * @param templateName
	 *            模板文件名称
	 * @param dataMap
	 *            数据模型
	 * @param fileName
	 *            导出文件名
	 */
	public static void exportDocWithTemplate(String templatePath, String templateName, Map<String, Object> dataMap, String fileName) {
		if (StringUtils.isBlank(fileName)) {
			throw new RuntimeException("Word导出文件名Null异常！");
		}
		HttpServletResponse response = SpringMVCUtil.getResponse();
		if (response == null)
			return;
		Template template = getTemplate(templatePath, templateName);
		try {
			fileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
			response.setContentType("octets/stream");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			PrintWriter out = response.getWriter();
			// 输出文件
			template.process(dataMap, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			throw new RuntimeException("文件导出异常！");
		}
	}

	private static Template getTemplate(String templatePath, String templateName) {
		if (StringUtils.isBlank(templatePath) || StringUtils.isBlank(templateName)) {
			throw new RuntimeException("模板参数Null异常！");
		}
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(WordUtil.class, templatePath);
		try {
			configuration.setDirectoryForTemplateLoading(new File(templatePath));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("模板文件夹未找到！：" + templatePath);
		}

		try {
			return configuration.getTemplate(templateName);
		} catch (IOException e) {
			throw new RuntimeException("模板文件不存在！：" + templateName);
		}
	}
}
