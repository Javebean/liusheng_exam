package com.liusheng.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;

import com.liusheng.entities.Interlocution;

public class CreateWord_Inter {
	

	public static void cinter(XWPFDocument doc,List<Interlocution> createInter,ServletContext context)
			throws InvalidFormatException, IOException {
		
		Map<String,String> info = null;
		if(createInter.size()>0){
			info = new HashMap<String, String>();
			for(Interlocution i :createInter){
				String pro = i.getProblem();
				String imgurl = i.getImgUrl();
				info.put(pro, imgurl);
			}
			
		}
		
		
		XWPFParagraph para = doc.createParagraph();
		XWPFRun runtitle = para.createRun();
		String text = "三、问答题(每小题10分，共60分)";
		WordUtil.setTextAndStyle(runtitle, "SimHei", Constant.XIAOSI_FONTSIZE,
				null, text, null, true);
		runtitle.addBreak();
		int index = 1;
		if(info!=null){
			for (Map.Entry<String, String> m : info.entrySet()) {
				XWPFParagraph p_para = doc.createParagraph();
				XWPFRun run = p_para.createRun();
				String text1 = index++ + "、" + m.getKey();
				WordUtil.setTextAndStyle(run, "SimSun", Constant.WUHAO_FONTSIZE,
						null, text1, null, true);
				run.addCarriageReturn();
				if(null!=m.getValue()){
					XWPFParagraph pic_para = doc.createParagraph();
					pic_para.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun picrun = pic_para.createRun();
					//暂时注释掉
					
					String path = context.getRealPath("")+"\\"+m.getValue();
				picrun.addPicture(new FileInputStream(path),
						WordUtil.getSuffix(path), "xxx", Units.toEMU(300),
						Units.toEMU(298));
				}
			}
			
		}
	}

}
