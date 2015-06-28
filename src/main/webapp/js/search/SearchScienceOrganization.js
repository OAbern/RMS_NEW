
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
                          { display: '机构名称', name: 'organizationName', align: 'left', width: 160, minWidth: 60 },
                          { display: '机构类型', name: 'organizationType', align: 'left', width: 60, minWidth: 60 },
                          { display: '机构类别', name: 'organizationCategory', align: 'left', width: 60, minWidth: 60 },
                          { display: '学科分类', name: 'sortSubject', align: 'left', width: 60, minWidth: 60 },
                          { display: '组成形式', name: 'modusComposition', align: 'left', width: 60, minWidth: 60 },
                          { display: '从业人员合计', name: 'totalEmployees', align: 'left', width: 80, minWidth: 60 },
                          { display: '博士毕业人数', name: 'doctorEmployees', align: 'left', width: 80, minWidth: 60 },
                          { display: '硕士毕业人数', name: 'masterEmployees', align: 'left', width: 80, minWidth: 60 },
                          { display: '经费内部支出', name: 'internalExpenditures', align: 'left', width: 80, minWidth: 60 },
                          { display: 'R&D支出', name: 'rdExpenditures', align: 'left', width: 60, minWidth: 60 },
                          { display: '承担课题', name: 'numIssueAssume', align: 'left', width: 60, minWidth: 60 },
                          { display: '提交者', name: 'submitUser', align: 'left', width: 60, minWidth: 60 , frozen: true},
                          { display: '审批者', name: 'approvedUser', align: 'left', width: 60, minWidth: 60 , frozen: true},
                             { display: '操作', isAllowHide: false, width: 60, frozen: true,
                             	render: function (row)
                                 {
                             		var html = '<a href="viewScienceOrganizationDetail.action?infoid='+row.organizationId+'">查看详细</a>';
                                     return html;
                                 }
                             }
                ], dataAction: 'server', data: rows, sortName: 'organizationId',
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
        	
        	var floatName1 = $("#floatName1").val();
        	var minFloatValue1 = $("#minFloatValue1").val();
        	var maxFloatValue1 = $("#maxFloatValue1").val();
        	
        	var floatName2 = $("#floatName2").val();
        	var minFloatValue2 = $("#minFloatValue2").val();
        	var maxFloatValue2 = $("#maxFloatValue2").val();
        	
        	var floatName3 = $("#floatName3").val();
        	var minFloatValue3 = $("#minFloatValue3").val();
        	var maxFloatValue3 = $("#maxFloatValue3").val();
        	
        	$.ajax({
        		type:"POST",
        		url:"searchCollegeScienceOrganization.action",
        		datatype:"script",
        		data:{stringName1:stringName1,stringValue1:stringValue1,stringName2:stringName2,stringValue2:stringValue2,
        			floatName1:floatName1,minFloatValue1:minFloatValue1,maxFloatValue1:maxFloatValue1,
        			floatName2:floatName2,minFloatValue2:minFloatValue2,maxFloatValue2:maxFloatValue2,
        			floatName3:floatName3,minFloatValue3:minFloatValue3,maxFloatValue3:maxFloatValue3
        			},
        		success:callback
        	});
        }

        function callback(builder){
        	rows = [];
        	var callbackdate = eval("("+builder+")");
        	for(var objid in callbackdate){
        		var scienceOrganization = callbackdate[objid];		
        		var row = {organizationId: scienceOrganization.organizationId,
        				organizationName: scienceOrganization.organizationName,
        				organizationType: scienceOrganization.organizationType, 
        				organizationCategory:scienceOrganization.organizationCategory, 
        				sortSubject:scienceOrganization.sortSubject,
        				modusComposition:scienceOrganization.modusComposition,
        				totalEmployees:scienceOrganization.totalEmployees,
        				doctorEmployees:scienceOrganization.doctorEmployees,
        				masterEmployees:scienceOrganization.masterEmployees,
        				internalExpenditures:scienceOrganization.internalExpenditures,
        				rdExpenditures:scienceOrganization.rdExpenditures,
        				numIssueAssume:scienceOrganization.numIssueAssume,
        				submitUser:scienceOrganization.submitUser,
        				approvedUser:scienceOrganization.approvedUser,
        				Status: scienceOrganization.Status
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
                        		checkedIds.push(this.organizationId);
                            });
                        	$.ligerDialog.confirm('确定导出已选择的数据?', function (result)
                        	{
                        		if(result)
                        		{
                        			link = 'downLoadExcelbyFactor/downloadScienceOrganizationExcel.action?factorName=organizationId&factorValue='+checkedIds;
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
