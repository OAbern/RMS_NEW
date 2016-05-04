<%@ page import="com.cqupt.mis.rms.model.ResearchRecord" %>
<%@ page import="com.cqupt.mis.rms.utils.RequestConstant" %>
<%@ page import="com.cqupt.mis.rms.utils.JSONUtils" %><%--
  Created by IntelliJ IDEA.
  User: Bern
  Date: 2016/5/2
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <title>审核科研信息</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap-3.3.5/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/SB-admin-2-1.0.8/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="css/SB-admin-2-1.0.8/bower_components/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Bern Custom CSS -->
    <link href="css/ifram-common.css" rel="stylesheet">

</head>
<body>
<%
    ResearchRecord record = (ResearchRecord) request.getAttribute(RequestConstant.RECORD_DETAIL);
    String recordJson = JSONUtils.toJSONString(record);
%>
<!-- Modal -->
<div class="modal fade" id="accept" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title"><label class="text-danger">警告：</label>您正在执行一个严肃的操作！</h5>
            </div>
            <div class="modal-body">
                <p class="text-info">审批通过的记录将不能在系统中进行修改或审批！</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">再看看</button>
                <a id="acceptHref" href="javascript:void(0)" class="btn btn-danger btn-normal active" role="button">直接通过</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="refuse" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title"><label class="text-danger">警告：</label>您正在执行一个严肃的操作！</h5>
            </div>
            <form id="refuseAction" action="javascript:void(0)">
                <div class="modal-body">
                    <p class="text-info">审批拒绝的操作将返还给提交者进行其他处理！</p>
                    <label>拒绝原因</label>
                    <input class="form-control" name="r" required>
                    <input class="form-control" name="s" value="3" type="hidden">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">再看看</button>
                    <button type="submit" class="btn btn-danger">确认拒绝</button>
                </div>
            </form>
        </div>
    </div>
</div>

<textarea id="recordJson" hidden><%=recordJson %></textarea>
<div class="row">
    <div class="col-lg-12">
        <h2 class="page-header">审核<label id="className"></label>科研详细信息</h2>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <label id="classRemark">备注</label>
            </div>
            <!--  /.panel-heading  -->

            <div class="panel-body">
                <div style="text-align: center">
                    <a class="btn btn-warning btn-normal active btn-margin" href="javascript:void(0)" onclick="approve(2)">审核通过</a>
                    <a class="btn btn-danger btn-normal active btn-margin" href="javascript:void(0)" onclick="approve(3)">审核拒绝</a>
                </div>
                <br>

                <div class="alert alert-success">
                    <h4 id="recordIdShow" style="color: red">当前科研记录的ID为：</h4>
                </div>
                <div class="alert alert-success">
                    <h4 id="submitUser" style="color: red">当前科研记录的提交人为：</h4>
                </div>
                <div class="alert alert-warning">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4 id="statusDes" style="color: red">当前科研记录的状态为：</h4>
                </div>
                <div id="returnReasonDIV" class="alert alert-warning" hidden="hidden">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4 id="returnReason" style="color: red">当前科研记录的拒绝原因为：</h4>
                </div>

                <form id="formData">
                    <input type="hidden" name="recordId" id="recordId">

                    <div class="panel-group" id="accordion">

                        <!-- 科研记录 -->
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">科研记录信息</a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <div class="panel-body" id="recordField">
                                    <%-- 动态字段模板
                                    <div class="form-group">
                                        <div class="line-25-per">
                                            <label class="text-danger">*字段1</label>
                                            <input class="form-control" id="name1" name="name" required>
                                            <p class="help-block text-info" id="tips1">提示</p>
                                        </div>

                                        <div class="line-25-per">
                                            <label class="text-danger">*字段1</label>
                                            <input class="form-control" id="name2" name="name" required>
                                            <p class="help-block text-info" id="tips2">提示</p>
                                        </div>

                                        <div class="line-25-per">
                                            <label class="text-danger">*字段1</label>
                                            <input class="form-control" id="name3" name="name" required>
                                            <p class="help-block text-info" id="tips3">提示</p>
                                        </div>

                                        <div class="clear-left"></div>
                                    </div>
                                    <!-- /.form-group -->
                                    --%>
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /#collapseOne -->
                        </div>
                        <!-- /.panel panel-danger -->

                        <!-- 相关成员 -->
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" id="a-person">相关人员信息</a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse in">
                                <div class="panel-body" id="person">
                                    <%--
                                    <div class="form-group">
                                        <div class="line-25-per">
                                            <label>相关成员姓名</label>
                                            <input class="form-control" name="pName0">
                                            <p class="help-block text-info">可选填</p>
                                        </div>

                                        <div class="line-25-per">
                                            <label>备注</label>
                                            <input class="form-control" name="pRemark0">
                                            <p class="help-block text-info">可选填</p>
                                        </div>

                                        <div class="line-25-per">
                                            <label>排名</label>
                                            <input class="form-control" name="pOrder0">
                                            <p class="help-block text-info">可选填</p>
                                        </div>

                                        <div class="clear-left"></div>
                                    </div>
                                    --%>
                                    <!-- /.form-group -->

                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /#collapseTwo -->
                        </div>
                        <!-- /.panel panel-info -->

                        <!-- 旁证材料 -->
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" id="a-proof">旁证材料</a>
                                </h4>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse in">
                                <div class="panel-body" id="proof">
                                    <%--
                                    <div class="form-group">
                                        <div class="line-25-per">
                                            <label>旁证材料上传</label>
                                            <input type="file" name="proof">
                                        </div>
                                        <div class="clear-left"></div>
                                    </div>
                                    --%>
                                    <!-- /.form-group -->

                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /#collapseThree -->
                        </div>
                        <!-- /.panel panel-info -->
                    </div>
                    <!-- /#accordion -->
                </form>

                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4 style="color: red">审核按钮在页面顶部哦！</h4>
                </div>

            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.pane; .panel-default -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- jQuery -->
