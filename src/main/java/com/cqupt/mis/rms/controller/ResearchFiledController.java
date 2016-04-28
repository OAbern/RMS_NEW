package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.dao.ResearchFiledDao;
import com.cqupt.mis.rms.model.ResearchFiled;
import com.cqupt.mis.rms.service.ResearchFiledService;
import com.cqupt.mis.rms.utils.JSONUtils;
import com.cqupt.mis.rms.utils.RequestConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 处理科研动态段的控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/filed")
public class ResearchFiledController {

    @Resource
    ResearchFiledService researchFiledService;

    @Resource
    ResearchFiledDao researchFiledDao;

    //TODO: 测试用
    @RequestMapping("/find/{classId}")
    public void findByClassIdForTest(@PathVariable int classId, HttpServletResponse response) {
        List<ResearchFiled> filedList = researchFiledDao.findByClassId(classId);
        JSONUtils.toJSON(filedList, response);
    }

    /**
     * 统计指定科研项目的字段数
     * @param classId   科研项目的classId
     * @param response HttpServletResponse
     */
    @RequestMapping("/count")
    public void countFiled(int classId, HttpServletResponse response) {
        Object result = researchFiledDao.countByClassId(classId);
        if(result == null) {
            result = 0;
        }
        JSONUtils.toJSON(result, response);
    }

    /**
     * 检测数据库字段名是否有重名
     * @param name  待检测的字段名
     * @param classId 字段所属类别
     * @param response HttpServletResponse
     */
    @RequestMapping("/checkNameBeforeAdd")
    public void checkNameBeforeAdd(String name, int classId, HttpServletResponse response) {
        ResearchFiled researchFiled = researchFiledDao.checkNameBeforeAdd(classId, name);
        if(researchFiled == null) {
            JSONUtils.toJSON(true, response);
        }else {
            JSONUtils.toJSON(false, response);
        }
    }

    /**
     * 检测字段展示名是否有重复
     * @param description 待检测的字段展示名
     * @param classId 字段所属类别
     * @param response HttpServletResponse
     */
    @RequestMapping("/checkDesBeforeAdd")
    public void checkDesBeforeAdd(String description, int classId, HttpServletResponse response) {
        ResearchFiled researchFiled = researchFiledDao.checkDesBeforeAdd(classId, description);
        if(researchFiled == null) {
            JSONUtils.toJSON(true, response);
        }else {
            JSONUtils.toJSON(false, response);
        }
    }

    /**
     * 添加字段
     * @param researchFiled 待添加的科研字段
     * @param request HttpServletResponse
     * @return 操作结果
     */
    @RequestMapping("/add")
    public ModelAndView add(ResearchFiled researchFiled, HttpServletRequest request) {
        ResultInfo<Object> result = researchFiledService.addFiled(researchFiled);
        request.setAttribute(RequestConstant.RESULT, result);
        return new ModelAndView("result.jsp");
    }

}
