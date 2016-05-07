package com.cqupt.mis.rms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqupt.mis.rms.dao.ResearchClassDao;
import com.cqupt.mis.rms.dao.ResourceInfoDao;
import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.model.ResourceInfo;
import com.cqupt.mis.rms.model.RolePurview;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Service;

import com.cqupt.mis.rms.dao.RolePurviewDao;
import com.cqupt.mis.rms.dao.RolePurviewDynDao;
import com.cqupt.mis.rms.model.RolePurviewDyn;
import com.cqupt.mis.rms.service.GrantService;

@Service("grantService")
public class GrantServiceImpl implements GrantService {
	
	@Resource
	private RolePurviewDao rolePurviewDao;

	@Resource
	private RolePurviewDynDao rolePurviewDynDao;

	@Resource
	private ResourceInfoDao resourceInfoDao;

	@Resource
	private ResearchClassDao researchClassDao;

	public boolean grant(int roleId, int[] resourceIdArr,List<RolePurviewDyn> rolePurviewDyns) {
		if(grantFixed(roleId, resourceIdArr)&&grantDyn(rolePurviewDyns))
			return true;
		
		return false;
	}

	public boolean grantFixed(int roleId, int[] resourceIdArr) {
		return rolePurviewDao.addRolePurview(roleId, resourceIdArr);
	}

	public boolean grantDyn(List<RolePurviewDyn> rolePurviewDyns) {
		return rolePurviewDynDao.addRolePurviewDyn(rolePurviewDyns);
	}

	public Map<String, Object> getAuthority(int roleId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ResourceInfo> resourceInfoList = resourceInfoDao.findAll();	//获取静态资源
		List<ResearchClass> researchClassList = researchClassDao.findAll();		//获取动态资源
		List<RolePurview> fixedRolePurviewList = rolePurviewDao.findByRoleId(roleId);	//获取静态权限
		List<RolePurviewDyn> dynamicRolePurivewList = rolePurviewDynDao.findSimpleByRoleId(roleId);		//获取动态权限

		//设置各种列表到Map中
		resultMap.put(FIXED_RES, resourceInfoList);
		resultMap.put(DYNAMIC_RES, researchClassList);
		resultMap.put(FIXED_AUTH, fixedRolePurviewList);
		resultMap.put(DYNAMIC_AUTH, dynamicRolePurivewList);
		return resultMap;
	}

	public ResultInfo<Object> grant(JSONObject map) {
		int roleId = (Integer) map.get(ROLEID);
		JSONArray addFixedAuth = (JSONArray) map.get(ADD_FIXED_AUTH);
		JSONArray delFixedAuth = (JSONArray) map.get(DEL_FIXED_AUTH);
		JSONArray changedDynAuth = (JSONArray) map.get(CHANGED_DYN_AUTH);

		int[] addFixedAuthArray = toArray(addFixedAuth);
		int[] delFixedAuthArray = toArray(delFixedAuth);
		rolePurviewDao.addRolePurview(roleId, addFixedAuthArray);		//增加静态权限
		rolePurviewDao.deleteRolePurview(roleId, delFixedAuthArray);		//删除静态权限

		for (Object aChangedDynAuth : changedDynAuth) {
			JSONObject obj = (JSONObject) aChangedDynAuth;
			int classsId = (Integer) obj.get("classId");
			RolePurviewDyn rolePurviewDyn = rolePurviewDynDao.findSimpleByRoleIdAndClassId(roleId, classsId);
			if (rolePurviewDyn == null) {
				rolePurviewDynDao.addOne(roleId, obj);
			} else {
				rolePurviewDynDao.updateByRoleIdAndClassId(roleId, obj);
			}
		}

		return null;
	}

	/**
	 *
	 * @param array
	 * @return
     */
	private int[] toArray(JSONArray array) {
		int[] result = new int[array.size()];
		for(int i=0; i<array.size(); i++) {
			try {
				Integer num = (Integer) array.get(i);
				result[i] = num;
			}catch (ClassCastException e) {
				e.printStackTrace();
				return null;
			}
		}
		return  result;
	}

	/**
	 * 静态资源的名称
	 */
	private final String FIXED_RES = "fixedRes";

	/**
	 * 动态资源的名称
	 */
	private final String DYNAMIC_RES = "dynamimapcRes";

	/**
	 * 静态资源的权限
	 */
	private final String FIXED_AUTH = "fixedAuth";

	/**
	 * 动态资源的权限
	 */
	private final String DYNAMIC_AUTH = "dynamicAuth";

	/**
	 * 角色Id参数名
	 */
	private final String ROLEID = "roleId";

	/**
	 * 待添加的静态权限参数名
	 */
	private final String ADD_FIXED_AUTH = "AddfixedAuth";

	/**
	 * 待删除的静态权限参数名
	 */
	private final String DEL_FIXED_AUTH = "delFixedAuth";

	/**
	 * 待修改的动态权限参数名
	 */
	private final String CHANGED_DYN_AUTH = "changedDynAuth";
}
