package com.cqupt.mis.rms.controller;

import com.cqupt.mis.rms.dao.ResearchFieldDao;
import com.cqupt.mis.rms.model.*;
import com.cqupt.mis.rms.service.ResearchRecordService;
import com.cqupt.mis.rms.utils.GenerateUtils;
import com.cqupt.mis.rms.utils.RequestConstant;
import com.cqupt.mis.rms.utils.SessionConstant;
import com.cqupt.mis.rms.vo.ResultInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
 * 处理个人科研记录的controller
 * @author Bern
 *
 */
@Controller
@RequestMapping("/record")
public class PersonalResearchRecordController {

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
            request.setAttribute(RequestConstant.RESULT, new ResultInfo<Object>(false, "查询相应的字段异常！请稍后重试，或者联系管理员查看当前科研类别是否有字段！"));
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

        List<String> personNameList = new ArrayList<String>();      //相关人员的名字信息列表
        List<String> personRemarkList = new ArrayList<String>();        //相关人员的备注信息列表
        List<String> personOrderList = new ArrayList<String>();     //相关人员的排名情况信息列表
        Map<String, String> param = new HashMap<String, String>();      //参数列表
        for(FileItem fileItem : itemList){      //注意：同名的参数添加进入map的时候会被覆盖，所以同名参数应该单独处理！
            if(fileItem.isFormField()) {    //判断是否是表单数据
                String paraName = fileItem.getFieldName();
                String paraValue = fileItem.getString("utf-8");       //如果你页面编码是utf-8
                if(PERSON_NAME.equals(paraName)) {
                    personNameList.add(paraValue);
                }else if(PERSON_REMARKS.equals(paraName)){
                    personRemarkList.add(paraValue);
                }else if(PERSON_ORDER.equals(paraName)) {
                    personOrderList.add(paraValue);
                }else {
                    param.put(fileItem.getFieldName(), paraValue);
                }
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
        researchRecord.setPersons(parseResearchPersons(personNameList, personRemarkList, personOrderList));      //设置相关人员信息

        //持久化
        ResultInfo<Object> result  = researchRecordServiceImpl.add(researchRecord, proofs);

        return new ModelAndView("result.jsp", RequestConstant.RESULT, result);
    }

    /**
     * 修改科研记录
     * @param request HttpServletRequest
     * @return 定向到操作结果页面
     * @throws FileUploadException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/modify", method=RequestMethod.POST)
    public ModelAndView modifyRecord(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {
        //TODO： FileUploadException 处理
        //通过commons-fileupload的包获取表单数据
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> proofs = new ArrayList<FileItem>();
        List<FileItem> itemList = upload.parseRequest(request);

        List<String> personNameList = new ArrayList<String>();      //相关人员的名字信息列表
        List<String> personRemarkList = new ArrayList<String>();        //相关人员的备注信息列表
        List<String> personOrderList = new ArrayList<String>();     //相关人员的排名情况信息列表
        List<Integer> fixedProofIds = new ArrayList<Integer>();     //不变的旁证材料id列表
        Map<String, String> param = new HashMap<String, String>();      //参数列表
        for(FileItem fileItem : itemList){      //注意：同名的参数添加进入map的时候会被覆盖，所以同名参数应该单独处理！
            if(fileItem.isFormField()) {    //判断是否是表单数据
                String paraName = fileItem.getFieldName();
                String paraValue = fileItem.getString("utf-8");       //如果你页面编码是utf-8
                if(FIXED_PROOF.equals(paraName)) {      //同名参数“fixedProof[]”处理，判断是否是“fixedProof[]”的数组
                    fixedProofIds.add(Integer.parseInt(paraValue));       //TODO: NumberFormatException
                }else if(PERSON_NAME.equals(paraName)) {
                    personNameList.add(paraValue);
                }else if(PERSON_REMARKS.equals(paraName)){
                    personRemarkList.add(paraValue);
                }else if(PERSON_ORDER.equals(paraName)) {
                    personOrderList.add(paraValue);
                }else {
                    param.put(paraName, fileItem.getString("utf-8"));//如果你页面编码是utf-8的
                }
            }else {
                proofs.add(fileItem);
            }
        }

        int classId = Integer.parseInt(param.get(CLASSID));     //TODO: NumberFormatException
        List<ResearchField> fieldList = researchFieldDao.findByClassId(classId);

        //拼装ResearchRecord对象
        ResearchRecord researchRecord = new ResearchRecord();

        //设置记录id
        researchRecord.setId(param.get(RECORDID));

        researchRecord.setStatus(Integer.parseInt(param.get(STATUS)));     //设置状态码  权限？判断<2???  //TODO: NumberFormatException

        //解析科研记录动态字段的值
        researchRecord.setFields(parseResearchDatas(param, fieldList));      //设置科研记录动态字段的值

        //解析相关人员信息
        researchRecord.setPersons(parseResearchPersons(personNameList, personRemarkList, personOrderList));      //设置相关人员信息

        //持久化
        ResultInfo<Object> result = researchRecordServiceImpl.modify(researchRecord, proofs, fixedProofIds);

        return new ModelAndView("result.jsp", RequestConstant.RESULT, result);
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
     *  查看个人提交科研信息详细
     * @param recordId 科研信息id
     * @return 定向到查看个人科研信息详细界面
     */
    @RequestMapping("/viewdetail/{recordId}")
    public ModelAndView viewRecordDetail(@PathVariable("recordId")String recordId) {
        ResearchRecord record = researchRecordServiceImpl.findOneById(recordId);
        return new ModelAndView("pages/record/viewdetail.jsp", RequestConstant.RECORD_DETAIL, record);
    }

    /**
     * 删除科研记录
     * @param recordId 科研信息id
     * @return 如果成功，定向到管理个人科研信息界面；如果失败，定向到结果页面
     */
    @RequestMapping("/delete/{recordId}")
    public ModelAndView deleteRecord(@PathVariable("recordId")String recordId) {
        ResultInfo<Integer> resultInfo = researchRecordServiceImpl.deleteById(recordId);
        if(resultInfo.isResult()) {
            int classId = resultInfo.getObject();
            return new ModelAndView("record/viewrecordlist/"+classId+".do");
        }else {
            return new ModelAndView("result.jsp", RequestConstant.RESULT, resultInfo);
        }
    }

    /**
     * 解析相关人员的信息
     * @param pNameList 成员名字的值列表
     * @param pRemarkList 成员备注信息的值列表
     * @param pOrderList 成员排名情况的值列表
     * @return List<ResearchPerson> 相关人员列表
     */
    private List<ResearchPerson> parseResearchPersons(List<String> pNameList, List<String> pRemarkList, List<String> pOrderList) {
        if(!(pNameList.size()==pRemarkList.size() && pRemarkList.size()==pOrderList.size()))        //判断是否三个List长度相等
            return null;

        List<ResearchPerson> personList = new ArrayList<ResearchPerson>();

        for(int i=0; i<pNameList.size(); i++) {         //三个值列表的size应该是一样的
            ResearchPerson person = new ResearchPerson();
            String pName = pNameList.get(i);
            String pRemark = pRemarkList.get(i);
            String orderStr = pOrderList.get(i);

            if(pName!=null && !pName.equals("")) {      //如果没有姓名，后面的字段无意义
                person.setName(pName);
            }else {
                continue;
            }

            if(pRemark!=null && !pRemark.equals(""))
                person.setRemarks(pRemark);

            if(orderStr!=null && !orderStr.equals("")) {

                person.setOrder(orderStr);
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
     * 修改记录时的recordId参数名
     */
    private static final String RECORDID = "recordId";

    /**
     * 状态码的参数名
     */
    private static final String STATUS = "status";

    /**
     * 成员姓名的参数名
     */
    private static final String PERSON_NAME = "pName[]";

    /**
     * 成员备注的参数名
     */
    private static final String PERSON_REMARKS = "pRemark[]";

    /**
     * 成员排名的参数名
     */
    private static final String PERSON_ORDER = "pOrder[]";

    /**
     * 不变的旁证材料编号的参数名
     */
    private static final String FIXED_PROOF = "fixedProof[]";

    /**
     * 不变的旁证材料计数
     */
    private static final String FIXED_PROOF_COUNT = "fixedProofCount";

}
