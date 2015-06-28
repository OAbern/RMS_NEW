<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link href="/RMS/css/admin/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/RMS/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="/RMS/js/admin/center.js"></script>
<style type="text/css" title="currentStyle">
@import "/RMS/js/media/css/demo_page.css";

@import "/RMS/js/media/css/demo_table.css";
</style>
<script type="text/javascript" language="javascript"
	src="/RMS/js/media/js/jquery.dataTables.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#example').dataTable({
			"oLanguage" : {
				"sUrl" : "./dataTables/1.txt"
			},
			"bStateSave" : true,
			"sPaginationType" : "full_numbers"//分页
		});
	});
</script>
</head>
<body id="dt_example">
	<form action="roleInfo.action" id="formList" method="post">
		<div class="center">
			<div class="position">
				<div class="positionleft"></div>
				<div class="positionmiddle">
					<table class="positionmiddletable" cellpadding="0" cellspacing="0">
						<tr>
							<td style="vertical-align: middle; text-align: left;"><img
								src="/RMS/images/tb.gif" /> <span
								style="font-size: 12px; font-weight: bold; margin-bottom: 15px;">你当前的位置:</span>[教师信息管理]
							</td>
							<td valign="bottom"
								style="text-align: right; vertical-align: middle;">
								<a href="javascript:window.history.back()"><img
									src="/RMS/images/44.png" />返回</a> 
								    &nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="viewAddUser.action"><img
									src="/RMS/images/22.gif" />教师添加</a> </td>
						</tr>
					</table>
				</div>
				<div class="positionright"></div>
			</div>
			<div id="container" class="content" style="margin-top: 0px;">
				<table class="addtable" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="7"
							style="background-image: url('/RMS/images/bg.gif'); text-align: left;">
							<b>检索条件</b>
						</td>
					</tr>
				</table>
				<table cellpadding="0" cellspacing="0" class="display"
					style="font-size: 12px;" id="example" align="center">
					<thead>
						<tr>
							<td colspan="11"
								style="background-image: url('/RMS/images/bg.gif'); text-align: left;">
								<b>教师用户</b>
							</td>
						</tr>
						<tr>
						
							
							<th width="30px">教师号</th>
							<th width="20px">姓名</th>
							<th width="20px">学院</th>
							<th width="20px">部门</th>
							<th width="20px">性别</th>
							<th width="20px">籍贯</th>
							<th width="20px">民族</th>
							<th width="40px">出生日期</th>
							<th width="40px">政治面貌</th>
							<th width="60px">进入重邮时间</th>
							<th width="50px">参加工作时间</th>
							<th width="40px">第一学位</th>
							<th width="80px">第一学位专业名称</th>
							<th width="80px">第一学位毕业学校</th>
							<th width="40px">最后学位</th>
							<th width="80px">最后学位专业名称</th>
							<th width="80px">最后学位毕业学校</th>
							<th width="40px">最后学历</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty collegeteacherList}">
							<c:forEach items="${collegeteacherList}" var="collegeteacher">
								<c:if test="${not empty collegeteacherList}">
									<tr>
						
										<td>
											${collegeteacher.userId }
										</td>
										<td>
											${collegeteacher.userName }
										</td>
										<td>
											${collegeteacher.cquptCollege.collegeName }
										</td>
										<td>
										    ${collegeteacher.department}
										</td>
										<td>
										    ${collegeteacher.gender}
										</td>
										<td>
										    ${collegeteacher.origin}
										</td>
										<td>
										    ${collegeteacher.nationality}
										</td>
										<td>
										    ${collegeteacher.birthday}
										</td>
										<td>
										    ${collegeteacher.politicalStatus}
										</td>
										<td>
										    ${collegeteacher.timeBeginCqupt}
										</td>
										<td>
										    ${collegeteacher.timeBeginWork}
										</td>
										<td>
										    ${collegeteacher.firstDegree}
										</td>
										<td>
										    ${collegeteacher.firstProfessionalName}
										</td>
										<td>
										    ${collegeteacher.firstGraduateSchool}
										</td>
										<td>
										    ${collegeteacher.lastDegree}
										</td>
										<td>
										    ${collegeteacher.lastProfessionalName}
										</td>
										<td>
										    ${collegeteacher.lastGraduateSchool}
										</td>
										<td>
										    ${collegeteacher.lastAcademic}
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${empty collegeteacherList}">
							<tr>
								<td colspan="11">
									<span style="color: red;">没有你要查找的记录！</span>
								</td>
							</tr>
						</c:if>
					</tbody>
					<tfoot></tfoot>
				</table>
			</div>
			<div class="foot">
				<div class="footleft"></div>
				<div class="footmiddle"></div>
				<div class="footright"></div>
			</div>
		</div>
	</form>
</body>
</html>