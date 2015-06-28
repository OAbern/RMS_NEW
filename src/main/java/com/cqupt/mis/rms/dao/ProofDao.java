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
	 * @param proofs
	 * @return	操作结果
	 */
	public boolean addList(@Param("proofs")List<Proof> proofs, @Param("rId")String recordId);
	
	/**
	 * 根据记录Id查找一组旁证材料
	 * @param recordId
	 * @return	符合条件的旁证材料
	 */
	public List<Proof> findListByRecordId(@Param("recordId")String recordId);
	
	/**
	 * 根据记录Id列表查找旁证材料
	 * @param recordId
	 * @return	符合条件的旁证材料
	 */
	public List<Proof> findListByRecordIds(@Param("rIds")List<String> recordIds);
}
