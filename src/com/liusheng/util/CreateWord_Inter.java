package com.liusheng.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.liusheng.entities.Interlocution;


public class CreateWord_Inter {
	public static void cinter(XWPFDocument doc,List<Interlocution> createInter)
			throws InvalidFormatException, IOException {
		Map<String,Boolean> info = null;
		if(createInter.size()>0){
			info = new HashMap<String, Boolean>();
			for(Interlocution i :createInter){
				String pro = i.getProblem();
				String imgurl = i.getImgUrl();
				info.put(pro, imgurl==null?true:false);
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
			for (Map.Entry<String, Boolean> m : info.entrySet()) {
				XWPFParagraph p_para = doc.createParagraph();
				XWPFRun run = p_para.createRun();
				String text1 = index++ + "、" + m.getKey();
				WordUtil.setTextAndStyle(run, "SimSun", Constant.WUHAO_FONTSIZE,
						null, text1, null, true);
				run.addCarriageReturn();
				if(m.getValue()){
					XWPFParagraph pic_para = doc.createParagraph();
					pic_para.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun picrun = pic_para.createRun();
					//暂时注释掉
					/*String path = "D:\\Java_workspace\\examination\\WebContent\\file\\pic.png";
				picrun.addPicture(new FileInputStream(path),
						getSuffix(path), "xxx", Units.toEMU(300),
						Units.toEMU(298));*/
				}
			}
			
		}
	}

	private static int getSuffix(String imgFile) {
		int format = 0;
		if (imgFile.endsWith(".emf"))
			format = XWPFDocument.PICTURE_TYPE_EMF;
		else if (imgFile.endsWith(".wmf"))
			format = XWPFDocument.PICTURE_TYPE_WMF;
		else if (imgFile.endsWith(".pict"))
			format = XWPFDocument.PICTURE_TYPE_PICT;
		else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
			format = XWPFDocument.PICTURE_TYPE_JPEG;
		else if (imgFile.endsWith(".png"))
			format = XWPFDocument.PICTURE_TYPE_PNG;
		else if (imgFile.endsWith(".dib"))
			format = XWPFDocument.PICTURE_TYPE_DIB;
		else if (imgFile.endsWith(".gif"))
			format = XWPFDocument.PICTURE_TYPE_GIF;
		else if (imgFile.endsWith(".tiff"))
			format = XWPFDocument.PICTURE_TYPE_TIFF;
		else if (imgFile.endsWith(".eps"))
			format = XWPFDocument.PICTURE_TYPE_EPS;
		else if (imgFile.endsWith(".bmp"))
			format = XWPFDocument.PICTURE_TYPE_BMP;
		else if (imgFile.endsWith(".wpg"))
			format = XWPFDocument.PICTURE_TYPE_WPG;
		else {
			System.err
					.println("Unsupported picture: "
							+ imgFile
							+ ". Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");

		}
		return format;
	}
}
