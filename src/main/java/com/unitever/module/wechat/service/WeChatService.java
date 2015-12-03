package com.unitever.module.wechat.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutTextMessage;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.popular.bean.EventMessage;
import weixin.popular.util.XMLConverUtil;

import com.unitever.module.employee.model.Employee;
import com.unitever.module.employee.service.EmployeeService;
import com.unitever.module.user.model.User;
import com.unitever.module.wechat.aes.WXBizMsgCrypt;
import com.unitever.module.wechat.manager.SessionManager;
import com.unitever.module.wechat.util.MessageUtil;
import com.unitever.module.wechat.util.WechatUtil;

import org.apache.commons.io.IOUtils;

@Service
@Transactional
public class WeChatService {
	
	/**
	 * 处理微信响应请求
	 * @param request
	 * @return
	 */
	public WxCpXmlOutMessage weChatHandler(WxCpXmlMessage wxMessage) {  
		
		
			WxCpServiceImpl service =(WxCpServiceImpl) SessionManager.getSession("service");
			//用户ID
			String fromUserName = wxMessage.getFromUserName();
			// 企业号CorpID 
			String toUserName = wxMessage.getToUserName();
			// 消息类型
			String msgType =wxMessage.getMsgType();
			
			WxCpXmlOutTextMessage msg=new WxCpXmlOutTextMessage();
			msg.setFromUserName(toUserName);
			msg.setToUserName(fromUserName);
			msg.setCreateTime(new Date().getTime());
			msg.setMsgType(WxConsts.XML_MSG_TEXT);
			try {
			//WxConsts.EVT_CLICK
			if (msgType.equals(WxConsts.XML_MSG_TEXT)) {// 文本消息
				// 接收用户发送的文本消息内容
				String content =wxMessage.getContent();
				System.out.println("content:"+content);
				if("menu".equals(content)){
					System.out.println("初始化菜单");
					WechatUtil.initMenu(toUserName);
					msg.setContent("初始化菜单成功！");
					return msg;
				}
				msg.setContent("谢谢您发消息给我们，我们会尽快给您答复！");
				return msg;
			}else if(msgType.equals(WxConsts.XML_MSG_EVENT)){
				String event = wxMessage.getEvent();
				
				//关注
				if(WxConsts.EVT_SUBSCRIBE.equals(event)){
					//通过userid获取user
					WxCpUser user=service.userGet(fromUserName);
					if(StringUtils.isBlank(user.getAvatar())){
						user=service.userGet(fromUserName);
					}
					msg.setContent("亲，欢迎关注云图科技企业号哦！");
					employeeService.initEmployee(user);
					return msg;
				}
				//取消关注
				else if(WxConsts.EVT_UNSUBSCRIBE.equals(event)){
					
				}
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
			msg.setContent("亲，您还不是我们公司的成员哦！");
			return msg;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Employee reGetHeadImgUrl(Employee employee){
		WxCpServiceImpl service =(WxCpServiceImpl) SessionManager.getSession("service");
		try {
			WxCpUser user=service.userGet(employee.getUserId());
			employee.setHeadimgurl(user.getAvatar());
			employeeService.update(employee);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
		
	}
	
	
	@Autowired
	private EmployeeService employeeService;
}