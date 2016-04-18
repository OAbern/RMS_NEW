package com.cqupt.mis.rms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理科研信息的controller 
 * @author Bern
 *
 */
@Controller
@RequestMapping("/record")
public class ResearchRecordController {

    /**
     * 录入一条记录
     * @param request
     * @return
     */
    @RequestMapping("/input")
    public ModelAndView inputRecord(HttpServletRequest request) {

        return null;
    }
}
