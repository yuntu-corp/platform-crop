package com.unitever.platform.core.cache;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.unitever.platform.core.spring.SpringContextHolder;
import com.unitever.platform.util.StringUtil;

@Aspect
@Component("sys_Cache4PersistenceAspect")
public class Cache4PersistenceAspect {

	private static final Log LOG = LogFactory.getLog(Cache4PersistenceAspect.class);

	@SuppressWarnings("unchecked")
	@Around("@annotation(com.unitever.platform.core.cache.CacheInPersistence)")
	public Object aroundCache(ProceedingJoinPoint pjp) throws Throwable {
		String businessKey = Cache4PersistenceAspect.class.getName() + "_" + getKey(pjp);
		Object obj = CacheUtil.getCache(businessKey);
		if (obj != null) {
			return obj;
		}
		obj = pjp.proceed();
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		CacheInPersistence cacheInPersistence = method.getAnnotation(CacheInPersistence.class);
		CacheExpiredKeyExecutor executor = SpringContextHolder.getBeanOneOfType(cacheInPersistence.expiredKeyExecutor());
		int expiredKeyPosition = cacheInPersistence.expiredKeyPosition();
		Object argVal = pjp.getArgs()[expiredKeyPosition];
		Object expiredKey = executor.execute(argVal);
		if (expiredKey instanceof String) {
			if (!cacheInPersistence.ignoreExpiredKeyCommaSplit() && expiredKey.toString().contains(",")) {
				CacheUtil.storeCache(StringUtil.strToStrArray(expiredKey.toString()), businessKey, obj);
			} else {
				CacheUtil.storeCache(expiredKey.toString(), businessKey, obj);
			}
		} else if (expiredKey instanceof String[]) {
			CacheUtil.storeCache((String[]) expiredKey, businessKey, obj);
		} else if (expiredKey instanceof List) {
			CacheUtil.storeCache(((List<String>) expiredKey).toArray(new String[] {}), businessKey, obj);
		} else {
			LOG.warn("不支持的expiredKey类型，忽略缓存！");
		}
		return obj;
	}

	private String getKey(ProceedingJoinPoint pjp) {
		Object args[] = pjp.getArgs();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().getName();
		String key = className + "." + methodName + "(";
		int i = 0;
		for (Object obj : args) {
			if (obj != null) {
				key += "arg" + i + ":" + obj.toString() + ",";
			} else {
				key += "arg" + i + ":" + "null,";
			}
			i++;
		}
		key += ")";
		return key;
	}
}
