package com.unitever.module.employee.model;

import java.util.Date;

import me.chanjar.weixin.cp.bean.WxCpUser;

import com.unitever.module.user.model.User;
import com.unitever.platform.util.DateUtil;
import com.unitever.platform.util.DoubleUtil;

/**
 * @author 作者 : 邓文杰
 * @version 创建时间：2015-11-6 下午5:02:09 类说明 员工类
 */
public class Employee {

	private String id;
	private String name;
	private String userId;
	private User user;// 对应企业的User类
	private String headimgurl;
	private String baseBitcoin;
	private String bitcoinSurplus;
	private String bitcoinIncome;
	private String bitcoinConsume;
	private WxCpUser wxCpUser;// 引用外部API的员工类
	private String status;
	private String evaluationLevel;// 评价平均值
	private String nowBitcoin;// 当前虚拟币
	private String nowDate;// 当前系统日期，方便页面使用
	//private Date minusDate;//为了解决执行两遍minus
	private String taskCount;
	private String publishTaskCount;
	private String currentBitcoin;
	
	

	public String getCurrentBitcoin() {
		return currentBitcoin;
	}
	public void setCurrentBitcoin(String currentBitcoin) {
		this.currentBitcoin = currentBitcoin;
	}
	public String getPublishTaskCount() {
		return publishTaskCount;
	}
	public void setPublishTaskCount(String publishTaskCount) {
		this.publishTaskCount = publishTaskCount;
	}
	public String getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(String taskCount) {
		this.taskCount = taskCount;
	}

	/* 员工状态：正常 * */
	public final static String EMPLOYE_STATUS_NORMAL = "1";
	/* 员工状态：删除 * */
	public final static String EMPLOYE_STATUS_REMOVE = "0";

	public Employee() {
	}
	public Employee(String id) {
		this.id = id;
	}
	
	public String getEvaluationLevel() {
		return evaluationLevel;
	}

	public void setEvaluationLevel(String evaluationLevel) {
		this.evaluationLevel = evaluationLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getBaseBitcoin() {
		return baseBitcoin;
	}

	public void setBaseBitcoin(String baseBitcoin) {
		this.baseBitcoin = baseBitcoin;
	}

	public String getBitcoinSurplus() {
		return bitcoinSurplus;
	}

	public void setBitcoinSurplus(String bitcoinSurplus) {
		this.bitcoinSurplus = bitcoinSurplus;
	}

	public String getBitcoinIncome() {
		return bitcoinIncome;
	}

	public void setBitcoinIncome(String bitcoinIncome) {
		this.bitcoinIncome = bitcoinIncome;
	}

	public String getBitcoinConsume() {
		return bitcoinConsume;
	}

	public void setBitcoinConsume(String bitcoinConsume) {
		this.bitcoinConsume = bitcoinConsume;
	}

	public WxCpUser getWxCpUser() {
		return wxCpUser;
	}

	public void setWxCpUser(WxCpUser wxCpUser) {
		this.wxCpUser = wxCpUser;
	}

	/**
	 * 获取当前虚拟币
	 * */
	public String getNowBitcoin() {
		/*double returnstr = (double)Math.ceil((Double.parseDouble(baseBitcoin) / DateUtil.getDayOfMonth())
				* Double.parseDouble(DateUtil.getCurrDateString("dd")));*/
		return DoubleUtil.mul(DoubleUtil.div(baseBitcoin, DateUtil.getDayOfMonth()+"", 2)+"", DateUtil.getCurrDateString("dd")+"", 2)+"";
		//return  String.format("%.2f", returnstr);
	}


	public void setNowBitcoin(String nowBitcoin) {
		this.nowBitcoin = nowBitcoin;
	}

	/**
	 * 获取当前日期字符串 格式为yyyy-MM-dd
	 * */
	public String getNowDate() {
		return DateUtil.getCurrDateString();
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	

}
