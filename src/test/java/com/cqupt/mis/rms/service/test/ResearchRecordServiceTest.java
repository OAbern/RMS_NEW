package com.cqupt.mis.rms.service.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.cqupt.mis.rms.dao.ResearchDataDao;
import com.cqupt.mis.rms.model.CQUPTUser;
import com.cqupt.mis.rms.model.Proof;
import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.model.ResearchData;
import com.cqupt.mis.rms.model.ResearchFiled;
import com.cqupt.mis.rms.model.ResearchPerson;
import com.cqupt.mis.rms.model.ResearchRecord;
import com.cqupt.mis.rms.service.ResearchFiledService;
import com.cqupt.mis.rms.service.ResearchRecordService;
import com.cqupt.mis.rms.utils.GenerateUtils;
import com.cqupt.mis.rms.utils.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@ContextConfiguration(locations={"classpath:/spring-myBatis.xml"})
public class ResearchRecordServiceTest {
	@Resource
	ResearchRecordService researchRecordServiceImpl;
	@Resource
	ResearchFiledService researchFiledServiceImpl;
	@Resource
	ResearchDataDao researchDataDao;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	@Rollback(true)
	public void addTest() {
		//构建一条记录
		ResearchRecord record = new ResearchRecord();
		ResearchClass researchClass = new ResearchClass();
		researchClass.setClassId(1);
		record.setResearchClass(researchClass);
		
		CQUPTUser user = new CQUPTUser();
		user.setUserId("000001");
		record.setSubmitUser(user);
		record.setId(GenerateUtils.getID());
		record.setStatus(1);
		
		List<ResearchFiled> fileds = researchFiledServiceImpl.findByClassId(1);
		Set<ResearchData> datas = new HashSet<ResearchData>();
		for(ResearchFiled filed : fileds) {
			ResearchData data = new ResearchData();
			data.setFiled(filed);
			data.setValue("测试值"+filed.getOrder());
			datas.add(data);
		}
		
		List<ResearchPerson> persons = new ArrayList<ResearchPerson>();
		ResearchPerson researchPerson = new ResearchPerson();
		researchPerson.setName("haha");
		persons.add(researchPerson);
		persons.add(researchPerson);
		
		List<Proof> proofs = new ArrayList<Proof>();
		Proof proof = new Proof();
		proof.setProofPath("/test");
		proofs.add(proof);
		proofs.add(proof);
		
		record.setFields(datas);
		record.setPersons(persons);
		record.setProofs(proofs);
		
		//添加一条记录
		researchRecordServiceImpl.add(record, null);
	}
	
	@Test
	@Rollback
	public void findByIdTest() {
		
		ResearchRecord record = researchRecordServiceImpl.findOneById("1");
		System.out.println(JSONUtils.toJSONString(record));
		
	}
	
	@Test
	@Rollback
	public void deleteByIdTest() {
		researchRecordServiceImpl.deleteById("1");
		findByIdTest();
	}
	
	@Test
	@Rollback
	public void acceptTest() {
		ResearchRecord record = new ResearchRecord();
		record.setId("1");
		researchRecordServiceImpl.accept(record, "000002");
	}

	@Test
	@Rollback
	public void refuseTest() {
		ResearchRecord record = new ResearchRecord();
		record.setId("1");
		record.setReturnReason("审批不通过");
		researchRecordServiceImpl.refuse(record, "000002");
	}
	
	@Test
	public void findListByUserAndClass() {
		List<ResearchRecord> records = researchRecordServiceImpl.findListByUserAndClass("000001", 1);
		System.out.println(JSONUtils.toJSONString(records));
	}
	
	@Test
	public void findListByClassForApprove() {
		List<ResearchRecord> records = researchRecordServiceImpl.findListByClassForApprove(1);
		System.out.println(JSONUtils.toJSONString(records));
	}
	
	@Test
	public void printJson() {
		ResearchRecord record = new ResearchRecord();
		System.out.println(JSONUtils.toJSONString(record));
	}
	
}
