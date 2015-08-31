package com.unitever.platform.core.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用在类上，缓存此类中所有方法的返回值，
 * 
 * @author tianyl
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheInPersistence {

	/**
	 * key位置，基于0
	 * 
	 * @return
	 */
	int expiredKeyPosition() default 0;

	/**
	 * 获取ExpiredKey的class，必须实现 CacheExpiredKeyExecutor
	 * 
	 * @return
	 */
	Class<? extends CacheExpiredKeyExecutor> expiredKeyExecutor() default CacheExpiredKeyCommonExecutor.class;

	/**
	 * 忽略逗号分割的ExpiredKey值。默认会把ExpiredKey值类型为String的，按逗号分割成数组。
	 * 
	 * @return
	 */
	boolean ignoreExpiredKeyCommaSplit() default false;
}
