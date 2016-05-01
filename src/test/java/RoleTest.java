import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.ResourceInfo;
import com.cqupt.mis.rms.model.RoleLevel;
import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.service.UserManagerService;
import com.cqupt.mis.rms.utils.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-myBatis.xml"})
public class RoleTest {
	
	@Resource
	private CQUPTRoleDao CQUPTRoleDao;
	@Resource
	private UserManagerService userManagerService;
	
	private int result = -1;
	
	@Test
	public void test_01() {
		
		CQUPTRole cquptRole = new CQUPTRole();
		RoleLevel roleLevel = new RoleLevel();
		roleLevel.setId(1);
		cquptRole.setRoleId(2012211161);
		cquptRole.setRoleName("学生");
		cquptRole.setRoleLevel(roleLevel);
//		result = cquptRoleMapper.add(cquptRole);
		if(result > 0)
			System.out.println("执行成功！");
		else
			System.out.println("执行失败！");
	}
	
	@Test
	public void test_02() {
		
		Set resourceInfoList = new HashSet<ResourceInfo>();
		 CQUPTRole cquptRole = CQUPTRoleDao.findRolePurviewByRoleId(1);
		resourceInfoList = cquptRole.getRolePurviews();
		Iterator<RolePurview> it =	resourceInfoList.iterator();
			while(it.hasNext()){
				ResourceInfo resourceInfo = it.next().getPurviewinfo();
				System.out.println(resourceInfo.getResourceName());
				
			}	
	}
	
	@Test
	public void test_03(){
		CQUPTRole cquptRole = new CQUPTRole();
		cquptRole = CQUPTRoleDao.selectByPrimaryKey(4);
		System.out.println(cquptRole.getRoleName());
		
	}
	
	@Test
	public void test_04(){
		CQUPTRole cquptRole = userManagerService.findRoleLevel("010317", 1);
		System.out.println(cquptRole.getDescription());
		
	}
	
	@Test
	public void test_05(){
		CQUPTRole cquptRole = new CQUPTRole();
		cquptRole = CQUPTRoleDao.findRolePurviewDynRoleId(2);
		String json = JSONUtils.toJSONString(cquptRole);
		System.out.println(json);
		
	}
	
	@Test
	public void test_06(){
		CQUPTRole cquptRole = new CQUPTRole();
		cquptRole = CQUPTRoleDao.findRolePurviewByRoleIdAndParent(2, 11);
		String json = JSONUtils.toJSONString(cquptRole);
		System.out.println(json);
		
	}
	
	
	
	
}
