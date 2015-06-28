
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
                { text: '导出Excel', id:'Excel', click: itemclick, icon: 'save' },{ line:true },
                { text: '刷新', id:'refresh', click: itemclick, icon: 'refresh' }
            ]
            });

        	showdata();
        });
        
        function showdata(){
        	//表格
            g = manager = $grid = $("#maingrid").ligerGrid({
                columns: [
                { display: '教材等级', name: 'setClass', align: 'left', width: 100, minWidth: 60 },
                { display: '教材名称', name: 'teachingMaterialName', align: 'left', minWidth: 140 },
                { display: '类别', name: 'numberProject', align: 'left', minWidth: 140 },
                { display: '出版单位', name: 'resultsPostedStatus', align: 'left', minWidth: 140 },
                { display: '出版日期', name: 'setTime', align: 'left', minWidth: 140 },
                { display: '字数（千字）', name: 'wordsNumbers', align: 'left', minWidth: 140 },
                { display: '备注', name: 'remarks', align: 'left', minWidth: 140 },
                { display: '奖励金额', name: 'collegeAward', align: 'left', minWidth: 140 },
                { display: '提交者', name: 'submitUser', align: 'left', width: 80, minWidth: 60 , frozen: true},
                { display: '审批者', name: 'approvedUser', align: 'left', width: 80, minWidth: 60 , frozen: true},
                { display: '操作', isAllowHide: false, width: 80, frozen: true,
                   	render: function (row)
                       {
                   		var html = '<a href="viewTeachingMaterialSetNewDetail.action?infoid='+row.teachingMaterialId+'">查看详细</a>';
                           return html;
                       }
                   }
                ], dataAction: 'server', data: rows, sortName: 'teachingMaterialId',
                width: '100%', height: '100%', pageSize: 100000,rownumbers:true,
                checkbox : true, pageSizeOptions : [10, 30, 50, 100, 500, 100000],
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6
            });
            show();
            
           gridManager = $("#maingrid").ligerGetGridManager();

            $("#pageloading").hide();
        }

        function getInfo(){
        	
        	var stringName1 = $("#stringName1").val();
        	var stringValue1 = $("#stringValue1").val();
        	
        	var stringName2 = $("#stringName2").val();
        	var stringValue2 = $("#stringValue2").val();
        	
        	var stringName3 = $("#stringName3").val();
        	var stringValue3 = $("#stringValue3").val();
        	
        	var stringName4 = $("#stringName4").val();
        	var stringValue4 = $("#stringValue4").val();
        	
        	$.ajax({
        		type:"POST",
        		url:"searchCollegeTeachingMaterialSetNew.action",
        		datatype:"script",
        		data:{stringName1:stringName1,stringValue1:stringValue1,stringName2:stringName2,stringValue2:stringValue2,
        			stringName3:stringName3,stringValue3:stringValue3,stringName4:stringName4,stringValue4:stringValue4
        			},
        		success:callback
        	});
        	}

        function callback(builder){
        	rows = [];
        	var callbackdate = eval("("+builder+")");
        	for(var objid in callbackdate){
        		var teachingMaterialSetNewInfo = callbackdate[objid];		
        		var row = {teachingMaterialId: teachingMaterialSetNewInfo.teachingMaterialId,
        				setClass: teachingMaterialSetNewInfo.setClass,
        				setTime: teachingMaterialSetNewInfo.setTime, 
        				numberProject:teachingMaterialSetNewInfo.numberProject, 
        				teachingMaterialName:teachingMaterialSetNewInfo.teachingMaterialName,
        				resultsPostedStatus:teachingMaterialSetNewInfo.resultsPostedStatus,
        				submitUser:teachingMaterialSetNewInfo.submitUser,
        				approvedUser:teachingMaterialSetNewInfo.approvedUser,
        				Status: teachingMaterialSetNewInfo.Status,
        				wordsNumbers: teachingMaterialSetNewInfo.wordsNumbers
        				};
        		rows.push(row);
        	}
        	showdata();	
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
                case "Excel":
                	var data = gridManager.getCheckedRows();
                    if (data.length == 0)
                        alert('请选择行!');
                    else
                    {
                    	var checkedIds = [];
                    	$(data).each(function ()
                        {
                    		checkedIds.push(this.teachingMaterialId);
                        });
                    	$.ligerDialog.confirm('确定导出已选择的数据?', function (result)
                    	{
                    		if(result)
                    		{
                    			link = 'downLoadExcelbyFactor/downloadTeachingMaterialSetNewExcel.action?factorName=teachingMaterialId&factorValue='+checkedIds;
                    			downloadExcel(link);
                    		}
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
