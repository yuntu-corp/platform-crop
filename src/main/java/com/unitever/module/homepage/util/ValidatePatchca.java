package com.unitever.module.homepage.util;

import java.awt.Color;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;


/**
 * 获取验证码图片以及数字
 * @author wzc
 */
public class ValidatePatchca {
	
	private static void init(ConfigurableCaptchaService cs, final Random random){
		cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("123456789");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
	}
	
	public static void patchca(HttpServletRequest request, HttpServletResponse response){
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		Random random = new Random();
		init(cs,random);
		try{
			switch (random.nextInt(5)) {
	            case 0:
	                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
	                break;
	            case 1:
	                cs.setFilterFactory(new MarbleRippleFilterFactory());
	                break;
	            case 2:
	                cs.setFilterFactory(new DoubleRippleFilterFactory());
	                break;
	            case 3:
	                cs.setFilterFactory(new WobbleRippleFilterFactory());
	                break;
	            case 4:
	                cs.setFilterFactory(new DiffuseRippleFilterFactory());
	                break;
			}
	        HttpSession session = request.getSession(false);
	        if (session == null) {
	            session = request.getSession();
	        }
	        OutputStream os = response.getOutputStream();
	        response.reset();
	   	 	response.setContentType("image/png");
	        response.setHeader("Cache-Control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        long time = System.currentTimeMillis();
	        response.setDateHeader("Last-Modified", time);
	        response.setDateHeader("Date", time);
	        response.setDateHeader("Expires", time);
	        response.flushBuffer();
	        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
	        session.setAttribute("PATCHCA", token);
	        os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
