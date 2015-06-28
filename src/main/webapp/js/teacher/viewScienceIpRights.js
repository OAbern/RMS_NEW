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
                { text: '删除', id:'delete', click: itemclick, icon: 'delete' },{ line:true },
                { text: '导出Excel', id:'Excel', click: itemclick, icon: 'save' },{ line:true },
                { text: '刷新', id:'refresh', click: itemclick, icon: 'refresh' }
            ]
            });
            //搜索
            //$("#ddlCountry").ligerComboBox();
            $("#searchbtn").ligerButton({ click: function ()
            {
                if (!gridManager) return;
                var patentName = $("#ddlCountry").val(); 
                gridManager.setOptions(
                    { parms: [{ name: 'patentName', value: patentName}] }
                );
                gridManager.loadData(true);
            }
        }); 
            
            var gradeType = [{ Status: 0, text: '已保存' }, { Status: 1, text: '未审批'},{ Status: 2, text: '审批通过'},{ Status: 3, text: '审批未通过'}];
            //表格
            manager = $grid = $("#maingrid").ligerGrid({
                columns: [
                { display: '专利名称', name: 'patentName', align: 'left', minWidth: 200 },
                { display: '所在学院', name: 'collegesIn', width: 100 },
                { display: '专利类型', name: 'patentType', width: 100 },
                { display: '申请号/授权号', name: 'applicationNumber' , width: 150},
                { display: '专利状态', name: 'patentStatus' , width: 100},
                { display: '申请日期', name: 'filingDate' , width: 150},
                { display: '授权公告日期', name: 'announcementDate' , width: 150},
                { display: '奖励金额（万元）', name: 'incentivePayments' , width: 150},
                { display: '备注', name: 'remarks' , width: 200},
                { display: '状态', name: 'Status' , width: 100 , frozen: true,
                	editor: { type: 'select', data: gradeType, valueColumnName: 'Status' },

                    render: function (item)

                    {

                        if (parseInt(item.Status) == 1) return '未审批';
                        else if (parseInt(item.Status) == 2) return '审批通过';
                        else if (parseInt(item.Status) == 3) return '审批未通过';
                        else return '已保存';

                    }
                },
                { display: '操作', isAllowHide: false, width: 60, frozen: true,
                	render: function (row)
                    {
                		var html = '<a href="viewScienceIpRightsDetail.action?infoid='+row.rightsId+'">查看详细</a>';
                        return html;
                    }
                },
                { display: '操作', isAllowHide: false, width: 60, frozen: true,
                	render: function (row)
                    {
                		var html = '<a href="viewScienceIpRightsDetail.action?flag=modify&infoid='+row.rightsId+'">修改</a>';
                        if(parseInt(row.Status) == 0) return html;
                        else if(parseInt(row.Status) == 3) return html;
                        else return "";
                    }
                }
                ], dataAction: 'server', data: rows, sortName: 'rightsId',
                width: '100%', height: '100%', pageSize: 30,rownumbers:true,
                checkbox : true,
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6
            });
            
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
            if(item.id)
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
                            $(data).each(function ()
                            {
                                if(parseInt(this.Status) != 0)
                                {
                                	alert('不能删除:'+this.patentName);
                                }else
                                {
                                	checkedIds.push(this.rightsId);
                                	checkedNames.push(this.patentName);
                                }
                            });
                            
                            if(checkedIds.length == data.length)
                            {
                            	$.ligerDialog.confirm('确定删除:' + checkedNames.join(' ; ') + '?', function (result)
                                {
                                    if(result)
                                    	{
                                    		url = 'deleteScienceIpRights.action?infoIds='+checkedIds;
                                    		deleteInfo(url);
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
                        		checkedIds.push(this.rightsId);
                            });
                        	$.ligerDialog.confirm('确定导出已选择的数据?', function (result)
                        	{
                        		if(result)
                        		{
                        			link = 'downLoadExcelbyFactor/downloadScienceIpRightsExcel.action?factorName=rightsId&factorValue='+checkedIds;
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