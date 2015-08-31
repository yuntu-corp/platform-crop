package com.unitever.module.user.model;



public class User {

	private String id;
	private String username;
	private String password;
	private String name;
	private String weChatNum;
	private String type;
	private String accessToken;
	private String jsapiTicket;
	private String expiresTime;
	private String appId;
	private String appSecret;
	private String mchId;
	private String apiSecret;
	private String discount;
	private String customerServiceUrl;
	private String wealthModeUrl;
	private String businessCultureUrl;
	private String ft;
	private String lt;
	private String fu;
	private String lu;
	
	/** 用户类型常量 */
	public static final String USER_TYPE_STUDENT = "0";
	public static final String USER_TYPE_PATRIARCH = "1";
	public static final String USER_TYPE_TEACHER = "2";
	public static final String USER_TYPE_ADMIN = "3";//学校管理员
	public static final String USER_TYPE_OTHER = "4";//系统管理员
	/** 默认密码 */
	public static final String DEFAULT_PASSWORD = "000000";
	
	public static final String PRODUCTTITLE = "微信分销系统";
	
	public User() {
		super();
	}
	public User(String id) {
		super();
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeChatNum() {
		return weChatNum;
	}
	public void setWeChatNum(String weChatNum) {
		this.weChatNum = weChatNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getJsapiTicket() {
		return jsapiTicket;
	}
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
	public String getExpiresTime() {
		return expiresTime;
	}
	public void setExpiresTime(String expiresTime) {
		this.expiresTime = expiresTime;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getApiSecret() {
		return apiSecret;
	}
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getCustomerServiceUrl() {
		return customerServiceUrl;
	}
	public void setCustomerServiceUrl(String customerServiceUrl) {
		this.customerServiceUrl = customerServiceUrl;
	}
	public String getWealthModeUrl() {
		return wealthModeUrl;
	}
	public void setWealthModeUrl(String wealthModeUrl) {
		this.wealthModeUrl = wealthModeUrl;
	}
	public String getBusinessCultureUrl() {
		return businessCultureUrl;
	}
	public void setBusinessCultureUrl(String businessCultureUrl) {
		this.businessCultureUrl = businessCultureUrl;
	}
	public String getFt() {
		return ft;
	}
	public void setFt(String ft) {
		this.ft = ft;
	}
	public String getLt() {
		return lt;
	}
	public void setLt(String lt) {
		this.lt = lt;
	}
	public String getFu() {
		return fu;
	}
	public void setFu(String fu) {
		this.fu = fu;
	}
	public String getLu() {
		return lu;
	}
	public void setLu(String lu) {
		this.lu = lu;
	}
}