package com.cqupt.mis.rms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.ResourceInfo;

/**
 * 处理静态资源DAO接口层
 * @author welkin
 *
 */
@Repository
public interface ResourceInfoDao extends BaseDao<ResourceInfo, Integer>{
    /**
     *通过静态资源父类Id查询相应的子类
     * @param parentId
     * @return
     */
	public List<ResourceInfo> findResourceByParentId(int parentId);
	
}