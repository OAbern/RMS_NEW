// JavaScript Document

var clickresult;
        
function checkResult(content){
	clickresult = content;
}
        
function checkClickAndSubmit(){
    var showContent;
    if(clickresult == 'save'){
        showContent = '确定保存已填写的科研信息？（保存后的科研信息仍可以修改！）';
    }else if(clickresult == 'confirm'){
        showContent = '确定提交已填写的科研信息？（提交后的科研信息不能再修改！）';
    }else{
        return false;
    }
    	var result = confirm(showContent);
        return result;
}

