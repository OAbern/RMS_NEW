/**
 * 检查动态字段类表单提交的字段类
 */
function checkClickAndSubmit() {
	var showContent;
	var selector = document.getElementById('select');
    var selectedValue = selector.selectedIndex;
	if(selectedValue == 0) {
		alert('请选择信息类别'); 
		return false;
	}
	
	var ovalue=document.getElementById('name');
	var text=ovalue.value;
    var pattern=/^[a-zA-Z_]+$/;
    if(!pattern.test(text)){
	  alert('字段数据库名不能含有中文！');
	  return false;
    }
    
	if(clickresult == 'save') {
		showContent = '确定提交已填写的字段信息？';
	} else if(clickresult == 'confirm') {
		showContent = '确定提交已填写的字段信息？';
	} else {
		return false;
	}
	var result = confirm(showContent);
	return result;
}

function checkClick() {
	var selector = document.getElementById('select');
    var selectedValue = selector.selectedIndex;
	if(selectedValue == 0) {
		alert('请选择信息类别'); 
		return false;
	}
	
	return true;
}