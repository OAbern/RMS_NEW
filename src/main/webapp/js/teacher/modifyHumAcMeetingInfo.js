// JavaScript Document
var $grid;
var manager, g;
var xmlhttp;
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
            
            $("#holdingTime").ligerDateEditor({ showTime: true, width: 130, label: '举办时间', labelWidth: 123,labelAlign: 'center', format: "yyyy-MM-dd", initValue: document.getElementById("holdingTime").value});
            
          //表格
            g = manager = $grid = $("#maingrid").ligerGrid({
                columns: [
                { display: '旁证材料名称', name: 'uploadProofName', align: 'left', width: 250, minWidth: 60 },
                { display: '文件类型', name: 'uploadContentType', align: 'left', width: 100, minWidth: 60 },
                { display: '上传时间', name: 'timeProofUpload', align: 'left', minWidth: 150 },
                { display: '旁证材料描述', name: 'descProof', align: 'left', minWidth: 200 },
                { display: '操作', isAllowHide: false, width: 100, render: function (row)
                    {
                        var html = '<a href="javascript:showView(\''+row.uploadProofName+'\',850,500,\''+row.proofId+'\',\'iframe:'+row.proofPath+'/'+row.uploadRealName+'\');">预览</a>';
                       
                        return html;

                     }
                }
                ], dataAction: 'server', data: rows, sortName: 'proofId',
                width: '100%', height: 300, pageSize: 30, rownumbers:true,
                checkbox : true,
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6
            });
            
            show();
            
            gridManager = $("#maingrid").ligerGetGridManager();
            
            //旁证材料工具条
            $("#proofstoolbar").ligerToolBar({ items: [
                { text: '删除', id:'deleteproof', click: itemclick, icon: 'delete' }
            ]
            });
        	//成员信息工具条
            $("#membertoolbar").ligerToolBar({ items: [
                { text: '添加', id:'addmember', click: itemclick, icon: 'add' },
            ]
            });

            

            $("#pageloading").hide();


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
                    case "deleteproof":
                        var data = gridManager.getCheckedRows();
                        if (data.length == 0)
                            alert('请选择行！');
                        else
                        {
                        	var checkedIds = [];
                            var checkedNames = [];
                            $(data).each(function ()
                            {
                                checkedIds.push(this.proofId);
                                checkedNames.push(this.uploadProofName);
                            });
                            $.ligerDialog.confirm('确定删除：' + checkedNames.join('；') + '？', function (result)
                            {
                                if(result){
                                	url = 'deleteProof.action?infoIds='+checkedIds;
                            		deleteInfo(url);
                                }
                            }); 
                        }
                        return;
                }
            }
            alert(item.text);
        }
        
        function getSelected()

        { 

            var row = manager.getSelectedRow();

            if (!row) { alert('请选择行！'); return; }


        }
       
        function GetxmlhttpObject()
    	{
    	   try
		   {
    		   // Firefox, Opera 8.0+, Safari
    		   xmlhttp = new XMLHttpRequest();
		   }
    	   catch (e)
		   {
    		   // Internet Explorer
    		   try
    		   {
    			   xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    		   }
    		   catch (e)
    		   {
    			   try
    			   {
    				   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    			   }
    			   catch (e)
    			   {
    				   alert("您的浏览器不支持AJAX！");
    				   xmlhttp = false;
    			   }
    		   }
		   }
    	   return xmlhttp;
    	}
        
        function showView(title,windth,height,id, concent) {
        	$.XYTipsWindow({
    			___title : title,
    			___content :concent,
    			___width : windth,
    			___height : height,
    			___dray : "___boxTitle",
    			___showbg : true
    		});
    		$('.clearField').clearField();
    	}
        
        function download(url)
        {
                 xmlhttp=GetxmlhttpObject();
                 xmlhttp.open("POST",url,true);
                 xmlhttp.send(null);
        }
        
        function deleteInfo(url)
        {
        	xmlhttp = GetxmlhttpObject();
            xmlhttp.open("POST",url,true);
            xmlhttp.onreadystatechange = showMessage;
            xmlhttp.send(null);
        }
        
        function showMessage(){
        	if(xmlhttp.readyState == 4){
        		$.ligerDialog.waitting('删除中，请稍候...');
        		setTimeout(function ()
                {
        			$.ligerDialog.closeWaitting();
        			if(xmlhttp.responseText == "true"){
        				gridManager.deleteSelectedRow();
        				$.ligerDialog.success('删除成功！');
        			}else{
        				$.ligerDialog.error('删除失败！');
        			}
                },1500);
        	}
        }
        
        function addPerson()
        {
        	$('.line:last').clone(true).appendTo($('.content'));
        }
        
        function addProof()
        {
        	$('.disc').clone(true).appendTo('#hippo').removeClass('disc');
			if($('.common').size()<2){
				$('.common').addClass('disc');
			}
        }

        
        function show()
        {
            var jsonObj = {};
            jsonObj.Rows = rows;
            $grid.set({ data: jsonObj });
        }
        
        var rows = [];