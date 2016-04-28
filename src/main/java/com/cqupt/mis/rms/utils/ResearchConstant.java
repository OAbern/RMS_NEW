package com.cqupt.mis.rms.utils;

/**
 * 记录与科研信息相关的常量类
 * @author Bern
 *
 */
public class ResearchConstant {
	/**
	 * 录入科研信息的url
	 */
	public static final String INPUTURL = "../../record/input/";
	
	/**
	 * 管理个人科研信息的url
	 */
	public static final String MANAGEURL = "pages/record/managerecord.html";
	
	/**
	 * 审批科研信息的url
	 */
	public static final String APPROVEURL = "pages/record/approverecord.html";
	
	/**
	 * 查询统计科研信息的url
	 */
	public static final String STATISTICSURL = "pages/record/statisticsrecord.html";

	/**
	 * 科研字段数据库表名
	 */
	public static final String TABLE_R_FIELD = "research_field";
	
	/**
	 * url的第一参数的key
	 */
	public static final String PARAM1 = "?classId=";

	/**
	 * 访问尾缀
	 */
	public static final String SUFFIX = ".do";
	
	/**
	 * 获取录入科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getInputUrl(int classId) {
		return INPUTURL + classId + SUFFIX;
	}
	
	/**
	 * 获取管理个人科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getManageUrl(int classId) {
		return MANAGEURL + PARAM1 +classId;
	}
	
	/**
	 * 获取审批科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getApproveUrl(int classId) {
		return APPROVEURL + PARAM1 +classId;
	}
	
	/**
	 * 获取查询统计科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getStatisticsUrl(int classId) {
		return STATISTICSURL + PARAM1 + classId;
	}
	
	/**
	 * 获取录入科研信息的菜单名字
	 * @param className 相应的科研类别名字
	 * @return 菜单名
	 */
	public static String getInputName(String className) {
		return "录入" + className + "科研信息";
	}
	
	/**
	 * 获取管理个人科研信息的菜单名字
	 * @param className 相应的科研类别名字
	 * @return 菜单名
	 */
	public static String getManageName(String className) {
		return "管理个人的" + className + "科研信息";
	}
	
	/**
	 * 获取审批科研信息的菜单名字
	 * @param className 相应的科研类别名字
	 * @return 菜单名
	 */
	public static String getApproveName(String className) {
		return "审批" + className + "科研信息";
	}
	
	/**
	 * 获取查询统计科研信息的菜单名字
	 * @param className 相应的科研类别名字
	 * @return 菜单名
	 */
	public static String getStatisticsName(String className) {
		return "查询统计" + className + "科研信息";
	}
}
