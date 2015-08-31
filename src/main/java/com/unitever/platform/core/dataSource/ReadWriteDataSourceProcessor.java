package com.unitever.platform.core.dataSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import com.unitever.platform.core.dataSource.helper.DataSourceHolder;

public class ReadWriteDataSourceProcessor implements BeanPostProcessor {

	private List<String> readMethodList = Collections.synchronizedList(new ArrayList<String>());

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (!(bean instanceof NameMatchTransactionAttributeSource)) {
			return bean;
		}

		try {
			NameMatchTransactionAttributeSource transactionAttributeSource = (NameMatchTransactionAttributeSource) bean;
			Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
			nameMapField.setAccessible(true);
			@SuppressWarnings("unchecked")
			Map<String, TransactionAttribute> nameMap = (Map<String, TransactionAttribute>) nameMapField.get(transactionAttributeSource);

			for (Entry<String, TransactionAttribute> entry : nameMap.entrySet()) {
				RuleBasedTransactionAttribute attr = (RuleBasedTransactionAttribute) entry.getValue();
				String methodName = entry.getKey();
				if (!attr.isReadOnly()) {
					continue;
				}
				readMethodList.add(methodName);
			}

		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}

		return bean;
	}

	public Object determineReadOrWriteDB(ProceedingJoinPoint pjp) throws Throwable {

		if (isChoiceReadDB(pjp.getSignature().getName())) {
			DataSourceHolder.markReadOnly();
		} else {
			DataSourceHolder.markWrite();
		}

		try {
			return pjp.proceed();
		} finally {
			DataSourceHolder.clearReadOnly();
		}

	}

	private boolean isChoiceReadDB(String methodName) {
		// 之前已设置为写
		if (DataSourceHolder.isWrite()) {
			return false;
		}

		for (String mappedName : readMethodList) {
			if (isMatch(methodName, mappedName)) {
				return true;
			}
		}

		// 默认选择 写库
		return false;
	}

	private boolean isMatch(String methodName, String mappedName) {
		return PatternMatchUtils.simpleMatch(mappedName, methodName);
	}
}
