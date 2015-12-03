package com.unitever.module.dicker.dao.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.unitever.module.dicker.model.Dicker;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class DickerDAO extends BaseDAO<Dicker, String>{
	
	public List<Dicker> getDickerByDicker(Dicker dicker) {
		return this.getList("getDickerByDicker", dicker);
	}
	
	public void deleteDickerByTaskId(String taskId) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("taskId", taskId);
		this.delete("deleteDickerByTaskId", taskId);
	}
}