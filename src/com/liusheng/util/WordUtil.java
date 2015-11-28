package com.liusheng.util;

import java.math.BigInteger;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
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
}
