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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.cqupt.mis.rms.dao.ResearchFieldDao;
import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.model.ResearchField;
import com.cqupt.mis.rms.service.ResearchFieldService;
import com.cqupt.mis.rms.utils.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@ContextConfiguration(locations={"classpath:/spring-myBatis.xml"})
public class ResearchFieldServiceTest {
	@Resource
	ResearchFieldService researchFieldServiceImpl;
	@Resource
	ResearchFieldDao researchFieldDao;
	
	
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
		ResearchField field = new ResearchField();
		field.setName("参与时间");
		field.setOrder(2);
		field.setResearchClass(class1);
		
		researchFieldServiceImpl.addField(field);
	}
	
	@Test
	public void findByClassId() {
		List<ResearchField> list = researchFieldServiceImpl.findByClassId(1);
		System.out.println(JSONUtils.toJSONString(list));
	}
	
	@Test
	public void deleteTest() {
		ResearchClass class1 = new ResearchClass();
		class1.setClassId(1);
		ResearchField field = new ResearchField();
		field.setId(5);
		field.setOrder(1);
		field.setResearchClass(class1);
		
//		researchFieldServiceImpl.deleteField(field);
	}
	
	@Test
	public void modifyTest() {
		ResearchField field = researchFieldDao.selectByPrimaryKey(5);
		System.out.println("修改前："+JSONUtils.toJSONString(field));
		field.setOrder(4);
		researchFieldServiceImpl.modifyField(field);
		System.out.println("修改后："+JSONUtils.toJSONString(field));
		
	}

}
