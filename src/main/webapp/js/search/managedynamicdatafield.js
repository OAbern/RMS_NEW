// JavaScript Document
var $grid;
var manager, g;

 var alert = function (content)
        {
            $.ligerDialog.alert(content);
        };
        var gridManager = null;
        $(function ()
			        {
			        	//工具条
			            $("#toptoolbar").ligerToolBar({ items: [
			                { text: '导出Excel', id:'Excel', click: itemclick, icon: 'save' },{ line:true }
			            ]
			            });
			          
			            g = manager = $grid = $("#maingrid").ligerGrid({
	     	                columns: [
	     	                { display: '字段数据库名', name: 'name', align: 'left', width: 100, minWidth: 60 },
	     	                { display: '字段前台展示名', name: 'description', align: 'left', width: 100, minWidth: 60 },
	     	                { display: '字段提交时间', name: 'submittime', align: 'left', width: 100, minWidth: 60 },
	     	                { display: '字段展示顺序', name: 'order', align: 'left', width: 100, minWidth: 60 },
	     					{ display: '操作', isAllowHide: false, width: 60, frozen: true,
	     	                   	render: function (row)
	     	                       {
	     	                   		var html = '<a href="deleteDynamicDataField.action?classNum='+row.classNum+'&fieldId='+row.id+'&fieldOrder='+row.order+'">删除</a>';
	     	                        return html;
	     	                       }
	     	                 },
	     	                { display: '操作', isAllowHide: false, width: 60, frozen: true,
	     	                   	render: function (row)
	     	                       {
	     	                   		 var html = '<a href="modifyDynamicDataFieldBefore.action?classNum='+row.classNum+'&fieldId='+row.id+'">修改</a>';
	     	                         return html;
	     	                       }
	     	                   }
	     	                ], dataAction: 'server', data: rows, sortName: 'id',
	     	                width: '100%', height: '100%', pageSize: 30,rownumbers:true,
	     	                checkbox : true, pageSizeOptions : [5, 10, 15, 20, 25, 30],
	     	                //应用灰色表头
	     	                cssClass: 'l-grid-gray', 
	     	                heightDiff: -6
	     	            });
	     	            show();
	     	            
	     	           gridManager = $("#maingrid").ligerGetGridManager();

	     	            $("#pageloading").hide();	
			        });
        function itemclick(item)
        { 
            if(item.id)
            {
                switch (item.id)
                {
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
                        }
                        return;
                }   
            }
            alert(item.text);
        }
        
        function downloadExcel(link)
        {
        	window.location.href=link;
        }
        
        function show()
        {
            var jsonObj = {};
           
            jsonObj.Rows = rows;
            $grid.set({ data: jsonObj });
            
        }
   	   
   	    
        var rows = [];
