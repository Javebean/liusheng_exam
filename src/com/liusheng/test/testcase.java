package com.liusheng.test;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liusheng.dao.FillBlankDao;
import com.liusheng.dao.InterlocutionDao;
import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.FillBlank;
import com.liusheng.entities.Interlocution;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.service.FillBlankService;
import com.liusheng.service.InterlocutionService;
import com.liusheng.service.SimpleSelectService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
@ContextConfiguration("classpath:application.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class testcase {
	
	@Autowired
	private ComboPooledDataSource dataSource;
	/*@Autowired
	private InterlocutionService iservice;*/
	@Autowired
	private SimpleSelectService simpleSelectService;
	
	@Autowired
	private FillBlankService fs;
	
	@Autowired
	private InterlocutionService is;
	
	@Autowired
	private SimpleSelectDao ssDao;
	
	@Autowired
	private FillBlankDao fbdao;
	
	@Autowired
	private InterlocutionDao idao;
	
	
	@Test
	public void testcreateFillBlankByKid(){
		String kpId[] ={"4","5","7"};
		List<FillBlank> fb = fbdao.createFillBlankByKid(kpId);
		for(FillBlank f: fb){
			System.out.println(f.getFillNums());
		}
		
	}
	
	
	@Test
	public void testcreateSimple(){
		Map<String ,Integer> map = new HashMap<String, Integer>();
		map.put("4", 3);
		map.put("6", 3);
		map.put("7", 4);
		List<SimpleSelection> createSimple = simpleSelectService.createSimple(map);
		System.out.println(createSimple.size());
	}
	
	@Test
	public void testcreateFill(){
		/*Map<String ,Integer> map = new HashMap<String, Integer>();
		map.put("4", 3);
		map.put("6", 3);
		map.put("7", 4);
		List<FillBlank> createFillBlank = fs.createFillBlank(map);
		System.out.println(createFillBlank.size());*/
	}
	@Test
	public void testcreateIl(){
		Map<String ,Integer> map = new HashMap<String, Integer>();
		map.put("4", 3);
		map.put("6", 3);
		map.put("7", 4);
		List<Interlocution> createFillBlank = is.createInter(map);
		System.out.println(createFillBlank.size());
	}
	
	
	
	
	@Test
	public void testgetCount(){
		long simpleSelectionCount = ssDao.getSimpleSelectionCount(1+"");
		System.out.println(simpleSelectionCount);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDBC(){
		System.out.println(dataSource);
	}
	
	@Test
	public void testAddSimpleSelect(){
		SimpleSelection ss = new SimpleSelection();
		simpleSelectService.addOneSimpleSelection(ss);
	}
	
	/*@Test
	public void testPagesInterlucation(){
		List<Interlocution> allInterlocution = iservice.getAllInterlocution(0, 10);
		System.out.println(allInterlocution.size());
	}*/

	@Test
	public void testRandSelectSimple(){
		List<SimpleSelection> a = ssDao.createSimpleByKid("5", 3);
		System.out.println("选择题");
		for(SimpleSelection s: a){
			System.out.print(s.getId()+" ");
		}
		
	/*	List<FillBlank> b  = fbdao.createFillBlankByKid("5", 3);
		System.out.println("填空题");
		for(FillBlank s: b){
			System.out.print(s.getId()+" ");
		}*/
		
		List<Interlocution> c = idao.createInterlocaionByKid("5", 3);
		
		System.out.println("问答题");
		for(Interlocution s: c){
			System.out.print(s.getId()+" ");
		}
	}
	
}
