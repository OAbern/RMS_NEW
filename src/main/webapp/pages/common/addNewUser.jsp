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
	<title>管理个人信息</title>
	<link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="css/manageinfo.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.4.4.min.js" type="text/javascript"></script> 
    <script src="js/vanadium.js" type="text/javascript"></script>
	<script src="js/check.js" type="text/javascript"></script>
    
</head>
<body style="padding:0px;">
	<div id="allpage">
		<div class="content">
			<form onsubmit="return checkSubmit()" action="addNewUser.action" method="post">
				<label for="userName">教师工号</label>
				<input onblur="checkuserId(this.value)" type="text" id="userId" name="userId" />
				<span id="wrapper" style="color:red"></span>
				<br /><br />
				
				<label for="userName">姓名</label>
				<input type="text" id="userName" name="userName" class=":required"/>
				<br /><br />
				
				<label for="college">所在学院</label>
				<select name="cquptCollegeId">
					<s:iterator value="cquptCollege">
						<option value="${collegeId}">${collegeName}</option>
					</s:iterator>
				</select>
				<br /><br />
				
				<label for="department">部门</label>
				<input type="text" id="department" name="department" />
				<br /><br />
				
				<label for="gender">性别</label>
				<input type="text" id="gender" name="gender"/>
				
				<br /><br />
				
				<label for="origin">籍贯</label>
				<input type="text" id="origin" name="origin"/>
				<br /><br />
				
				<label for="nationality">民族</label>
				<input type="text" id="nationality" name="nationality" />
				<br /><br />
				
				<label for="birthday">出生日期</label>
				<input type="text" id="birthday" name="birthday" />
				<br /><br />
				
				<label for="politicalStatus">政治面貌</label>
				<input type="text" id="politicalStatus" name="politicalStatus" />
				<br /><br />
				
				<label for="timeJoinParty">参加党派时间</label>
				<input type="text" id="timeJoinParty" name="timeJoinParty" />
				<br /><br />
				
				<label for="timeBeginCqupt">进入重邮时间</label>
				<input type="text" id="timeBeginCqupt" name="timeBeginCqupt" />
				<br /><br />
				
				<label for="timeBeginWork">参加工作时间</label>
				<input type="text" id="timeBeginWork" name="timeBeginWork"/>
				<br /><br />
				
				<label for="firstDegree">第一学位</label>
				<input type="text" id="firstDegree" name="firstDegree" />
				<br /><br />
				
				<label for="firstProfessionalName">第一学位专业名称</label>
				<input type="text" id="firstProfessionalName" name="firstProfessionalName"/>
				<br /><br />
				
				<label for="firstGraduateSchool">第一学位毕业学校</label>
				<input type="text" id="firstGraduateSchool" name="firstGraduateSchool" />
				<br /><br />
				
				<label for="lastDegree">最后学位</label>
				<input type="text" id="lastDegree" name="lastDegree" />
				<br /><br />
				
				<label for="lastProfessionalName">最后学位专业名称</label>
				<input type="text" id="lastProfessionalName" name="lastProfessionalName" />
				<br /><br />
				
				<label for="lastGraduateSchool">最后学位毕业学校</label>
				<input type="text" id="lastGraduateSchool" name="lastGraduateSchool" />
				<br /><br />
				
				<label for="lastAcademic">最后学历</label>
				<input type="text" id="lastAcademic" name="lastAcademic" />
				<br /><br />
				
				<p>
				<input type="submit" class="btn" value="添加" />
				<input type="reset" class="btn" value="重置"/>
				</p>
			</form>
			
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>