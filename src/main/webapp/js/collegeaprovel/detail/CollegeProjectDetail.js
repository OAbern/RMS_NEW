/*// JavaScript Document
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
                { display: '旁证材料名称', name: 'uploadProofName', align: 'left', width: 100, minWidth: 60 },
                { display: '上传时间', name: 'timeProofUpload', align: 'left', minWidth: 240 },
                { display: '旁证材料描述', name: 'descProof', align: 'left', minWidth: 140 },
                { display: '文件类型', name: 'uploadContentType', align: 'left', width: 100, minWidth: 60 },
                { display: '操作', isAllowHide: false, width: 100, render: function (row)
                    {
                        var html = '<a href="javascript:showView(\''+row.uploadProofName+'\',750,450,\''+row.proofId+'\',\'iframe:'+row.proofPath+'/'+row.uploadRealName+'\');">预览</a>';
                       
                        return html;

                     }
                },
                { display: '操作', isAllowHide: false, width: 120, render: function (row)
                    {
                		
                        var html='<a href="download.action?proofId='+row.proofId+'"> 下载资料</a>';
                    
                        return html;
                     }
                }, ], dataAction: 'server', data: rows, sortName: 'CustomerID',
                width: '100%', height: 300, pageSize: 30,rownumbers:true,
                checkbox : false,
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
       
        
        function getSelected()

        { 

            var row = manager.getSelectedRow();

            if (!row) { alert('请选择行'); return; }


        }
        var rows = [];
        
        
        
        */
        
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
                                  { display: '上传时间', name: 'timeProofUpload', align: 'left', minWidth: 150 },
                                  { display: '旁证材料描述', name: 'descProof', align: 'left', minWidth: 200 },
                                  { display: '文件类型', name: 'uploadContentType', align: 'left', width: 100, minWidth: 60 },
                                  { display: '操作', isAllowHide: false, width: 65, render: function (row)
                            {
                        	 var html = '<a href="javascript:showView(\''+row.uploadProofName+'\',750,450,\''+row.proofId+'\',\'iframe:'+row.proofPath+'/'+row.uploadRealName+'\');">预览</a>';
                             
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
                
                function checkPassed(){
                	var result1 = confirm("确定审批通过该科研信息？");
                	return result1;
                }
                
                function checkReturnReason(){
                	var returnReason = document.getElementById("returnReason").value;
                	var reason = trim(returnReason);
                	if(reason.length==0){
                		alert("请输入返回理由！");
                		document.form.returnReason.focus();
                		return false;
                	}else{
                		var result0 = confirm("确定审批拒绝该科研信息？");
                		return result0;
                	}
                }
                function trim(sstr)
                {
                var astr="";
                var dstr="";
                var flag=0;
                var i;
                for (i=0;i<sstr.length;i++)
                    {if ((sstr.charAt(i)!=' ')||(flag!=0)) 
                    {dstr+=sstr.charAt(i);
                     flag=1;
                        }
                    }
                flag=0;
                for (i=dstr.length-1;i>=0;i--)
                    {if ((dstr.charAt(i)!=' ')||(flag!=0)) 
                    {astr+=dstr.charAt(i);
                     flag=1; 
                        }
                    }
                dstr="";
                for (i=astr.length-1;i>=0;i--) dstr+=astr.charAt(i);
                return dstr;
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