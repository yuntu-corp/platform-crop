package com.unitever.platform.core.spring.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.unitever.platform.core.web.context.ServletContextMonitor;


/**
 * @author : wdw
 */

public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, ServletContextMonitor{

	private ResourceDetailsService resourceDetailsService;

	public void setResourceDetailsService(ResourceDetailsService resourceDetailsService) {
		this.resourceDetailsService = resourceDetailsService;
	}
    private PathMatcher urlMatcher = new AntPathMatcher();
    private static Map<String, Collection<ConfigAttribute>> commonResourceMap = null;
    private static Map<String, Collection<ConfigAttribute>> authorizeResourceMap = null;

    public MyInvocationSecurityMetadataSource() {
    }

    /**
     * 加载受权限控制的系统资源
     */
    private void loadResourceDefine() {
        try {
        	commonResourceMap = buildSecurityConfigMap(resourceDetailsService.getCommonRequestMap());
        	authorizeResourceMap = buildSecurityConfigMap(resourceDetailsService.getUserRequestMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    /**
     * 构建Spring权限资源Map集合
     */
    private Map<String, Collection<ConfigAttribute>> buildSecurityConfigMap(LinkedHashMap<String, String> resrouceMap){
    	Map<String, Collection<ConfigAttribute>> securityConfigMap = new LinkedHashMap<String, Collection<ConfigAttribute>>(0);
		for (Map.Entry<String, String> entry : resrouceMap.entrySet()) {
			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>(0);
			if (StringUtils.isNotBlank(entry.getValue())) {
				String[] auths = entry.getValue().split(",");
				for(String auth : auths){
					atts.add(new SecurityConfig(auth));
				}
			} 
			securityConfigMap.put(entry.getKey(), atts);
		}
    	return securityConfigMap;
    }
    
	/**
	 * 搜索匹配资源地址的权限集合
	 */
	private Collection<ConfigAttribute> searchConfigAttributeWithPathMatchesUrl(String url,Map<String, Collection<ConfigAttribute>> resourceMap){
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
		   String resURL = ite.next();
		   if (urlMatcher.match(resURL,url)) {
			   return resourceMap.get(resURL);
		   }
		}
		return null;
	}
    
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // guess object is a URL.
		String url = ((FilterInvocation)object).getRequestUrl();
		if(authorizeResourceMap.containsKey(url)){
			return authorizeResourceMap.get(url);
		}
		return searchConfigAttributeWithPathMatchesUrl(url,commonResourceMap);
	}
	
	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	
	public static void main(String[] args) {
		PathMatcher urlMatcher = new AntPathMatcher();
		System.out.println(urlMatcher.match("/**", "/ac/index!toIndex.action"));
	}

	@Override
	public void init(ServletContext context) {
		//服务器启用时候调用
		loadResourceDefine();
	}

	@Override
	public void destroyed(ServletContext context) {
		
	}

}


