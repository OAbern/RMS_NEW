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
	<title>添加职称信息</title>
	<link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.4.4.min.js" type="text/javascript"></script> 
    <script src="js/vanadium.js" type="text/javascript"></script>
	<script src="js/check.js" type="text/javascript"></script>
	<script src="lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
    <style type="text/css">
		.clear{ clear:both;}
		body{ background:#E4E4E4;}
		#allpage{ background:#fff; padding-top:6px;}
		.content{ margin:20px 0;}
		.content form{ width:760px; margin:0 auto;}
		.content form label{ padding-right:18px; cursor:pointer; display:block; float:left; width:100px; text-align:right;}
		.content form input , .content form select{ margin-left:10px; width:20em;}
		.content form input:focus{ background:#F6F6F6;}
		.content form p{ margin:3em 0 0 20em;}
		.content form .add{ color:#F06274; padding-left:16px;}
		
		input.rightformcss,select.rightformcss,textarea.rightformcss{border:1px solid #5383F2;padding:1px;}
		.failmsg{color:#a40000;}
		input.failformcss,select.failformcss,textarea.failformcss{border:1px solid #F06274;padding:1px;}
		.content form input.btn{background:url(images/button.png) no-repeat; width:57px; height:25px; line-height:25px; color:#FFF; border:0; cursor:pointer;}
	</style>
	<script type="text/javascript">
	$(function ()
	{
		$("#timeTitlesBegin").ligerDateEditor({ showTime: true, width: 132, label: '职称开始时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
		$("#timeTitlesEnd").ligerDateEditor({ showTime: true, width: 132, label: '职称结束时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
	});
	</script>
</head>
<body style="padding:0px;">
	<div id="allpage">
		<div class="content">
			<form id="form" name="form" action="manageTitles.action" method="post">
				<label for="titlesName">职称:</label>
				<input type="text" id="titlesName" name="titlesName" class=":required" />
				<br /><br />

				<label for="timeTitlesBegin"></label>
				<input type="text" id="timeTitlesBegin" name="timeTitlesBegin" />
				<br /><br />
				
				<label for="timeTitlesEnd"></label>
				<input type="text" id="timeTitlesEnd" name="timeTitlesEnd" />
				<br /><br />
				
				<label for="remarks">备注:</label>
				<input type="text" id="remarks" name="titles.remarks"class=":required" />
				<br /><br />
				
				<p>
				<input type="submit" class="btn" value="确定" />
				<input type="reset" class="btn" value="重置"/>
				</p>
			</form>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>