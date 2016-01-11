package com.unitever.module.distribute.service;

import java.util.Date;
import java.util.List;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.module.distribute.dao.manual.DistributeDAO;
import com.unitever.module.distribute.model.Distribute;
import com.unitever.module.employee.dao.manual.EmployeeDAO;
import com.unitever.module.employee.model.Employee;
import com.unitever.module.log.model.Log;
import com.unitever.module.user.model.User;
import com.unitever.module.wechat.manager.SessionManager;
import com.unitever.module.wechat.util.LogUtil;
import com.unitever.platform.core.common.helper.UserHelper;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.util.DoubleUtil;

@Service
@Transactional
public class DistributeService {

	public Distribute getDistributeById(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return distributeDAO.get(id);
	}

	public void save(Distribute distribute) {
		if (distribute != null) {
			distributeDAO.save(distribute);
		}
	}

	public List<Distribute> getAllDistributes() {
		return distributeDAO.getAll();
	}

	/**
	 * 获取任务分页对象
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	public Page<Distribute> getPage(Page<Distribute> page, Distribute distribute) {
		List<Distribute> distributeList = distributeDAO.getDistributeByDistribute(distribute);
		page.setTotalRecord(distributeList.size());
		if (distributeList.size() >= (page.getStartOfPage() + page.getPageSize())) {
			page.setResults(distributeList.subList(page.getStartOfPage(), page.getStartOfPage() + page.getPageSize()));
		} else {
			page.setResults(distributeList.subList(page.getStartOfPage(), distributeList.size()));
		}
		return page;
	}
	
	
	
	public void distribute(Distribute distribute) throws WxErrorException{
		if(StringUtils.isNotBlank(distribute.getEmployeeId())&&StringUtils.isNotBlank(distribute.getBitcoin())){
			User user = UserHelper.getCurrUser();
			Employee employeeReal=employeeDAO.get(distribute.getEmployeeId());
			//更新虚拟币
			Employee employee=new Employee();
			employee.setId(distribute.getEmployeeId());
			employee.setBitcoinIncome(DoubleUtil.add(employeeReal.getBitcoinIncome(), distribute.getBitcoin(), 8)+"");
			employee.setBitcoinSurplus(DoubleUtil.add(employeeReal.getBitcoinSurplus(),distribute.getBitcoin(),8)+ "");
			employeeDAO.update(employee);
			//添加日志
			LogUtil.saveLog("管理员分发给："+employeeReal.getName()+distribute.getBitcoin()+"虚拟币","管理员", Log.ADMIN_TYPE_YES, user.getId(), Log.LOGTYPE_DISTRIBUTE, distribute.getId(), Log.OPERATE_DISTRIBUTE);
			//保存分发实体
			distribute.setDate(new Date());
			distribute.setEmployee(new Employee(distribute.getEmployeeId()));
			distributeDAO.save(distribute);
			
			WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
			WxCpMessage me = new WxCpMessage();
			me.setMsgType(WxConsts.XML_MSG_TEXT);
			me.setAgentId("15");
			me.setContent( "管理员给您分发了："+distribute.getBitcoin()+"虚拟币！\r\n原因："+distribute.getReason());
			me.setToUser(employeeReal.getUserId());
			service.messageSend(me);
		}
	}
	
	
	
	
	@Autowired
	private DistributeDAO distributeDAO;
	@Autowired
	private EmployeeDAO employeeDAO;

}