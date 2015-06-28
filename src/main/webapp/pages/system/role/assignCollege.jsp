<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>学院分配</title>
<link href="/RMS/css/admin/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/RMS/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="/RMS/js/admin/center.js"></script>
</head>
<body>
	<form id="myForm" action="assignCollege.action" method="post">
		<div class="center">
			<div class="position">
				<div class="positionleft"></div>
				<div class="positionmiddle">
					<table class="positionmiddletable" cellpadding="0" cellspacing="0">
						<tr>
							<td style="vertical-align: middle; text-align: left;">
                               <img src="/RMS/images/tb.gif" /> 
                               <span style="font-size: 12px; font-weight: bold; margin-bottom: 15px;">你当前的位置:</span>[系统管理]-[角色管理]-[分配所管学院]
							</td>
							<td valign="bottom" style="text-align: right; vertical-align: middle;">
                            <a href="javascript:window.history.back()">
                            <img src="/RMS/images/44.png" />返回</a></td>
						</tr>
					</table>
				</div>
				<div class="positionright"></div>
			</div>
			<div class="content">
				<table class="addtable" cellpadding="2" cellspacing="0">
					${collegeId}
					<tr>
						<td colspan="2" align="center" style="text-align: center;">
							 <input type="submit" class="btn" value="保存" />
							 <input type="hidden" name="roleId" value="${roleId }" />
							 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							 <input type="reset" class="btn" name="重置" />
						</td>
					</tr>
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