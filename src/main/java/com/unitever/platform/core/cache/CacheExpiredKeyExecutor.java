package com.unitever.platform.core.cache;

public interface CacheExpiredKeyExecutor {

	/**
	 * 
	 * @param obj
	 *            只可以为String类型或String[]类型或List<String>类型
	 * @return
	 */
	public Object execute(Object obj);

}
