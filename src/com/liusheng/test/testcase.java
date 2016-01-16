package com.liusheng.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liusheng.dao.SimpleSelectDao;
import com.liusheng.entities.SimpleSelection;
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
	private SimpleSelectDao ssDao;
	
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
		System.out.println(a.size());
	}
	
}
