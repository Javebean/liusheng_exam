package com.liusheng.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.liusheng.entities.Interlocution;

public class CreateWord_Inter {
	

	public static void cinter(XWPFDocument doc,List<Interlocution> createInter,ServletContext context)
			throws InvalidFormatException, IOException {
		
		XWPFParagraph para = doc.createParagraph();
		XWPFRun runtitle = para.createRun();
		String text = "三、问答题(每小题10分，共60分)";
		WordUtil.setTextAndStyle(runtitle, "SimHei", Constant.XIAOSI_FONTSIZE,
				null, text, null, true);
		runtitle.addBreak();
		int index = 1;
		if(createInter!=null){
			for (Interlocution m : createInter) {
				XWPFParagraph p_para = doc.createParagraph();
				XWPFRun run = p_para.createRun();
				String text1 = index++ + "、" + m.getProblem();
			 	WordUtil.setTextAndStyle(run, "SimSun", Constant.WUHAO_FONTSIZE,
						null, text1, null, true);
				run.addCarriageReturn();
				if(Tools.notEmpty(m.getImgUrl())){
					XWPFParagraph pic_para = doc.createParagraph();
					pic_para.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun picrun = pic_para.createRun();
					//暂时注释掉
					
					//String path = context.getRealPath("")+"\\"+m.getImgUrl();
					String path = "D:/liu_exam/temp_pic/"+m.getImgUrl();
					
				    picrun.addPicture(new FileInputStream(path),
						WordUtil.getSuffix(path), "xxx", Units.toEMU(300),
						Units.toEMU(298));
				}
			}
			
		}
	}

}
