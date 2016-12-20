package com.liusheng.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.stereotype.Service;

import com.liusheng.dao.FillBlankDao;
import com.liusheng.dao.InterlocutionDao;
import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.Interlocution;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.util.Constant;
import com.liusheng.util.CreateWord;

@Service
public class CreateExamService {

	private Logger log = Logger.getLogger(CreateExamService.class);
	
	@Autowired
	private SimpleSelectDao sdao;
	@Autowired
	private FillBlankDao fdao;
	@Autowired
	private InterlocutionDao idao;
	//单选题不足
	private static final int LESS_SIMPLE = 1;
	//填空题不足
	private static final int LESS_FILL = 2;
	//问答题不足
	private static final int LESS_INTER = 3;
	//题目都是充足的
	private static final int ENOUGH = 0;
	
	//先检查题目数量足不足(审核通过的题目数量)
	private int checkNumsOfQuestion(){
		long simpleSelectionCount = sdao.getSimpleSelectionCount();
		long fillBlankCount = fdao.getFillBlankCount();
		long interlocaionCount = idao.getInterlocaionCount();
		
		if(simpleSelectionCount>Constant.SIMPLE_SELECT_NUMS){
			return LESS_SIMPLE;
		} else if (fillBlankCount>Constant.FILL_BLANK_NUMS){
			return LESS_FILL;
		} else if (interlocaionCount> Constant.INTERLOCATION_NUMS){
			return LESS_INTER;
		} else {
			return ENOUGH;
		}
		
	}
	
	
	
	public String createexamService(String simple,String fill,String inter,ServletContext context){
		log.info("出题各类题目的知识点：" +simple+"--"+fill+"--"+inter);
		JSONObject result = new JSONObject();
		//单选题
		int checkNums = checkNumsOfQuestion();
		
		if(checkNums!=0){
			result.put("code", checkNums);
			return result.toString();
		}
		
		
		
		JSONArray simpleArr = new JSONArray(simple);
			int simpleLen = simpleArr.length();
			//存放传过来的知识点id
			String sarr[] = new String[simpleLen]; 
			for(int i=0;i<simpleLen;i++){
				String str = (String) simpleArr.get(i);
				sarr[i] = str;
			}
			List<SimpleSelection> createSimple = createSimple(sarr);
		
		
		
		
		//填空题
		JSONArray fillArr = new JSONArray(fill);
		int fillLen = fillArr.length();
		String fillKpIdArr[] = new String[fillLen];
		
		for(int i=0;i<fillLen;i++){
			fillKpIdArr[i] = fillArr.get(i).toString();
		}
		List<FillBlank> createFill = fservice.createFillBlank(fillKpIdArr);
		
		//问答题
		JSONArray interArr = new JSONArray(inter);
		int interLen = interArr.length();
		String iArr[] = new String[interLen];//存放kpid的数组
		for(int i=0;i<interLen;i++){
			iArr[i] = (String) interArr.get(i);
		}
		List<Interlocution> createInter = iservice.createInter(iArr);
		/*Map<String,Boolean> interInfo = null;
		if(createInter.size()>0){
			interInfo = new HashMap<String, Boolean>();
			for(Interlocution i :createInter){
				String pro = i.getProblem();
				String imgurl = i.getImgUrl();
				interInfo.put(pro, imgurl==null?true:false);
			}
			
		}*/
		
		try {
			CreateWord.createExam(createSimple, createFill, createInter,context);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
