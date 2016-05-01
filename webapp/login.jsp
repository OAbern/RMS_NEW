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

</head>

<body>

<div class="container">

    <h1>重邮经管学院科研信息管理系统</h1>

    <form class="form-signin" action="login/check.do" method="post" onsubmit="return true">
        <h3 class="form-signin-heading">Please sign in</h3>

        <!-- 用户名 -->
        <label for="inputUsername" class="sr-only">用户名</label>
        <input type="Username" name="userName" id="inputEmail" class="form-control" placeholder="用户名" required autofocus>

        <!-- 密码 -->
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" name="userPwd" id="inputPassword" class="form-control" placeholder="密码" required>

        <!-- 验证码 -->
        <div>
            <label for="inputCode" class="sr-only">验证码</label>
            <input type="code" name="check" id="input" class="form-control" placeholder="验证码" required>
            <!-- 验证码图片 -->
            <div id="checkCode">
                <img id="randImage" src="image.jsp" />
                <span id="change" onclick="javascript:loadimage();">换一张</span>
            </div>
        </div>

        <!-- 身份 -->
        <label for="inputIdentity" class="sr-only">身份</label>
        <select class="form-control1" name="loginType" id="role">
            <option value="0">请选择角色</option>
        </select>

        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        <button class="btn btn-lg btn-primary btn-block" type="reset">重置</button>
    </form>

</div> <!-- /container -->

<div class="mastfoot">
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
        /*提示信息*/
        //$.ligerDialog.tip({ title: '提示信息', content: '请不要使用IE7,否则部分科研成果不能正常展示！如果您使用的是360浏览器，请打开浏览器的极速模式进行访问！' });

        /*加载角色信息*/
        $.ajax({
            url : "login/findRoleList.do",
            type : 'POST',
            dataType : 'JSON',
            timeout : 5000,
            error : function() {
                alert('没有选择身份');
            },
            success : function(msg) {
                $("#role").empty();
                $.each(eval(msg), function(i, item) {
                    $(
                            "<option value='" + item.roleId + "'>"
                            + item.roleName + "</option>")
                            .appendTo($("#role"));
                });
            }
        });
    })

    /**
     * 表单完整性校验
     **/
    function checkForm() {
        if($("#userName").attr("value") == "") {
            alert("请输入用户名");
            return false;
        } else if($("#userPwd").attr("value") == "") {
            alert("请输入用户密码");
            return false;
        } else if($("#check").attr("value")=="" || $("#check").attr("value").length<4) {
            alert("请输入完整的验证码");
            return false;
        } else if($("#role").val() == "") {
            alert("请选择身份信息");
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

</script>
</body>
</html>
