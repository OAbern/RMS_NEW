package com.cqupt.mis.rms.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Title:封装一级和二级菜单
 *
 * @author Welkin
 * */
public class MenuInfo implements Serializable {
	private static final long serialVersionUID = 6838692197212711550L;
	
	// 一级菜单
	private ResourceInfo first;
	// 二级菜单静态资源
	private List<ResourceInfo> second;

	public ResourceInfo getFirst() {
		return first;
	}
	public void setFirst(ResourceInfo first) {
		this.first = first;
	}
	public List<ResourceInfo> getSecond() {
		return second;
	}
	public void setSecond(List<ResourceInfo> second) {
		this.second = second;
	}
}
