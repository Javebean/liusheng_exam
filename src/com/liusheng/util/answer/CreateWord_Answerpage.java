package com.liusheng.util.answer;

import java.math.BigInteger;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import sun.misc.UUDecoder;

import com.liusheng.util.Constant;
import com.liusheng.util.WordUtil;

public class CreateWord_Answerpage {

	public static void canswerpage(XWPFDocument doc){
		 	XWPFParagraph title = doc.createParagraph();
	        //设置文本的对齐方式
	        title.setAlignment(ParagraphAlignment.CENTER);
	       
	        //为段落添加文本
	        XWPFRun r1 = title.createRun();
	        WordUtil.setTextAndStyle(r1, "SimHei", Constant.XIAOER_FONTSIZE, null, "南京信息工程大学滨江学院", null,false);
	        
	        XWPFParagraph yearAndExam_info = doc.createParagraph();
	        yearAndExam_info.setAlignment(ParagraphAlignment.CENTER);
	        /*设置开始年份*/
	        XWPFRun yearStart = yearAndExam_info.createRun();
	        //设置行距
	        //r1.setTextPosition(300);
	        //换行
	        WordUtil.setTextAndStyle(yearStart, "SimSun", Constant.XIAOSAN_FONTSIZE, Constant.FONTCOLOR, " 2014  ", UnderlinePatterns.SINGLE,true);
	      
	        /*设置横杠*/
	        XWPFRun dash = yearAndExam_info.createRun();
	        WordUtil.setTextAndStyle(dash, "SimSun", Constant.XIAOSAN_FONTSIZE, null, "─", null,true);
	        
	        /*设置结束年份*/
	        XWPFRun yearEnd = yearAndExam_info.createRun();
	        WordUtil.setTextAndStyle(yearEnd, "SimSun", Constant.XIAOSAN_FONTSIZE, Constant.FONTCOLOR, " 2015  ", UnderlinePatterns.SINGLE,true);

	        /*继续写 “学年”*/
	        XWPFRun studyYear = yearAndExam_info.createRun();
	        WordUtil.setTextAndStyle(studyYear, "SimSun", Constant.XIAOSAN_FONTSIZE, null, "学年 ", null,true);
	        
	        /*设置第几学期*/
	        XWPFRun term_1 = yearAndExam_info.createRun();
	        WordUtil.setTextAndStyle(term_1, "SimSun", Constant.XIAOSAN_FONTSIZE, null, "第", null,true);
	        
	        XWPFRun term_2 = yearAndExam_info.createRun();
	        WordUtil.setTextAndStyle(term_2, "SimSun", Constant.XIAOSAN_FONTSIZE, Constant.FONTCOLOR, " 一 ", UnderlinePatterns.SINGLE,true);
	        
	        XWPFRun term_3 = yearAndExam_info.createRun();
	        WordUtil.setTextAndStyle(term_3, "SimSun", Constant.XIAOSAN_FONTSIZE, null, "学期", null,true);
	        term_3.addCarriageReturn();
	        
	        /*设置试卷题目名称*/
	        XWPFRun examName = yearAndExam_info.createRun();
	        WordUtil.setTextAndStyle(examName, "SimHei", Constant.XIAOSAN_FONTSIZE, Constant.FONTCOLOR, "            计 算 机 网 络              ", UnderlinePatterns.SINGLE,false);
	       
	        XWPFRun examName_1 = yearAndExam_info.createRun();
	        WordUtil.setTextAndStyle(examName_1, "SimSun", Constant.XIAOSAN_FONTSIZE, null, "课程试卷", null,false);
	        examName_1.addCarriageReturn();
	        
	        /*答题纸*/
	        XWPFRun datizhi = yearAndExam_info.createRun();
	        WordUtil.setTextAndStyle(datizhi, "SimHei", Constant.XIAOER_FONTSIZE, null, "答题纸", null,false);
	        
	        /*学生信息*/
	        
	        XWPFParagraph stuInfoPara = doc.createParagraph();
	        stuInfoPara.setAlignment(ParagraphAlignment.LEFT);
	        XWPFRun stuInfo_1 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_1, "SimSun", Constant.SIHAO_FONTSIZE, null, "姓名", null, false);
	        
