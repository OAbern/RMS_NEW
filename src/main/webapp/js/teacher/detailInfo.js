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

            //表格
            g = manager = $grid = $("#maingrid").ligerGrid({
                columns: [
                { display: '旁证材料名称', name: 'uploadProofName', align: 'left', width: 250, minWidth: 60 },
                { display: '文件类型', name: 'uploadContentType', align: 'left', width: 100, minWidth: 60 },
                { display: '上传时间', name: 'timeProofUpload', align: 'left', minWidth: 150 },
                { display: '旁证材料描述', name: 'descProof', align: 'left', minWidth: 200 },
                { display: '操作', isAllowHide: false, width: 65, render: function (row)
                    {
                        var html = '<a href="javascript:showView(\''+row.uploadProofName+'\',850,500,\''+row.proofId+'\',\'iframe:'+row.proofPath+'/'+row.uploadRealName+'\');">预览</a>';
                       
                        return html;

                     }
                },
                { display: '操作', isAllowHide: false, width: 100, render: function (row)
                    {
                		var html='<a href="download.action?proofId='+row.proofId+'"> 下载资料</a>';
                    
                		return html;

                     }
                }
                ], dataAction: 'server', data: rows, sortName: 'proofId',
                width: '100%', height: 300, pageSize: 30, rownumbers:true,
                checkbox : false,
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
                
                }   
            }
            alert(item.text);
        }
        
        
        function getSelected()

        { 
            var row = manager.getSelectedRow();

            if (!row) { alert('请选择行'); return; }
        }
       
        function showView(title,windth,height,id, concent) {
        	$.XYTipsWindow({
    			___title : title,
    			___content :concent,
    			___width : windth,
    			___height : height,
    			___dray : "___boxTitle",
    			___showbg : true
    		});
    		$('.clearField').clearField();
    	}
        
        function show()
        {
            var jsonObj = {};
            jsonObj.Rows = rows;
            $grid.set({ data: jsonObj });
        }
        
        var rows = [];