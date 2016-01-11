package com.unitever.module.distribute.dao.manual;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitever.module.distribute.model.Distribute;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class DistributeDAO extends BaseDAO<Distribute, String>{
	
	public List<Distribute> getDistributeByDistribute(Distribute distribute) {
		return this.getList("getDistributeByDistribute", distribute);
	}

}