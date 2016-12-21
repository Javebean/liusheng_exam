package com.liusheng.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		// ids用来存放查出来题目的id，检查是否该题已经存在了
		List<Integer> ids = new ArrayList<Integer>();
		List<SimpleSelection> simpleList = new ArrayList<SimpleSelection>();
		//对单选题知识点进行随机排序
		for(int i=0;i<Constant.SIMPLE_SELECT_NUMS;i++){
			int index = i%arr.length;
			if(index==0){
				Collections.shuffle(Arrays.asList(arr));
			}
			SimpleSelection rand = sdao.getRandSimpleByName(arr[index]);
			
			if(!ids.contains(rand.getId())){
				simpleList.add(rand);
				ids.add(rand.getId());
			} else {
				while(true){
					rand = sdao.getRandSimpleByName(arr[index]);
					if(!ids.contains(rand.getId())){
						simpleList.add(rand);
						ids.add(rand.getId());
						break;
					}
				}
			}
			
		}
		
		
		ids = new ArrayList<Integer>();
		List<FillBlank> fillList = new ArrayList<FillBlank>();
		//对填空题知识点进行随机排序
		for(int i=0;i<Constant.FILL_BLANK_NUMS;i++){
			int index = i%arr1.length;
			if(index==0){
				Collections.shuffle(Arrays.asList(arr1));
			}
			FillBlank rand = fdao.getRandFillBlankByName(arr1[index]);
			if(!ids.contains(rand.getId())){
				fillList.add(rand);
				ids.add(rand.getId());
			} else {
				while(true){
					rand = fdao.getRandFillBlankByName(arr1[index]);
					if(!ids.contains(rand.getId())){
						fillList.add(rand);
						ids.add(rand.getId());
						break;
					}
				}
			}
			
		}
		
		ids = new ArrayList<Integer>();
		List<Interlocution> interList = new ArrayList<Interlocution>();
		//对填空题知识点进行随机排序
		for(int i=0;i<Constant.INTERLOCATION_NUMS;i++){
			int index = i%arr2.length;
			if(index==0){
				Collections.shuffle(Arrays.asList(arr2));
			}
			Interlocution rand = idao.getRandInterByName(arr2[index]);
			if(!ids.contains(rand.getId())){
				interList.add(rand);
				ids.add(rand.getId());
			} else {
				while(true){
					rand = idao.getRandInterByName(arr2[index]);
					if(!ids.contains(rand.getId())){
						interList.add(rand);
						ids.add(rand.getId());
						break;
					}
				}
			}
			
		}
		
		
		try {
			CreateWord.createExam(simpleList, fillList, interList,context);
			result.put("code", 0);
			result.put("msg", "恭喜，出题成功！");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", "sorry，出题失败！");
		}
		return result.toString();
	}
}
