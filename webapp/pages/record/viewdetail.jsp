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
    <title>查看个人科研信息列表</title>

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
<textarea id="recordJson" hidden><%=recordJson %></textarea>
<div class="row">
    <div class="col-lg-12">
        <h2 class="page-header">查看个人<label id="className"></label>科研详细信息</h2>
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
                <h4 id="statusDes" style="color: red">当前科研记录的状态为：</h4>
                <form action="#" method="post" enctype="multipart/form-data">
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
                                <h4 class="panel-title" style="float: left">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" id="a-person">相关人员信息</a>
                                </h4>
                                <div style="text-align: right">
                                    <button type="button" id="addPerson" class="btn btn-default btn-sm" style="align-items: right;" onclick="addPerson()">增加人员</button>
                                </div>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse in">
                                <div class="panel-body" id="person">
                                    <!--
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
                                    </div>-->
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
                                <h4 class="panel-title" style="float: left">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" id="a-proof">旁证材料</a>
                                </h4>
                                <div style="text-align: right">
                                    <button type="button" id="addProof" class="btn btn-default btn-sm" style="align-items: right;">增加材料</button>
                                </div>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse in">
                                <div class="panel-body" id="proof">
                                    <!--
                                    <div class="form-group">
                                        <div class="line-25-per">
                                            <label>旁证材料上传</label>
                                            <input type="file" name="proof">
                                        </div>
                                        <div class="clear-left"></div>
                                    </div>
                                    -->
                                    <!-- /.form-group -->

                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /#collapseThree -->
                        </div>
                        <!-- /.panel panel-info -->

                    </div>
                    <!-- /#accordion -->

                    <input type="hidden" value="" name="status" id="status">
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-warning btn-margin" onclick="setStatus(0)">保存</button>
                        <button type="submit" class="btn btn-danger btn-margin" onclick="setStatus(1)">提交审核</button>
                        <button type="reset" class="btn btn-info btn-margin">重置</button>
                    </div>
                </form>
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
    var operatorInfo = '； 您可以对当前信息进行修改！';
    if(status==1 || status==2)
        operatorInfo = '； 您不可以对当前信息进行修改！';

    var classRemark = rClass.classRemark;
    $('#className').append(rClass.className);      //设置标题头
    $('#recordId').val(record.id);     //设置recordId的值
    $('#statusDes').append(record.statusDes + operatorInfo);       //设置科研记录状态描述信息
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

        $('#person').append('<div class="form-group"><div class="line-25-per"><label>相关成员姓名</label><input class="form-control" name="pName0" value="'+name+'"> <p class="help-block text-info">可选填</p> </div> <div class="line-25-per"> <label>备注</label> <input class="form-control" name="pRemark0" value="'+remarks+'"><p class="help-block text-info">可选填</p></div><div class="line-25-per"> <label>排名</label> <input class="form-control" name="pOrder0" value="'+order+'"> <p class="help-block text-info">可选填</p> </div>\<div class="clear-left"></div> </div>');
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

        $('#proof').append('<div class="form-group"><div class="line-25-per"><label>'+pName+'</label><br><a href="'+pPath+'">点击下载旁证材料</a></div><div class="clear-left"></div></div>');
    }

    if(status==1 || status==2) {        //科研记录状态为‘待审核’ 或 ‘审核通过’时，记录处于不可编辑的状态
        $('input').attr("disabled", true);
        $('button').attr("disabled", true);
    }

</script>
</body>
</html>
