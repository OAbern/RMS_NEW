
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%    
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<base href="<%=basePath%>">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>学生获奖信息查询</title>
	<link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="css/Data.css" rel="stylesheet" type="text/css" />
    <link href="css/search.css" rel="stylesheet" type="text/css" />
    <script src="lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script> 
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
    
    <script src="js/search/SearchStudentAwardsreccords.js" type="text/javascript"></script>
</head>
<body style="padding:0px; "> 
  
<form id="form1" action="searchCollegeStudentAwardsRecordInfo.action" onsubmit="return checkClick();" method="post"> 
<div id="hippo">
	<ul class="list">
		<li class="til">
			<span class="logical">查询条件</span>
			<div class="clear"></div>
		</li>
		<li class="item">
			<span class="logical">
			</span>
			<select class="logical_item" name="stringName1" id="stringName1">
				<option>请选择</option>
				<option value="recordId">记录编号</option>
				<option value="recordName">记录名称</option>
				<option value="submitUser">提交者</option>
				<option value="approvedUser">审批者</option>
				<option value="teacher">指导教师</option>
				<s:iterator value="#fields" id="f">
					<option value="<s:property value="#f.field.name"/>"><s:property value="#f.field.description"/></option>
				</s:iterator>
			</select>
			<input type="text" class="logical_word"  name="stringValue1" id="stringValue1"/>
			<div class="clear"></div>
			
			<span class="logical">
			</span>
			<select class="logical_item" name="stringName2" id="stringName2">
				<option>请选择</option>
				<option value="recordId">记录编号</option>
				<option value="recordName">记录名称</option>
				<option value="submitUser">提交者</option>
				<option value="approvedUser">审批者</option>
				<option value="teacher">指导教师</option>
				<s:iterator value="#fields" id="f">
					<option value="<s:property value="#f.field.name"/>"><s:property value="#f.field.description"/></option>
				</s:iterator>
			</select>
			<input type="text" class="logical_word"  name="stringValue2" id="stringValue2"/>
			<div class="clear"></div>
			<div class="clear"></div>
		</li>
		<li class="item">
			<span class="logical">
			</span>
			<select class="logical_item" name="stringName3" id="stringName3">
				<option>请选择</option>
				<option value="recordId">记录编号</option>
				<option value="recordName">记录名称</option>
				<option value="submitUser">提交者</option>
				<option value="approvedUser">审批者</option>
				<option value="teacher">指导教师</option>
				<s:iterator value="#fields" id="f">
					<option value="<s:property value="#f.field.name"/>"><s:property value="#f.field.description"/></option>
				</s:iterator>
			</select>
			<input type="text" class="logical_word"  name="stringValue3" id="stringValue3"/>
			<div class="clear"></div>
			
			<span class="logical">
			</span>
			<select class="logical_item" name="stringName4" id="stringName4">
				<option>请选择</option>
				<option value="recordId">记录编号</option>
				<option value="recordName">记录名称</option>
				<option value="submitUser">提交者</option>
				<option value="approvedUser">审批者</option>
				<option value="teacher">指导教师</option>
				<s:iterator value="#fields" id="f">
					<option value="<s:property value="#f.field.name"/>"><s:property value="#f.field.description"/></option>
				</s:iterator>
			</select>
			<input type="text" class="logical_word"  name="stringValue4" id="stringValue4"/>
			<div class="clear"></div>
		</li>
	</ul>
</div>
	<p id="tijiao" style="margin:1em 0 1em 20em;">
		<input type="submit" class="btn" value="查询" />
		<input type="reset" class="btn" value="重置" />
	</p>
    <input type="hidden" value='<s:property value="#fieldJson"/>' id="data"/>
    <div id="toptoolbar"></div> 
	<div id="maingrid" style="margin:0; padding:0">
    	<s:if test="#studentAwardsInfos!=null">
    		<s:iterator value="studentAwardsInfos" id="i">
    		<div id="search"><input type="hidden" value="<s:property value="#i.model.fieldsJson"/>"/></div>
     			   <script type="text/javascript">
     			   //行数据
     			   var data = $("#search input").last();
     			   $.each(data, function(){
     				  var r1 = '\"id\": \"${i.model.id}\", \"approvedUser\": \"${i.model.approvedUser.userName}\", \"submitUser\": \"${i.model.submitUser.userName}\",\"name\":\"${i.model.name}\"';
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