



import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
import com.cqupt.mis.rms.dao.ResourceInfoDao;
import com.cqupt.mis.rms.model.ResourceInfo;
import com.cqupt.mis.rms.service.UserManagerService;
import com.cqupt.mis.rms.utils.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-mybatis.xml"})
public class ResourceTest {
	
	@Resource
	private CQUPTRoleDao cquptRoleMapper;
	@Resource
	private UserManagerService userManagerService;
	@Resource
	private ResourceInfoDao resourceInfoMapper;

	
	@Test
	@Rollback
	public void test_01() {
		
//		ResourceInfo parentResourceinfo = resourceInfoMapper.selectByPrimaryKey(1);
		ResourceInfo parentResourceinfo = new ResourceInfo();
		parentResourceinfo.setResourceId(1);
		
		ResourceInfo resourceInfo = new ResourceInfo();
		resourceInfo.setResourceId(777);
		resourceInfo.setResourceName("hello");
		resourceInfo.setResourceRemark("");
		resourceInfo.setResourceUrl("");
		resourceInfo.setParentResourceinfo(parentResourceinfo);
		resourceInfoMapper.add(resourceInfo);
	}
	
	
	@Test
	public void test_02(){
		List<ResourceInfo> list = resourceInfoMapper.findResourceByParentId(0);
		System.out.println(JSONUtils.toJSONString(list));
		
	}
	
	
	
	
	
	
	
	
}
