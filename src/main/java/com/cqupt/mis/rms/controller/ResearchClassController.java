package com.cqupt.mis.rms.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("/get")
	public void get(HttpServletResponse response) {
		try {
			response.getWriter().print(JSONUtils.toJSONString(researchClassServiceImpl.findAll()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
