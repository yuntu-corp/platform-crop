package com.unitever.platform.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;

/**
 * 拼音和汉字工具类
 * 
 * @author Administrator
 * 
 */
public class PinyinUtil {
	// 汉字
	private String hanZi;
	// 汉字的拼音，小写
	private String pinYin;
	// 拼音的首字母组合，小写
	private String jianXie;

	public PinyinUtil(String hanZi) {
		this.hanZi = hanZi;
	}

	/**
	 * 初始化，只调用一次，用pinYin是否是null来决定
	 */
	private void init() {
		if (pinYin != null) {
			return;
		}
		this.pinYin = "";
		this.jianXie = "";
		CharSequence s = this.hanZi;
		char[] hanzhi = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			hanzhi[i] = s.charAt(i);
		}
		char[] t1 = hanzhi;
		// 设置输出格式
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				if (java.lang.Character.toString(t1[i]).equals("　") || java.lang.Character.toString(t1[i]).equals(" ")) {
				} else if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					String[] t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					this.pinYin = this.pinYin + t2[0].toString();
					this.jianXie = this.jianXie + t2[0].toString().substring(0, 1);
				} else {
					this.pinYin += java.lang.Character.toString(t1[i]).toLowerCase();
					this.jianXie += java.lang.Character.toString(t1[i]).toLowerCase();
				}
				this.pinYin += " ";
			}
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			throw ExceptionUtil.convertExceptionToUnchecked(e1);
		}
	}

	/**
	 * 设置汉字
	 * 
	 * @param hanZi
	 */
	public void setHanZi(String hanZi) {
		this.hanZi = hanZi;
		this.pinYin = null;
		this.jianXie = null;
	}

	/**
	 * 得到拼音
	 * 
	 * @return
	 */
	public String getPinYin() {
		init();
		return pinYin;
	}

	/**
	 * 得到简写
	 * 
	 * @return
	 */
	public String getJianXie() {
		init();
		return jianXie;
	}

	/**
	 * 得到首字母
	 * 
	 * @return
	 */
	public String getFirstChar() {
		if (StringUtils.isNotEmpty(this.getJianXie())) {
			return this.getJianXie().substring(0, 1);
		} else {
			return "";
		}
	}
}
