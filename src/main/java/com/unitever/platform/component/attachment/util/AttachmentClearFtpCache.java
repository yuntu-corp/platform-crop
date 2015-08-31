package com.unitever.platform.component.attachment.util;

import java.io.File;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;

import org.springframework.stereotype.Component;

import com.unitever.platform.core.web.listener.RequestContextMonitor;

@Component("fw_AttachmentClearFtpCache")
public class AttachmentClearFtpCache implements RequestContextMonitor {

	public static final String DEL_FTP_FILE_ATTR_NAME = "com.unitever.framework.component.attachment.util.AttachmentClearFtpCache_DEL_FTP_FILE_ATTR_NAME";

	@Override
	public void destroyed(ServletRequestEvent sre) {
		ServletRequest req = sre.getServletRequest();
		@SuppressWarnings("unchecked")
		List<String> delFileList = (List<String>) req.getAttribute(DEL_FTP_FILE_ATTR_NAME);
		if (delFileList != null) {
			for (String fileName : delFileList) {
				File file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}

	@Override
	public void init(ServletRequestEvent evt) {
		// do nothing
	}

}
