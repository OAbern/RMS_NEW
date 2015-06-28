package com.cqupt.mis.rms.service;

import java.util.List;

import com.cqupt.mis.rms.model.ResearchRecord;

/**
 * 处理科研记录的逻辑层接口
 * @author Bern
 *
 */
public interface ResearchRecordService {
	
	/**
	 * 添加一条记录（提交和保存共用）
	 * @param record
	 * @return 操作结果
	 */
	public boolean add(ResearchRecord record);
	
	/**
	 * 根据科研记录Id查找一条完整的科研记录
	 * @param recordId
	 * @return 查找记录
	 */
	public ResearchRecord findOneById(String recordId);
	
	/**
	 * 查找指定用户、指定类别下所有的记录
	 * @param userId 指定用户的Id
	 * @param classId 指定类别
	 * @return 符合条件的结果
	 */
	public List<ResearchRecord> findListByUserAndClass(String userId, int classId);
	
	/**
	 * 查找指定类别下的所有待审核记录
	 * @param classId 指定类别
	 * @return 符合条件的数据
	 */
	public List<ResearchRecord> findListByClassForApprove(int classId);
	
	/**
	 * 根据科研记录Id删除一条科研记录
	 * @param recordId
	 * @return 操作结果
	 */
	public boolean deleteById(String recordId);
	
	/**
	 * 修改一条科研记录
	 * @param record
	 * @return 操作结果
	 */
	public boolean modify(ResearchRecord record);
	
	/**
	 * 审核通过一条科研记录
	 * 仅修改审核者，记录状态
	 * @param record
	 * @return 操作结果
	 */
	public boolean accept(ResearchRecord record, String approvedUserId);
	
	/**
	 * 审核拒绝一条科研记录
	 * 仅修改审核者，记录状态，拒绝原因
	 * @param record
	 * @return 操作结果
	 */
	public boolean refuse(ResearchRecord record, String approvedUserId);
}
