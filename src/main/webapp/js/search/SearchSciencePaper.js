
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
                          { display: '论文名称', name: 'PaperName', align: 'left', width: 160, minWidth: 60 },
                          { display: '所在学科', name: 'subjectsIn', align: 'left', width: 60, minWidth: 60 },
                          { display: '发表刊物', name: 'postPublication', align: 'left', width: 60, minWidth: 60 },
                          { display: '收录情况', name: 'includeSituation', align: 'left', width: 60, minWidth: 60 },
                          { display: '出版年月', name: 'publishedTime', align: 'left', width: 60, minWidth: 60 },
                          { display: '卷期号及页码', name: 'titleNumber', align: 'left', width: 100, minWidth: 60 },
                          { display: '奖励等级', name: 'awardingGrades', align: 'left', width: 60, minWidth: 60 },
                          { display: '奖励金额', name: 'totalPrize', align: 'left', width: 60, minWidth: 60 },
                          { display: '扣减金额', name: 'deductionsDistPosts', align: 'left', width: 60, minWidth: 60 },
                          { display: '剩余奖励金额', name: 'actualAward', align: 'left', width: 100, minWidth: 60 },
                          { display: '署名单位', name: 'papersUnits', align: 'left', width: 60, minWidth: 60 },
                          { display: '提交者', name: 'submitUser', align: 'left', width: 60, minWidth: 60,  frozen: true},
                          { display: '审批者', name: 'approvedUser', align: 'left', width: 60, minWidth: 60,  frozen: true},
                      
                             { display: '操作', isAllowHide: false, width: 60, frozen: true,
                             	render: function (row)
                                 {
                             		var html = '<a href="viewSciencePaperDetail.action?infoid='+row.PaperId+'">查看详细</a>';
                                     return html;
                                 }
                             }
                ], dataAction: 'server', data: rows, sortName: 'PaperId',
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
        		url:"searchCollegeSciencePaperInfo.action",
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
        		var sciencePaperInfo1 = callbackdate[objid];		
        		var row = {PaperId: sciencePaperInfo1.PaperId,
        				PaperName: sciencePaperInfo1.PaperName,
        				subjectsIn: sciencePaperInfo1.subjectsIn, 
        				postPublication:sciencePaperInfo1.postPublication, 
        				includeSituation:sciencePaperInfo1.includeSituation,
        				publishedTime:sciencePaperInfo1.publishedTime,
        				titleNumber:sciencePaperInfo1.titleNumber,
        				awardingGrades:sciencePaperInfo1.awardingGrades,
        				totalPrize:sciencePaperInfo1.totalPrize,
        				deductionsDistPosts:sciencePaperInfo1.deductionsDistPosts,
        				actualAward:sciencePaperInfo1.actualAward,
        				papersUnits:sciencePaperInfo1.papersUnits,
        				submitUser:sciencePaperInfo1.submitUser,
        				approvedUser:sciencePaperInfo1.approvedUser,
        				Status: sciencePaperInfo1.Status
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
                    		checkedIds.push(this.paperId);
                        });
                    	$.ligerDialog.confirm('确定导出已选择的数据?', function (result)
                    	{
                    		if(result)
                    		{
                    			link = 'downLoadExcelbyFactor/downloadSciencePaperExcel.action?factorName=paperId&factorValue='+checkedIds;
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
