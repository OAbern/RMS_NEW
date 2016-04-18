package com.cqupt.mis.rms.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.cqupt.mis.rms.dao.ResearchClassDao;
import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.service.ResearchClassService;
import com.cqupt.mis.rms.utils.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@ContextConfiguration(locations={"classpath:/spring-myBatis.xml"})
public class ResearchClassServiceTest extends AbstractJUnit4SpringContextTests {
	@Resource
	ResearchClassService researchClassServiceImpl;
	@Resource
	ResearchClassDao researchClassDao;
	
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
		class1.setClassName("教学类");
		class1.setOrder(1);
		researchClassServiceImpl.addClass(class1);
	}
	
	@Test
	@Rollback
	public void deleteTest() {
		ResearchClass class1 = new ResearchClass();
		class1.setClassId(4);
		class1.setClassName("教学类");
		class1.setOrder(3);
		researchClassServiceImpl.deleteClass(class1);
	}
	
	@Test
	public void findAllTest() {
		List<ResearchClass> classes = researchClassServiceImpl.findAll();
		System.out.println(JSONUtils.toJSONString(classes));
		for(ResearchClass class1 : classes) {
			System.out.println(class1.getClassId());
		}
	}
	
	@Test
	public void modifyTest() {
		ResearchClass researchClass = researchClassDao.selectByPrimaryKey(1);
		System.out.println("修改前："+JSONUtils.toJSONString(researchClass));
		researchClass.setOrder(1);
		researchClassServiceImpl.modifyClass(researchClass);
		System.out.println("修改后："+JSONUtils.toJSONString(researchClass));
	}
	
	
	
	
	
	
	

}
