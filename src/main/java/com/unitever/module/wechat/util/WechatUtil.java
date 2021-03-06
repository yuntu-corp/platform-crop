package com.unitever.module.wechat.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.unitever.module.wechat.manager.SessionManager;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.PKCS7Encoder;
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
			WxCpServiceImpl menuService = (WxCpServiceImpl) SessionManager.getSession("service");
			List<WxMenuButton> buttons = new ArrayList<WxMenuButton>();

			WxMenuButton button1 = new WxMenuButton();
			button1.setType(WxConsts.BUTTON_VIEW);
			button1.setName("今日价格");
			button1.setUrl(menuService.oauth2buildAuthorizationUrl("http://" + domin + "/platform/weChat/currentInfo", corpId));
			buttons.add(button1);

			WxMenuButton button2 = new WxMenuButton();
			button2.setName("任务");
			buttons.add(button2);

			WxMenuButton button21 = new WxMenuButton();
			button21.setType(WxConsts.BUTTON_VIEW);
			button21.setName("任务公告");
			button21.setUrl(menuService.oauth2buildAuthorizationUrl("http://" + domin + "/platform/weChat/taskList", corpId));

			WxMenuButton button22 = new WxMenuButton();
			button22.setType(WxConsts.BUTTON_VIEW);
			button22.setName("发布任务");
			button22.setUrl(menuService.oauth2buildAuthorizationUrl("http://" + domin + "/platform/weChat/publishTask", corpId));

			WxMenuButton button23 = new WxMenuButton();
			button23.setType(WxConsts.BUTTON_VIEW);
			button23.setName("待处理任务");
			button23.setUrl(menuService.oauth2buildAuthorizationUrl("http://" + domin + "/platform/weChat/msgList", corpId));

			button2.getSubButtons().add(button21);
			button2.getSubButtons().add(button22);
			button2.getSubButtons().add(button23);

			WxMenuButton button3 = new WxMenuButton();
			button3.setType(WxConsts.BUTTON_VIEW);
			button3.setName("个人中心");
			button3.setUrl(menuService.oauth2buildAuthorizationUrl("http://" + domin + "/platform/weChat/personalCenter", corpId));
			buttons.add(button3);

			WxMenu menu = new WxMenu();
			menu.setButtons(buttons);
			menuService.menuCreate(menu);
		} catch (WxErrorException e) {
			System.out.println("error:" + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static String getCorpId(String echostr,String encodingAesKey) {
		byte[] aesKey=Base64.decodeBase64(encodingAesKey + "=");
		byte[] original;
		try {
			// 设置解密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

			// 使用BASE64对密文进行解码
			byte[] encrypted = Base64.decodeBase64(echostr);

			// 解密
			original = cipher.doFinal(encrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		String xmlContent, from_appid;
		try {
			// 去除补位字符
			byte[] bytes = PKCS7Encoder.decode(original);

			// 分离16位随机字符串,网络字节序和AppId
			byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

			int xmlLength = bytesNetworkOrder2Number(networkOrder);

			xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
			from_appid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length), CHARSET);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return xmlContent;

	}
	
	  /**
	   * 4个字节的网络字节序bytes数组还原成一个数字
	   *
	   * @param bytesInNetworkOrder
	   */
	  private static  int bytesNetworkOrder2Number(byte[] bytesInNetworkOrder) {
	    int sourceNumber = 0;
	    for (int i = 0; i < 4; i++) {
	      sourceNumber <<= 8;
	      sourceNumber |= bytesInNetworkOrder[i] & 0xff;
	    }
	    return sourceNumber;
	  }
	
	  private static final Charset CHARSET = Charset.forName("utf-8");

}
