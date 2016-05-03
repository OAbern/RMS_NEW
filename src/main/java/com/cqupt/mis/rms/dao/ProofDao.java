package com.cqupt.mis.rms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cqupt.mis.rms.model.Proof;

/**
 * 处理旁证材料的Dao层接口
 * @author Bern
 *
 */
@Repository
public interface ProofDao extends BaseDao<Proof, Integer>{
	/**
	 * 添加一组旁证材料
	 * @param proofs 待添加的旁证材料信息
	 * @param recordId 旁证材料所属的记录Id
	 * @return	操作结果
	 */
	public boolean addList(@Param("proofs")List<Proof> proofs, @Param("rId")String recordId);
	
	/**
	 * 根据记录Id查找一组旁证材料
	 * @param recordId 待查找的记录id
	 * @return	符合条件的旁证材料
	 */
	public List<Proof> findListByRecordId(@Param("recordId")String recordId);
	
	/**
	 * 根据记录Id列表查找旁证材料
	 * @param recordIds 待查找的记录id列表
	 * @return	符合条件的旁证材料
	 */
	public List<Proof> findListByRecordIds(@Param("rIds")List<String> recordIds);

	/**
	 * 根据记录Id删除一组旁证材料信息(不删除proofId在notDeleteProofIdList列表中的值)
	 * @param recordId 待删除的记录Id
	 * @param notDeleteProofIdList 不删除的旁证材料id列表（没有请置null）
	 * @return 操作结果
     */
	public boolean deleteByRecordId(@Param("rId")String recordId, @Param("pIds")List<Integer> notDeleteProofIdList);
}
