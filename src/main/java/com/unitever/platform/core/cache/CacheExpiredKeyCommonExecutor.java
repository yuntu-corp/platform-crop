package com.unitever.platform.core.cache;

import org.springframework.stereotype.Component;

@Component("fw_CacheExpiredKeyCommonExecutor")
public class CacheExpiredKeyCommonExecutor implements CacheExpiredKeyExecutor {

	@Override
	public Object execute(Object obj) {
		return obj;
	}

}
