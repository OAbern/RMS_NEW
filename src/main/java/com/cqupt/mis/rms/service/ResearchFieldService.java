package com.cqupt.mis.rms.service;

import java.util.List;

import com.cqupt.mis.rms.model.ResearchField;
import com.cqupt.mis.rms.vo.ResultInfo;

/**
 * 处理科研动态字段的逻辑层接口
 * @author Bern
 *
 */
public interface ResearchFieldService {
	/**
	 * 添加科研动态字段
	 * @param researchField
	 * @return
	 */
	public ResultInfo<Object> addField(ResearchField researchField);
	
	/**
	 * 删除科研动态字段(假删除)
	 * @param researchField
	 * @return
	 */
	public boolean deleteField(ResearchField researchField);
	
	/**
	 * 修改科研动态字段
	 * @param researchField
	 * @return
	 */
	public boolean modifyField(ResearchField researchField);
	
	/**
	 * 根据classId查找所有的科研动态字段
	 * @param classId
	 * @return
	 */
	public List<ResearchField> findByClassId(int classId);
}
