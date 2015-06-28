
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
            g = manager = $grid = $("#maingrid").ligerGrid({
                columns: [
                          { display: '所在学院', name: 'collegesIn', align: 'left', width: 80, minWidth: 60 },
                          { display: '项目名称', name: 'projectName', align: 'left', width: 160, minWidth: 60 },
                          { display: '奖励等级', name: 'awardingGrades', align: 'left', width: 80, minWidth: 60 },
                          { display: '主要完成单位', name: 'completeUnit', align: 'left', width: 80, minWidth: 60 },
                          { display: '单位奖励金额', name: 'unitAward', align: 'left', width: 80, minWidth: 60 },
                          { display: '个人奖励金额', name: 'personAward', align: 'left', width: 80, minWidth: 60 },
                          { display: '合计奖励金额', name: 'totalAward', align: 'left', width: 80, minWidth: 60 },
                          { display: '提交者', name: 'submitUser', align: 'left', width: 60, minWidth: 60 , frozen: true},
                          { display: '审批者', name: 'approvedUser', align: 'left', width: 60, minWidth: 60 , frozen: true},
                          { display: '操作', isAllowHide: false, width: 60, frozen: true,
                             	render: function (row)
                                 {
                             		var html = '<a href="viewScienceGovernmentAwardDetail.action?infoid='+row.awardId+'">查看详细</a>';
                                     return html;
                                 }
                             }
                ], dataAction: 'server', data: rows, sortName: 'awardId',
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
        	
        	var floatName1 = $("#floatName1").val();
        	var minFloatValue1 = $("#minFloatValue1").val();
        	var maxFloatValue1 = $("#maxFloatValue1").val();
        	
        	var floatName2 = $("#floatName2").val();
        	var minFloatValue2 = $("#minFloatValue2").val();
        	var maxFloatValue2 = $("#maxFloatValue2").val();
        	
        	
        	$.ajax({
        		type:"POST",
        		url:"searchCollegeScienceGovernmentAward.action",
        		datatype:"script",
        		data:{stringName1:stringName1,stringValue1:stringValue1,stringName2:stringName2,stringValue2:stringValue2,
        			stringName3:stringName3,stringValue3:stringValue3,stringName4:stringName4,stringValue4:stringValue4,
        			floatName1:floatName1,minFloatValue1:minFloatValue1,maxFloatValue1:maxFloatValue1,
        			floatName2:floatName2,minFloatValue2:minFloatValue2,maxFloatValue2:maxFloatValue2
        			},
        		success:callback
        	});
        	}

        function callback(builder){
        	rows = [];
        	var callbackdate = eval("("+builder+")");
        	for(var objid in callbackdate){
        		var scienceGovernmentAward1 = callbackdate[objid];		
        		var row = {awardId: scienceGovernmentAward1.awardId,
        				collegesIn: scienceGovernmentAward1.collegesIn,
        				projectName: scienceGovernmentAward1.projectName, 
        				awardingGrades:scienceGovernmentAward1.awardingGrades, 
        				completeUnit:scienceGovernmentAward1.completeUnit,
        				unitAward:scienceGovernmentAward1.unitAward,
        				personAward:scienceGovernmentAward1.personAward,
        				totalAward:scienceGovernmentAward1.totalAward,
        				submitUser:scienceGovernmentAward1.submitUser,
        				approvedUser:scienceGovernmentAward1.approvedUser,
        				Status: scienceGovernmentAward1.Status
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
                    		checkedIds.push(this.awardId);
                        });
                    	$.ligerDialog.confirm('确定导出已选择的数据?', function (result)
                    	{
                    		if(result)
                    		{
                    			link = 'downLoadExcelbyFactor/downloadScienceGovernmentAwardExcel.action?factorName=awardId&factorValue='+checkedIds;
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