<script src="js/jquery-1.12.3/jquery-1.12.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap-3.3.5/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/SB-admin-2-1.0.8/sb-admin-2.js"></script>

<!-- Bern Custom JavaScript -->
<script src="js/record-CRUD.js"></script>

<script>
    var record = JSON.parse($('#recordJson').val());        //科研记录详细信息
    var fieldsList = record.fields;      //科研记录字段数据信息列表
    var rClass = record.researchClass;      //科研记录所属科研类别
    var personsList = record.persons;        //相关成员信息列表
    var proofsList = record.proofs;         //旁证材料信息列表
    var status = record.status;         //科研记录的审批状态
    var classRemark = rClass.classRemark;   //科研类别备注
    var returnReason = record.returnReason;     //审批拒绝原因

    $('#className').append(rClass.className);      //设置标题头
    $('#recordId').val(record.id);     //设置recordId的值
    $('#classId').val(rClass.classId);      //设置科研类别Id
    $('#statusDes').append(record.statusDes);       //设置科研记录状态描述信息
    $('#recordIdShow').append(record.id);       //设置记录id
    $('#submitUser').append(record.submitUser.userName);    //设置提交用户
    if(returnReason!=null && returnReason!="") {
        $('#returnReasonDIV').attr("hidden",false);
        $('#returnReason').append(returnReason);
    }

    if(classRemark!=null && classRemark!="") {      //在面板头填写类备注
        $('#classRemark').empty();
        $('#classRemark').append(classRemark);
    }

    //加载记录的动态字段
    for(var i=0; i<fieldsList.length;) {
        var fieldData = fieldsList[i];
        var field = fieldData.field;
        var value = fieldData.value;
        if(value == null)
            value = "";

        if(field.isNull == 0) {     //不可以为null
            $('#recordField').append('<div class="line-25-per"><label class="text-danger">'+field.description+'</label><input class="form-control" name="'+field.name+'" value="'+value+'" required><p class="help-block text-info" id="tips1">必填</p> </div>');
        }else {         //可以为null
            $('#recordField').append('<div class="line-25-per"><label>'+field.description+'</label><input class="form-control" name="'+field.name+'" value="'+value+'"><p class="help-block text-info" id="tips1">可选填</p> </div>');
        }

        if(++i/3 == 0)      //换行
            $('#recordField').append('<div class="clear-left"></div>');
    }

    //加载相关人员信息
    for(var i=0; i<personsList.length; i++) {
        var p = personsList[i];
        var name= p.name, order= p.order, remarks= p.remarks;

        if(name == null)
            name = "";

        if(order == null)
            order = "";

        if(remarks == null)
            remarks = "";

        $('#person').append('<div class="form-group"><hr class="hr-double"><div class="clear-left"></div><div class="line-25-per"><label>相关成员姓名</label><input class="form-control" name="pName[]" value="'+name+'"> <p class="help-block text-info">可选填</p> </div> <div class="line-25-per"> <label>备注</label> <input class="form-control" name="pRemark[]" value="'+remarks+'"><p class="help-block text-info">可选填</p></div><div class="line-25-per"> <label>排名情况</label> <input class="form-control" name="pOrder[]" value="'+order+'"> <p class="help-block text-info">可选填</p> </div>\<div class="clear-left"></div> </div>');
    }

    //加载旁证材料的数据
    for(var i=0; i<proofsList.length; i++) {
        var p = proofsList[i];
        var pPath = p.proofPath;
        var pName = p.uploadProofName;
        if(pPath == null)
            pPath = "javascript:void(0)";

        if(pName == null)
            pName = p.uploadRealName;

        $('#proof').append('<div class="form-group"><hr class="hr-double"><div class="clear-left"></div><div class="line-25-per"><label>'+pName+'</label><br><a href="'+pPath+'">点击下载旁证材料</a></div><input type="hidden" name="fixedProof[]" value="'+ p.proofId+'"><div class="clear-left"></div></div>');
    }

    //科研记录处于不可编辑的状态
    $('#formData').find('input').attr("disabled", true);
    $('#formData').find('button').attr("disabled", true);

    window.parent.iFrameHeight();   //iframe自适应高度，最后执行

    /**
     * 审核操作
     * @param status 状态
     */
    function approve(status) {
        if(status == 2) {       //审核通过
            $('#acceptHref').attr('href', 'pubrecord/approve/'+record.id+'.do?s='+status);
            $('#accept').modal({backdrop: 'static', keyboard: false});
        }else if(status == 3){      //审核拒绝
            $('#refuseAction').attr('action', 'pubrecord/approve/'+record.id+'.do');
            $('#refuse').modal({backdrop: 'static', keyboard: false});
        }
    }
</script>
</body>
</html>
