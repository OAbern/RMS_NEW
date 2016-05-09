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
        <h2 class="page-header">查询统计<label id="className"></label>科研信息</h2>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- Nav tabs -->
<ul class="nav nav-tabs">
    <li class="active"><a href="#table-panel" data-toggle="tab">记录数据</a>
    </li>
    <li><a href="#flot-panel" data-toggle="tab">状态统计</a>
    </li>
</ul>
<br>
<div class="tab-content">
    <div class="row tab-pane fade in active" id="table-panel">
        <div class="col-lg-6">
            <div class="panel panel-yellow">
                <div class="panel-heading">
                    点击查看详细
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="alert alert-info" id="hidden-column">
                        点击相应的字段名你可以 <b>隐藏/显示</b> 相应的列:<br><br>
                        <a data-column="0" href="javascript:void(0)" onclick="hiddenColumn(this)">No.</a>
                        <->
                        <a data-column="1" href="javascript:void(0)" onclick="hiddenColumn(this)">操作</a>
                        <->
                        <a data-column="2" href="javascript:void(0)" onclick="hiddenColumn(this)">记录id</a>
                        <->
                        <a data-column="3" href="javascript:void(0)" onclick="hiddenColumn(this)">记录状态</a>
                        <->
                        <a data-column="4" href="javascript:void(0)" onclick="hiddenColumn(this)">提交时间</a>
                        <->
                        <a data-column="5" href="javascript:void(0)" onclick="hiddenColumn(this)">提交人</a>
                        <->
                        <a data-column="6" href="javascript:void(0)" onclick="hiddenColumn(this)">审批人</a>
                    </div>
                    <div class="table-responsive" style="overflow-x: hidden;">
                        <table class="table table-striped table-bordered table-hover" id="dataTables-record-list">
                            <thead>
                            <tr id="table-title">
                                <th>No.</th>
                                <th>操作</th>
                                <th>记录id</th>
                                <th>记录状态</th>
                                <th>提交时间</th>
                                <th>提交人</th>
                                <th>审批人</th>
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
    <!-- /#table-panel -->

    <div class="row col-lg-6 tab-pane fade" id="flot-panel">
        <div class="panel panel-primary">
            <div class="panel-heading">
                系统中当前科研类别下记录状态统计
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="flot-chart">
                    <div class="flot-chart-content" id="flot-pie-chart" style="width:400px;height:400px;" align="center"></div>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-6 #flot-panel -->
</div>
<!-- /.tab-content -->

<!-- jQuery -->
<script src="js/jquery-1.12.3/jquery-1.12.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap-3.3.5/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="js/SB-admin-2-1.0.8/bower_components/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/SB-admin-2-1.0.8/sb-admin-2.js"></script>

<!-- DataTables JavaScript -->
<script src="js/SB-admin-2-1.0.8/bower_components/datatables/media/jquery.dataTables.min.js"></script>
<script src="js/SB-admin-2-1.0.8/bower_components/datatables-plugins/integration/bootstrap3/dataTables.bootstrap.min.js"></script>

<!-- Flot Charts JavaScript -->
<%--<script src="js/SB-admin-2-1.0.8/bower_components/flot/excanvas.min.js"></script>--%>
<script src="js/SB-admin-2-1.0.8/bower_components/flot/jquery.flot.js"></script>
<script src="js/SB-admin-2-1.0.8/bower_components/flot/jquery.flot.pie.js"></script>
<%--<script src="js/SB-admin-2-1.0.8/bower_components/flot/jquery.flot.resize.js"></script>--%>
<%--<script src="js/SB-admin-2-1.0.8/bower_components/flot/jquery.flot.time.js"></script>--%>
<script src="js/SB-admin-2-1.0.8/bower_components/flot.tooltip/jquery.flot.tooltip.min.js"></script>

<script>
    var json = $('#recordJson').val();
    var recordList = JSON.parse(json);
    var classId;
    if(recordList!=null && recordList.length!=0) {
        var className = recordList[0].researchClass.className;
        $('#className').append(className);
        classId = recordList[0].researchClass.classId;

        var fieldList = recordList[0].fields;
        for(var i=0; i<fieldList.length; i++) {
            var data = fieldList[i];
            $('#table-title').append('<th>'+data.field.description+'</th>');
            var columnNum = i+7;
            $('#hidden-column').append('<-> <a data-column="'+columnNum+'" href="javascript:void(0)" onclick="hiddenColumn(this)"> '+data.field.description+'</a> ');
        }
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

        var innerHtmlCode = '<tr><td>'+i+'</td><td><a href="'+detailURL+'">查看详细</a></td><td>'+r.id+'</td><td>'+r.statusDes+'</td><td>'+r.submitTimeString+'</td><td>'+submitUserName+'</td><td>'+approveUserName+'</td>';

        var dataList = r.fields;
        for(var j=0; j<dataList.length; j++) {
            var data = dataList[j];
            var value = data.value;
            if(typeof(value) == "undefined") {
                value = '';
            }
            innerHtmlCode += '<td>'+value+'</td>';
        }
        innerHtmlCode += '</tr>';
        $('#record').append(innerHtmlCode);
    }

    //加载dataTables
    var table = $('#dataTables-record-list').DataTable({
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
        "responsive": true,
        "sScrollX": "100%",
        "sScrollXInner": "110%",
        "bScrollCollapse": true
    });

    $.ajax({       //后台获取统计状态的数据
        url: "pubrecord/statistics/status/"+classId+".do",
        type: "get",
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        success:function(data){
            //画饼图
            var plotObj = $.plot($("#flot-pie-chart"), data, {
                series: {
                    pie: {
                        show: true,
                        label: {
                            show: true,
                            radius: 180,
                            formatter: function (label, series) {
                                return '<div style="border:1px solid grey;font-size:8pt;text-align:center;padding:5px;color:white;background-color:black;opacity:0.7;">' + label + ' : '+series.data[0][1]+'条</div>';      //Math.round(series.percent) + '%'百分比
                            }
                        }
                    }
                },
                grid: {
                    hoverable: true
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
                    shifts: {
                        x: 20,
                        y: 0
                    },
                    defaultTheme: false
                }
            });
        },
        error:function() {
        }
    });

    window.parent.iFrameHeight();   //iframe自适应高度，最后执行

    //隐藏相应的列
    $('#hidden-column a').on('click', function (e) {
        e.preventDefault();

        // Get the column API object
        var column = table.column( $(this).attr('data-column') );
        if(column.visible()) {
            $(this).attr("style", "color:red; font-weight:bold; font-style:italic;");
        }else {
            $(this).removeAttr("style");
        }
        // Toggle the visibility
        column.visible( ! column.visible() );
    } );


//    var data = [{
//        label: "Series 0",
//        data: 1
//    }, {
//        label: "Series 1",
//        data: 3
//    }, {
//        label: "Series 2",
//        data: 9
//    }, {
//        label: "Series 3",
//        data: 20
//    }];


</script>
</body>
</html>
