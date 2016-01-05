package com.unitever.module.log.dao.manual;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitever.module.log.model.Log;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class LogDAO extends BaseDAO<Log, String> {

	/**
	 * <p>
	 * 根据Log条件查询Log
	 * </p>
	 * 
	 * @param log
	 * @return List<Log>
	 */
	public List<Log> getLogByLog(Log log) {
		return this.getList("getLogByLog", log);
	}

}