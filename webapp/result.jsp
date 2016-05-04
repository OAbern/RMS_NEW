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
    <link href="css/SB-admin-2-1.0.8/bower_components/font-awesome/font-awesome.min.css" rel="stylesheet">

    <!-- Bern Custom CSS -->
    <link href="css/ifram-common.css" rel="stylesheet">
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
                <h2 class="page-header" style="color: green">恭喜您，操作成功^_^</h2>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->

    <div id="failed" class="container-fluid" hidden="true">
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header text-danger">我们对此感到遗憾，但是您操作失败了！</h2>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

        <div class="alert alert-danger">
            <h4 class="help-block text-danger" id="fRemark"><b><%=result.getFailedReason() %></b></h4>
        </div>
    </div>
    <!-- /.container-fluid -->

<!-- jQuery -->
<script src="js/jquery-1.12.3/jquery-1.12.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap-3.3.5/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/SB-admin-2-1.0.8/sb-admin-2.js"></script>

<script>
    //根据结果展示相应的结果面板
    var result = JSON.parse($('#result').val());
    if(result) {
        $('#success').show();
    }else {
        $('#failed').show();
    }
    window.parent.iFrameHeight();
</script>
</body>
</html>
