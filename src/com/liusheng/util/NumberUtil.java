package com.liusheng.util;

public class NumberUtil {
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
}
