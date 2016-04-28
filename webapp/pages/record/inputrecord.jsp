<%@ page import="com.cqupt.mis.rms.utils.RequestConstant" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cqupt.mis.rms.model.ResearchFiled" %>
<%@ page import="com.cqupt.mis.rms.utils.JSONUtils" %><%--
  Created by IntelliJ IDEA.
  User: Bern
  Date: 2016/4/27
  Time: 16:30
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
    <title>录入科研信息</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap-3.3.5/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/SB-admin-2-1.0.8/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="css/SB-admin-2-1.0.8/bower_components/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">

    <style type="text/css">
        .line-25-per {
            padding: 10px;
            margin-left: 4%;
            margin-right: 4%;
            width: 25%;
            float: left;
        }

        .clear-left {
            clear: left;
        }

        .hr-double {
            size: 3px;
            border: 3px double blueviolet;
            width: 95%;
        }
    </style>

</head>
<body style="background-color: white">
<%
    List<ResearchFiled> filedList = (List<ResearchFiled>) request.getAttribute(RequestConstant.ALL_FIELD_LIST);
    String json = JSONUtils.toJSONString(filedList);
%>
<textarea id="filedsJson" hidden><%=json %></textarea>

<div class="row">
    <div class="col-lg-12">
        <h2 class="page-header">录入科研信息</h2>
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
                <form action="record/add.do" method="post" enctype="multipart/form-data" name="record" id="record">
                    <input type="hidden" name="record.researchClass.classId" id="classId">

                    <div class="panel-group" id="accordion">

                        <!-- 科研记录 -->
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">科研记录信息</a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <div class="panel-body" id="recordFiled">
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

                                    <div class="form-group">
                                        <div class="line-25-per">
                                            <label>相关成员姓名</label>
                                            <input class="form-control" name="record.persons[0].name">
                                            <p class="help-block text-info">可选填</p>
                                        </div>

                                        <div class="line-25-per">
                                            <label>备注</label>
                                            <input class="form-control" name="record.persons[0].remarks">
                                            <p class="help-block text-info">可选填</p>
                                        </div>

                                        <div class="line-25-per">
                                            <label>排名</label>
                                            <input class="form-control" name="record.persons[0].order">
                                            <p class="help-block text-info">可选填</p>
                                        </div>

                                        <div class="clear-left"></div>
                                    </div>
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
                                    <div class="form-group">
                                        <div class="line-25-per">
                                            <label>旁证材料上传</label>
                                            <input type="file" name="proofs">
                                        </div>
                                        <div class="clear-left"></div>
                                    </div>
                                    <!-- /.form-group -->
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /#collapseThree -->
                        </div>
                        <!-- /.panel panel-info -->

                    </div>
                    <!-- /#accordion -->

                    <div style="text-align: center">
                        <button type="submit" class="btn btn-danger">提交</button>
                        <button type="reset" class="btn btn-info">重置</button>
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

<script>
    //加载记录的动态字段
    var filedsList = JSON.parse($('#filedsJson').val());
    var classRemark = filedsList[0].researchClass.classRemark;
    $('#classId').val(filedsList[0].researchClass.classId);     //设置classId的值
    if(classRemark!=null && classRemark!="") {      //在面板头填写类备注
        $('#classRemark').empty();
        $('#classRemark').append(classRemark);
    }

    for(var i=0; i<filedsList.length;) {    //加载记录的动态字段
        var filed = filedsList[i];
        if(filed.isNull == 0) {     //不可以为null
            $('#recordFiled').append('<div class="line-25-per"><label class="text-danger">'+filed.description+'</label><input class="form-control" name="'+filed.name+'" required><p class="help-block text-info" id="tips1">提示</p> </div>');
        }else {         //不可以为null
            $('#recordFiled').append('<div class="line-25-per"><label class="text-danger">'+filed.description+'</label><input class="form-control" name="'+filed.name+'"><p class="help-block text-info" id="tips1">提示</p> </div>');
        }

        if(++i/3 == 0)      //换行
            $('#recordFiled').append('<div class="clear-left"></div>');
    }

    /**
     * 增加相关人员
     */
    $('#addPerson').click(function() {
        //$('#a-person').click();
        $('#person').append('<div class="form-group"><hr class="hr-double"><button type="button" class="btn btn-warning btn-xs" onclick="deleteParentEle(this)">删除</button><div class="clear-left"></div><div class="line-25-per"><label>相关成员姓名</label><input class="form-control" name="researchPerson.name"><p class="help-block text-info">可选填</p> </div><div class="line-25-per"><label>备注</label><input class="form-control" name="researchPerson.remark"> <p class="help-block text-info">可选填</p></div><div class="line-25-per"><label>排名</label><input class="form-control" name="researchPerson.order"> <p class="help-block text-info">可选填</p></div><div class="clear-left"></div></div><!-- /.form-group -->');
        window.parent.iFrameHeight();   //iframe自适应高度
    });

    /**
     * 增加材料
     */
    $('#addProof').click(function() {
        //$('#a-proof').click();
        $('#proof').append('<div class="form-group"><hr class="hr-double"><button type="button" class="btn btn-warning btn-xs" onclick="deleteParentEle(this)">删除</button><div class="clear-left"></div><div class="line-25-per"><label>旁证材料上传</label><input type="file" name="proof"> </div><div class="clear-left"></div></div><!-- /.form-group -->');
        window.parent.iFrameHeight();   //iframe自适应高度
    });

    /**
     * 删除相应的元素
     * @param ref
     */
    function deleteParentEle(ref) {
        ref.closest('.form-group').remove();
        window.parent.iFrameHeight();   //iframe自适应高度
    }

</script>
</body>
</html>