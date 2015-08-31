package com.unitever.platform.core.spring.external;

import java.util.List;

/**
 * 匿名URL资源对外提供接口，不受系统登录限制；
 * 本接口由子产品实现,例如：AnonymousAccessResourcesImpl；
 * 实现类的Spring Component注解要加上子产品缩写，例如：@Component("XX_AnonymousAccessResourcesImpl")。
 * @author wdw
 */
public interface IAnonymousAccessResources {
	/**
	 * 获取匿名资源URL集合（例如："/component/i18n!ajaxGetI18nValues.action*" 或  "/framework/common/**"）
	 */
	List<String> getResourceUrls();
	
}
