package com.unitever.module.wechat.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;

import org.springframework.beans.factory.InitializingBean;

import com.unitever.module.user.model.User;
import com.unitever.module.user.service.UserService;
import com.unitever.module.wechat.manager.SessionManager;

public class TokenManagerListener  implements InitializingBean{
	
	/*给web容器添加一个Listener类，在容器启动的时候执行Listener的“初始化”方法，在这个初始化方法中执行查询数据库的所有操作，然后将数据库中的信息缓存起来 
	问题：上面的方法很好，但问题是如何去查询数据库，由于使用了spring的IOC特性，查询数据库的service控制dao层，dao层访问数据库，
	而Listener类只是在系统启动的时候会执行初始化方法，但是“service”对象没有被spring管理，也就是说没有service对象访问数据库，
	只能使用jdbc的方式连接，这个很显然不符合MVC开发的特性。那么如何才能在启动web容器的时候利用spring的IOC特性呢？ 
	解决方法：spring给出的解决方案——写一个类，实现InitializingBean接口，然后交由spring容器管理
	需要在applicationContext中配置
	<bean class="com.unitever.module.user.service.UserService" id="userService"></bean>
	
	<bean class="com.unitever.module.wechat.listener.TokenManagerListener">
		<property name="userService" ref="userService"></property>
	</bean>
	*/
		  
	   private UserService userService;
	      
	    public UserService getUserService() {  
	        return userService;  
	    }  
	    public void setUserService(UserService userService) {  
	        this.userService = userService;  
	    } 
	  
	    @Override  
	    public void afterPropertiesSet() throws Exception {  
	    	System.out.println("<<<<<<<<<<初始化");
	    	//在这个方法里面写 初始化的数据也可以。  
	    	/*List<User> userList=userService.getUserList();
	    	for(User user:userList){
	    		WxCpServiceImpl service = new WxCpServiceImpl();
				WxCpInMemoryConfigStorage storage=new WxCpInMemoryConfigStorage();
				storage.setCorpId(user.getCorpId());
				storage.setCorpSecret(user.getCorpSecret());
				storage.setAgentId(user.getAgentId());
				storage.setAesKey(user.getEncodingAesKey());
				storage.setToken(user.getToken());
				service.setWxCpConfigStorage(storage);
				SessionManager.addSession("service"+user.getCorpId(), service);
				SessionManager.addSession("storage"+user.getCorpId(), storage);
	    		System.out.println("<<<<<<<<<<初始化");
	    	}*/
	    	//Map<String, User> map=new HashMap<String, User>();
	    	//初始化所有user
	    	/*List<User> users=userService.getUserList();
	    	for(User user:users){
	    		WxCpServiceImpl service = new WxCpServiceImpl();
		    	WxCpInMemoryConfigStorage storage=new WxCpInMemoryConfigStorage();
				storage.setCorpId(user.getCorpId());
				storage.setCorpSecret(user.getCorpSecret());
				storage.setAgentId(user.getAgentId());
				storage.setAesKey(user.getEncodingAesKey());
				storage.setToken(user.getToken());
				service.setWxCpConfigStorage(storage);
		    	SessionManager.addSession("storage"+user.getCorpId(), storage);
		    	SessionManager.addSession("service"+user.getCorpId(), service);
	    	}*/
	    	User user=userService.getUserList().get(0);
	    	WxCpServiceImpl service = new WxCpServiceImpl();
	    	WxCpInMemoryConfigStorage storage=new WxCpInMemoryConfigStorage();
			storage.setCorpId(user.getCorpId());
			storage.setCorpSecret(user.getCorpSecret());
			storage.setAgentId(user.getAgentId());
			storage.setAesKey(user.getEncodingAesKey());
			storage.setToken(user.getToken());
			service.setWxCpConfigStorage(storage);
	    	SessionManager.addSession("storage", storage);
	    	SessionManager.addSession("service", service);
	    }     
}
		
