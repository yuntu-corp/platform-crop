package com.unitever.platform.core.spring;

import java.util.Collection;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 * 
 * @author 关攀攀 E-mail:panpan_001@126.com
 */
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBean(clazz);
	}

	/**
	 * 按类型获得所有实现类
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
		checkApplicationContext();
		return applicationContext.getBeansOfType(clazz);
	}

	/**
	 * 按类型获得一个实现类，没有时返回null
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBeanOneOfType(Class<T> clazz) {
		checkApplicationContext();
		Collection<T> results = applicationContext.getBeansOfType(clazz).values();
		return results.size() > 0 ? results.iterator().next() : null;
	}

	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}
}
