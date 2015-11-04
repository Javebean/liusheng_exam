package com.liusheng.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
@ContextConfiguration("classpath:application.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class testcase {
	
	@Autowired
	private ComboPooledDataSource dataSource;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDBC(){
		System.out.println(dataSource);
	}

}
