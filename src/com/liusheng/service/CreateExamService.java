package com.liusheng.service;

import java.util.Arrays;
import java.util.Collections;
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
	//题目bu充足的
	private static final int NOT_ENOUGH = -1;
	
	
	
	
	//2016-12-21现在的出题逻辑是这样的，每个知识点对应的类型题 必须大于 该类型的出题数量
	//例如：单选题要出关于知识点1 和 知识点2的题目，那么知识点1和知识点2的单选题数量都必须大于10
	public String createexamService(String simple,String fill,String inter,ServletContext context){
		log.info("出题各类题目的知识点：" +simple+"--"+fill+"--"+inter);
		JSONObject result = new JSONObject();
		//单选题检查
		String arr[] = simple.split(",");
		Object[] nums = sdao.getSimpleSelectionCountByName(arr);
		for(int i=0,len=nums.length;i<len;i++){
			int num = (int)nums[i];
			if(num<Constant.SIMPLE_SELECT_NUMS){
				result.put("code", NOT_ENOUGH);
				result.put("msg", "单选题中,知识点：“"+arr[i]+"”的题目数量不足"+Constant.SIMPLE_SELECT_NUMS+"题");
				return result.toString();
			}
		}
		
		//填空题检查
		String arr1[] = fill.split(",");
		nums = fdao.getFillBlankCountByName(arr1);
		for(int i=0,len=nums.length;i<len;i++){
			int num = (int)nums[i];
			if(num<Constant.FILL_BLANK_NUMS){
				result.put("code", NOT_ENOUGH);
				result.put("msg", "填空题中,知识点：“"+arr1[i]+"”的题目数量不足"+Constant.FILL_BLANK_NUMS+"题");
				return result.toString();
			}
		}
		
		//问答题检查
		String arr2[] = inter.split(",");
		nums = idao.getInterlocaionCountByName(arr2);
		for(int i=0,len=nums.length;i<len;i++){
			int num = (int)nums[i];
			if(num<Constant.INTERLOCATION_NUMS){
				result.put("code", NOT_ENOUGH);
				result.put("msg", "问答题中,知识点：“"+arr2[i]+"”的题目数量不足"+Constant.INTERLOCATION_NUMS+"题");
				return result.toString();
			}
		}
		
		
		
		//对单选题知识点进行随机排序
		Collections.shuffle(Arrays.asList(arr));
		for(){
			
		}
		
		
		
		
		
		
		
		
		JSONArray simpleArr = new JSONArray(simple);
			int simpleLen = simpleArr.length();
			//存放传过来的知识点id
			String sarr[] = new String[simpleLen]; 
			for(int i=0;i<simpleLen;i++){
				String str = (String) simpleArr.get(i);
				sarr[i] = str;
			}
			//List<SimpleSelection> createSimple = createSimple(sarr);
		
		
		
		
		//填空题
		JSONArray fillArr = new JSONArray(fill);
		int fillLen = fillArr.length();
		String fillKpIdArr[] = new String[fillLen];
		
		for(int i=0;i<fillLen;i++){
			fillKpIdArr[i] = fillArr.get(i).toString();
		}
		//List<FillBlank> createFill = fservice.createFillBlank(fillKpIdArr);
		
		//问答题
		JSONArray interArr = new JSONArray(inter);
		int interLen = interArr.length();
		String iArr[] = new String[interLen];//存放kpid的数组
		for(int i=0;i<interLen;i++){
			iArr[i] = (String) interArr.get(i);
		}
		//List<Interlocution> createInter = iservice.createInter(iArr);
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
			//CreateWord.createExam(createSimple, createFill, createInter,context);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
