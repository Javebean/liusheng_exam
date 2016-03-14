package com.liusheng.util;

import java.math.BigInteger;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

public class WordUtil {
	 public static void setTextAndStyle(XWPFRun xr,String fontFamily,int fontSize,String fontColor,String text,UnderlinePatterns u,boolean bold){
	    	if(fontFamily!=null)
	    		xr.setFontFamily(fontFamily);
	    	
	    	//xr.setFontSize(fontSize);
	    	setChineseFontSize(xr, fontSize);
	    	
	    	if(fontColor!=null)
	    		xr.setColor(fontColor);
	    	if(u!=null)
	    		xr.setUnderline(u);
	    	if(text!=null)
	    		xr.setText(text);
	    	
	    	xr.setBold(bold);
	    }
	   public  static void setChineseFontSize(XWPFRun xr,int fontSize){
	    	 CTRPr pRpr = xr.getCTR().getRPr();
	         // 设置字体大小  
	         if(pRpr==null)
	         	pRpr = xr.getCTR().addNewRPr();  
	         
	         CTHpsMeasure sz = pRpr.isSetSz() ? pRpr.getSz() : pRpr.addNewSz();  
	         sz.setVal(new BigInteger(fontSize+""));  
	    	
	    }
	    
	    
	    public static void setSingleLineSpacing(XWPFParagraph para,int lineSpace) {
	    	 CTPPr ppr = para.getCTP().getPPr();
	    	    if (ppr == null) ppr = para.getCTP().addNewPPr();
	    	    CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
	    	    spacing.setAfter(BigInteger.valueOf(0));
	    	    spacing.setBefore(BigInteger.valueOf(0));
	    	    spacing.setLineRule(STLineSpacingRule.AUTO);
	    	    spacing.setLine(BigInteger.valueOf(lineSpace));
	    }
	    
	    
		public static int getSuffix(String imgFile) {
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
