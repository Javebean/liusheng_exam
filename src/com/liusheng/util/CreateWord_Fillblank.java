package com.liusheng.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class CreateWord_Fillblank {
	public static void cfillblank(XWPFDocument doc, List<String> info){
		XWPFParagraph para = doc.createParagraph();
		XWPFRun runtitle = para.createRun();
		String text = "二、填空题(每空2，共20分)";
		WordUtil.setTextAndStyle(runtitle, "SimHei",Constant.XIAOSI_FONTSIZE, null, text, null, true);
		runtitle.addBreak();
		
		int len = info.size();
		int num = 1;
		for(int i=1;i<=len;i++){
			XWPFRun run = para.createRun();
			String pro = info.get(i-1);
			
			Pattern reg = Pattern.compile("(<)(\\W+?)(>)");
			Matcher matcher = reg.matcher(pro);
			StringBuffer sb = new StringBuffer();
			while(matcher.find()){
				matcher.appendReplacement(sb, "__["+num+++"]____");
			}
			matcher.appendTail(sb);
			String text1 = i+"、"+sb;
			
			WordUtil.setTextAndStyle(run, "SimSun",Constant.WUHAO_FONTSIZE, null, text1, null, true);
			run.addCarriageReturn();
		}
	}
}
