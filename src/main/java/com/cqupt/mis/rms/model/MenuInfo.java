/**
 * 
 */
package com.cqupt.mis.rms.model;

import java.util.List;

import com.cqupt.mis.rms.model.Purviewinfo;

/**
 * <p>
 * Title:封装一级和二级菜单
 *
 * @author Welkin
 * */
public class MenuInfo {
	
	// 一级菜单
	private ResourceInfo first;
	// 二级菜单静态资源
	private List<ResourceInfo> second;
	// 二级菜单动态资源
	private List<ResearchClass> secondDyn;

	public List<ResearchClass> getSecondDyn() {
		return secondDyn;
	}
	public void setSecondDyn(List<ResearchClass> secondDyn) {
		this.secondDyn = secondDyn;
	}
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
