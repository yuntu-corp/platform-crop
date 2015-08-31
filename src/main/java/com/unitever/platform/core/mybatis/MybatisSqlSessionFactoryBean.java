package com.unitever.platform.core.mybatis;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.unitever.platform.util.StringUtil;

public class MybatisSqlSessionFactoryBean extends SqlSessionFactoryBean {
	final static Logger logger = LoggerFactory.getLogger(MybatisSqlSessionFactoryBean.class);
	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		String[] typeAliasesPackageArray = typeAliasesPackage.split(",");
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		Set<String> typeAliasesPackages = new LinkedHashSet<String>(0);
		for (String _typeAliasesPackage : typeAliasesPackageArray) {
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + _typeAliasesPackage.replace('.', '/');
			String prefix = _typeAliasesPackage.substring(0, _typeAliasesPackage.indexOf(".")) + "/";
			try {
				Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
				for (Resource resource : resources) {
					String path = resource.getURI().getPath();
					if(path==null || path.indexOf("jar")>-1)continue;
					String typeAliasesPack = path.substring(path.indexOf(prefix), path.length()).replace('/', '.');
					if(typeAliasesPack.endsWith(".")){
						typeAliasesPack=typeAliasesPack.substring(0, typeAliasesPack.length()-1);
					}
					typeAliasesPackages.add(typeAliasesPack);
				}
			} catch (IOException e) {
				logger.error("参数typeAliasesPackage 获取别名包路径异常");
			}
		}

		String[] typeAliases = typeAliasesPackages.toArray(new String[] {});
		if (typeAliases != null && typeAliases.length > 0) {
			super.setTypeAliasesPackage(StringUtil.arrayToStr(typeAliases));
		} else {
			logger.warn("参数typeAliasesPackage:"+typeAliasesPackage+"，未找到任何包");  
		}
	}
}
