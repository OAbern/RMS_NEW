package com.cqupt.mis.rms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.ResearchPerson;

/**
 * 处理科研记录相关人员的Dao层接口
 * @author Bern
 *
 */
@Repository
public interface ResearchPersonDao extends BaseDao<ResearchPerson, Integer> {
	/**
	 * 添加一组科研记录相关人
	 * @param persons 待添加的相关人信息列表
	 * @return 操作结果
	 */
	public boolean addList(@Param("persons")List<ResearchPerson> persons, @Param("rId")String recordId);
	
	/**
	 * 根据记录ID查找一组科研记录相关人
	 * @param recordId 待查找的记录id
	 * @return 查找结果
	 */
	public List<ResearchPerson> findListByRecordId(@Param("recordId")String recordId);
	
	/**
	 * 根据记录ID列表查找科研记录相关人
	 * @param recordIds 待查找的记录id列表
	 * @return 查找结果
	 */
	public List<ResearchPerson> findListByRecordIds(@Param("rIds")List<String> recordIds);

	/**
	 * 根据记录ID删除一组科研记录相关人员
	 * @param recordId 待删除的记录id
	 * @return 操作结果
     */
	public boolean deleteByRecordId(@Param("rId")String recordId);
}
