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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>重庆邮电大学经管科研管理系统</title>
<link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet"
	type="text/css" />
<link href="css/main_index.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="js/ligerui.min.js" type="text/javascript"></script>
<script src="js/indexdata.js" type="text/javascript"></script>
<script type="text/javascript">
	var tab = null;
	var accordion = null;
	var tree = null;
	$(function() {
		//布局
		$("#layout1").ligerLayout({
			leftWidth : 190,
			height : '100%',
			heightDiff : -34,
			space : 4,
			onHeightChanged : f_heightChanged
		});

		var height = $(".l-layout-center").height();

		//Tab
		$("#framecenter").ligerTab({
			height : height
		});

		//面板
		$("#accordion1").ligerAccordion({
			height : height - 24,
			speed : null
		});

		$(".l-link").hover(function() {
			$(this).addClass("l-link-over");
		}, function() {
			$(this).removeClass("l-link-over");
		});
		//树
		$("#tree1").ligerTree({
			data : indexdata,
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid)
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});

		tab = $("#framecenter").ligerGetTabManager();
		accordion = $("#accordion1").ligerGetAccordionManager();
		tree = $("#tree1").ligerGetTreeManager();
		$("#pageloading").hide();

	});
	function f_heightChanged(options) {
		if (tab)
			tab.addHeight(options.diff);
		if (accordion && options.middleHeight - 24 > 0)
			accordion.setHeight(options.middleHeight - 24);
	}
	function f_addTab(tabid, text, url) {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url
		});
	}
</script>
</head>
<body style="padding: 0px; background: #EAEEF5;">
	<div id="pageloading"></div>
	<div id="topmenu" class="l-topmenu"
		style="background: #C4D9FB; height: 40px;">
		<div class="l-topmenu-logo"
			style="color: #48648A; line-height: 40px; font-size: 20px;">重庆邮电大学经管科研管理系统</div>
		<div class="l-topmenu-welcome" style="margin-top: 14px;">
			<a href="login.jsp" class="l-link2" style="color: #666;">首页</a>
			<span class="space">|</span> 
			<a href="downExcelModel.action?modelName=Document" class="l-link2" style="color: #666;">使用文档下载</a>
			<span class="space">|</span>
			<a href="logout.jsp" class="l-link2" style="color: #666;">退出</a>
		</div>
	</div>
	<div id="layout1"
		style="width: 99.2%; margin: 0 auto; margin-top: 4px;">
		<div position="left" title="主菜单" id="accordion1">
			<c:if test="${not empty list }">
				<c:forEach items="${list}" var="menu">
					<div title="${menu.first.purviewName }">
						<div style="height: 7px;"></div>
						<c:if test="${not empty menu.second }">
							<c:forEach items="${menu.second }" var="menuSecond">
								<a class="l-link"
									href="javascript:f_addTab('${menuSecond.purviewRemark }','${menuSecond.purviewName }','${menuSecond.purviewUrl }')">${menuSecond.purviewName
									}</a>
							</c:forEach>
						</c:if>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div position="center" id="framecenter">
			<div tabid="home" title="欢迎" style="height: 300px">
				<iframe frameborder="0" name="home" id="home" src="pages/common/welcome.html"></iframe>
			</div>
		</div>
	</div>
	<div style="height: 32px; line-height: 32px; text-align: center;">
		Copyright © 2011-2012 重邮信管学生工作室</div>
	<div style="display: none"></div>
</body>
</html>