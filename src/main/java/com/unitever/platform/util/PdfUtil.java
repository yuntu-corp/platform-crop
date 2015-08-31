package com.unitever.platform.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;
import com.unitever.platform.core.spring.SpringMVCUtil;
import com.unitever.platform.util.web.WebUtil;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class PdfUtil {

	private static Configuration cfg = new Configuration();
	private static List<String> fontPaths = new ArrayList<String>();
	static {
		cfg.setTemplateLoader(new ClassTemplateLoader(PdfUtil.class, "/"));
		// 查找系统自带的字体
		for (char i = 'a'; i < 'z'; i++) {
			String simsun = Character.valueOf(i).toString() + ":/Windows/Fonts/simsun.ttc";
			String simsunBold = Character.valueOf(i).toString() + ":/Windows/Fonts/simsun.ttc";
			if (new File(simsun).exists()) {
				fontPaths.add(simsun);
			}
			if (new File(simsunBold).exists()) {
				fontPaths.add(simsunBold);
			}
		}
		// 添加配置的字体
		String propFontPath = PropertyUtil.getProperty("/conf/system.properties", "fontPath");
		if (StringUtils.isNotBlank(propFontPath)) {
			for (String path : propFontPath.split("\\|")) {
				if (StringUtils.isNotBlank(path)) {
					if (new File(path).exists()) {
						fontPaths.add(path);
					}
				}
			}
		}
	}

	public static final void exportPdf(String fileName, Map<String, Object> dataMap, String ftlPath) {
		if (!fileName.endsWith(".pdf")) {
			fileName += ".pdf";
		}
		try {
			Template t = cfg.getTemplate(ftlPath);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintWriter writer = new PrintWriter(baos);
			t.process(dataMap, writer);

			ITextRenderer renderer = new ITextRenderer();
			ITextFontResolver fontResolver = renderer.getFontResolver();
			String content = new String(baos.toByteArray());
			renderer.setDocumentFromString(content);

			for (String fontPath : fontPaths) {
				fontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			}

			renderer.layout();

			HttpServletResponse response = SpringMVCUtil.getResponse();
			HttpServletRequest request = SpringMVCUtil.getRequest();
			String charsetName = "utf-8";
			if (WebUtil.isIE(request)) {
				charsetName = "gb2312";
			}
			response.setContentType("application/force-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(charsetName), "iso8859-1"));

			renderer.createPDF(response.getOutputStream());
			renderer.finishPDF();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("生成pdf错误");
		}
	}
}
