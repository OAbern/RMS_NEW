package com.cqupt.mis.rms.service;

import java.util.List;

import com.cqupt.mis.rms.model.ResearchRecord;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.apache.commons.fileupload.FileItem;

/**
 * 处理科研记录的逻辑层接口
 * @author Bern
 *
 */
public interface ResearchRecordService {
	
	/**
	 * 添加一条记录（提交和保存共用）
	 * @param record	待提交的记录
	 * @param proofs	旁证材料源文件
	 * @return 操作结果
	 */
	public ResultInfo<Object> add(ResearchRecord record, List<FileItem> proofs);
	
	/**
	 * 根据科研记录Id查找一条完整的科研记录
	 * @param recordId 待查找的记录Id
	 * @return 查找记录
	 */
	public ResearchRecord findOneById(String recordId);
	
	/**
	 * 查找指定用户、指定类别下所有的记录(包含全部信息)
	 * @param userId 指定用户的Id
	 * @param classId 指定类别
	 * @return 返回的记录中有全部详细的信息
	 */
	public List<ResearchRecord> findListByUserAndClass(String userId, int classId);

	/**
	 * 查找指定用户、指定类别下所有的记录(不包含详细的字段，相关人员，旁证材料信息)
	 * @param userId 指定用户的Id
	 * @param classId 指定类别
     * @return 返回的记录中只有简单信息(不包含详细的字段，相关人员，旁证材料信息)
     */
	public List<ResearchRecord> findSimpleListByUserAndClass(String userId, int classId);
	
	/**
	 * 查找指定类别下的所有待审核记录
	 * @param classId 指定类别
	 * @return 符合条件的数据
	 */
	public List<ResearchRecord> findListByClassForApprove(int classId);
	
	/**
	 * 根据科研记录Id删除一条科研记录
	 * @param recordId 待删除的记录id
	 * @return 操作结果
	 */
	public boolean deleteById(String recordId);
	
	/**
	 * 修改一条科研记录
	 * @param record 待修改的记录文件
	 * @param proofFiles 旁证材料源文件
	 * @param fixedProofIdList 未修改的旁证材料id列表
	 * @return 操作结果
	 */
	public ResultInfo<Object> modify(ResearchRecord record, List<FileItem> proofFiles, List<Integer> fixedProofIdList);
	
	/**
	 * 审核通过一条科研记录
	 * 仅修改审核者，记录状态
	 * @param record 待审核的科研记录
	 * @return 操作结果
	 */
	public boolean accept(ResearchRecord record, String approvedUserId);
	
	/**
	 * 审核拒绝一条科研记录
	 * 仅修改审核者，记录状态，拒绝原因
	 * @param record 待审核的科研记录
	 * @return 操作结果
	 */
	public boolean refuse(ResearchRecord record, String approvedUserId);
}
