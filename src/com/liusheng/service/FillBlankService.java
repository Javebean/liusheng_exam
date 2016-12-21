package com.liusheng.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liusheng.dao.FillBlankDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.FillBlankAnswer;
import com.liusheng.entities.Keypoints;
import com.liusheng.util.Tools;
@Service
public class FillBlankService {
	private Logger log = Logger.getLogger(FillBlankService.class);
	
	@Autowired
	private FillBlankDao fillDao;
	@Autowired
	private KeypointsService kpservice;
	
	public String addOneFillBlank(FillBlank fb) {
		String keypointId = fb.getKeypointId();
		String keypoint = fb.getKeypoint();
		//说明是更新数据
		if(0!=fb.getId()){
			fb.setCheckStatus(1);
		}
		
		if(Tools.isEmpty(keypointId)){
			//解析excel中的
			int kpid = kpservice.getKeypointByName(keypoint);
			if(kpid!=-1){
				fb.setKeypointId(kpid+"");
			}else{
				Keypoints k = new Keypoints(keypoint, Tools.createNum());
				kpservice.addKeypoints(k);
				fb.setKeypointId(k.getId()+"");
			}
		}
		fb.setNumber(Tools.createNum());
		
		JSONObject obj = new JSONObject();
		
		String problem = fb.getProblem();
		//检测所提交的字符串时候符合格式要求
		char[] charArray = problem.toCharArray();
		int ans=0;
		for(char c : charArray){
			if(ans==0 && c=='>'){
				obj.put("code", 1);
				obj.put("status", "上传题目格式不符合条件");
				return obj.toString();
			}
			
			if(c=='<'){
				ans++;
			}else if(c=='>'){
				ans--;
			}
			if(ans>=2||ans<=-2){
				obj.put("code", 1);
				obj.put("status", "上传题目格式不符合条件");
				return obj.toString();
			}
		}
		if(ans!=0){
			obj.put("code", 1);
			obj.put("status", "上传题目格式不符合条件");
			return obj.toString();
		}
		
		
		
		//因为这样会把<>>这种也解析成功,所以加了上面的判断
		Pattern reg = Pattern.compile("(<)(.*?)(>)");
		Matcher matcher = reg.matcher(problem);
		StringBuilder answer = new StringBuilder(100);
		int fillnum = 0;
		boolean isbreak =false;
		while(matcher.find()){
			fillnum++;
			String group = matcher.group(2);
			if(Tools.isEmpty(group)){
				isbreak = true;
				break;
			}
			answer.append(group);
			answer.append(",");
		}
		
		//如果有一个空中没有答案，就只有<>,格式不合格
		if(isbreak){
			obj.put("code", 1);
			obj.put("status", "上传题目格式不符合条件");
			return obj.toString();
		}
		//删掉最后一个逗号“，”
		answer.deleteCharAt(answer.length()-1);
		log.info("上传填空题后，被截取的答案。。。"+answer.toString());
		fb.setFillNums(fillnum);
		fb.setAnswer(answer.toString());
		boolean b =  fillDao.addOneFillBlank(fb);
		
		obj.put("status", b?"上传成功":"上传失败");
		return obj.toString();
	}

	public boolean deleteOneFillBlank(int id) {
		return fillDao.deleteOneFillBlank(id);
	}

	public void updataOneFillBlank(FillBlank fb) {
	}

	public FillBlank getOneFillBlank(int id) {
		return null;
	}

	public List<FillBlank> getAllFillBlank(int page, int itemNums,int state) {
		int start = (page-1)*itemNums;
		return fillDao.getAllFillBlank(start, itemNums,state);
	}
	
	
	public int getFillBlankPageNums(int items){
		double count = fillDao.getFillBlankCount();
		int pages = (int) Math.ceil(count/items);
		return pages;
	}

	public boolean checkOneFillBlank(int agreeId,String question,String keypoint,String keypointId) {
		return fillDao.checkOneFillBlank(agreeId,question,keypoint,keypointId);
	}
	
	public List<FillBlankAnswer> getFBAnswer(int fbId) {
		return fillDao.getFBAnswer(fbId);
	}
}
