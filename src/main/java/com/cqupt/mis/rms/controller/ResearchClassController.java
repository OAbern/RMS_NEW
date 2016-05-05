package com.cqupt.mis.rms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqupt.mis.rms.dao.ResearchClassDao;
import com.cqupt.mis.rms.model.ResearchClass;
import com.cqupt.mis.rms.utils.RequestConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.mis.rms.service.ResearchClassService;
import com.cqupt.mis.rms.utils.JSONUtils;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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

	@Resource
	private ResearchClassDao researchClassDao;

	/**
	 * 获取全部科研类别
	 * @param response HttpServletResponse
     */
	@RequestMapping("/getAll")
	public void get(HttpServletResponse response) {
		JSONUtils.toJSON(researchClassServiceImpl.findAll(), response);
		
	}

	/**
	 * 添加一个科研类别
	 */
	@RequestMapping("/add")
	public ModelAndView add(ResearchClass researchClass, HttpServletRequest request) {
		ResultInfo<Object> result = researchClassServiceImpl.addClass(researchClass);
		request.setAttribute(RequestConstant.RESULT, result);
		return new ModelAndView("result.jsp");
	}

	/**
	 * 添加科研类别之前的检查
	 * @param className 待检查的科研类别名称
	 * @param response HttpServletResponse
     */
	@RequestMapping("/checkBeforeAdd")
	public void checkBeforeAdd(String className, int pId, HttpServletResponse response) {
		try {
			className = URLDecoder.decode(className, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.getStackTrace();
			JSONUtils.toJSON(false, response);
		}
		ResearchClass classResult = researchClassDao.selectByNameAndPid(className, pId);
		if(classResult != null) {
			JSONUtils.toJSON(false, response);
		}else {
			JSONUtils.toJSON(true, response);
		}
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
