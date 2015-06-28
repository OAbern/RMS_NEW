package com.cqupt.mis.rms.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.ResearchData;

/**
 * 处理科研数据信息的逻辑层接口
 * @author Bern
 *
 */
@Repository
public interface ResearchDataDao {
	/**
	 * 添加一组科研信息的数据
	 * @param fileds 添加的科研数据
	 * @param recordId 添加科研记录的记录id
	 * @return
	 */
	public boolean addSet(@Param("datas")Set<ResearchData> datas, @Param("rId")String recordId);
	
	/**
	 * 根据记录id和类别id查找完整的科研记录（包含完整的一个类别下所有未删除字段）
	 * @param recordId 所要查找的科研记录的记录id
	 * @param classId 所要查找的科研记录的类别id
	 * @return 符合条件的科研数据
	 */
	public Set<ResearchData> findList1(@Param("rId")String recordId, @Param("cId")int classId);
	
	/**
	 * 根据记录id列表和类别id查找科研记录（不完整，不包括空值字段）
	 * @param recordIds 所要查找的记录Id列表
	 * @param classId 所要查找的科研记录的类别id
	 * @return 符合条件的科研数据
	 */
	public List<ResearchData> findList2(@Param("rIds")List<String> recordIds, @Param("cId")int classId);
	
}
