/*设置自动适应浏览器*/
$(document).ready(function () {
	$(".center").css("height",$(this).height());
	$(".center").css("width", $(this).width());
	$(".positionmiddle").css("width", $(this).width() - $(".positionleft").width() - $(".positionright").width());
	if($.browser.mozilla){
		$(".content").css("width", $(this).width() - 7);
		$(".addtable").css("width", $(".content").width() - 8);
		$(".display").css("width", $(".content").width() - 8);
	} else {
		$(".content").css("width", $(this).width() - 9);
		$(".addtable").css("width", $(".content").width() - 8);
		$(".display").css("width", $(".content").width() - 8);
	}
	$(".footmiddle").css("width", $(this).width() - $(".footleft").width() - $(".footright").width());
	$(window).resize(function () {
		$(".center").css("height",$(this).height());
		$(".center").css("width", $(this).width());
		$(".positionmiddle").css("width", $(this).width() - $(".positionleft").width() - $(".positionright").width());
		if($.browser.mozilla){
			$(".content").css("width", $(this).width() - 7);
			$(".addtable").css("width", $(".content").width() - 8);
			$(".display").css("width", $(".content").width() - 8);
			$(".footmiddle").css("width", $(this).width() - $(".footleft").width() - $(".footright").width());
			$(this).reload();
		} else {
			$(".content").css("width", $(this).width() - 9);
			$(".addtable").css("width", $(".content").width() - 8);
			$(".display").css("width", $(".content").width() - 8);
			$(".footmiddle").css("width", $(this).width() - $(".footleft").width() - $(".footright").width());
		}
	});
	$("#topCheckAll").click(function(){
		var checked = $(this).attr("checked");
		$(".display :checkbox").each(function(){
			$(this).attr("checked",checked);
		});
	});
	$("#checkAll").click(function(){
		var checked = $(this).attr("checked");
		$("#topCheckAll").attr("checked",checked);
		$(".display :checkbox").each(function(){
			if($(this).id!="checkAll"){
			   $(this).attr("checked",checked);
			}
		});
	});
});
function isCheckAll(){
	var flag = true;
	$(".display :checkbox").each(function(){
		if($(this).attr("id")!="checkAll"&&!$(this).attr("checked")){
			flag = false;
			return false;
		}
	});
	if(!flag){
		$("#topCheckAll").attr("checked",false);
		$("#checkAll").attr("checked",false);
	} else {
		$("#topCheckAll").attr("checked",true);
		$("#checkAll").attr("checked",true);
	}
}
function upd(url){
		var tag = 0;
		$(".display :checkbox").each(function(){
			if($(this).attr("id")!="checkAll"&&$(this).attr("checked")){
				value = $(this).attr("value");
				tag = tag +1;
				if(tag>1){
					return false;
				}
			}
		});
		if(tag==0){
			alert("请至少选择一项");
			return false;
		} else {
			$("#formList").attr("action",url);
			$("#formList").submit();
		}
}
function del(url){
	if(confirm("你确认要删除吗?")){
		var tag = 0;
		$(".display :checkbox").each(function(){
			if($(this).attr("id")!="checkAll"&&$(this).attr("checked")){
				value = $(this).attr("value");
				tag = tag +1;
				if(tag>1){
					return false;
				}
			}
		});
		if(tag==0){
			alert("请至少选择一项");
			return false;
		} else {
			$("#formList").attr("action",url);
			$("#formList").submit();
		}
	}
}
