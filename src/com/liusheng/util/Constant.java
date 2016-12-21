package com.liusheng.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constant {
	public static final int CHECK_SUCCESS = 1;//审核通过
	
	public static final int NO_CHECK = 0;//审核不通过
	
	public static final int ERHAO_FONTSIZE =44;
	
	public static final int XIAOER_FONTSIZE = 36;
	
	public static final int XIAOSAN_FONTSIZE = 30;
	
	public static final int SIHAO_FONTSIZE = 28;
	
	public static final int XIAOSI_FONTSIZE = 24;

	public static final int WUHAO_FONTSIZE = 21;
	
	public static final String FONTCOLOR = "FF0000";
	
	/*单选题的数量*/
	public static final int SIMPLE_SELECT_NUMS;
	/*填空题填空的数量*/
	public static final int FILL_BLANK_NUMS;
	/*问答题的数量*/
	public static final int INTERLOCATION_NUMS;
	//excel上传路径
	public static final String UPLOAD_EXCEL_URL;
	
	//上传图片的路径
	public static final String UPLOAD_PIC_URL;
	//试卷存放路径
	public static final String EXAM_SAVE_URL;
	static{
		ClassLoader cl = Thread.currentThread().getContextClassLoader();  
		if (cl == null) cl = Constant.class.getClassLoader(); // fallback  
		InputStream in = cl.getResourceAsStream("config.properties");
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        UPLOAD_PIC_URL = p.getProperty("upload_pic_url");
        UPLOAD_EXCEL_URL = p.getProperty("upload_excel_url");
        EXAM_SAVE_URL = p.getProperty("exam_save_url");
        SIMPLE_SELECT_NUMS = Integer.parseInt(p.getProperty("simple_select_nums"));
        FILL_BLANK_NUMS = Integer.parseInt(p.getProperty("fill_blank_nums"));
        INTERLOCATION_NUMS = Integer.parseInt(p.getProperty("interlocation_nums"));
	}
	
	
	
}
