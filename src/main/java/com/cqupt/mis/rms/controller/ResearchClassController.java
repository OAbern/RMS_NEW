package com.cqupt.mis.rms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqupt.mis.rms.model.ResearchClass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.mis.rms.service.ResearchClassService;
import com.cqupt.mis.rms.utils.JSONUtils;

/**
 * 处理科研类别的控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/class")
public class ResearchClassController {
	@Resource
	private ResearchClassService researchClassServiceImpl;

	/**
	 * 获取全部科研类别
	 * @param response
     */
	@RequestMapping("/get")
	public void get(HttpServletResponse response) {
		JSONUtils.toJSON(researchClassServiceImpl.findAll(), response);
		
	}

	/**
	 * 添加一个科研类别
	 */
	@RequestMapping("/add")
	public void add(ResearchClass researchClass, HttpServletRequest request) {
		researchClassServiceImpl.addClass(researchClass);
	}

	@RequestMapping("/modify")
	public void modify(ResearchClass researchClass) {
		researchClassServiceImpl.modifyClass(researchClass);
	}

	@RequestMapping("/delete")
	public void delete(ResearchClass researchClass) {
		researchClassServiceImpl.deleteClass(researchClass);
	}

}
