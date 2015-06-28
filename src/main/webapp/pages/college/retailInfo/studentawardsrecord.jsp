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
        <title>审批指导学生参赛获奖信息</title>
   
    
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
       <link rel="stylesheet" type="text/css" href="js/XYTipsWindow/style.css">
	<script type="text/javascript" src="js/XYTipsWindow/jquery.XYTipsWindow.2.8.js"></script>
	<script type="text/javascript" src="js/jquery.clearfield.js"></script>
    <script src="js/collegeaprovel/detail/CollegeProjectDetail.js" type="text/javascript"></script>
</head>
<body style="padding:0px;"> 
<div id="allpage">

	<div class="item">
		<div class="title">
			1.指导学生参赛获奖信息
		</div>
		<div class="content">
			<div class="element">
				<label for="awardsName">赛事名称</label>
				<input type="text" name="awardsName" value="<s:property value="#record.name"/>" readonly />
			</div>
			<s:iterator value="#record.fields" id="f">
			<div class="element">
				<label for="<s:property value="#f.field.name"/>"><s:property value="#f.field.description"/></label>
				<input type="text"  value="<s:property value="#f.value"/>" readonly />
			</div>
			</s:iterator>

		</div>
	</div>
	<div class="clear" style="height:15px;"></div>
	<div class="item">
		<div class="title">
			2.指导老师信息
		</div>
		<s:iterator value="#memberList" id="m">
		<div class="content">
			<div class="line">
				<div class="element">
					<label for="memberName">姓名:</label>
					<input type="text" name="memberName" value='<s:property value="#m.memberName"/>' readonly/>
				</div>
				<div class="element">
					<label for="remarks">备注:</label>
					<input type="text" id="remarks" name="remarks" value='<s:property value="#m.remarks"/>' readonly/>
				</div>
			</div>
		</div>
		</s:iterator>
	</div>
	<div class="clear" style="height:15px;"></div>
    <div class="item">
    	<div class="title">
			3.旁证材料
		</div>
		<div class="content">
    	<div id="maingrid" style="margin:0; padding:0">
     	<s:iterator value="#proofs">
     	<script type="text/javascript">
     		var row = {proofId: "${proofId}", infoApprovedId: "${infoApprovedId}", proofPath: "${proofPath}",
     				uploadProofName: "${uploadProofName}", uploadRealName: "${uploadRealName}", 
     				uploadContentType: "${uploadContentType}", timeProofUpload: "${timeProofUpload}", descProof: "${descProof}"};
			rows.push(row);
     	</script>
     	</s:iterator>
    	</div>
    	</div>
    </div>
    
    <div class="item">
		<div class="content">
    	<div style="text-align:right; padding:0 30px 20px 30px;">
	    <form action="collegeUpdateStatus.action?id=<s:property value="#record.id"/>&modelName=StudentAwardsRecord&idName=id" onsubmit="return checkPassed();" method="post"> 
		
			<input style="background:url(images/button.png); width:57px; height:25px; border:0; cursor:pointer; color:#fff;" type="submit"  value="审批通过" />
			<input type="hidden" value="2" name="status">
		
	    </form>
	    
	    
	    <form name="form" onsubmit="return checkReturnReason();" action="collegeUpdateStatus.action?id=<s:property value="#record.id"/>&modelName=StudentAwardsRecord&idName=id" method="post"> 
	    	<br/>
		    <p>
		    	拒绝理由：
		  		<input type="text" name="returnReason" id="returnReason" style="margin-right:20px;">
				<input type="submit" style="float:right;background:url(images/button.png); width:57px; height:25px; border:0; cursor:pointer;color:#fff;" value="审批拒绝"/>
				<input type="hidden" style="float:right;" value="3" name="status">
			</p>
	    </form>
	  </div>
		</div>
	</div>
    
</div>
</body>
</html>