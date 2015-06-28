// JavaScript Document
var $grid;
var manager, g;
var xmlhttp;
var clickresult;
		var alert = function (content)
        {
            $.ligerDialog.alert(content);
        };
        var gridManager = null;
        $(function ()
        {
        	 //添加旁证材料工具条
            $("#newproofstoolbar").ligerToolBar({ items: [
                { text: '上传更多...', id:'addnewproof', click: itemclick, icon: 'add' }
            ]
            });
        	
            $("#timeProjectApproved").ligerDateEditor({ showTime: true, width: 130, label: '合同批准（合同签订）时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
            $("#filingDate").ligerDateEditor({ showTime: true, width: 130, label: '申请日期', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
    		$("#announcementDate").ligerDateEditor({ showTime: true, width: 130, label: '授权公告日期', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
            $("#humProTimeApproved").ligerDateEditor({ showTime: true, width: 130, label: '批准时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
            $("#humExChPaperPublishedTime").ligerDateEditor({ showTime: true, width: 130, label: '发表时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
            $("#humBookPublishedTime").ligerDateEditor({ showTime: true, width: 130, label: '出版日期', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
            $("#humAcMeetingholdingTime").ligerDateEditor({ showTime: true, width: 130, label: '举办时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
            $("#humRewardApproveTime").ligerDateEditor({ showTime: true, width: 130, label: '获奖批准时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
            $("#checkTime").ligerDateEditor({ showTime: true, width: 130, label: '立项时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
    		$("#endTime").ligerDateEditor({ showTime: true, width: 130, label: '结题时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
    		$("#startTime").ligerDateEditor({ showTime: true, width: 130, label: '转让技术研究的起始时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
    		$("#endTime1").ligerDateEditor({ showTime: true, width: 130, label: '转让技术研究的终止时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd"});
            
        	//成员信息工具条
            $("#membertoolbar").ligerToolBar({ items: [
                { text: '添加', id:'addmember', click: itemclick, icon: 'add' },
            ]
            });
           



        });


        function itemclick(item)
        { 
            if(item.id)
            {
                switch (item.id)
                {
                    case "addmember":
                    	addPerson();
                        return;
                    case "addnewproof":
                    	addProof();
                    	return;
                }
            }
            alert(item.text);
        }
        
        
        function addPerson()
        {
        	$('.line:last').clone(true).removeClass('spe').insertBefore('#hippo');
        }
        
        function addProof()
        {
        	$('.disc').clone(true).appendTo('#hippo').removeClass('disc');
			if($('.common').size()<2){
				$('.common').addClass('disc');
			}
        }
        
        function checkResult(content){
        	clickresult = content;
        }
        
        function checkClickAndSubmit(){
        	var showContent;
        	
        	var ovalue=document.getElementById('fieldName');
        	var text=ovalue.value;
            var pattern=/^[a-zA-Z_]+$/;
            if(!pattern.test(text)){
        	  alert('字段数据库名不能含有中文！');
        	  return false;
            }
        	
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

