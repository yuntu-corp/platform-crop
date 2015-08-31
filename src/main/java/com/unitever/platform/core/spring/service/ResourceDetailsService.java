package com.unitever.platform.core.spring.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.platform.core.spring.SpringContextHolder;
import com.unitever.platform.core.spring.external.IAnonymousAccessResources;
import com.unitever.platform.core.spring.security.SecurityUser;

/**
 * @author : wdw
 */
@Service
@Transactional
public class ResourceDetailsService {

	public LinkedHashMap<String, String> getCommonRequestMap() {
		LinkedHashMap<String, String> resourceMap = new LinkedHashMap<String, String>();
		List<UrlResource> rs = new ArrayList<UrlResource>();
		//静态地址
		rs.add(buildUrlResourceWithAnonymously("/sys/i18n/ajaxGetI18nValues*"));
		rs.add(buildUrlResourceWithAnonymously("/bd/welcome/welcome*"));
		rs.add(buildUrlResourceWithAnonymously("/module/homepage/welcome*.jsp"));
		rs.add(buildUrlResourceWithAnonymously("/bd/welcome/ajaxValidationUser*"));
		rs.add(buildUrlResourceWithAnonymously("/bd/welcome/validatePatchca*"));
		rs.add(buildUrlResourceWithAnonymously("/bd/welcome/getSessionPatchca*"));
		rs.add(buildUrlResourceWithAnonymously("/bd/welcome/validateSessionPatchca*"));
		rs.add(buildUrlResourceWithAnonymously("/platform/common/css/**"));
		rs.add(buildUrlResourceWithAnonymously("/platform/common/js/**"));
		rs.add(buildUrlResourceWithAnonymously("/platform/common/jsp/**"));
		rs.add(buildUrlResourceWithAnonymously("/platform/common/images/**"));
		//登录页引baseJs用到的,如果没引用可以去掉
		rs.add(buildUrlResourceWithAnonymously("/platform/common/js/**"));
		rs.add(buildUrlResourceWithAnonymously("/platform/theme/login/**"));
		
		rs.add(buildUrlResourceWithAnonymously("/bd/school/register*"));
		rs.add(buildUrlResourceWithAnonymously("/bd/school/validateName*"));
		rs.add(buildUrlResourceWithAnonymously("/bd/school/validateUsername*"));
		rs.add(buildUrlResourceWithAnonymously("/bd/school/doRegister*"));
		rs.add(buildUrlResourceWithAnonymously("/bd/area/listChildrenJson*"));
		rs.add(buildUrlResourceWithAnonymously("/platform/theme/*/component/**"));
		rs.add(buildUrlResourceWithAnonymously("/platform/component/**"));//组件的先全放开吧
		rs.add(buildUrlResourceWithAnonymously("/platform/theme/distributionSystem/**"));
		rs.add(buildUrlResourceWithAnonymously("/weChat/**"));
		rs.add(buildUrlResourceWithAnonymously("/sys/attachment/**"));
		rs.add(buildUrlResourceWithAnonymously("/platform/images/**"));
		
		//所有实现IAnonymousAccessResources接口的子类
		Map<String, IAnonymousAccessResources> anonymousResourcesServiceMap = SpringContextHolder.getBeansOfType(IAnonymousAccessResources.class);
		if(anonymousResourcesServiceMap!=null){
			for (IAnonymousAccessResources accessResources : anonymousResourcesServiceMap.values()) {
				List<String> resourceUrls = accessResources.getResourceUrls();
				for(String resourceUrl : resourceUrls){
					rs.add(buildUrlResourceWithAnonymously(resourceUrl));
				}
			}
		}
		
		/** -所有权限都允许都不过滤URL__Start- **/
		rs.add(buildUrlResourceWithAuthenticated("/**/*.html*", SecurityUser.AUTH_DEFAULT));
		rs.add(buildUrlResourceWithAuthenticated("/**/*.jsp*", SecurityUser.AUTH_DEFAULT));
		rs.add(buildUrlResourceWithAuthenticated("/**/*.do*", SecurityUser.AUTH_DEFAULT));
		/** -所有权限都允许都不过滤URL__End- **/
		rs.add(buildUrlResourceWithAuthenticated("/**", "IS_AUTHENTICATED_FULLY"));
		rs.add(buildUrlResourceWithAuthenticated("/**", "IS_AUTHENTICATED_REMEMBERED"));
		
		for (UrlResource resource : rs) {
			String url = resource.getUrl();
			String auth = resource.getAuth();
			if (resourceMap.containsKey(url)) {
				String value = resourceMap.get(url);
				resourceMap.put(url, value + "," + auth);
			} else {
				resourceMap.put(url, auth);
			}
		}
		return resourceMap;
	}

	public LinkedHashMap<String, String> getUserRequestMap() {
		LinkedHashMap<String, String> resourceMap = new LinkedHashMap<String, String>();
		List<UrlResource> rs = new ArrayList<UrlResource>();
		//自定义的操作
		for (UrlResource resource : rs) {
			String url = resource.getUrl();
			String auth = resource.getAuth();
			if (resourceMap.containsKey(url)) {
				String value = resourceMap.get(url);
				resourceMap.put(url, value + "," + auth);
			} else {
				resourceMap.put(url, auth);
			}
		}
		return resourceMap;
	}

	/**
	 * 构建资源（匿名）
	 */
	private UrlResource buildUrlResourceWithAnonymously(String url){
		return new UrlResource(url,"IS_AUTHENTICATED_ANONYMOUSLY");
	}
	
	/**
	 * 构建资源（权限认证）
	 */
	private UrlResource buildUrlResourceWithAuthenticated(String url , String auth){
		return new UrlResource(url,auth);
	}
	
	/**
	 * @author : wdw
	 */
	private static class UrlResource {
		private String url;
		private String auth;

		public UrlResource() {
			
		}

		public UrlResource(String url, String auth) {
			this.url = url;
			this.auth = auth;
		}

		public String getUrl() {
			return url;
		}

		public String getAuth() {
			return auth;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public void setAuth(String role) {
			this.auth = role;
		}
	}

}
