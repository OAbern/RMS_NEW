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
	<title>指导学生参赛获奖</title>
	<link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="css/Data.css" rel="stylesheet" type="text/css" />
    <script src="lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
    <script  src="js/teacher/viewStudentAwardsrecords.js" type="text/javascript" language="JavaScript" charset="utf-8"></script>
    
</head>
<body style="padding:0px; overflow:hidden;">
<form id="form1">
    <input type="hidden" value='<s:property value="#fieldJson"/>' id="data"/>
    <div id="toptoolbar"></div> 
	<div id="maingrid" style="margin:0; padding:0">
    	<s:if test="#records!=null">
    		<s:iterator value="records" id="r">
    		<div id="search"><input type="hidden" value="<s:property value="#r.fieldsJson"/>"/></div>
     			   <script type="text/javascript">
     			   //行数据
     			   var data = $("#search input").last();
     			   $.each(data, function(){
     				  var r1 = '\"id\": \"${r.id}\", \"refuse\": \"${r.returnReason}\", \"name\": \"${r.name}\",\"Status\":\"${r.statusDes}\"';
        			  var rowdata="";
        			  var rvalue = $(this).val();
  			    	  var objVal = eval(rvalue);
        			  for(var j=1;j<=objVal.length;j++) //在这里读json的行数据
   		              {
   		                 rowdata+="\"value"+j+"\":\""+objVal[j-1].value+"\",";
   		              }
  		             rowdata=rowdata+r1;
  		             var row="{"+rowdata+"}";
  		             var rowObj = JSON.parse(row);
  		             rows.push(rowObj);
     			   });
	     		 </script>
   			</s:iterator> 
   			 </s:if>
    	</div>
	
<div style="display:none;">

</div>
</form>
</body>
</html>