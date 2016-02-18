package com.liusheng.util;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.liusheng.entities.FillBlank;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.util.answer.CreateWord_Answer;
import com.liusheng.util.answer.CreateWord_Answerpage;


public class CreateWord {
/*
 * 1.simpleInfo: key:题目，value:四个选项
 * 2.fillblankInfo : 题目
 * 3.interInfo   key 题目，value 是否有图片
 */
    public static void createExam(List<SimpleSelection> simpleInfo,List<FillBlank> fillblankInfo, Map<String,Boolean> interInfo) throws Exception {
		XWPFDocument doc = new XWPFDocument();
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
        
        XWPFRun fill = yearAndExam_info.createRun();
        WordUtil.setTextAndStyle(fill, "SimHei", 7, null, "", null,false);
        fill.addCarriageReturn();
        
        /*设置试卷类型*/
        XWPFRun exam_type = yearAndExam_info.createRun();
        WordUtil.setTextAndStyle(exam_type, "SimSun", Constant.XIAOSI_FONTSIZE, null, "   试卷类型", null,false);
        
        XWPFRun exam_type_1 = yearAndExam_info.createRun();
        WordUtil.setTextAndStyle(exam_type_1, "SimSun", Constant.XIAOSI_FONTSIZE, null, "  B  ", UnderlinePatterns.SINGLE,false);
        
        XWPFRun exam_type_2 = yearAndExam_info.createRun();
        WordUtil.setTextAndStyle(exam_type_2, "SimSun", Constant.XIAOSI_FONTSIZE, null, "(注明A、B卷)        ", null,false);
        
        /*设置考试类型*/
        XWPFRun test_type = yearAndExam_info.createRun();
        WordUtil.setTextAndStyle(test_type, "SimSun", Constant.XIAOSI_FONTSIZE, null, "考试类型", null,false);
        
        XWPFRun test_type_1 = yearAndExam_info.createRun();
        WordUtil.setTextAndStyle(test_type_1, "SimSun", Constant.XIAOSI_FONTSIZE, null, "  闭卷  ", UnderlinePatterns.SINGLE,false);
        
        XWPFRun test_type_2 = yearAndExam_info.createRun();
        WordUtil.setTextAndStyle(test_type_2, "SimSun", Constant.XIAOSI_FONTSIZE, null, "（注明开、闭卷）", null,false);
        test_type_2.addCarriageReturn();
        
        
        
        /*设置注意事项*/
        XWPFParagraph note_para = doc.createParagraph();
        WordUtil.setSingleLineSpacing(note_para, 280);
        
        
        XWPFRun note = note_para.createRun();
        WordUtil.setTextAndStyle(note, "SimSun", Constant.WUHAO_FONTSIZE, null, "注意：1、本课程为", null,false);
        
        XWPFRun note_1 = note_para.createRun();
        WordUtil.setTextAndStyle(note_1, "SimSun", Constant.WUHAO_FONTSIZE, Constant.FONTCOLOR, " 必修  ", UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_2 = note_para.createRun();
        WordUtil.setTextAndStyle(note_2, "SimSun", Constant.WUHAO_FONTSIZE, null, "（注明必修或选修）， 学时为", null,false);
        
        XWPFRun note_3 = note_para.createRun();
        WordUtil.setTextAndStyle(note_3, "SimSun", Constant.WUHAO_FONTSIZE, Constant.FONTCOLOR, "  51  ", UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_4 = note_para.createRun();
        WordUtil.setTextAndStyle(note_4, "SimSun", Constant.WUHAO_FONTSIZE, null, "，学分为",null,false);
        
        XWPFRun note_5 = note_para.createRun();
        WordUtil.setTextAndStyle(note_5, "SimSun", Constant.WUHAO_FONTSIZE, Constant.FONTCOLOR, "  3  ",UnderlinePatterns.SINGLE,false);
        note_5.addCarriageReturn();
        
        XWPFRun note_6 = note_para.createRun();
        WordUtil.setTextAndStyle(note_6, "SimSun", Constant.WUHAO_FONTSIZE, null, "      2、本试卷共",null,false);
        
        XWPFRun note_7 = note_para.createRun();
        WordUtil.setTextAndStyle(note_7, "SimSun", Constant.WUHAO_FONTSIZE, Constant.FONTCOLOR, "  5  ",UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_8 = note_para.createRun();
        WordUtil.setTextAndStyle(note_8, "SimSun", Constant.WUHAO_FONTSIZE, null, "页；考试时间",null,false);
        
        XWPFRun note_9 = note_para.createRun();
        WordUtil.setTextAndStyle(note_9, "SimSun", Constant.WUHAO_FONTSIZE, Constant.FONTCOLOR, "  120  ",UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_10 = note_para.createRun();
        WordUtil.setTextAndStyle(note_10, "SimSun", Constant.WUHAO_FONTSIZE, null, "分钟；出卷时间：",null,false);
        
        XWPFRun note_11 = note_para.createRun();
        WordUtil.setTextAndStyle(note_11, "SimSun", Constant.WUHAO_FONTSIZE, Constant.FONTCOLOR, "  2015  ",UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_12 = note_para.createRun();
        WordUtil.setTextAndStyle(note_12, "SimSun", Constant.WUHAO_FONTSIZE, null, "年",null,false);
        
        XWPFRun note_13= note_para.createRun();
        WordUtil.setTextAndStyle(note_13, "SimSun", Constant.WUHAO_FONTSIZE, Constant.FONTCOLOR, " 1 ",UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_14= note_para.createRun();
        WordUtil.setTextAndStyle(note_14, "SimSun", Constant.WUHAO_FONTSIZE, null, " 月  ",null,false);
        note_14.addCarriageReturn();
        
        XWPFRun note_15= note_para.createRun();
        WordUtil.setTextAndStyle(note_15, "SimSun", Constant.WUHAO_FONTSIZE, null, "      3、姓名、学号等必须写在指定地方；      考试时间：",null,false);
        
        XWPFRun note_16= note_para.createRun();
        WordUtil.setTextAndStyle(note_16, "SimSun", Constant.WUHAO_FONTSIZE, Constant.FONTCOLOR, " 2015 ",UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_17= note_para.createRun();
        WordUtil.setTextAndStyle(note_17, "SimSun", Constant.WUHAO_FONTSIZE, null, "年",null,false);
        
        XWPFRun note_18= note_para.createRun();
        WordUtil.setTextAndStyle(note_18, "SimSun", Constant.WUHAO_FONTSIZE, null, " 11 ",UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_19= note_para.createRun();
        WordUtil.setTextAndStyle(note_19, "SimSun", Constant.WUHAO_FONTSIZE, null, "月",null,false);
        
        XWPFRun note_20= note_para.createRun();
        WordUtil.setTextAndStyle(note_20, "SimSun", Constant.WUHAO_FONTSIZE, null, " 13 ",UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_21= note_para.createRun();
        WordUtil.setTextAndStyle(note_21, "SimSun", Constant.WUHAO_FONTSIZE, null, "日",null,false);
        note_21.addCarriageReturn();
        
        
        XWPFRun note_22= note_para.createRun();
        WordUtil.setTextAndStyle(note_22, "SimSun", Constant.WUHAO_FONTSIZE, null, "      4、本考卷适用专业年级：",null,false);
        
        XWPFRun note_23= note_para.createRun();
        WordUtil.setTextAndStyle(note_23, "SimSun", Constant.WUHAO_FONTSIZE, null, " 2012级计科、软工、网工、动漫、信管",UnderlinePatterns.SINGLE,false);
        
        XWPFRun note_24= note_para.createRun();
        WordUtil.setTextAndStyle(note_24, "SimSun", Constant.WUHAO_FONTSIZE, null, " 任课教师：",null,false);
        
        XWPFRun note_25= note_para.createRun();
        WordUtil.setTextAndStyle(note_25, "SimSun", Constant.WUHAO_FONTSIZE, null, " 刘  生",UnderlinePatterns.SINGLE,false);
        
        
    	XWPFTable table = doc.createTable(3,14);
	     //column width in Twentieths of a Point
    	table.setRowBandSize(0);
    	String textArr [] = {"题号","一","二","三","四","五","六","七","八","九","十","十一","十二","总分"};
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
	            		 WordUtil.setTextAndStyle(run, "SimSun", Constant.XIAOSI_FONTSIZE, null, "题  号", null, true);
	            	 }else{
	            		 WordUtil.setTextAndStyle(run, "SimSun", Constant.WUHAO_FONTSIZE, null, textArr[j], null, true);
	            	 }
	            	 
	             }else if(i==1 && j==0){
	            	 row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(630));
	            	 //XWPFRun run = cell.addParagraph().createRun();
	            	 WordUtil.setTextAndStyle(run, "SimSun", Constant.XIAOSI_FONTSIZE, null, "得  分", null, true);
	             }else if(i==2 && j==0){
	            	 row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(630));
	            	 //XWPFRun run = cell.addParagraph().createRun();
	            	 WordUtil.setTextAndStyle(run, "SimSun", Constant.XIAOSI_FONTSIZE, null, "阅卷人", null, true);
	             }
	             if(j==0)
	            	cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(945));
	             else  if(j<11){
	            	 cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(570));
	             } else if(j<13){
	            	 cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(645));
	             }else if(j==numCells-1)
	            	 cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1155));
	            	 
	         }
	     }
        
        
        
        //以上内容为教师填写
        XWPFParagraph p2 = doc.createParagraph();
        p2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2 = p2.createRun();
        WordUtil.setTextAndStyle(r2, "SimSun", Constant.WUHAO_FONTSIZE, null, "（以上内容为教师填写）", null, true);
        
        
      /*  双线的曲线救国的方法
        
        XWPFTable doubleLine = doc.createTable(1,1);
        doubleLine.setCellMargins(-300, 500, 0, 0);
        CTTblBorders borders = doubleLine.getCTTbl().getTblPr().addNewTblBorders();//9735
        doubleLine.getRows().get(0).getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(9735));
        CTBorder rBorder=borders.addNewRight();  
        rBorder.setVal(STBorder.Enum.forString("none"));  
          
        CTBorder lBorder=borders.addNewLeft();  
        lBorder.setVal(STBorder.Enum.forString("none"));  
        
        CTBorder tBorder=borders.addNewTop();  
        tBorder.setVal(STBorder.Enum.forString("none"));  
          
        CTBorder bBorder=borders.addNewBottom();  
        bBorder.setVal( STBorder.Enum.forInt(STBorder.INT_DOUBLE));  
        bBorder.setSz(new BigInteger("3"));  */
        
        /*****************************/
	     //下划线
	     XWPFParagraph underPara = doc.createParagraph();
	     XWPFRun underRun = underPara.createRun();
	     String un ="                                                                                                                                ";
	     WordUtil.setTextAndStyle(underRun, "SimHei", Constant.XIAOER_FONTSIZE, null, un, UnderlinePatterns.DOUBLE, true);
	     underRun.setTextPosition(20);
	     /*****************************/
        
        
        
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
        
        /*学生信息和下面注意事项之间的空行*/
        XWPFParagraph fillParagraph = doc.createParagraph();
        fillParagraph.setAlignment(ParagraphAlignment.CENTER);
        
        
        XWPFTable noteItem = doc.createTable(1,1);
        noteItem.getCTTbl().getTblPr().addNewTblBorders();
        XWPFTableCell cell = noteItem.getRow(0).getCell(0);
        
        cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8535));
        XWPFParagraph noteItem_para = cell.getParagraphs().get(0);
        XWPFRun r3 = noteItem_para.createRun();
       
        r3.setFontFamily("SimHei");
        WordUtil.setChineseFontSize(r3, Constant.WUHAO_FONTSIZE);
        
        r3.setText("请仔细阅读以下内容：");
        r3.addCarriageReturn();
        r3.setText("1、考生必须遵守考试纪律，详细内容见《南京信息工程大学滨江学院考试纪律规定》。");
        r3.addCarriageReturn();
        r3.setText("2、所有考试材料不得带离考场。");
        r3.addCarriageReturn();
        r3.setText("3、考生进入考场后，须将学生证或身份证放在座位的左上角。");
        r3.addCarriageReturn();
        r3.setText("4、考场内不许抽烟、吃食物、喝饮料。");
        r3.addCarriageReturn();
        r3.setText("5、考生不得将书籍、作业、笔记、草稿纸袋入考场，主考教师允许带入的除外。");
        r3.addCarriageReturn();
        r3.setText("6、考试过程中，不允许考生使用通讯工具。");
        r3.addCarriageReturn();
        r3.setText("7、开考15分钟后不允许考生进入考场，考试进行30分钟后方可离场。");
        r3.addCarriageReturn();
        r3.setText("8、考生之间不得进行任何形式的信息交流。");
        r3.addCarriageReturn();
        r3.setText("9、除非被允许，否则考生交卷后才能离开座位。");
        r3.addCarriageReturn();
        r3.setText("10、考试违纪或作弊的同学将被请出考场，其违纪或作弊行为将上报学院。");
        r3.addCarriageReturn();
        r3.setText("被人郑重承诺：我已阅读上述10项规定，如果考试是违反了上述10项规定，本人将自愿");
        r3.addCarriageReturn();
        r3.setText("接受学校按照有关规定所进行的处理。上面姓名栏所填姓名即表示本人已阅读本框的内容");
        r3.addCarriageReturn();
        r3.setText("并签名。");
       // r3.addCarriageReturn();
        
        XWPFParagraph breakpage = doc.createParagraph();
        XWPFRun page = breakpage.createRun();
        page.addBreak(BreakType.PAGE);
        
        
        
        XWPFParagraph note2 = doc.createParagraph();
        XWPFRun note2Run = note2.createRun();
        WordUtil.setTextAndStyle(note2Run, "SimHei", Constant.ERHAO_FONTSIZE, null, 
        			"注意：所有答案必须写在后面的答题纸上，写在试卷部分的不予评分！", null, true);
        
        //单选
      /*  Map<String ,List<String>> info = new HashMap<String, List<String>>();
        for(int i=0;i<10;i++){
        	List<String> list = new ArrayList<String>();
        	list.add("IP、ICMP、ARP、TCP");
        	list.add("TCP、ICMP、UDP、ARP");
        	list.add("IP 、ICMP、ARP、RARP");
        	list.add("UDP、 IP、 ICMP、 RARP");
        	
        	info.put("、TCP/IP 的网络层含有四"+i+"个重要的协议，分别为________。", list);
        }*/
        
        
        
      //标题，list为四个选项	
  		Map<String ,List<String>> simMap = null;
  		int pnum =1;
  		if(simpleInfo.size()>0){
  			simMap = new LinkedHashMap<String, List<String>>();
  			List<String> list = null;
  			for(SimpleSelection s : simpleInfo){
  				list = new ArrayList<String>();
  				list.add(s.getOptionA());
  				list.add(s.getOptionB());
  				list.add(s.getOptionC());
  				list.add(s.getOptionD());
  				simMap.put(pnum+++"."+s.getProblem(), list);
  			}
  			
  		}
        CreateWord_Simple.csimple(doc, simMap);
        
        //填空
      /*  List<String> info2 = new ArrayList<String>();
        for(int i=0;i<8;i++){
        	info2.add("IP数据报由#和#两部分组成。");
        }*/
        
		if(fillblankInfo.size()>0){
			List<String> fillblankInfolist = new ArrayList<String>();
			for(FillBlank f:fillblankInfo){
				fillblankInfolist.add(f.getProblem());
			}
			CreateWord_Fillblank.cfillblank(doc, fillblankInfolist);
		}

        
        

        //问答
        CreateWord_Inter.cinter(doc, interInfo);
        
        /*******************************************/
        XWPFParagraph breakpage2 = doc.createParagraph();
        XWPFRun page2 = breakpage2.createRun();
        page2.addBreak(BreakType.PAGE);
        
        XWPFParagraph breakpage3 = doc.createParagraph();
        XWPFRun page3 = breakpage3.createRun();
        page3.addBreak(BreakType.PAGE);
        /*******************************************/
        
        /*答题纸*/
        CreateWord_Answerpage.canswerpage(doc);
        /*参考答案*/
        int simpleAsLen = simpleInfo.size();
        String simAnswer[] = new String[10];
        for(int i=0;i<10;i++){
        	simAnswer[i] = "无";
        }
        for(int i=0;i<simpleAsLen;i++){
        	
        	simAnswer[i] = simpleInfo.get(i).getAnswer().toUpperCase();
        }
        
        //填空题答案
        String [] fillAnswer = new String[10];
        List<String> fillAnswerList = new ArrayList<String>();
        for(int i=0;i<10;i++){
        	fillAnswer[i] = "无";
        }
        int fillLen = fillblankInfo.size();
        for(int i=0;i<fillLen;i++){
        	String s = fillblankInfo.get(i).getAnswer();
        	if(s.contains(",")){
        		
        	}
        }
        
        Map<String,String> interAnswer = new LinkedHashMap<String, String>();
        interAnswer.put("1、什么是计算机网络？", "计算机网络是一些互相连接的自治的计算机的集合，是将不同地理位置上的具有独立功能的多个计算机系统用通信线路相互连接起来，在协议的控制之下，以实现资源共享和数据通信为目的的系统。");
        interAnswer.put("2、简述OSI参考模型。", "OSI七层模型从高到低依次为应用层、表示层、会话层、运输层、网络层、数据链路层、物理层。");
        interAnswer.put("3、	试比较虚电路和数据报两种服务的优缺点。", "OSI七层模型从高到低依次为应用层、表示层、会话层、运输层、网络层、数据链路层、物理层。");
        
        CreateWord_Answer.canswerpage(doc,simAnswer,fillAnswer,interAnswer);

        FileOutputStream out = new FileOutputStream("e://simple.docx");
        doc.write(out);
        out.close();

    }
    
   
    
}