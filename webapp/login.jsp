<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>重庆邮电大学科研管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap-3.3.5/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/bootstrap-3.3.5/ex/signin.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="css/SB-admin-2-1.0.8/bower_components/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<%
    String failedInfo = (String)request.getAttribute("loginFailed");
    if(failedInfo == null) {
        failedInfo = "";
    }
%>

<body>
<input id="failedInfo" type="hidden" value="<%=failedInfo %>"/>
<!-- 提示信息的Modal -->
<div class="modal fade" id="tips-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><label class="text-danger">提示信息</label></h4>
            </div>
            <div class="modal-body">
                <br/>
                <h2 class="text-primary" id="tips"></h2>
                <br/>
            </div>
            <div class="modal-footer" align="center">
                <button type="button" class="btn btn-warning btn-block" data-dismiss="modal">Got it</button>
            </div>
        </div>
    </div>
</div>

<div class="container">

    <h1>重邮经管学院科研信息管理系统</h1>

    <form class="form-signin" action="login/check.do" method="post" onsubmit="return checkForm()">
        <h3 class="form-signin-heading">请登录</h3>

        <!-- 用户名 -->
        <div class="form-group">
            <label class="sr-only">用户名</label>
            <input name="userName" id="userName" class="form-control" placeholder="用户名" title="用户名" required autofocus>
        </div>

        <!-- 密码 -->
        <div class="form-group">
            <label class="sr-only">密码</label>
            <input type="password" name="userPwd" id="userPwd" class="form-control" placeholder="密码" required>
        </div>

        <!-- 验证码 -->
        <div class="form-group" style="overflow: hidden;">
            <label class="sr-only">验证码</label>
            <div class="form-control" style="text-align: left;">
                <input name="check" id="check" placeholder="验证码" style="width: 60%; padding-left: 6px; border: none;" required>
                <!-- 验证码图片 -->
                <span id="checkCode" style="margin-left: 4%;" onclick="loadimage();">
                    <img style="" id="randImage" src="image.jsp" />
                    <span style="margin-left: 2%; vertical-align: middle;" class="fa fa-refresh" id="change"></span>
                </span>
            </div>

        </div>

        <!-- 身份 -->
        <div class="form-group">
            <label class="sr-only">身份</label>
            <select class="form-control" name="loginType" id="role">
                <option value="0">请选择角色</option>
            </select>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        <button class="btn btn-lg btn-info btn-block" type="reset">重置</button>
    </form>

</div> <!-- /container -->

<div class="mastfoot" style="left: 39%;">
    <div class="inner">
        <p>Support By <a href="javascript:void(0)">重邮信管工作室</a>, by <a href="javascript:void(0)">@Bern</a>.</p>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery-1.12.3/jquery-1.12.3.min.js"></script>
<script src="js/bootstrap-3.3.5/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function() {
        if($('#failedInfo').val() != '') {
            showTipsModal($('#failedInfo').val());
        }

        /*加载角色信息*/
        $.ajax({
            url : "login/findRoleList.do",
            type : 'POST',
            dataType : 'JSON',
            timeout : 0,
            error : function() {
                alert('没有选择身份');
            },
            success : function(msg) {
//                $("#role").empty();
                $.each(eval(msg), function(i, item) {
                    $(
                            "<option value='" + item.roleId + "'>"
                            + item.roleName + "</option>")
                            .appendTo($("#role"));
                });
            }
        });
    });

    /**
     * 表单完整性校验
     **/
    function checkForm() {
        if($("#userName").val() == "") {
            showTipsModal("请输入用户名");
            return false;
        } else if($("#userPwd").val() == "") {
            showTipsModal("请输入用户密码");
            return false;
        } else if($("#check").val()=="" || $("#check").val().length != 4) {
            showTipsModal("请输入完整的验证码");
            return false;
        } else if($("#role").val()==0 || $("#role").val()=="0") {
            showTipsModal("请选择身份信息");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 重新加载验证码图片
     */
    function loadimage(){
        document.getElementById("randImage").src = "image.jsp?"+Math.random();
    }

    /**
     * 展示提示信息的modal
     * @param tipsInfo 提示信息
     */
    function showTipsModal(tipsInfo) {
        $('#tips').empty().append(tipsInfo);
        $('#tips-modal').modal({
            keyboard: false
        });
    }
</script>
</body>
</html>
