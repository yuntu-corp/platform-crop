package com.unitever.platform.core.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用在方法上，把参数值作为key，返回值作为value缓存，方法参数均为基本类型和字符串类型。此缓存只在当前request中有效。
 * 
 * @author tianyl
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheInRequest {

}
