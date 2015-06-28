<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息和角色信息管理</title>
<link href="/RMS/css/admin/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/RMS/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="/RMS/js/admin/center.js"></script>
<script type="text/javascript">

function checkPassed(){
	var result1 = confirm("确定重置密码？");
	return result1;
}
</script>
<style type="text/css" title="currentStyle">
@import "/RMS/js/media/css/demo_page.css";
@import "/RMS/js/media/css/demo_table.css";
</style>
<script type="text/javascript" language="javascript" src="/RMS/js/media/js/jquery.dataTables.js"></script>
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
	<body id="dt_example" >
		<form action="roleInfo.action" id="formList" method="post">
			<div class="center">
				<div class="position">
					<div class="positionleft"></div>
					<div class="positionmiddle">
						<table class="positionmiddletable" cellpadding="0" cellspacing="0">
							<tr>
								<td style="vertical-align: middle; text-align: left;">
									<img src="/RMS/images/tb.gif" />
									<span style="font-size: 12px; font-weight: bold; margin-bottom: 15px;">你当前的位置:</span>[用户角色管理]-[用户角色信息]
								</td>
							</tr>
						</table>
					</div>
					<div class="positionright"></div>
				</div>
				<div id="container" class="content" style="margin-top: 0px;">
					<table class="addtable" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="7" style="background-image: url('/RMS/images/bg.gif');text-align: left;">
							<b>检索条件</b>
							</td>
						</tr>
					</table>
					<table cellpadding="0" cellspacing="0" class="display" style="font-size: 12px;"	id="example" align="center" >
					<thead>
						<tr>
							<td colspan="11" style="background-image: url('/RMS/images/bg.gif');text-align: left;">
							<b>用户角色列表</b>
							</td>
						</tr>
						<tr>
						    <th>用户编号</th>
							<th>用户姓名</th>
							<th>角色名称</th>
							<th>操作</th>
						</tr>
						</thead>
						
						<tbody>
						<c:if test="${not empty userAndRolelist}">
							<c:forEach items="${userAndRolelist}" var="userRoleInfo">
								<c:if test="${not empty userRoleInfo}">
									<tr>
										<td>${userRoleInfo.user.userId }</td>
									    <td>${userRoleInfo.user.userName }</td>
										
										<td>
										  <c:if test="${not empty userRoleInfo.roleList }">
										    <c:forEach items="${userRoleInfo.roleList }" var="roleinfo">
										      <c:if test="${not empty roleinfo }">
										         ${roleinfo.roleName  }&nbsp;&nbsp;
										      </c:if>
										    </c:forEach>
										  </c:if>
										</td>
										
										<td >
											<form onsubmit="return checkPassed();" action="resetPassword.action?userId=${userRoleInfo.user.userId }" method="post" >
											<purviewTag:purviewTag imagePath="/RMS/images/pur.gif" name="重新分配角色" url="forwardAssignRole.action?userId=${userRoleInfo.user.userId }" simpleName="assignRole"></purviewTag:purviewTag>										

												<input type="submit" value="重置密码"/>
											</form>
										
										</td>
									
										   
										
											
										
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${empty userAndRolelist}">
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
					<div class="footmiddle">
					</div>
					<div class="footright"></div>
				</div>
			</div>
		</form>
	</body>
</html>