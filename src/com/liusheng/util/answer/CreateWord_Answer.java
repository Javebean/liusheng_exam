package com.liusheng.util.answer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.liusheng.entities.Interlocution;
import com.liusheng.util.Constant;
import com.liusheng.util.WordUtil;

public class CreateWord_Answer {

	private static  Logger log = Logger.getLogger(CreateWord_Answer.class);
	
	public static void canswerpage(XWPFDocument doc,String [] simAnswer,String []fillAnswer,List<Interlocution> interInfo,ServletContext context){
		XWPFParagraph answerTitle = doc.createParagraph();
		XWPFRun answerTitleRun = answerTitle.createRun();
		WordUtil.setTextAndStyle(answerTitleRun, "SimHei", Constant.ERHAO_FONTSIZE, null, "参考答案及评分标准（B卷）", null, true);
		
		
		  /*单项选择题答案*/
	     XWPFParagraph simtitle = doc.createParagraph();
	     XWPFRun simrun = simtitle.createRun();
	     String sim_title = "一、单项选择题(每小题 2分，共20 分)";
	     WordUtil.setTextAndStyle(simrun, "SimHei", Constant.XIAOSI_FONTSIZE, null, sim_title, null, true);
	     
	     simrun.setTextPosition(8);
	     
	     /*单项选择题答案表格*/
	        XWPFTable simtable = doc.createTable(2,11);
		     //column width in Twentieths of a Point
		     for(int i = 0; i < simtable.getNumberOfRows(); i++){
		         XWPFTableRow row = simtable.getRow(i);
		         row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(480));
		         int numCells = row.getTableCells().size();
		         for(int j = 0; j < numCells; j++){
		             XWPFTableCell cell = row.getCell(j);
		             cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(780));
		             cell.setVerticalAlignment(XWPFVertAlign.CENTER);
		             // table cells have a list of paragraphs; there is an initial
		             // paragraph created when the cell is created. If you create a
		             // paragraph in the document to put in the cell, it will also
		             // appear in the document following the table, which is probably
		             // not the desired result.
		            XWPFParagraph paragraph = cell.getParagraphs().get(0);
		             paragraph.setAlignment(ParagraphAlignment.CENTER);
		             XWPFRun run = paragraph.createRun();
		             if(i==0){
		            	 if(j==0){
		            		 WordUtil.setTextAndStyle(run, "SimHei", Constant.XIAOSI_FONTSIZE, null, "题号", null, false);
		            	 }else{
		            		 WordUtil.setTextAndStyle(run, "Calibri", Constant.WUHAO_FONTSIZE, null, j+"", null, false);
		            	 }
		            	 
		             }else if(i==1 && j==0){
		            	 WordUtil.setTextAndStyle(run, "SimHei", Constant.XIAOSI_FONTSIZE, null, "答案", null, false);
		             }else{
		            	 WordUtil.setTextAndStyle(run, "SimSun", Constant.WUHAO_FONTSIZE, null, simAnswer[j-1], null, false);
		             }
		            	 
		         }
		     }
		     
		     /***************************************************/
		     	XWPFParagraph fill = doc.createParagraph();
		     	fill.createRun();
		     	//fillRun.addCarriageReturn();
		     
		     /***************************************************/
		     
		     
		     
		     /*填空题答案*/
		     XWPFParagraph filltitle = doc.createParagraph();
		     XWPFRun fillrun = filltitle.createRun();
		     String fill_title = "二、填空题(每空2分，共20分)";
		     WordUtil.setTextAndStyle(fillrun, "SimHei", Constant.XIAOSI_FONTSIZE, null, fill_title, null, true);
		     
		     
		     /*填空答案表格*/
		        XWPFTable filltable = doc.createTable(fillAnswer.length+1,2);
			     //column width in Twentieths of a Point
			     for(int i = 0; i < filltable.getNumberOfRows(); i++){
			         XWPFTableRow row = filltable.getRow(i);
			         row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(480));
			         int numCells = row.getTableCells().size();
			         for(int j = 0; j < numCells; j++){
			             XWPFTableCell cell = row.getCell(j);
			            // cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(780));
			             cell.setVerticalAlignment(XWPFVertAlign.CENTER);
			             XWPFParagraph paragraph = cell.getParagraphs().get(0);
			             paragraph.setAlignment(ParagraphAlignment.CENTER);
			             XWPFRun run = paragraph.createRun();
			             if(i==0){
			            	 if(j==0){
			            		 WordUtil.setTextAndStyle(run, "SimHei", Constant.XIAOSI_FONTSIZE, null, "序号", null, false);
			            	 }else{
			            		 WordUtil.setTextAndStyle(run, "SimHei", Constant.XIAOSI_FONTSIZE, null, "答案", null, false);
			            	 }
			            	 
			             }else if(j==0){
			            	 WordUtil.setTextAndStyle(run, "SimHei", Constant.XIAOSI_FONTSIZE, null, "【"+i+"】", null, false);
			             }else{
			            	 WordUtil.setTextAndStyle(run, "SimHei", Constant.XIAOSI_FONTSIZE, null, fillAnswer[i-1], null, false);
			             }
			             
			             if(j==0){
			            	 cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1020));
			             }else{
			            	 cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7455));
			             }
			            	 
			         }
			     }
			     
			     /*问答题答案*/
			     XWPFParagraph intertitle = doc.createParagraph();
			     XWPFRun interrun = intertitle.createRun();
			     String inter_title = "三、问答题(每小题10分，共60分)";
			     WordUtil.setTextAndStyle(interrun, "SimHei", Constant.XIAOSI_FONTSIZE, null, inter_title, null, true);
			     interrun.addCarriageReturn();
			     
			    log.info("答题纸上的问答题数量："+interInfo.size());
			    int proNum = 1;
			    for(Interlocution m : interInfo){
			    	XWPFParagraph problem = doc.createParagraph();
			    	XWPFRun key = problem.createRun();
			    	WordUtil.setTextAndStyle(key, "SimSun", Constant.WUHAO_FONTSIZE, null,proNum+++"、 "+ m.getProblem(), null, true);
			    	key.addCarriageReturn();
			    	//答题纸问答题如果有图片的话，
			    	if(null!=m.getImgUrl()){
						XWPFParagraph pic_para = doc.createParagraph();
						pic_para.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun picrun = pic_para.createRun();
						
						String path = Constant.UPLOAD_PIC_URL+"/"+m.getImgUrl();
						
						try {
							
							picrun.addPicture(new FileInputStream(path),
									WordUtil.getSuffix(path), "xxx", Units.toEMU(300),
									Units.toEMU(298));
							} catch (InvalidFormatException e) {
								e.printStackTrace();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
			    	
			    	XWPFParagraph answer = doc.createParagraph();
			    	XWPFRun value	 = answer.createRun();
			    	WordUtil.setTextAndStyle(value, "SimSun", Constant.WUHAO_FONTSIZE, null, "答："+m.getAnswer(), null, false);
			    	value.addCarriageReturn();
			    	
			    }
	
	}
}
