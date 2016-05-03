package com.cqupt.mis.rms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.ResearchRecord;

/**
 * 处理科研记录的Dao层接口
 * @author Bern
 *
 */
@Repository
public interface ResearchRecordDao extends BaseDao<ResearchRecord, String> {
	/**
	 * 根据记录id更改记录的状态
	 * @param id 要更改的记录Id	
	 * @param status 要更改的记录状态
	 * @return 操作结果
	 */
	public boolean modifyStatus(@Param("rId")String id, @Param("status")int status);
	
	/**
	 * 更改记录的状态为2，添加审批者
	 * @param record
	 * @param approvedUserId
	 * @return	操作结果
	 */
	public boolean accept(@Param("record")ResearchRecord record, @Param("userId")String approvedUserId);
	
	/**
	 * 更改记录的状态为3，添加审批者，添加拒绝原因
	 * @param record
	 * @param approvedUserId
	 * @return
	 */
	public boolean refuse(@Param("record")ResearchRecord record, @Param("userId")String approvedUserId);
	
	/**
	 * 查找指定用户、指定类别下所有的记录
	 * @param userId 指定用户的Id
	 * @param classId 指定类别
	 * @return 符合条件的结果
	 */
	public List<ResearchRecord> findListByUserAndClass(@Param("uId")String userId, @Param("cId")int classId);
	
	/**
	 * 查找指定类别下的所有待审核记录
	 * @param classId 指定类别
	 * @return 符合条件的数据
	 */
	public List<ResearchRecord> findListByClassForApprove(@Param("cId")int classId);
	
}
