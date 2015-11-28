package com.liusheng.util;

import java.util.List;

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
			String text1 = i+"、"+info.get(i-1);
			String substring = "#";
			int index = text1.indexOf(substring);
			StringBuilder sb = new StringBuilder(text1);
			while(index != -1) {
			    sb = sb.replace(index, index+1, "___["+num+++"]_______");
			    index = sb.indexOf(substring, index + 1);
			}
			
			
			WordUtil.setTextAndStyle(run, "SimSun",Constant.WUHAO_FONTSIZE, null, sb.toString(), null, true);
			run.addCarriageReturn();
			
			if(num==11)
				break;
		}
	}
}
