<%@ page import="com.cqupt.mis.rms.utils.RequestConstant" %>
<%@ page import="com.cqupt.mis.rms.vo.ResultInfo" %><%--
  Created by IntelliJ IDEA.
  User: bern
  Date: 2016/4/24
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
<head>
    <title>操作结果显示页</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap-3.3.5/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/SB-admin-2-1.0.8/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="css/SB-admin-2-1.0.8/bower_components/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<body>
    <%
        ResultInfo<Object> result = (ResultInfo<Object>) request.getAttribute(RequestConstant.RESULT);
        if(result == null) {
            response.sendRedirect("/pages/common/index.html");
        }
    %>

    <input id="result" type="hidden" value="<%=result.isResult() %>">

    <div id="success" class="container-fluid" hidden="true">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header" style="color: green">操作成功</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->

    <div id="failed" class="container-fluid" hidden="true">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header" style="color: red">操作失败</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

        <div class="row">
            <p class="help-block" style="color: red" id="fRemark"><b><%=result.getFailedReason() %></b></p>
        </div>
    </div>
    <!-- /.container-fluid -->

    <!-- jQuery -->
    <script src="js/jquery-1.12.3/jquery-1.12.3.min.js" type="javascript"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap-3.3.5/bootstrap.min.js" type="javascript"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/SB-admin-2-1.0.8/sb-admin-2.js" type="javascript"></script>

    <script type="javascript">
        //根据结果展示相应的结果面板
        alert(1);
        var result = JSON.parse($('#result').val());
        alert(2);
        if(result) {
            alert(3);
            $('#success').show();
        }else {
            alert(4);
            $('#failed').show();
        }
        window.parent.iFrameHeight();
        alert(5);
    </script>
</body>
</html>
