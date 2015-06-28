     
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
                columns: [
                          { display: '教材等级', name: 'setClass', align: 'left', width: 100, minWidth: 60 },
                          { display: '教材名称', name: 'teachingMaterialName', align: 'left', minWidth: 140 },
                          { display: '类别', name: 'numberProject', align: 'left', minWidth: 140 },
                          { display: '出版单位', name: 'resultsPostedStatus', align: 'left', minWidth: 140 },
                          { display: '出版日期', name: 'setTime', align: 'left', minWidth: 140 },
                          { display: '字数（千字）', name: 'wordsNumbers', align: 'left', minWidth: 140 },
                          { display: '备注', name: 'remarks', align: 'left', minWidth: 140 },
                          { display: '奖励金额', name: 'collegeAward', align: 'left', minWidth: 140 },
                          { display: '提交者', name: 'submitUser', align: 'left', minWidth: 80 },
                          { display: '审批者', name: 'approvedUser', align: 'left', minWidth: 80 },
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
                             		var html = '<a href="collegeAchNewStatus/teachingMaterialSetNew.action?id='+row.teachingMaterialId+'&type='+row.type+'">查看详细</a>';
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