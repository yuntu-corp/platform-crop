package com.unitever.platform.core.dao;

import com.unitever.platform.core.external.UserUtil;
import com.unitever.platform.util.DateUtil;
import com.unitever.platform.util.ExceptionUtil;
import com.unitever.platform.util.ReflectUtil;
import com.unitever.platform.util.UUID;

public class ModelHelper {
	public static String PK_COLUMN_NAME = "id";

	/**
	 * 取得对象的主键名.
	 */
	public static String getIdName(Class<?> entityClass) {
		return PK_COLUMN_NAME;
	}


	public static Object getIdValue(Object entity) {
		return ReflectUtil.invokeGetterMethod(entity, getIdName(entity.getClass()));
	}

	/**
	 * 生成对象
	 */
	public static Object newInstance(Class<?> entityClass) {
		try {
			return entityClass.newInstance();
		} catch (Exception e) {
			throw ExceptionUtil.convertExceptionToUnchecked(e);
		}
	}


	/**
	 * 设置实体的主键
	 * 
	 * @param entity
	 */
	public static void setPK(Object entity) {
		setPK(entity, UUID.getUUID());
	}


	/**
	 * 设置实体的主键
	 * 
	 * @param entity
	 */
	public static void setPK(Object entity, String id) {
		ReflectUtil.setFieldValue(entity, getIdName(entity.getClass()), id);
	}

	/**
	 * 设置添加用户与添加时间
	 */
	public static void setDefaultWithSave(Object entity) {
		ReflectUtil.setFieldValue(entity, "fu", UserUtil.getCurrUserID());
		ReflectUtil.setFieldValue(entity, "ft", DateUtil.getCurrDateSecondString());
	}
	
	/**
	 * 设置修改用户与修改时间
	 */
	public static void setDefaultWithUpdate(Object entity) {
		ReflectUtil.setFieldValue(entity, "lu", UserUtil.getCurrUserID());
		ReflectUtil.setFieldValue(entity, "lt", DateUtil.getCurrDateSecondString());
	}
}