	        XWPFRun stuInfo_2 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_2, "Calibri", Constant.SIHAO_FONTSIZE, null, "                    ", UnderlinePatterns.SINGLE, false);
	        
	        XWPFRun stuInfo_3 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_3, "SimSun", Constant.SIHAO_FONTSIZE, null, "专业", null, false);
	       
	        XWPFRun stuInfo_4 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_4, "Calibri", Constant.SIHAO_FONTSIZE, null, "             ", UnderlinePatterns.SINGLE, false);
	        
	        XWPFRun stuInfo_5 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_5, "SimSun", Constant.SIHAO_FONTSIZE, null, "班级", null, false);
	        
	        XWPFRun stuInfo_6 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_6, "Calibri", Constant.SIHAO_FONTSIZE, null, "          ", UnderlinePatterns.SINGLE, false);
	        stuInfo_6.addCarriageReturn();
	        
	        XWPFRun stuInfo_7 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_7, "SimSun", Constant.SIHAO_FONTSIZE, null, "学号", null, false);
	        
	        XWPFRun stuInfo_8 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_8, "Calibri", Constant.SIHAO_FONTSIZE, null, "                    ", UnderlinePatterns.SINGLE, false);
	        
	        XWPFRun stuInfo_9 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_9, "SimSun", Constant.SIHAO_FONTSIZE, null, "姓名", null, false);
	       
	        XWPFRun stuInfo_10 = stuInfoPara.createRun();
	        WordUtil.setTextAndStyle(stuInfo_10, "Calibri", Constant.SIHAO_FONTSIZE, null, "             ", UnderlinePatterns.SINGLE, false);
	        
	        /*成绩表格*/
	        XWPFTable table = doc.createTable(3,12);
	        /*表格居中★★*/
	       // table.getCTTbl().getTblPr().addNewJc().setVal(STJc.CENTER);
		     //column width in Twentieths of a Point
	    	table.setRowBandSize(0);
	    	String textArr [] = {"题号","一","二","三","四","五","六","七","八","九","十","总分"};
		     for(int i = 0; i < table.getNumberOfRows(); i++){
		         XWPFTableRow row = table.getRow(i);
		         
		         int numCells = row.getTableCells().size();
		         for(int j = 0; j < numCells; j++){
		             XWPFTableCell cell = row.getCell(j);
		             cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(645));
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
		            	 row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(330));
		            	 if(j==0){
		            		 WordUtil.setTextAndStyle(run, "SimSun", Constant.XIAOSI_FONTSIZE, null, "题  号", null, false);
		            	 }else{
		            		 WordUtil.setTextAndStyle(run, "SimSun", Constant.XIAOSI_FONTSIZE, null, textArr[j], null, false);
		            	 }
		            	 
		             }else if(i==1 && j==0){
		            	 row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(630));
		            	 //XWPFRun run = cell.addParagraph().createRun();
		            	 WordUtil.setTextAndStyle(run, "SimSun", Constant.XIAOSI_FONTSIZE, null, "得  分", null, false);
		             }else if(i==2 && j==0){
		            	 row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(630));
		            	 //XWPFRun run = cell.addParagraph().createRun();
		            	 WordUtil.setTextAndStyle(run, "SimSun", Constant.XIAOSI_FONTSIZE, null, "阅卷人", null, false);
		             }
		             if(j==0)
		            	cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(945));
		             else  if(j<11){
		            	 cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(570));
		             }else if(j==numCells-1)
		            	 cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1155));
		            	 
		         }
		     }
		     
		     /*****************************/
		     //下划线
		     XWPFParagraph underPara = doc.createParagraph();
		     XWPFRun underRun = underPara.createRun();
		     String un ="                                                                 ";
		     WordUtil.setTextAndStyle(underRun, "SimHei", Constant.XIAOER_FONTSIZE, null, un, UnderlinePatterns.DASH_DOT_HEAVY, false);
		     //underRun.setTextPosition(-40);
		     underRun.addBreak();
		     /*****************************/
		     
		     
	        /*单项选择题答案*/
		     XWPFParagraph simtitle = doc.createParagraph();
		     XWPFRun simrun = simtitle.createRun();
		     String sim_title = "一、单项选择题(每小题 2分，共20 分)";
		     WordUtil.setTextAndStyle(simrun, "SimHei", Constant.XIAOSI_FONTSIZE, null, sim_title, null, false);
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
			             }
			            	 
			         }
			     }
		
			     /*填空题答案*/
			     XWPFParagraph filltitle = doc.createParagraph();
			     XWPFRun fillrun = filltitle.createRun();
			     String fill_title = "二、填空题(每空2分，共20分)";
			     WordUtil.setTextAndStyle(fillrun, "SimHei", Constant.XIAOSI_FONTSIZE, null, fill_title, null, false);
			     
			     
			     /*单项选择题答案表格*/
			        XWPFTable filltable = doc.createTable(11,2);
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
				             }
				             
				             if(j==0){
				            	 cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1020));
				             }else{
				            	 cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7455));
				             }
				            	 
				         }
				     }
				     
				     /*填空题答案*/
				     XWPFParagraph intertitle = doc.createParagraph();
				     XWPFRun interrun = intertitle.createRun();
				     String inter_title = "三、问答题(每小题10分，共60分)";
				     WordUtil.setTextAndStyle(interrun, "SimHei", Constant.XIAOSI_FONTSIZE, null, inter_title, null, false);
				     interrun.addBreak(BreakType.PAGE);
			     
		     
	}
}
