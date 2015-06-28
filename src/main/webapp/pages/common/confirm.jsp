<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>操作确认页面</title>
		<link rel="stylesheet" href="/RMS/css/admin/common.css" type="text/css"></link>
		<script type="text/javascript" src="/RMS/js/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="/RMS/js/admin/center.js"></script>
	</head>
	<body>
		<div class="center">
			<div class="position">
				<div class="positionleft"></div>
				<div class="positionmiddle">
					<table class="positionmiddletable" cellpadding="0" cellspacing="0">
						<tr>
							<td style="vertical-align: middle; text-align: left;">
								<img src="/RMS/images/tb.gif" />
								<span
									style="font-size: 12px; font-weight: bold; margin-bottom: 15px;">你当前的位置:</span>[确认页面]
							</td>
							<td valign="bottom"
								style="text-align: right; vertical-align: middle;">
							</td>
						</tr>
					</table>
				</div>
				<div class="positionright"></div>
			</div>
			<div class="content">
				<form action="" id="myForm" method="post">
					<table class="addtable" cellspacing="0">
						<tr>
							<td align="left" width="15%;" style="padding: 30px; border: 0px;">
								<c:if test="${confirm.isSuccess=='right'}">
									<img src="/RMS/images/right.png"
										style="width: 120px; height: 120px;" />
								</c:if>
								<c:if test="${confirm.isSuccess!='right'}">
									<img src="/RMS/images/error.png"
										style="width: 120px; height: 120px;" />
								</c:if>
							</td>
							<td align="left" style="border: 0px;">
								<B style="color: red; font-size: 24px;">${confirm.message }</B>
								<br />
								<br />
								<br />
								<span style="vertical-align: top;">距离开页面还有<span
									style="font-size: 13px;" id="secend">5</span>秒钟</span>
								<br />
								<a href="${confirm.url }"><span style="color: #87D6FF;">返回${confirm.retName}</span>
								</a>
								<script language="javascript" type="text/javascript">
						            var interval = 5;
						            var checkTime = function() {
						               interval--;
						               if (interval <= 0) {
						                  window.location.href = "${confirm.url}";
						                  window.clearInterval();
						               }
						               else {
						                  document.getElementById("secend").innerHTML = interval;
						               }
						            }
						            window.setInterval("checkTime()", 1000);
						        </script>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="foot">
				<div class="footleft"></div>
				<div class="footmiddle">
				</div>
				<div class="footright">
				</div>
			</div>
		</div>
	</body>
</html>
