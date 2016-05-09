<%@ page import="com.cqupt.mis.rms.utils.RequestConstant" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cqupt.mis.rms.model.ResearchRecord" %>
<%@ page import="com.cqupt.mis.rms.utils.JSONUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: Bern
  Date: 2016/5/1
  Time: 13:49
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
    <title>查看科研信息列表</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap-3.3.5/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/SB-admin-2-1.0.8/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="css/SB-admin-2-1.0.8/bower_components/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- DataTables CSS -->
    <link href="css/SB-admin-2-1.0.8/bower_components/datatables-plugins/integration/bootstrap3/dataTables.bootstrap.css" rel="stylesheet">
    <!-- DataTables Responsive CSS -->
    <link href="css/SB-admin-2-1.0.8/bower_components/datatables-responsive/responsive.dataTables.scss" rel="stylesheet">
    <!-- Bern Custom CSS -->
    <link href="css/ifram-common.css" rel="stylesheet">

</head>

<body>
<%
    List<ResearchRecord> recordList = (List<ResearchRecord>) request.getAttribute(RequestConstant.RECORD_LIST);
    String recordListJson = JSONUtils.toJSONString(recordList);
%>
<textarea id="recordJson" hidden><%=recordListJson %></textarea>

<div class="row">
    <div class="col-lg-12">
        <h2 class="page-header">审核<label id="className"></label>科研信息</h2>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-red">
            <div class="panel-heading">
                点击查看详细
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive" style="overflow-x: hidden;">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-record-list">
                        <thead>
                        <tr>
                            <th>No.</th>
                            <th>记录id</th>
                            <th>记录状态</th>
                            <th>提交时间</th>
                            <th>提交人</th>
                            <th>审批人</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="record">
                        <!--
                        <tr>
                            <td>1</td>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                            <td>@mdo</td>
                            <td>@mdo</td>
                            <td>@mdo</td>
                            <td>@mdo</td>
                        </tr>
                        -->
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-6 -->
</div>
<!-- /.row -->

<!-- jQuery -->
<script src="js/jquery-1.12.3/jquery-1.12.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap-3.3.5/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/SB-admin-2-1.0.8/sb-admin-2.js"></script>

<!-- DataTables JavaScript -->
<script src="js/SB-admin-2-1.0.8/bower_components/datatables/media/jquery.dataTables.min.js"></script>
<script src="js/SB-admin-2-1.0.8/bower_components/datatables-plugins/integration/bootstrap3/dataTables.bootstrap.min.js"></script>

<script>
    var json = $('#recordJson').val();
    var recordList = JSON.parse(json);
    if(recordList!=null && recordList.length!=0) {
        var className = recordList[0].researchClass.className;
        $('#className').append(className);
    }

    //添加科研记录
    for(var i=0; i<recordList.length;) {
        var r = recordList[i++];
        var detailURL = 'pubrecord/viewdetail/'+ r.id+'.do';
        var approveUserName, submitUserName;
        if(r.approvedUser != null) {
            approveUserName = r.approvedUser.userName;
        }else {
            approveUserName = '';
        }

        if(r.submitUser != null) {
            submitUserName = r.submitUser.userName;
        }else {
            submitUserName = '';
        }

        if(r.returnReason == null) {
            r.returnReason = '';
        }

        $('#record').append('<tr><td>'+i+'</td><td>'+r.id+'</td><td>'+r.statusDes+'</td><td>'+r.submitTimeString+'</td><td>'+submitUserName+'</td><td>'+approveUserName+'</td><td><a href="'+detailURL+'">查看详细</a></td></tr>');

    }

    //加载dataTables
    $('#dataTables-record-list').DataTable({
        "language": {       //配置dataTables的提示信息为中文
            "sProcessing":   "处理中...",
            "sLengthMenu":   "显示 _MENU_ 项结果",
            "sZeroRecords":  "没有匹配结果",
            "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix":  "",
            "sSearch":       "搜索:",
            "sUrl":          "",
            "sEmptyTable":     "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands":  ",",
            "oPaginate": {
                "sFirst":    "首页",
                "sPrevious": "上页",
                "sNext":     "下页",
                "sLast":     "末页"
            },
            "oAria": {
                "sSortAscending":  ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        "responsive": true
    });

    window.parent.iFrameHeight();   //iframe自适应高度，最后执行
</script>
</body>
</html>
