
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
                { display: '学术会议名称', name: 'academicMeetingName', align: 'left', width: 160, minWidth: 60 },
                { display: '出版时间/期号', name: 'hostUnit', align: 'left', width: 100, minWidth: 60 },
                { display: '主办单位', name: 'meetingClassify', align: 'left', width: 100, minWidth: 60 },
                { display: '举办时间', name: 'holdingTime', align: 'left', width: 100, minWidth: 60 },
                { display: '会议地点', name: 'meetingLocation', align: 'left', width: 100, minWidth: 60 },
                { display: '参加人数', name: 'participantsNumber', align: 'left', width: 100, minWidth: 60 },
                { display: '提交者', name: 'submitUser', align: 'left', width: 60, minWidth: 60 , frozen: true},
                { display: '审批者', name: 'approvedUser', align: 'left', width: 60, minWidth: 60 , frozen: true},
                { display: '操作', isAllowHide: false, width: 60, frozen: true,
                	render: function (row)
                    {
                		var html = '<a href="viewHumanitiesAcademicMeetingDetail.action?infoid='+row.academicMeetingId+'">查看详细</a>';
                        return html;
                    }
                }
                ], dataAction: 'server', data: rows, sortName: 'academicMeetingId',
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
        	
        	var dateName = $("#dateName").val();
        	var begin = $("#begin").val();
        	var end = $("#end").val();
        	
        	
        	$.ajax({
        		type:"POST",
        		url:"searchCollegeHumanitiesAcademicMeeting.action",
        		datatype:"script",
        		data:{stringName1:stringName1,stringValue1:stringValue1,stringName2:stringName2,stringValue2:stringValue2,
        			stringName3:stringName3,stringValue3:stringValue3,stringName4:stringName4,stringValue4:stringValue4,
        			dateName:dateName,begin:begin,end:end
        			},
        		success:callback
        	});
        	}

        function callback(builder){
        	rows = [];
        	var callbackdate = eval("("+builder+")");
        	for(var objid in callbackdate){
        		var humanitiesAcademicMeeting = callbackdate[objid];		
        		var row = {academicMeetingId: humanitiesAcademicMeeting.academicMeetingId,
        				academicMeetingName: humanitiesAcademicMeeting.academicMeetingName,
        				hostUnit: humanitiesAcademicMeeting.hostUnit, 
        				meetingClassify:humanitiesAcademicMeeting.meetingClassify, 
        				holdingTime:humanitiesAcademicMeeting.holdingTime,
        				meetingLocation:humanitiesAcademicMeeting.meetingLocation,
        				participantsNumber:humanitiesAcademicMeeting.participantsNumber,
        				submitUser:humanitiesAcademicMeeting.submitUser,
        				approvedUser:humanitiesAcademicMeeting.approvedUser,
        				Status: humanitiesAcademicMeeting.Status
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
                        		checkedIds.push(this.academicMeetingId);
                            });
                        	$.ligerDialog.confirm('确定导出数据已选择的数据?', function (result)
                        	{
                        		if(result)
                        		{
                        			link = 'downLoadExcelbyFactor/downloadHumanitiesAcademicMeetingExcel.action?factorName=academicMeetingId&factorValue='+checkedIds;
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
