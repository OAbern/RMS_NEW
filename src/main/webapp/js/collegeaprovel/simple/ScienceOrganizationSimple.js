     
// JavaScript Document

     
var $grid;
var manager, g;

 var alert = function (content)
        {
            $.ligerDialog.alert(content);
        };
        
        var gridManager = null;
        $(function ()
        {  var gradeType = [{ Status: 0, text: '已保存' }, { Status: 1, text: '未审批'},{ Status: 2, text: '审批通过'},{ Status: 3, text: '审批未通过'}];
            //表格
            g = manager = $grid = $("#maingrid").ligerGrid({
                columns:  [
                           { display: '提交者', name: 'Upload_Name', align: 'left', width: 100, minWidth: 60 },
                           { display: '机构名称', name: 'Project_Name', align: 'left', minWidth: 140 },
                           { display: '审批状态', name: 'Status', width: 120 ,
                           	
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
                              		var html = '<a href="collegeTecStatus/scienceOrganization.action?id='+row.Project_ID+'&type='+row.type+'">查看详细</a>';
                                      return html;
                                  }
                              }
                           ], dataAction: 'server', data: rows, sortName: 'CustomerID',
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

    
	 function show()
     {
         var jsonObj = {};
         
         jsonObj.Rows = rows;
         $grid.set({ data: jsonObj }); 
     }
	   
	    
     var rows = [];