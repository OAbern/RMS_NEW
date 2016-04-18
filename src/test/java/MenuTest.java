
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.model.MenuInfo;
import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.service.GrantService;
import com.cqupt.mis.rms.service.MenuService;
import com.cqupt.mis.rms.utils.JSONUtils;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-mybatis.xml"})
public class MenuTest {

	@Resource
	private MenuService menuService;
	@Resource
	private GrantService grantService;
	
	@Test
	public void test_01() {
		List<MenuInfo> menuInfos = menuService.findMenuList(2);
		System.out.println(JSONUtils.toJSONString(menuInfos));
	}
	
	@Test
	public void test_02(){
		int	resourceIdArr[] = {11,12,13,14,16,21};
		grantService.grantFixed(10, resourceIdArr);
	}
	
	@Test
	public void test_03(){
		List<RolePurviewDyn> rolePurviewDyns = new ArrayList<RolePurviewDyn>(); 
		RolePurviewDyn rolePurviewDyn = new RolePurviewDyn();
		CQUPTRole roleInfo = new CQUPTRole();
		roleInfo.setRoleId(10);
		ResearchClass researchClass = new ResearchClass();
		researchClass.setClassId(1);
		
		rolePurviewDyn.setRoleInfo(roleInfo);
		rolePurviewDyn.setResearchClass(researchClass);
		rolePurviewDyn.setInput(true);
		rolePurviewDyn.setManage(false);
		rolePurviewDyn.setStatistics(true);
		rolePurviewDyn.setApprove(true);
		
		RolePurviewDyn rolePurviewDyn2 = new RolePurviewDyn();

		ResearchClass researchClass2 = new ResearchClass();
		researchClass.setClassId(2);
		
		rolePurviewDyn.setRoleInfo(roleInfo);
		rolePurviewDyn.setResearchClass(researchClass2);
		rolePurviewDyn.setInput(false);
		rolePurviewDyn.setManage(true);
		rolePurviewDyn.setStatistics(true);
		rolePurviewDyn.setApprove(true);
		
		rolePurviewDyns.add(rolePurviewDyn);
		rolePurviewDyns.add(rolePurviewDyn2);
		
		grantService.grantDyn(rolePurviewDyns);
		
	}

}
