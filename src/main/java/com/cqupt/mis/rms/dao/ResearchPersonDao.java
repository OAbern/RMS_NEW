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
	 * @param persons
	 * @return
	 */
	public boolean addList(@Param("persons")List<ResearchPerson> persons, @Param("rId")String recordId);
	
	/**
	 * 根据记录ID查找一组科研记录相关人
	 * @param recordId
	 * @return
	 */
	public List<ResearchPerson> findListByRecordId(@Param("recordId")String recordId);
	
	/**
	 * 根据记录ID列表查找科研记录相关人
	 * @param recordIds
	 * @return
	 */
	public List<ResearchPerson> findListByRecordIds(@Param("rIds")List<String> recordIds);
}
