package com.unitever.platform.core.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 对标有@CacheInRequest的方法进行缓存的拦截器
 * 
 * @author tianyl
 * 
 */
@Aspect
@Component("sys_Cache4RequestAspect")
public class Cache4RequestAspect {

	@Around("@annotation(com.unitever.platform.core.cache.CacheInRequest)")
	public Object aroundCache(ProceedingJoinPoint pjp) throws Throwable {
		String key = Cache4RequestAspect.class.getName() + "_" + getKey(pjp);
		Object cache = Cache4RequestUtil.get(key);
		if (cache != null) {
			return cache;
		}
		Object obj = pjp.proceed();
		if (obj != null) {
			Cache4RequestUtil.put(key, obj);
		}
		return obj;
	}

	private String getKey(ProceedingJoinPoint pjp) {
		Object args[] = pjp.getArgs();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().getName();
		String key = className + "." + methodName + "(";
		for (Object obj : args) {
			if (obj != null) {
				key += obj.toString() + ",";
			}
		}
		key += ")";
		return key;
	}
}
