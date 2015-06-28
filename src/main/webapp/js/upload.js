$(document).ready(function(){
	
		$('#add').click(function(){
			$('.disc').clone(true).appendTo('#hippo').removeClass('disc');//复制当前点击的节点，并将它追加到《ul》元素中，当添加参数时复制它的事件
		});
		$('.common').mouseover(function(){
			$('.current').css('background','#FFFFFF').addClass('current').css('background','#F5F5F5');
		});
		$('.addPerson').click(function(){
			$('.person').clone(true).appendTo($('.content')).removeClass('person');
			
		});
		$('.delPerson').click(function(){
			$('.del:last').remove();
		});
	});