package com.cqupt.mis.rms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqupt.mis.rms.dao.*;
import com.cqupt.mis.rms.model.*;
import com.cqupt.mis.rms.utils.JSONUtils;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Service;

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

	@Resource
	private CQUPTRoleDao cquptRoleDao;

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
		//TODO:事务
		int roleId = (Integer) map.get(ROLEID);
		//检查角色是否存在
		CQUPTRole role = cquptRoleDao.selectByPrimaryKey(roleId);
		if(role == null) {
			return new ResultInfo<Object>(false, "您正在为一个不存在的角色分配权限");
		}

		JSONArray addFixedAuth = (JSONArray) map.get(ADD_FIXED_AUTH);
		JSONArray delFixedAuth = (JSONArray) map.get(DEL_FIXED_AUTH);
		JSONArray changedDynAuth = (JSONArray) map.get(CHANGED_DYN_AUTH);

		int[] addFixedAuthArray = JSONUtils.toArray(addFixedAuth);
		int[] delFixedAuthArray = JSONUtils.toArray(delFixedAuth);

		boolean result;
		if(addFixedAuthArray!=null && addFixedAuthArray.length!=0) {
			result = rolePurviewDao.addRolePurview(roleId, addFixedAuthArray);		//增加静态权限
			if(!result)
				return new ResultInfo<Object>(false, "增加静态权限失败！请稍后重试，或者联系管理员解决！");
		}

		if(delFixedAuthArray!=null && delFixedAuthArray.length!=0) {
			result = rolePurviewDao.deleteRolePurview(roleId, delFixedAuthArray);		//删除静态权限
			if(!result)
				return new ResultInfo<Object>(false, "删除静态权限失败！请稍后重试，或者联系管理员解决！");
		}

		for (Object aChangedDynAuth : changedDynAuth) {
			JSONObject obj = (JSONObject) aChangedDynAuth;
			int classsId = (Integer) obj.get(CLASSID);
			RolePurviewDyn rolePurviewDyn = rolePurviewDynDao.findSimpleByRoleIdAndClassId(roleId, classsId);
			if (rolePurviewDyn == null) {
				rolePurviewDynDao.addOne(roleId, obj);
			} else {
				rolePurviewDynDao.updateByRoleIdAndClassId(roleId, obj);
			}
		}

		return new ResultInfo<Object>(null, true);
	}

	public boolean assignRole(String userId, int[] roleIdArrNew) {
		Integer[] roleIdArrOld = cquptRoleDao.findRoleIdByUserId(userId);
		final int PLACE_HOLDER = -1;		//角色Id占位符，注意角色Id的的值不应该设为-1，否则将导致程序出现逻辑错误
		List<Integer> roleIdListAdd = new ArrayList<Integer>();
		List<Integer> roleIdListDel = new ArrayList<Integer>();

		//查找要增加的元素
		for(int i=0; i<roleIdArrNew.length; i++) {
			boolean addFlag = true;
			for(int j=0; j<roleIdArrOld.length; j++) {
				if(roleIdArrNew[i] == roleIdArrOld[j]) {
					addFlag = false;
					roleIdArrOld[j] = PLACE_HOLDER;		//相同则意为在数据表中不加不减，置为-1
					break; 		//终止内层循环
				}
			}
			if(addFlag) {		//addFlag为true，表示在roleIdArrNew有，在roleIdArrOld无的元素，即为将要在数据表增加的元素
				roleIdListAdd.add(roleIdArrNew[i]);
			}
		}

		//查找要删除的元素
		for(int item : roleIdArrOld) {
			if(item != PLACE_HOLDER) {		//值不等，表示在roleIdArrNew无，在roleIdArrOld有的元素，即为将要在数据表删除的元素
				roleIdListDel.add(item);
			}
		}

		//插入数据库
		if(roleIdListAdd.size() != 0) {
			int[] roleIdArrAdd = new int[roleIdListAdd.size()];
			for(int i=0; i<roleIdArrAdd.length; i++) {
				roleIdArrAdd[i] = roleIdListAdd.get(i);
			}
			cquptRoleDao.assignRoleAdd(userId, roleIdArrAdd);
		}

		//从数据库删除
		if(roleIdListDel.size() != 0) {
			int[] roleIdArrDel = new int[roleIdListDel.size()];
			for(int i=0; i<roleIdArrDel.length; i++) {
				roleIdArrDel[i] = roleIdListDel.get(i);
			}
			cquptRoleDao.assignRoleDelete(userId, roleIdArrDel);
		}

		return true;
	}


	/**
	 * 静态资源的名称
	 */
	private final String FIXED_RES = "fixedRes";

	/**
	 * 动态资源的名称
	 */
	private final String DYNAMIC_RES = "dynamicRes";

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
	 * 类别Id参数名
	 */
	private final String CLASSID = "classId";

	/**
	 * 待添加的静态权限参数名
	 */
	private final String ADD_FIXED_AUTH = "addfixedAuth";

	/**
	 * 待删除的静态权限参数名
	 */
	private final String DEL_FIXED_AUTH = "delFixedAuth";

	/**
	 * 待修改的动态权限参数名
	 */
	private final String CHANGED_DYN_AUTH = "changedDynAuth";
}
