<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%    
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<base href="<%=basePath%>">
<head>
	<meta charset="UTF-8">
	<title>高校科研管理系统——登录</title>
	<link href="css/common.css" rel="stylesheet" type="text/css" />
	<link href="css/logo.css" rel="stylesheet" type="text/css" />
	<link href="lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<link href="css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	.l-dialog-body { color:black; }
	</style>
</head>
<body>
<div id="allpage">
	<div id="logo">
		<a class="left" href="http://www.cqupt.edu.cn" target="_blank">
			<img src="image/logo.png" />
		</a>
		<div class="right">
			<p class="cn">重邮经济管理学院科研信息管理系统</p>
			<p class="slice"></p>
			<p class="en">CQUPT JingGuan KeYanXinXi GuanLiXiTong</p>
		</div>
	</div>
	<div id="mid">
		<div class="left"></div>
		<div class="right radius">
			<form action="login/check.do" method="post" onsubmit="return true">

			<%-- TODO:方便测试代码，跳过登录检验 <form action="login/check.do" method="post" onsubmit="return checkForm()"> --%>
				<h3>请输入用户名和密码</h3>
				<FONT color="red">
				<%
					Object object = request.getAttribute("loginFailed");
					if(object != null)
						out.print(object);		
				%>
				</FONT>
				<br>
				<label for="userName">用户名</label>
				<input type="text" name="userName" id="userName" />
				<p class="clear"></p>
				<label for="userPwd">密码</label>
				<input type="password" name="userPwd" id="userPwd" />
				<p class="clear"></p>
				
				<label for="check">验证码</label>
				<input type="text" name="check" id="check" />
				<img id="randImage" src="image.jsp" />
				<span id="change" onclick="javascript:loadimage();">换一张</span>
				
				<p class="clear"></p>
				<label for="role">身份</label>
				<select name="loginType" id="role">
					<option value="0">请选择角色</option>
				</select>
				<p class="clear"></p>
				<div>
					<input type="submit" value="登录" id="login" />
					<input type="reset" value="重置" id="reset" />
					<p class="clear"></p>
				</div>
			</form>
		</div>
	</div>
	<div id="footer">
		<p>版权所有：&emsp;&emsp;管理员：&emsp;&emsp;电话：&emsp;&emsp;地点：重邮经管学院大楼</p>
		<p class="slice"></p>
		<p>技术支持：重庆邮电大学信管工作室</p>
	</div>
</div>
</body>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script src="lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script type="text/javascript">
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
	function loadimage(){
    	document.getElementById("randImage").src = "image.jsp?"+Math.random();
  	}
	$(function() {
		$.ligerDialog.tip({ title: '提示信息', content: '请不要使用IE7,否则部分科研成果不能正常展示！如果您使用的是360浏览器，请打开浏览器的极速模式进行访问！' });
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
</script>
</html>