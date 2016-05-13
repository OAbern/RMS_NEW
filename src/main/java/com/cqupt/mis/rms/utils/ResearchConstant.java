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
	public static final String INPUT_URL = "../../record/input/";
	
	/**
	 * 管理个人科研信息的url
	 */
	public static final String MANAGE_URL = "../record/managerecord.html";

	/**
	 * 查看管理个人科研信息列表的url
	 */
	public static final String VIEW_MANAGE_LIST_URL = "../../record/viewrecordlist/";

	/**
	 * 查看管理科研信息列表的url
	 */
	public static final String VIEW_APPROVE_LIST_URL = "../../pubrecord/viewrecordlist/";
	
	/**
	 * 审批科研信息的url
	 */
	public static final String APPROVE_URL = "pages/record/approverecord.html";
	
	/**
	 * 查询统计科研信息的url
	 */
	public static final String STATISTICS_URL = "../../pubrecord/statistics/";

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

	public static final String STATUS_0 = "保存";
	public static final String STATUS_1 = "待审批";
	public static final String STATUS_2 = "审批通过";
	public static final String STATUS_3 = "审批拒绝";

	/**
	 * 旁证材料存储的根路径
	 * TODO: 写在XML里面
	 */
	public static final String PROOF_STORE_PATH_ROOT = "E:/RMS旁证材料";

	/**
	 * 重置密码
	 * TODO: 写在XML里面
	 */
	public static final String RESET_PW = "123456";

	/**
	 * 便于人阅读的时间格式
	 */
	public static final String DATE_FORMAT_PATTERN = "yyyy/MM/dd HH:mm";
	
	/**
	 * 获取录入科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getInputUrl(int classId) {
		return INPUT_URL + classId + SUFFIX;
	}
	
	/**
	 * 获取管理个人科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getManageUrl(int classId) {
		return MANAGE_URL + PARAM1 +classId;
	}

	/**
	 * 获取管理个人科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getViewManageListUrl(int classId) {
		return VIEW_MANAGE_LIST_URL + classId + SUFFIX;
	}

	/**
	 * 获取管理个人科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getViewApproveListUrl(int classId) {
		return VIEW_APPROVE_LIST_URL + classId + SUFFIX;
	}
	
	/**
	 * 获取审批科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getApproveUrl(int classId) {
		return APPROVE_URL + PARAM1 +classId;
	}
	
	/**
	 * 获取查询统计科研信息的url
	 * @param classId 相应的科研类别id，用作url的参数
	 * @return url
	 */
	public static String getStatisticsUrl(int classId) {
		return STATISTICS_URL + classId + SUFFIX;
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
