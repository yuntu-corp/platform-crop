package com.unitever.platform.core.i18n.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.platform.core.i18n.constant.I18nConstant;
import com.unitever.platform.core.i18n.util.LocalHolderUtil;
import com.unitever.platform.util.FileUtil;

@Controller
@RequestMapping("/sys/i18n")
public class I18nController {

	@RequestMapping("/ajaxGetI18nValues")
	@ResponseBody
	public void ajaxGetI18nValues(HttpServletRequest request, HttpServletResponse response) {
		String bundleNames = request.getParameter("bundleName");
		for (String bundleName : bundleNames.split(",")) {
			if (StringUtils.isBlank(bundleName)) {
				continue;
			}
			String language = LocalHolderUtil.getLocal().getLanguage();
			String fileName = I18nConstant.I18N_BUNDLE_DEFAULT_PATH + bundleName + "_" + language + ".properties";

			if (FileUtil.isExist(fileName)) {
				BufferedReader br = null;
				PrintWriter pw = null;
				try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(FileUtil.getFile(fileName))));
					pw = new PrintWriter(response.getOutputStream());
					String temp = null;
					while ((temp = br.readLine()) != null) {
						pw.println(temp);
					}
					pw.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
						}
					}
				}
			}
		}
	}
}
