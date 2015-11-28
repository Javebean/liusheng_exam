package com.liusheng.util;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class CreateWord_Simple {
//数据封装在 Map<String ,List<String>>中
	public static void csimple(XWPFDocument doc, Map<String ,List<String>> info){
		XWPFParagraph para = doc.createParagraph();
		XWPFRun run = para.createRun();
		String s_title="一、单项选择题(每小题 2分，共20 分)";
		WordUtil.setTextAndStyle(run, "SimHei", Constant.XIAOSI_FONTSIZE, null, s_title, null, true);
		int proNum = 0;
		for(Map.Entry<String, List<String>> map : info.entrySet()){
			XWPFParagraph p_para = doc.createParagraph();
			XWPFRun p_run = p_para.createRun();
			String text = ++proNum+map.getKey();
			WordUtil.setTextAndStyle(p_run, "SimSun", Constant.WUHAO_FONTSIZE, null, "  "+text, null, true);
			//run.addCarriageReturn();
			
			XWPFTable tableItems = doc.createTable(2,2);
			tableItems.getCTTbl().getTblPr().unsetTblBorders();
			int index = 0;
			String [] itemFlag = {"A","B","C","D"};
			for(int i=0;i<tableItems.getNumberOfRows();i++){
				XWPFTableRow row = tableItems.getRow(i);
				int numCells = row.getTableCells().size();
				 for(int j = 0; j < numCells; j++){
					 XWPFTableCell cell = row.getCell(j);
		             cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(4650));
		             cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		             XWPFParagraph paragraph = cell.getParagraphs().get(0);
		             paragraph.setAlignment(ParagraphAlignment.LEFT);
		             
		             XWPFRun run2 = paragraph.createRun();
		             if(j==0){
		            	 WordUtil.setTextAndStyle(run2, "SimSun", Constant.WUHAO_FONTSIZE, null, "    "+itemFlag[index]+"."+map.getValue().get(index++), null, true);
		             }else{
		            	 WordUtil.setTextAndStyle(run2, "SimSun", Constant.WUHAO_FONTSIZE, null, itemFlag[index]+"."+map.getValue().get(index++), null, true);
		             }
				 }
			}
			
		}
	}
}
