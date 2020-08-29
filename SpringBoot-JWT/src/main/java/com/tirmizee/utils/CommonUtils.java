package com.tirmizee.utils;

import org.apache.commons.codec.binary.Base64;

public class CommonUtils {
	
	public static String decodeBase64ToString(String base64) {
		return new String(Base64.decodeBase64(base64));
	}

}
