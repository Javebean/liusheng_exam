package com.liusheng.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liusheng.entities.Interlocution;
import com.liusheng.entities.SimpleSelection;
import com.liusheng.service.InterlocutionService;
import com.liusheng.service.SimpleSelectService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
@ContextConfiguration("classpath:application.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class testcase {
	
	@Autowired
	private ComboPooledDataSource dataSource;
	@Autowired
	private InterlocutionService iservice;
	@Autowired
	private SimpleSelectService simpleSelectService;
	
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
	
	@Test
	public void testPagesInterlucation(){
		List<Interlocution> allInterlocution = iservice.getAllInterlocution(0, 10);
		System.out.println(allInterlocution.size());
	}

}
