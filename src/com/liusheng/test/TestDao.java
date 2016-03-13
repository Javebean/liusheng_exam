package com.liusheng.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liusheng.dao.FillBlankDao;
import com.liusheng.dao.InterlocutionDao;
import com.liusheng.dao.KeypointsDao;
import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.Interlocution;
import com.liusheng.entities.Keypoints;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.util.Constant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class TestDao {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Autowired
	private SimpleSelectDao ssdao;
	
	@Autowired
	private FillBlankDao fbdao;
	
	@Autowired
	private InterlocutionDao idao;
	
	@Autowired
	private KeypointsDao kdao;
	
	
	@Test
	public void testselectAllkp(){
		List<Object[]> simpleAllkp = kdao.getSimpleAllkp();
		for(Object[] s:simpleAllkp){
			System.out.println(Arrays.toString(s));
		}
	}
	
	
	
	//插入99道单选题
	@Test
	public void insertSimple(){
		Random rand = new Random();
		String answer[] = {"A","B","C","D"};
		for(int i=1;i<100;i++){
			SimpleSelection ss = new SimpleSelection("10234223422", "问题"+i,"问题"+i+"的A", "问题"+i+"的B", "问题"+i+"的C", "问题"+i+"的D",Constant.CHECK_SUCCESS);
			int  n = rand.nextInt(20) + 1;
			int ans = rand.nextInt(4);
			ss.setAnswer(answer[ans]);
			ss.setKeypointId(n+"");
			ss.setKeypoint("知识点"+n);
			ss.setAnswerText("问题"+i+"的答案");
			boolean a = ssdao.addOneSimpleSelection(ss);
			if(!a){
				System.out.println("序号"+i+"这个出错");
			}
		}
		
		
	}
	
	
	//插入99道填空题
	@Test
	public void insertFillBlank(){
		Random ran = new Random();
		for(int i=1;i<60;i++){
			
			int n = ran.nextInt(20)+1;
			FillBlank fb = new FillBlank("10034324", "问题"+i+"<答案>网络工程<题目>是什么？", n+"", Constant.CHECK_SUCCESS, 2);
			fb.setKeypoint("知识点"+n);
			fb.setAnswer("答案,题目");
			fbdao.addOneFillBlank(fb);
		}
		for(int i=60;i<100;i++){
			int n = ran.nextInt(20)+1;
			FillBlank fb = new FillBlank("10034324", "问题"+i+"网络工程<题目>是什么？", n+"", Constant.CHECK_SUCCESS, 1);
			fb.setKeypoint("知识点"+n);
			fb.setAnswer("题目");
			fbdao.addOneFillBlank(fb);
		}
	}
	
	//插入99道问答题
	@Test
	public void insertInterlocation(){
		Random ran = new Random();
		for(int i=1;i<100;i++){
			Interlocution in = new Interlocution("10034234", "问答题似的发射点发射点啊手动阀手动阀,问题："+i, "", Constant.CHECK_SUCCESS);
			int n = ran.nextInt(20)+1;
			in.setKeypointId(n+"");
			in.setKeypoint("知识点"+n);
			in.setAnswer("问题"+i+"的答案");
			idao.addOneInterlocution(in);
		}
	}
	
	//插入20个知识点
	@Test
	public void insertKeypoint(){
		for(int i=1;i<21;i++){
			Keypoints kp = new Keypoints("知识点"+i, "124324234");
			kdao.addKeypoints(kp);
		}
	}
	
	

}
