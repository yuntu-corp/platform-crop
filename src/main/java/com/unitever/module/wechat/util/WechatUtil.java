package com.unitever.module.wechat.util;

import java.util.ArrayList;
import java.util.List;

import com.unitever.module.wechat.manager.SessionManager;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;

/**
 * @author 作者 : 邓文杰
 * @version 创建时间：2015-11-5 上午9:17:13 类说明
 */
public class WechatUtil {

	public static void initMenu(String corpId) {
		try {
			/*
			 * WxCpServiceImpl menuService = new WxCpServiceImpl();
			 * WxCpInMemoryConfigStorage storage=new
			 * WxCpInMemoryConfigStorage();
			 * storage.setCorpId("wxef727939c7cd2d6c"); storage.setCorpSecret(
			 * "WdgrkeErR3SYMO4l3ukiUEilUQ4b8haLaWsgjvsOtxyWzho8dIcuL5LE_RG3_CP6"
			 * ); storage.setAgentId("15");
			 * menuService.setWxCpConfigStorage(storage);
			 */
			String domin = "test.yuntunet.cn";
			WxCpServiceImpl menuService = (WxCpServiceImpl) SessionManager
					.getSession("service");
			List<WxMenuButton> buttons = new ArrayList<WxMenuButton>();

			WxMenuButton button1 = new WxMenuButton();
			button1.setType(WxConsts.BUTTON_VIEW);
			button1.setName("今日价格");
			button1.setUrl(menuService.oauth2buildAuthorizationUrl("http://"
					+ domin + "/platform/weChat/currentInfo", corpId));
			buttons.add(button1);

			WxMenuButton button2 = new WxMenuButton();
			button2.setName("任务");
			buttons.add(button2);

			WxMenuButton button21 = new WxMenuButton();
			button21.setType(WxConsts.BUTTON_VIEW);
			button21.setName("任务公告");
			button21.setUrl(menuService.oauth2buildAuthorizationUrl("http://"
					+ domin + "/platform/weChat/taskList", corpId));

			WxMenuButton button22 = new WxMenuButton();
			button22.setType(WxConsts.BUTTON_VIEW);
			button22.setName("发布任务");
			button22.setUrl(menuService.oauth2buildAuthorizationUrl("http://"
					+ domin + "/platform/weChat/publishTask", corpId));

			button2.getSubButtons().add(button21);
			button2.getSubButtons().add(button22);

			WxMenuButton button3 = new WxMenuButton();
			button3.setType(WxConsts.BUTTON_VIEW);
			button3.setName("个人中心");
			button3.setUrl(menuService.oauth2buildAuthorizationUrl("http://"
					+ domin + "/platform/weChat/personalCenter", corpId));
			buttons.add(button3);

			WxMenu menu = new WxMenu();
			menu.setButtons(buttons);
			menuService.menuCreate(menu);
		} catch (WxErrorException e) {
			System.out.println("error:" + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}
