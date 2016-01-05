package com.unitever.module.wechat.util;

import java.util.Date;

import com.unitever.module.log.model.Log;
import com.unitever.module.log.service.LogService;
import com.unitever.platform.core.spring.SpringContextHolder;
import com.unitever.platform.util.DateUtil;
import com.unitever.platform.util.UUID;

public class LogUtil {

	/**
	 * LogUtil--保存日志记录
	 * 
	 * @param logContent
	 *            日志内容；
	 * @param operateName
	 *            操作人；
	 * @param isAdmin
	 *            是否是管理员操作；
	 * @param operateId
	 *            操作人员ID；
	 * @param logType
	 *            对象类型；
	 * @param objectId
	 *            对象id；
	 * @param operate
	 *            操作动作
	 */
	public static void saveLog(String logContent, String operatorName, String isAdmin, String operatorId, String logType,
			String objectId, String operate) {
		Log log = new Log();
		log.setId(UUID.getUUID());
		log.setOperatorName(operatorName);
		log.setContent(logContent);
		log.setCreateTime(new Date());
		log.setOperatorId(operatorId);
		log.setIsAdmin(isAdmin);
		log.setLogType(logType);
		log.setObjectId(objectId);
		log.setOperate(operate);
		LogService logService = SpringContextHolder.getBean(LogService.class);
		logService.save(log);
	}

}
