<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
       
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>导入用户信息</title>
	<link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="css/Data.css" rel="stylesheet" type="text/css" />
    <script src="lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script> 
    <script src="js/vanadium.js" type="text/javascript"></script>
	<script src="lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
</head>

<body style="padding:0px;">
	<div id="allpage">
	<div class="item">
		<div class="title">
			导入教师信息
			<div class="clear"></div>
		</div>
		<div class="content">
			<form action="importUserInfo.action" id="form" name="form" method="post" enctype="multipart/form-data">
				<input type="file" class="file" name="upload" value="浏览" />
				<input type="submit" name="submit" class="btn" value="导入">
			</form>
		</div>
		
		<div class="clear" style="height:15px;"></div>
    	
	</div>
	
	</div>
	
</body>
</html>