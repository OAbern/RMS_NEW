<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>查看理科科技详细项目信息</title>
    <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="css/Data.css" rel="stylesheet" type="text/css" />
    <link href="css/input.css" rel="stylesheet" type="text/css" />
    <script src="lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
    <script src="js/vanadium.js" type="text/javascript"></script>
	<script src="js/check.js" type="text/javascript"></script>
	<script src="js/teacher/showdetailinfo.js" type="text/javascript"></script>	
</head>
<body style="padding:0px;"> 
		
	<form id="form" name="form" action="modifyScienceTechProjectDetail.action" onsubmit="return checkClickAndSubmit();" method="post">
	<div id="allpage">

	<div class="item">
		<div class="title">
			科技项目详细信息
		</div>
		<div class="content">
			<div class="clear" style="height:15px;"></div>
			<s:iterator value="scienceDetailTechProject" >
			<input type="hidden" id="deatilProjectId" name="deatilProjectId" value='<s:property value="deatilProjectId"/>'/>
			<div class="line">
				<div class="element">
					<label for="updateTime">更新时间:</label>
					<input type="text" id="updateTime" name="updateTime" value="${updateTime}" readonly/>
				</div>
				<div class="element">
					<label for="inputThisYear">当年拨入经费（千元）:</label>
					<input type="text" id="inputThisYear" name="inputThisYear" value='<s:property value="inputThisYear"/>' class=":number"/>
				</div>
				<div class="element">
					<label for="expenditureThisYear">当年支出经费（千元）:</label>
					<input type="text" id="expenditureThisYear" name="expenditureThisYear" value='<s:property value="expenditureThisYear"/>' class=":number"/>
				</div>
			</div>
			<div class="line">
				<div class="element">
					<label for="totalStaff">当年投入人员（人年）【合计】:</label>
					<input type="text" id="totalStaff" name="totalStaff" value='<s:property value="totalStaff"/>' class=":digits"/>
				</div>
				<div class="element">
					<label for="advancedStaff">当年投入人员（人年）【高级职务】:</label>
					<input type="text" id="advancedStaff" name="advancedStaff" value='<s:property value="advancedStaff"/>' class=":digits"/>
				</div>
				<div class="element">
					<label for="middleStaff">当年投入人员（人年）【中级职务】:</label>
					<input type="text" id="middleStaff" name="middleStaff" value='<s:property value="middleStaff"/>' class=":digits"/>
				</div>
			</div>
			<div class="line">
			    <div class="element">
					<label for="juniorStaff">当年投入人员（人年）【初级职务】:</label>
					<input type="text" id="juniorStaff" name="juniorStaff" value='<s:property value="juniorStaff"/>' class=":digits"/>
				</div>
				<div class="element">
					<label for="otherStaff">当年投入人员（人年）【其他】:</label>
					<input type="text" id="otherStaff" name="otherStaff" value='<s:property value="otherStaff"/>' class=":digits"/>
				</div>
			</div>
			<div class="line">
			    <div class="element">
					<label for="graduateJoin">参与研究生数（人）:</label>
					<input type="text" id="graduateJoin" name="graduateJoin" value='<s:property value="graduateJoin"/>' class=":digits"/>
				</div>
				<div class="element">
					<label for="projectStatus">项目状态:</label>
					<input type="text" id="projectStatus" name="projectStatus" value='<s:property value="projectStatus"/>' />
				</div>
			</div>
			</s:iterator>
		</div>
	</div>
	<div class="clear" style="height:30px;"></div>
	<p id="tijiao">
		<input type="submit" id="submit" name="submit" onclick="checkResult('save');" class="btn" value="保存" />
		<input type="submit" id="submit" name="submit" onclick="checkResult('confirm');" class="btn" value="提交" />
		<input type="reset" class="btn" value="重置" />
	</p>
	</div>
    
	</form>

</body>
</html>