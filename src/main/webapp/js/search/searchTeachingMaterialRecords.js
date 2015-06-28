// JavaScript Document
var $grid;
var manager, g;
        $(function ()
        {
        	//工具条
            $("#toptoolbar").ligerToolBar({ items: [
                { text: '导出Excel', id:'Excel', click: itemclick, icon: 'save' },{ line:true },
                { text: '查看详情', id:'detail', click: itemclick, icon: 'home' },{ line:true },
                { text: '刷新', id:'refresh', click: itemclick, icon: 'refresh' }
               ]
            });
          //列标题
        	var json = $("#data").val();
        	//alert(json);
        	var objJson = JSON.parse(json);
            var s1 = "{ \"display\": \"提交者\", \"name\": \"submitUser\", \"align\": \"center\", \"width\": 100, \"minWidth\": 60 },{ \"display\": \"审批者\", \"name\": \"approvedUser\", \"align\": \"center\", \"minWidth\": 140 },{ \"display\": \"信息名字\", \"name\": \"name\", \"width\": 120 }";
            var colnames="";
            var s3 ="\"dataAction\": \"server\", \"data\": \"rows\", \"sortName\": \"id\",\" width\": \"100%\", \"height\": \"100%\", \"pageSize\": 30,\"rownumbers\":true,\"checkbox\" : false,\"colDraggable\" : true,\"rowDraggable\" : true,\"cssClass\" : \"l-grid-gray\", \"heightDiff\": 0";
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
                    case "detail":
                        var data = gridManager.getCheckedRows();
                        if (data.length == 0)
                            alert('请选择行!');
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
                                    		url = 'viewTeachingMaterialRecordDetail.action?recordId='+checkedIds;
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
                        			link = 'downLoadExcelbyFactor/downloadDynamicDataRecordExcel.action?factorName=id&factorValue='+checkedIds+'&classNum=4';
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

