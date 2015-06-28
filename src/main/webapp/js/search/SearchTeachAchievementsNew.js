
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
                { display: '期刊类别', name: 'classAchievements', align: 'left', width: 100, minWidth: 60 },
                { display: '期刊名称', name: 'levelAchievements', align: 'left', minWidth: 140 },
                { display: '论文名称', name: 'projectName', align: 'left', minWidth: 140 },
                { display: '是否为第一负责人', name: 'firstChargeMan', align: 'left', minWidth: 140 },
                { display: '作者排名', name: 'authorRank', align: 'left', minWidth: 140 },
                { display: '出版单位', name: 'publisher', align: 'left', minWidth: 140 },
                { display: '出版日期', name: 'timeAchievements', align: 'left', minWidth: 140 },
                { display: '字数', name: 'wordsNumber', align: 'left', minWidth: 140 },
                { display: '备注', name: 'remarks', align: 'left', minWidth: 140 },
                { display: '奖励金额', name: 'collegeAward', align: 'left', minWidth: 140 },
                { display: '提交者', name: 'submitUser', align: 'left', width: 80, minWidth: 60 , frozen: true},
                { display: '审批者', name: 'approvedUser', align: 'left', width: 80, minWidth: 60 , frozen: true},
                { display: '操作', isAllowHide: false, width: 80, frozen: true,
                   	render: function (row)
                       {
                   		var html = '<a href="viewTeachAchievementsNewDetail.action?infoid='+row.achievementsId+'">查看详细</a>';
                           return html;
                       }
                   }
                ], dataAction: 'server', data: rows, sortName: 'achievementsId',
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
               	
        	
        	$.ajax({
        		type:"POST",
        		url:"searchCollegeTeachAchievementsNew.action",
        		datatype:"script",
        		data:{stringName1:stringName1,stringValue1:stringValue1,stringName2:stringName2,stringValue2:stringValue2,
        			stringName3:stringName3,stringValue3:stringValue3,stringName4:stringName4,stringValue4:stringValue4,
        			floatName1:floatName1,minFloatValue1:minFloatValue1,maxFloatValue1:maxFloatValue1
        			},
        		success:callback
        	});
        	}

        function callback(builder){
        	rows = [];
        	var callbackdate = eval("("+builder+")");
        	for(var objid in callbackdate){
        		var teachAchievementsNewInfo = callbackdate[objid];		
        		var row = {achievementsId: teachAchievementsNewInfo.achievementsId,
        				classAchievements: teachAchievementsNewInfo.classAchievements,
        				projectName: teachAchievementsNewInfo.projectName, 
        				levelAchievements:teachAchievementsNewInfo.levelAchievements, 
        				timeAchievements:teachAchievementsNewInfo.timeAchievements,
        				collegeAward:teachAchievementsNewInfo.collegeAward,
        				submitUser:teachAchievementsNewInfo.submitUser,
        				approvedUser:teachAchievementsNewInfo.approvedUser,
        				Status: teachAchievementsNewInfo.Status,
        				firstChargeMan:teachAchievementsNewInfo.firstChargeMan, 
        				authorRank:teachAchievementsNewInfo.authorRank,
        				wordsNumber:teachAchievementsNewInfo.wordsNumber
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
                    		checkedIds.push(this.achievementsId);
                        });
                    	$.ligerDialog.confirm('确定导出已选择的数据?', function (result)
                    	{
                    		if(result)
                    		{
                    			link = 'downLoadExcelbyFactor/downloadTeachAchievementsNewExcel.action?factorName=achievementsId&factorValue='+checkedIds;
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
