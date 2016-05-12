package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.dao.ResearchFieldDao;
import com.cqupt.mis.rms.model.ResearchField;
import com.cqupt.mis.rms.service.ResearchFieldService;
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
@RequestMapping("/field")
public class ResearchFieldController {

    @Resource
    ResearchFieldService researchFieldService;

    @Resource
    ResearchFieldDao researchFieldDao;

    @RequestMapping("/findbyclass/{classId}")
    public void findByClassId(@PathVariable int classId, HttpServletResponse response) {
        List<ResearchField> fieldList = researchFieldDao.findByClassId(classId);
        JSONUtils.toJSON(fieldList, response);
    }

    /**
     * 统计指定科研项目的字段数
     * @param classId   科研项目的classId
     * @param response HttpServletResponse
     */
    @RequestMapping("/count")
    public void countField(int classId, HttpServletResponse response) {
        Object result = researchFieldDao.countByClassId(classId);
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
        ResearchField researchField = researchFieldDao.checkNameBeforeAdd(classId, name);
        if(researchField == null) {
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
        ResearchField researchField = researchFieldDao.checkDesBeforeAdd(classId, description);
        if(researchField == null) {
            JSONUtils.toJSON(true, response);
        }else {
            JSONUtils.toJSON(false, response);
        }
    }

    /**
     * 添加字段
     * @param researchField 待添加的科研字段
     * @param request HttpServletResponse
     * @return 操作结果
     */
    @RequestMapping("/add")
    public ModelAndView add(ResearchField researchField, HttpServletRequest request) {
        ResultInfo<Object> result = researchFieldService.addField(researchField);
        request.setAttribute(RequestConstant.RESULT, result);
        return new ModelAndView("result.jsp");
    }

    /**
     * 根据字段ID删除字段
     * @param id 字段id
     * @return
     */
    @RequestMapping("/delete/{fieldId}")
    public ModelAndView deleteById(@PathVariable("fieldId")int id) {
        researchFieldService.deleteField(id);
        return new ModelAndView("redirect:/pages/system/managedynamicfield.html");
    }

    @RequestMapping("/modify")
    public ModelAndView modify(ResearchField field) {
        researchFieldService.modifyField(field);
        return  new ModelAndView("redirect:/pages/system/managedynamicfield.html");
    }
}
