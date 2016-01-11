package com.unitever.module.log.model;

import java.util.Date;

public class Log {
	private String id;
	private String operatorName;
	private Date createTime;
	private String content;
	private String isAdmin;
	private String operatorId;
	private String logType;
	private String objectId;
	private String operate;

	/** 是否是管理员 ：不是管理员 **/
	public static final String ADMIN_TYPE_NO = "0";
	/** 是否是管理员 ：是管理员 **/
	public static final String ADMIN_TYPE_YES = "1";

	/** 操作动作 ：任务--删除任务 **/
	public static final String OPERATE_TASK_DELETE = "0";
	/** 操作动作 ：任务--发布任务 **/
	public static final String OPERATE_TASK_SAVE = "1";
	/** 操作动作 ：任务--接受任务 **/
	public static final String OPERATE_TASK_ACCEPT = "2";
	/** 操作动作 ：任务--拒绝任务 **/
	public static final String OPERATE_TASK_REFUSE = "3";
	/** 操作动作 ：任务--提交审核 **/
	public static final String OPERATE_TASK_COMMITVERIFY = "4";
	/** 操作动作 ：任务--审核未通过 **/
	public static final String OPERATE_TASK_VERIFYFAIL = "5";
	/** 操作动作 ：任务--审核通过 **/
	public static final String OPERATE_TASK_VERIFYSUCCESS = "6";

	/** 操作动作 ：人员--关注 **/
	public static final String OPERATE_EMPLOYEE_SUBSCRIBE = "7";
	/** 操作动作 ：人员--取消关注 **/
	public static final String OPERATE_EMPLOYEE_UNSUBSCRIBE = "8";
	/** 操作动作 ：人员--修改今日价格 **/
	public static final String OPERATE_EMPLOYEE_UPDATECURRENRINFO = "9";
	/** 操作动作 ：人员--修改基础虚拟币 **/
	public static final String OPERATE_EMPLOYEE_UPDATEBASEBITCOIN = "10";
	/** 操作动作 ：人员--添加人员 **/
	public static final String OPERATE_EMPLOYEE_SAVE = "18";
	/** 操作动作 ：人员--修改人员信息 **/
	public static final String OPERATE_EMPLOYEE_UPDATE = "19";

	/** 操作动作 ：评价--提交评价 **/
	public static final String OPERATE_EVALUATION_SAVE = "11";

	/** 操作动作 ： 讨价--提交讨价 **/
	public static final String OPERATE_DICKER_SAVE = "12";
	/** 操作动作 ： 讨价--同意讨价 **/
	public static final String OPERATE_DICKER_ACCEPT = "13";
	/** 操作动作 ： 讨价--拒绝讨价 **/
	public static final String OPERATE_DICKER_REFUSE = "14";

	/** 操作动作 ： 任务类型--添加任务类型 **/
	public static final String OPERATE_TASKTYPE_SAVE = "15";
	/** 操作动作 ： 任务类型--删除任务类型 **/
	public static final String OPERATE_TASKTYPE_DELETE = "16";
	/** 操作动作 ： 任务类型--更新任务类型 **/
	public static final String OPERATE_TASKTYPE_UPDATE = "17";
	
	/** 操作动作 ： 分发 **/
	public static final String OPERATE_DISTRIBUTE = "20";

	/** 对象：Task **/
	public static final String LOGTYPE_TASK = "task";
	/** 对象：Dicker **/
	public static final String LOGTYPE_DICKER = "dicker";
	/** 对象：Employee **/
	public static final String LOGTYPE_EMPLOYEE = "employee";
	/** 对象：Evaluation **/
	public static final String LOGTYPE_EVALUATION = "evaluation";
	/** 对象：Tasktype **/
	public static final String LOGTYPE_TASKTYPE = "tasktype";
	/** 对象：Tasktype **/
	public static final String LOGTYPE_DISTRIBUTE = "distribute";

