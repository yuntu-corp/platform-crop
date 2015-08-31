package com.unitever.demo.dao.manual;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitever.demo.model.TBUser;
import com.unitever.platform.core.dao.BaseDAO;
import com.unitever.platform.core.dao.Page;

@Repository
public class TBUserDAO extends BaseDAO<TBUser, String>{
	public Page<TBUser> getPageWithDemo(List<TBUser> list,Page<TBUser> page){
		return this.getPage(list, page);
	}
}
