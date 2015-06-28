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
			$('.line:last').clone(true).removeClass('spe').insertBefore('#hippo');
			
		});
		$('.delPerson').click(function(){
			if($(this).parent().parent().hasClass('spe')||$(this).parent().parent().hasClass('disc'))
			{
				
			}
			else{
				$(this).parent().parent().remove();
			
			}
		});
		
		function add(){
			var person = $('.person').clone(true);
			person.appendTo($('.content'));
			person.removeClass('person');
			
		}
	});

