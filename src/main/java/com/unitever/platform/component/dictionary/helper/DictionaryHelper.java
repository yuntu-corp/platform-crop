package com.unitever.platform.component.dictionary.helper;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.component.dictionary.service.DictionaryValueService;
import com.unitever.platform.core.spring.SpringContextHolder;


/**
 * 数据字典助手类
 */

public class DictionaryHelper {

	
	/**
	 * 根据[字典编码] 与[字典值编码 ]得到字典值显示结果
	 */
	public static String getDictValue(String dictCode , String valueCode , boolean isFullPath){
		if(StringUtils.isEmpty(dictCode) || StringUtils.isEmpty(valueCode)){
			return "";
		}
		String valueView = "";
		DictionaryValueService dictValueService = SpringContextHolder.getBean(DictionaryValueService.class);
		
		if (valueCode.indexOf(",") == -1) {
			valueView = dictValueService.getDictViewWithDictCodeAndValueCode(dictCode, valueCode, isFullPath);
		} else {
			String[] valueCodes = valueCode.split(",");
			for (String code : valueCodes) {
				valueView += dictValueService.getDictViewWithDictCodeAndValueCode(dictCode, code, isFullPath) + "&nbsp;";
			}
		}
		return valueView;
	}
}


