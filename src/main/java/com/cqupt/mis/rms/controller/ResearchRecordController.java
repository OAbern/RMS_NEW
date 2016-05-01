package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.dao.ResearchFieldDao;
import com.cqupt.mis.rms.model.*;
import com.cqupt.mis.rms.service.ResearchRecordService;
import com.cqupt.mis.rms.utils.GenerateUtils;
import com.cqupt.mis.rms.utils.RequestConstant;
import com.cqupt.mis.rms.utils.SessionConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

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
    ResearchFieldDao researchFieldDao;

    /**
     * 加载录入记录页面
     * @param classId 记录类别
     * @param request HttpServletRequest
     * @return 定向到录入页面
     */
    @RequestMapping("/input/{classId}")
    public ModelAndView input(@PathVariable int classId, HttpServletRequest request) {
        List<ResearchField> fieldList = researchFieldDao.findByClassId(classId);
        if(fieldList==null || fieldList.size()==0) {
            request.setAttribute(RequestConstant.RESULT, new ResultInfo<Object>(false, "查询相应的字段异常！请稍后重试，或者联系管理查看当前当前记录是否有字段！"));
            return new ModelAndView("result.jsp");
        }

        request.setAttribute(RequestConstant.ALL_FIELD_LIST, fieldList);
        return new ModelAndView("pages/record/inputrecord.jsp");
    }

    /**
     * 录入一条记录
     * @param request HttpServletRequest request
     * @return 定向到录入结果页面
     */
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView inputRecord(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {
        //TODO： FileUploadException 处理
        //通过commons-fileupload的包获取表单数据
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> proofs = new ArrayList<FileItem>();
        List<FileItem> itemList = upload.parseRequest(request);
        Map<String, String> param = new HashMap<String, String>();
        for(FileItem fileItem : itemList){
            if(fileItem.isFormField()) {    //判断是否是表单数据
                param.put(fileItem.getFieldName(), fileItem.getString("utf-8"));//如果你页面编码是utf-8的
            }else {
                proofs.add(fileItem);
            }
        }

        int classId = Integer.parseInt(param.get(CLASSID));     //TODO: NumberFormatException
        List<ResearchField> fieldList = researchFieldDao.findByClassId(classId);

        //拼装ResearchRecord对象
        ResearchRecord researchRecord = new ResearchRecord();

        //设置唯一的记录id
        researchRecord.setId(GenerateUtils.getID());

        //设置提交用户
        CQUPTUser user = new CQUPTUser();
        user.setUserId((String)request.getSession().getAttribute(SessionConstant.USERID));
        researchRecord.setSubmitUser(user);

        ResearchClass researchClass = new ResearchClass();
        researchClass.setClassId(classId);      //设置类别ID
        researchRecord.setResearchClass(researchClass);

        researchRecord.setStatus(Integer.parseInt(param.get(STATUS)));     //设置状态码  权限？判断<2???  //TODO: NumberFormatException

        //解析科研记录动态字段的值
        researchRecord.setFields(parseResearchDatas(param, fieldList));      //设置科研记录动态字段的值

        //解析相关人员信息
        int count = (param.size()-fieldList.size()-2)/3;
        researchRecord.setPersons(parseResearchPersons(param, count));      //设置相关人员信息

        //持久化
        ResultInfo<Object> result  = researchRecordServiceImpl.add(researchRecord, proofs);

        request.setAttribute(RequestConstant.RESULT, result);
//        request.setAttribute(RequestConstant.RESULT, null);
        return new ModelAndView("result.jsp");
    }

    /**
     * 查看个人提交科研信息列表
     * @param classId 科研信息类别
     * @param request HttpServletRequest
     * @return 定向到管理个人科研信息界面
     */
    @RequestMapping("/viewrecordlist/{classId}")
    public ModelAndView viewPersonalRecordList(@PathVariable("classId")int classId, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(SessionConstant.USERID);
        List<ResearchRecord> recordList = researchRecordServiceImpl.findSimpleListByUserAndClass(userId, classId);
        return new ModelAndView("pages/record/viewrecordlist.jsp", RequestConstant.RECORD_LIST, recordList);
    }

    /**
     * 解析相关人员的信息
     * @param param form-data解析出来的参数Map
     * @param count 相关人员组数
     * @return List<ResearchPerson> 相关人员列表
     */
    private List<ResearchPerson> parseResearchPersons(Map<String, String> param, int count) {
        List<ResearchPerson> personList = new ArrayList<ResearchPerson>();
        for(int i=0; i<count; i++) {
            ResearchPerson person = new ResearchPerson();
            String pName = param.get(PERSON_NAME+i);
            String pRemark = param.get(PERSON_REMARKS+i);
            String orderStr = param.get(PERSON_ORDER+i);

            if(pName!=null && !pName.equals("")) {      //如果没有姓名，后面的字段无意义
                person.setName(pName);
            }else {
                continue;
            }

            if(pRemark!=null && !pRemark.equals(""))
                person.setRemarks(pRemark);

            if(orderStr!=null && !orderStr.equals("")) {

                person.setOrder(Integer.parseInt(orderStr));        //TODO: NumberFormatException
            }

            personList.add(person);
        }
        return personList;
    }

    /**
     * 解析出动态字段
     * @param param form-data解析出来的参数Map
     * @param fieldList 动态字段列表
     * @return Set<ResearchData> 返回动态字段和字段的值
     */
    private Set<ResearchData> parseResearchDatas(Map<String, String> param, List<ResearchField> fieldList) {
        Set<ResearchData> dataSet = new HashSet<ResearchData>();
        for(ResearchField field : fieldList) {
            String value = param.get(field.getName());
            if(value!=null && !value.equals("")) {
                ResearchData researchData = new ResearchData();
                researchData.setField(field);
                researchData.setValue(value);
                dataSet.add(researchData);
            }
        }
        return dataSet;
    }

    /**
     * classId 所属类别id的参数名
     */
    private static final String CLASSID = "classId";

    /**
     * 状态码的参数名
     */
    private static final String STATUS = "status";

    /**
     * 成员姓名的参数名
     */
    private static final String PERSON_NAME = "pName";

    /**
     * 成员备注的参数名
     */
    private static final String PERSON_REMARKS = "pRemark";

    /**
     * 成员排名的参数名
     */
    private static final String PERSON_ORDER = "pOrder";

}
