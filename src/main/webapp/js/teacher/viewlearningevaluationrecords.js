// JavaScript Document

var $grid;
var manager;

 var alert = function (content)
        {
            $.ligerDialog.alert(content);
        };
        var gridManager = null;
        $(function ()
        {
        	//工具条
            $("#toptoolbar").ligerToolBar({ items: [
                { text: '导出Excel', id:'Excel', click: itemclick, icon: 'save' },{ line:true },
                { text: '删除', id:'delete', click: itemclick, icon: 'delete' },{ line:true },
                { text: '修改', id:'modify', click: itemclick, icon: 'modify' },{ line:true },
                { text: '查看详情', id:'detail', click: itemclick, icon: 'home' },{ line:true },
                { text: '刷新', id:'refresh', click: itemclick, icon: 'refresh' }
            ]
            });
          //列标题
        	var json = $("#data").val();
        	var objJson = JSON.parse(json);
            var s1 = "{ \"display\": \"信息名字\", \"name\": \"name\", \"width\": 120 },{ \"display\": \"状态\", \"name\": \"Status\", \"align\": \"center\", \"width\": 100, \"minWidth\": 60 },{ \"display\": \"拒绝原因\", \"name\": \"refuse\", \"align\": \"center\", \"minWidth\": 140 }";
            var colnames="";
            var s3 ="\"dataAction\": \"server\", \"data\": \"rows\", \"sortName\": \"id\",\" width\": \"100%\", \"height\": \"100%\", \"pageSize\": 30,\"rownumbers\":true,\"checkbox\" : true,\"colDraggable\" : true,\"rowDraggable\" : true,\"cssClass\" : \"l-grid-gray\", \"heightDiff\": 0";
            for(var i=1;i<=objJson.field.length;i++) //在这里读json的列名，当作表格的列名
              {
                  colnames+=",{\"name\":\"value"+i+"\",\"minWidth\": 60, \"display\":\""+objJson.field[i-1].des+"\"}";
              }
              colnames=s1+colnames;
              var col="{"+"\"columns\":["+colnames+"],"+s3+"}";
              var colObj = JSON.parse(col);
              g = manager = $grid = $("#maingrid").ligerGrid(colObj);
	          show();
	          gridManager = $("#maingrid").ligerGetGridManager();
	          $("#pageloading").hide();	
        });
      
        function showDetail(id)
        {
        	alert("您选中的ID是" + id);
        }
        
        function itemclick(item)
        { 
            if(item)
            {
                switch (item.id)
                {
                	case "refresh":
                	window.location.reload();
                    return;
                	case "delete":
                        var data = gridManager.getCheckedRows();
                        if (data.length == 0)
                            alert('请选择行!');
                        else
                        {
                            var checkedIds = [];
                            var checkedNames = [];
                            var checkedStatus = [];
                            $(data).each(function()
                            {
                            	checkedStatus.push(this.Status);
                            	if(this.Status == "审批通过"||this.Status == "未审批"){
                            		alert("您所选数据中有正在审批中的条目");
                            		return;
                                }else{
                                	checkedIds.push(this.id);
                                	checkedNames.push(this.name);
                                }   
                            });
                            if(checkedIds.length == data.length)
                            {
                            	$.ligerDialog.confirm('确定删除:' + checkedNames.join(' ; ') + '?', function (result)
                                {
                                    if(result)
                                    	{
                                    		url = 'deleteLearningEvaluationRecord.action?recordIdString='+checkedIds;
                                    		deleteInfo(url);
                                        }
                                });
                            }
                           
                        }
                    return;
                	 case "modify":
                    	 var data = gridManager.getCheckedRows();
                    	 if (data.length != 1)
                        	 alert('请选择单行进行操作!');
                         else
                         {
                             var checkedIds = [];
                             var checkedNames = [];
                             var checkedStatus = [];
                             $(data).each(function ()
                                     {
                                     
                                         	checkedIds.push(this.id);
                                         	checkedNames.push(this.name);
                                         	checkedStatus.push(this.Status);
                                         	
                                     });
                             if(checkedStatus == "审批通过"||checkedStatus == "未审批"){
                             	alert("不能修改已提交审批的界面");
                            	return;
                            }
                            else if(checkedIds.length == data.length)
                               {
                            	
	                            	$.ligerDialog.confirm('确定修改:' + checkedNames.join(' ; ') + '?', function (result)
	                                {
	                            		if(result)
	                            		{
	                                       		url = 'viewLearningEvaluationRecordDetail.action?flag=modify'+'&recordId='+checkedIds;
	                                       		modifyInfo(url);
	                                    }
	                            	 });
                             }
                            }
                        return;
                    case "detail":
                        var data = gridManager.getCheckedRows();
                        if (data.length != 1)
                        	alert('请选择单行进行操作!');
                        else
                        {
                            var checkedIds = [];
                            var checkedNames = [];
                            $(data).each(function ()
                                    {
                                    
                                        	checkedIds.push(this.id);
                                        	checkedNames.push(this.name);
                                    });
                            
                            if(checkedIds.length == data.length)
                            {
                            	$.ligerDialog.confirm('确定查看:' + checkedNames.join(' ; ') + '?', function (result)
                                {
                                    if(result)
                                    	{
                                    		url = 'viewLearningEvaluationRecordDetail.action?recordId='+checkedIds;
                                    		detailInfo(url);
                                        }
                                });
                             }
                          }
                        return;
                    case "Excel":
                    	var data = gridManager.getCheckedRows();
                        if (data.length == 0)
                            alert('请选择行!');
                        else
                        {
                        	var checkedIds = [];
                        	$(data).each(function ()
                            {
                        		checkedIds.push(this.id);
                            });
                        	$.ligerDialog.confirm('确定导出已选择的数据?', function (result)
                        	{
                        		if(result)
                        		{
                        			link = 'downLoadExcelbyFactor/downloadDynamicDataRecordExcel.action?factorName=id&factorValue='+checkedIds+'&classNum=7';
                        			downloadExcel(link);
                        		}
                        	});
                        }
                    	
                        return;
                }   
            }
            alert(item.text);
        }
       
        function GetxmlhttpObject()
    	{
    	   var xmlhttp;
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
        function modifyInfo(url)
        {
        	window.location.href=url;
        }
        function detailInfo(url)
        {
        	window.location.href=url;
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
        
  
        
        function downloadExcel(link)
        {
        	window.location.href=link;
        }
        
        function getSelected()
        { 

            var row = manager.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
        }
       
	 function show()
     {
         var jsonObj = {};
         
         jsonObj.Rows = rows;
         $grid.set({ data: jsonObj });
     }
	 
	 var rows = [];