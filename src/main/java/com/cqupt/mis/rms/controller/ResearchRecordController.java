package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.dao.ResearchFiledDao;
import com.cqupt.mis.rms.model.ResearchFiled;
import com.cqupt.mis.rms.model.ResearchRecord;
import com.cqupt.mis.rms.service.ResearchRecordService;
import com.cqupt.mis.rms.utils.RequestConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 处理科研信息的controller 
 * @author Bern
 *
 */
@Controller
@RequestMapping("/record")
public class ResearchRecordController {

    @Resource
    ResearchRecordService researchRecordServiceImpl;

    @Resource
    ResearchFiledDao researchFiledDao;

    /**
     * 加载录入记录页面
     * @param classId 记录类别
     * @param request HttpServletRequest
     * @return 定向到录入页面
     */
    @RequestMapping("/input/{classId}")
    public ModelAndView input(@PathVariable int classId, HttpServletRequest request) {
        List<ResearchFiled> filedList = researchFiledDao.findByClassId(classId);
        if(filedList==null || filedList.size()==0) {
            request.setAttribute(RequestConstant.RESULT, new ResultInfo<Object>(false, "查询相应的字段异常！请稍后重试，或者联系管理查看当前当前记录是否有字段！"));
            return new ModelAndView("result.jsp");
        }

        request.setAttribute(RequestConstant.ALL_FIELD_LIST, filedList);
        return new ModelAndView("pages/record/inputrecord.jsp");
    }

    /**
     * 录入一条记录
     * @param request
     * @return
     */
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView inputRecord(@RequestParam(value="record", required=false)ResearchRecord record, @RequestParam(value="proofs", required=false) MultipartFile[] proofs, HttpServletRequest request) {
        //TODO:
        System.out.println(record);
        System.out.println(request.getParameter("record.researchClass.classId"));
        System.out.println(request.getParameter(""));
        return null;
    }
}
