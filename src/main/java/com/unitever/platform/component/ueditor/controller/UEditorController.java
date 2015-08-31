package com.unitever.platform.component.ueditor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.platform.core.web.controller.SpringController;
import com.unitever.platform.util.JsonUtil;

@Controller
@RequestMapping("/sys/ueditor")
public class UEditorController extends SpringController {

	@RequestMapping("/exec")
	@ResponseBody
	public void exec() {
		String action = getRequest().getParameter("action");
		// 所有配置.详见：jsp/config.json文件
		if ("config".equals(action)) {
			Map<String, Object> configMap = new HashMap<String, Object>();
			configMap.put("imageActionName", "uploadimage");/* 执行上传图片的action名称，不使用此值 */
			configMap.put("imageActionUrl", this.getRequest().getContextPath() + "/sys/attachment/saveUEditorFile.do");/* 执行上传图片的url路径 */
			configMap.put("imageFieldName", "upfile");/* 提交的图片表单名称 */
			configMap.put("imageMaxSize", 2048000000);/* 上传大小限制，单位B */
			configMap.put("imageAllowFiles", new String[] { ".png", ".jpg", ".jpeg", ".gif", ".bmp" });/* 上传图片格式显示 */
			configMap.put("imageCompressEnable", false);/* 是否压缩图片,默认是true */
			configMap.put("imageCompressBorder", 1600);/* 图片压缩最长边限制 */
			configMap.put("imageInsertAlign", "none");/* 插入的图片浮动方式 */
			configMap.put("imageUrlPrefix", "");/* 插入的图片访问地址前缀 */
			this.print(JsonUtil.map2Json(configMap));
		} else {
			throw new RuntimeException("未处理的action：" + action);
		}
	}
}
