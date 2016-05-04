package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.dao.ResearchFieldDao;
import com.cqupt.mis.rms.model.ResearchRecord;
import com.cqupt.mis.rms.service.ResearchRecordService;
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
import java.util.List;

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
     * @return 定向到管理个人科研信息界面
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
     * @return 定向到查看个人科研信息详细界面
     */
    @RequestMapping("/viewdetail/{recordId}")
    public ModelAndView viewRecordDetail(@PathVariable("recordId")String recordId) {
        ResearchRecord record = researchRecordServiceImpl.findOneById(recordId);
        return new ModelAndView("pages/record/viewpubrecorddetail.jsp", RequestConstant.RECORD_DETAIL, record);
    }

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
}