	/** 是否管理员字段value值，用于网页显示 **/
	private String isAdminValue;
	/** 操作动作value值，用于网页显示 **/
	private String operateValue;

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getIsAdminValue() {
		if (ADMIN_TYPE_NO.equals(isAdmin)) {
			return "员工";
		} else if (ADMIN_TYPE_YES.equals(isAdmin)) {
			return "管理员";
		}
		return isAdminValue;
	}

	public void setIsAdminValue(String isAdminValue) {
		this.isAdminValue = isAdminValue;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getOperateValue() {
		// 任务--删除任务
		if (OPERATE_TASK_DELETE.equals(operate)) {
			return "任务--删除任务";
		}
		// 任务--发布任务
		else if (OPERATE_TASK_SAVE.equals(operate)) {
			return "任务--发布任务";
		}
		// 任务--接受任务
		else if (OPERATE_TASK_ACCEPT.equals(operate)) {
			return "任务--接受任务";
		}
		// 任务--拒绝任务
		else if (OPERATE_TASK_REFUSE.equals(operate)) {
			return "任务--拒绝任务";
		}
		// 任务--提交审核
		else if (OPERATE_TASK_COMMITVERIFY.equals(operate)) {
			return "任务--提交审核";
		}
		// 任务--审核未通过
		else if (OPERATE_TASK_VERIFYFAIL.equals(operate)) {
			return "任务--审核未通过";
		}
		// 操作动作 ：任务--审核通过
		else if (OPERATE_TASK_VERIFYSUCCESS.equals(operate)) {
			return "任务--审核通过";
		}
		// 操作动作 ：人员--关注
		else if (OPERATE_EMPLOYEE_SUBSCRIBE.equals(operate)) {
			return "人员--关注";
		}
		// 操作动作 ：人员--取消关注
		else if (OPERATE_EMPLOYEE_UNSUBSCRIBE.equals(operate)) {
			return "人员--取消关注";
		}
		// 操作动作 ：人员--修改今日价格
		else if (OPERATE_EMPLOYEE_UPDATECURRENRINFO.equals(operate)) {
			return "人员--修改今日价格";
		}
		// 操作动作 ：人员--修改基础虚拟币
		else if (OPERATE_EMPLOYEE_UPDATEBASEBITCOIN.equals(operate)) {
			return "人员--修改基础虚拟币";
		}
		// 操作动作 ：人员--添加人员
		else if (OPERATE_EMPLOYEE_SAVE.equals(operate)) {
			return "人员--添加人员";
		}
		// 操作动作 ：人员--修改人员信息
		else if (OPERATE_EMPLOYEE_UPDATE.equals(operate)) {
			return "人员--修改人员信息";
		}
		// 操作动作 ：评价--提交评价
		else if (OPERATE_EVALUATION_SAVE.equals(operate)) {
			return "评价--提交评价";
		}
		// 操作动作 ： 讨价--提交讨价
		else if (OPERATE_DICKER_SAVE.equals(operate)) {
			return "讨价--提交讨价";
		}
		// 操作动作 ： 讨价--同意讨价
		else if (OPERATE_DICKER_ACCEPT.equals(operate)) {
			return "讨价--同意讨价";
		}
		// 操作动作 ： 讨价--拒绝讨价
		else if (OPERATE_DICKER_REFUSE.equals(operate)) {
			return "讨价--拒绝讨价";
		}
		// 操作动作 ： 任务类型--添加任务类型
		else if (OPERATE_TASKTYPE_SAVE.equals(operate)) {
			return "任务类型--添加任务类型";
		}
		// 操作动作 ： 任务类型--删除任务类型
		else if (OPERATE_TASKTYPE_DELETE.equals(operate)) {
			return "任务类型--删除任务类型";
		}
		// 操作动作 ： 任务类型--更新任务类型
		else if (OPERATE_TASKTYPE_UPDATE.equals(operate)) {
			return "任务类型--更新任务类型";
		}
		// 操作动作 ： 任务类型--更新任务类型
		else if (OPERATE_DISTRIBUTE.equals(operate)) {
			return "任务类型--分发虚拟币";
		}
		return operateValue;
	}

	public void setOperateValue(String operateValue) {
		this.operateValue = operateValue;
	}

}
