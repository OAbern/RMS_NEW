package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.dao.ResearchFieldDao;
import com.cqupt.mis.rms.model.ResearchRecord;
import com.cqupt.mis.rms.service.ResearchRecordService;
import com.cqupt.mis.rms.utils.JSONUtils;
import com.cqupt.mis.rms.utils.RequestConstant;
import com.cqupt.mis.rms.utils.SessionConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 科研管理员处理科研记录的控制器
 * Created by Bern on 2016/5/4.
 */
@Controller
@RequestMapping("/pubrecord")
public class PublicResearchRecordContorller {
    @Resource
    ResearchRecordService researchRecordServiceImpl;

    @Resource
    ResearchFieldDao researchFieldDao;

    /**
     * 查看科研信息列表
     * @param classId 科研信息类别
     * @param request HttpServletRequest
     * @return 定向到管理科研信息界面
     */
    @RequestMapping("/viewrecordlist/{classId}")
    public ModelAndView viewPersonalRecordList(@PathVariable("classId")int classId, HttpServletRequest request) {
        //TODO: 权限检查
        String userId = (String) request.getSession().getAttribute(SessionConstant.USERID);
        List<ResearchRecord> recordList = researchRecordServiceImpl.findListByClassForApprove(classId);
        return new ModelAndView("pages/record/viewpubrecordlist.jsp", RequestConstant.RECORD_LIST, recordList);
    }

    /**
     *  查看科研信息详细
     * @param recordId 科研信息id
     * @return 定向到查看科研信息详细界面
     */
    @RequestMapping("/viewdetail/{recordId}")
    public ModelAndView viewRecordDetail(@PathVariable("recordId")String recordId) {
        ResearchRecord record = researchRecordServiceImpl.findOneById(recordId);
        return new ModelAndView("pages/record/viewpubrecorddetail.jsp", RequestConstant.RECORD_DETAIL, record);
    }

    /**
     * 审批科研记录
     * @param recordId 科研记录id
     * @param status 科研记录状态信息
     * @param returnReason 科研记录拒绝审核的原因
     * @param request @see HttpServletRequest
     * @return 定向到结果页面
     */
    @RequestMapping("/approve/{recordId}")
    public ModelAndView approve(@PathVariable("recordId")String recordId, @RequestParam(value="s", required=true)int status, @RequestParam(value="r", required=false)String returnReason, HttpServletRequest request) {
        boolean result = false;
        String userId = (String) request.getSession().getAttribute(SessionConstant.USERID);
        ResearchRecord record = new ResearchRecord();
        record.setId(recordId);
        record.setStatus(status);
        if(status==3 && returnReason!=null && !"".equals(returnReason)) {        //审批拒绝
            record.setReturnReason(returnReason);
            result = researchRecordServiceImpl.refuse(record, userId);
        }else if(status == 2){     //审批通过
            result = researchRecordServiceImpl.accept(record, userId);
        }
        if(result) {
            return new ModelAndView("result.jsp", RequestConstant.RESULT, new ResultInfo<Object>(null, true));
        }else {
            return new ModelAndView("result.jsp", RequestConstant.RESULT, new ResultInfo<Object>(false, "审批操作异常！请稍后再试！或者联系管理员解决！"));
        }
    }

    /**
     * 查询统计相应科研记录下的
     * @param classId 待查询科研类别id
     * @return 定向到查看科研记录列表界面
     */
    @RequestMapping("/statistics/{classId}")
    public ModelAndView statistics(@PathVariable("classId")int classId) {
        List<ResearchRecord> recordList = researchRecordServiceImpl.findListByClassForStatistics(classId);
        return new ModelAndView("pages/record/statistics.jsp", RequestConstant.RECORD_LIST, recordList);
    }

    @RequestMapping("/statistics/status/{classId}")
    public void statisticsStatus(@PathVariable("classId")int classId, HttpServletResponse response) {
        List<Object> result = researchRecordServiceImpl.statisticsStauts(classId);
        JSONUtils.toJSON(result, response);
    }
}
