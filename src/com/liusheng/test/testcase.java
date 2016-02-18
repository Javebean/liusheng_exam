package com.liusheng.test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class testcase{
	
	public static void main(String[] args) {
		String str = "的首<付款>分店的看法<是老款的>";
		
		/*String[] split = str.split("(<)(\\W+?)(>)");
		System.out.println(Arrays.toString(split));*/
		
		Pattern reg = Pattern.compile("(<)(\\W+?)(>)");
		Matcher matcher = reg.matcher(str);
		
		while(matcher.find()){
			System.out.println(matcher.group(3));
		}
	}
	
	
}