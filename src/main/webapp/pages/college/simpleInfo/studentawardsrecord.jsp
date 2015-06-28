<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%    
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://" + 

request.getServerName() + ":" + request.getServerPort() + path + "/";
       
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<base href="<%=basePath%>">
<head>
        <title>学生获奖信息</title>
    <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="css/Data.css" rel="stylesheet" type="text/css" />
    <script src="lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script> 
    <script src="lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script> 
    <script src="lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerMenu.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerMenuBar.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>

    <script src="js/collegeaprovel/simple/studentawardsecord.js" type="text/javascript"></script>
 </head>
<body style="padding:0px; overflow:hidden;"> 
  <form id="form1" > 
  <div id="maingrid" style="margin:0; padding:0">
    	<s:if test="#records!=null">
    		<s:iterator value="records">
     			<script type="text/javascript">
	     			var row = {
	     					id:"${id}",
	     					submitUser: "${submitUser.userName}",
	     					name: "${name}", 
	     					Status: "${statusDes}",
					};
	     			rows.push(row);
	     			 
	     		</script>
   			</s:iterator> 
   			 </s:if>
    	</div>
	
<div style="display:none;">

</div>
<%-- 	<table border="1">
    	<tr>
    		<th>操作</th>
    		<th>提交者</th>
    		<th>信息名称</th>
    		<th>状态</th>
     	</tr>
     	<s:iterator value="#records" id="r">
     		<tr>
     			<td><a href="collegeAchStatus/studentAwardsRecord.action?recordId=<s:property value="#r.id"/>">查看详细</a></td>
     			<td><s:property value="#r.submitUser.userName"/></td>
     			<td><s:property value="#r.name"/></td>
     			<td><s:property value="#r.statusDes"/></td>
     		</tr>
     	</s:iterator>
     	</table> --%>
  </form>
</body>
</html>
