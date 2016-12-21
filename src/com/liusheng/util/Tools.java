package com.liusheng.util;

public class Tools {
	public static String createNum() {
		Long ctime = System.currentTimeMillis();
		String str = ctime.toString();
		int len = str.length();
		StringBuilder sb = new StringBuilder(str);
		String substring = sb.substring(len - 8, len);
		StringBuilder sb2 = new StringBuilder("1000");
		sb2.append(substring);
		return sb2.toString();
	}
	
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}
}
