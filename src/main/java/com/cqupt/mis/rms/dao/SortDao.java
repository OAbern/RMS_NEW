package com.cqupt.mis.rms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 处理所有表中排序字段的Dao层接口
 * @author Bern
 *
 */
@Repository("sortDao")
public interface SortDao {
	/**
	 * 添加记录前维护排序字段
	 * @param tableName	操作的数据库表名
	 * @param order	插入的order值
	 * @param classId 对应的科研类别id（仅对修改科研字段时，此参数有用！不需要此参数时，请置为0）
	 * @return 操作结果
	 */
	public boolean sortBeforeAdd(@Param("table")String tableName, @Param("order")int order, @Param("classId")int classId);
	
	/**
	 * 删除记录后维护排序字段
	 * @param tableName	操作的数据库表名
	 * @param order	移除的order值
	 * @param classId 对应的科研类别id（仅对修改科研字段时，此参数有用！不需要此参数时，请置为0）
	 * @return 操作结果
	 */
	public boolean sortAfterDelete(@Param("table")String tableName, @Param("order")int order, @Param("classId")int classId);
	
	/**
	 * 处理修改时，旧排序值比新排序值大时的情况 
	 * @param tableName 操作数据库的表名
	 * @param oldOrder 旧的order值
	 * @param newOrder 新的order值（待修改的值）
	 * @param classId 对应的科研类别id（仅对修改科研字段时，此参数有用！不需要此参数时，请置为0）
	 * @return
	 */
	public boolean sortForModify1(@Param("table")String tableName, @Param("oldOrder")int oldOrder, @Param("newOrder")int newOrder, @Param("classId")int classId);
	
	/**
	 * 处理修改时，旧排序值比新排序值小时的情况
	 * @param tableName 操作数据库的表名
	 * @param oldOrder 旧的order值
	 * @param newOrder 新的order值（待修改的值）
	 * @param classId 对应的科研类别id（仅对修改科研字段时，此参数有用！不需要此参数时，请置为0）
	 * @return
	 */
	public boolean sortForModify2(@Param("table")String tableName, @Param("oldOrder")int oldOrder, @Param("newOrder")int newOrder, @Param("classId")int classId);

}
