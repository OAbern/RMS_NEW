$(document).ready(function(){
	
		$('#add').click(function(){
		
			$('.disc').clone(true).appendTo('#hippo').removeClass('disc');//复制当前点击的节点，并将它追加到《ul》元素中，当添加参数时复制它的事件
			
				if($('.common').size()<2){
					$('.common').addClass('disc');
				}
		});
		$('.common').mouseover(function(){
			$('.current').css('background','#FFFFFF').addClass('current').css('background','#F5F5F5');
		});
		$('.addPerson').click(function(){
			$('.line:last').clone(true).appendTo($('.content'));
			
		});
		
		$('.delProof').click(function(){
			if($('.common').size()>1)
			{
				$(this).parent().parent().remove();
			}
			else{
				
			}
		});
		
		$('.delPerson').click(function(){
			
			if($('.spe').size()>1)
			{
				$(this).parent().parent().remove();
			}
			else{
			
			}
		});
		
		function add(){
			var person = $('.person').clone(true);
			person.appendTo($('.content'));
			person.removeClass('person');
			
		}
	});

function checkResult(content){
	clickresult = content;
}

function checkClickAndSubmit(){
	var showContent;
	if(clickresult == 'save'){
		showContent = '确定保存已修改的科研信息？（保存后的科研信息仍可以修改！）';
	}else if(clickresult == 'confirm'){
		showContent = '确定提交已修改的科研信息？（提交后的科研信息不能再修改！）';
	}else{
		return false;
	}
	var result = confirm(showContent);
	return result;
}

