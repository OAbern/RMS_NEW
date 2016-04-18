package com.cqupt.mis.rms.dao.test;

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

import com.cqupt.mis.rms.dao.RolePurviewDynDao;
import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.service.test.ResearchClassServiceTest;
import com.cqupt.mis.rms.utils.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@ContextConfiguration(locations={"classpath:/spring-myBatis.xml"})
public class RolePurviewDynDaoTest {
	@Resource
	RolePurviewDynDao rolePurviewDynDao;
	
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
	public void findByRoleIdTest() {
		List<RolePurviewDyn> list = rolePurviewDynDao.findByRoleId(2);
		System.out.println(JSONUtils.toJSONString(list));
	}
	
	@Test
	public void findAllTest() {
		List<RolePurviewDyn> list = rolePurviewDynDao.findAll();
		System.out.println(JSONUtils.toJSONString(list));
	}
	
}
