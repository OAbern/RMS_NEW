



import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cqupt.mis.rms.dao.CQUPTUserDao;
import com.cqupt.mis.rms.dao.UserLoginDao;
import com.cqupt.mis.rms.model.UserLogin;
import com.cqupt.mis.rms.model.UserRoleInfo;
import com.cqupt.mis.rms.service.UserManagerService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-mybatis.xml" })
public class UserTest {
	
	@Resource
	private UserLoginDao  userLoginMapper;
	
	@Resource
	private CQUPTUserDao cquptUserMapper;
	
	@Resource
	private UserManagerService userManagerService;
	
	private int result = -1;
	private boolean boolean_result = false;
	
	@Test
	public void test_01() {
		
		UserLogin userLogin = userLoginMapper.findCQUPTRoleByUserId("010317");
		Set<UserRoleInfo> userRoleInfos = userLogin.getUserRoleInfo();
		Iterator<UserRoleInfo> it = userRoleInfos.iterator();
		while(it.hasNext()){
			UserRoleInfo userRoleInfo = it.next();
			System.out.println(userRoleInfo.getRoleinfo().getRoleName()); 
		}			
	}
	
	@Test
	@Rollback
	public void test_02(){
		
		String userId[] = new String [5];
		userId[0]="Y00003";
		userId[1]="Y00002";
		userId[2]="340616";
		userId[3]="340205";
		userId[4]="310167";
		cquptUserMapper.deleteByArray(userId);
		
	}
	
	@Test
	public void test_03(){
		
		boolean_result = userManagerService.findUNameAndUPass("030112", "123");
		System.out.println(boolean_result);
		
	}

}
