package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.model.CQUPTRole;
import com.cqupt.mis.rms.service.CQUPTRoleService;
import com.cqupt.mis.rms.utils.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/getrole")
    public void getRoleList(HttpServletResponse response) {
        List<CQUPTRole> roleList = CQUPTRoleServiceImpl.findAll();
        JSONUtils.toJSON(roleList, response);
    }
}
