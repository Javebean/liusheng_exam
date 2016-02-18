package com.liusheng.test;

import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class testcase{
	
	public static void main(String[] args) {
		String str = "的首<付款>分店的看法<是老款的>";
		
		
		Pattern reg = Pattern.compile("(<)(\\W+?)(>)");
		Matcher matcher = reg.matcher(str);
		int n = 0;
		/*while(matcher.find()){
			System.out.println(matcher.group(3));
		}*/
		
		
			String content = "xxxxxaxxaaaaxxayyy";
			//String expected = "xxxxx1xx2345xx6yyy"; // 期望的结果
	        StringBuffer sb = new StringBuffer();
	        Pattern p = Pattern.compile("a");
	        Matcher m = p.matcher(content);
	        int i = 1;
	        while (m.find()) {
	            m.appendReplacement(sb, i++ + "");
	        }
	      //  m.appendTail(sb); // 把字符串的尾部也附加上，不然"yyy"将会丢失。
	        System.out.println(sb.toString());
		
		/*String replaceAll = str.replaceAll("(<)(\\W+?)(>)", "#");
		System.out.println(replaceAll);*/
		
		
		
	}
	
	
}