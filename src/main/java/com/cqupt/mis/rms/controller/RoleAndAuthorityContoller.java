package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.dao.CQUPTRoleDao;
import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.service.CQUPTRoleService;
import com.cqupt.mis.rms.utils.JSONUtils;
import com.cqupt.mis.rms.utils.RequestConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色权限相关的控制器
 * Created by Bern on 2016/5/4.
 */
@Controller
@RequestMapping("/roleandauth")
public class RoleAndAuthorityContoller {
    @Resource
    CQUPTRoleService CQUPTRoleServiceImpl;

    @Resource
    CQUPTRoleDao CQUPTRoleDao;

    /**
     * 获取所有的角色信息列表
     * @param response HttpServletResponse
     */
    @RequestMapping("/getrole")
    public void getRoleList(HttpServletResponse response) {
        List<CQUPTRole> roleList = CQUPTRoleServiceImpl.findAll();
        JSONUtils.toJSON(roleList, response);
    }

    /**
     * 添加角色信息
     * @param role 待添加的角色
     * @return 如果成功重定向到角色权限管理页面；否则定向到结果页面
     */
    @RequestMapping("/addrole")
    public ModelAndView addRole(CQUPTRole role) {
        ResultInfo<Object> result = CQUPTRoleServiceImpl.add(role);
        if(result.isResult()) {
            return new ModelAndView("redirect:/pages/system/roleandauthority.html");
        }else {
            return new ModelAndView("result.jsp", RequestConstant.RESULT, result);
        }
    }

    /**
     * 角色名字重名检测
     * @param roleName 待检测的角色名字
     * @param response HttpServletResponse response
     */
    @RequestMapping("/checkrolename")
    public void checkRoleName(@RequestParam(value="roleName", required=true)String roleName, HttpServletResponse response) {
        CQUPTRole role = CQUPTRoleDao.findByName(roleName);
        if(role == null) {
            JSONUtils.toJSON(true, response);
        }else {
            JSONUtils.toJSON(false, response);
        }
    }

    /**
     * 删除角色信息
     * @param roleId    角色id
     * @return 如果成功重定向到角色权限管理页面， 如果失败定向到结果页面
     */
    @RequestMapping("/deleterole/{roleId}")
    public ModelAndView deleteRole(@PathVariable("roleId")int roleId) {
        ResultInfo<Object> result = CQUPTRoleServiceImpl.deleteByRoleId(roleId);
        if(result.isResult()) {
            return new ModelAndView("redirect:/pages/system/roleandauthority.html");
        }else {
            return new ModelAndView("result.jsp", RequestConstant.RESULT, result);
        }
    }

    @RequestMapping("/modifyrole")
    public ModelAndView modifyRole(CQUPTRole role) {
        ResultInfo<Object> result = CQUPTRoleServiceImpl.modifyRole(role);
        if(result.isResult()) {
            return new ModelAndView("redirect:/pages/system/roleandauthority.html");
        }else {
            return new ModelAndView("result.jsp", RequestConstant.RESULT, result);
        }
    }
}
