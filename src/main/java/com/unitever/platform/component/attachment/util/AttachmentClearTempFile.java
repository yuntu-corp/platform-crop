package com.unitever.platform.component.attachment.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import com.unitever.platform.core.web.context.ServletContextMonitor;

@Component
public class AttachmentClearTempFile implements ServletContextMonitor {

	@Override
	public void init(ServletContext context) {
		// 清空附件临时目录中的文件
		File dir = new File(AttachmentUtil.getTempDir());
		for (File f : dir.listFiles()) {
			if (f.isFile()) {
				f.delete();
			}
		}
	}

	@Override
	public void destroyed(ServletContext context) {
		// do nothing
	}

}
