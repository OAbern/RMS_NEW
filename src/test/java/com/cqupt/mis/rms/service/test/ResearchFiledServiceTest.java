package com.cqupt.mis.rms.service.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.cqupt.mis.rms.dao.ResearchFiledDao;
import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.model.ResearchFiled;
import com.cqupt.mis.rms.service.ResearchFiledService;
import com.cqupt.mis.rms.utils.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@ContextConfiguration(locations={"classpath:config/spring-myBatis.xml"})  
public class ResearchFiledServiceTest {
	@Resource
	ResearchFiledService researchFiledServiceImpl;
	@Resource
	ResearchFiledDao researchFiledDao;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PropertyConfigurator.configure(ResearchClassServiceTest.class.getClassLoader().getResource("log4j.properties"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addTest() {
		ResearchClass class1 = new ResearchClass();
		class1.setClassId(1);
		ResearchFiled field = new ResearchFiled();
		field.setName("参与时间");
		field.setOrder(2);
		field.setResearchClass(class1);
		
		researchFiledServiceImpl.addFiled(field);
	}
	
	@Test
	public void findByClassId() {
		List<ResearchFiled> list = researchFiledServiceImpl.findByClassId(1);
		for(ResearchFiled filed : list) {
			System.out.println(filed.getId());
			System.out.println(filed.getName());
			System.out.println("classOrder:"+filed.getResearchClass().getClassName());
			System.out.println("classOrder:"+filed.getResearchClass().getOrder());
			System.out.println("***********");
		}
	}
	
	@Test
	public void deleteTest() {
		ResearchClass class1 = new ResearchClass();
		class1.setClassId(1);
		ResearchFiled field = new ResearchFiled();
		field.setId(5);
		field.setOrder(1);
		field.setResearchClass(class1);
		
		researchFiledServiceImpl.deleteFiled(field);
	}
	
	@Test
	public void modifyTest() {
		ResearchFiled filed = researchFiledDao.selectByPrimaryKey(5);
		System.out.println("修改前："+JSONUtils.toJSONString(filed));
		filed.setOrder(4);
		researchFiledServiceImpl.modifyFiled(filed);
		System.out.println("修改后："+JSONUtils.toJSONString(filed));
		
	}

}
