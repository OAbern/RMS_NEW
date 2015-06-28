// JavaScript Document

var $grid;
var manager;

 var alert = function (content)
        {
            $.ligerDialog.alert(content);
        };
        //菜单二
        var menu2 = { width: 100, items:
            [
            {
                text: '文件', children:
                [
                    { text: 'Excel',id:'Excel', click: itemclick },
                    { line: true }
                ]
            },
            ]
        };
        //菜单三
        var menu3 = { width: 120, items:
            [
            { text: '灰色',id:'Gray', click: itemclick },
            { text: '浅绿色',id:'Aqua', click: itemclick }
            ]
        };
        var gridManager = null;
        $(function ()
        {
            
            //工具条
            $("#toptoolbar").ligerToolBar({ items: [
                { text: '添加', id:'add', click: itemclick }
            ]
            });
            
            //表格
            manager = $grid = $("#maingrid").ligerGrid({
                columns: [
                { display: '教师号', name: 'userId', minWidth: 100 },
                { display: '姓名', name: 'userName', width: 100 },
                { display: '学院', name: 'cquptCollege', minWidth: 100 },
                { display: '部门', name: 'department', width: 100 },
                { display: '性别', name: 'gender', width: 50 },
                { display: '籍贯', name: 'origin', width: 50 },
                { display: '民族', name: 'nationality', width: 50 },
                { display: '出生日期', name: 'birthday', width: 100 },
                { display: '政治面貌', name: 'politicalStatus', width: 100 },
                { display: '进入重邮时间', name: 'timeBeginCqupt', width: 100 },
                { display: '参加工作时间', name: 'timeBeginWork', width: 100 },
                { display: '第一学位', name: 'firstDegree', width: 100 },
                { display: '第一学位专业名称', name: 'firstProfessionalName', width: 150 },
                { display: '第一学位毕业学校', name: 'firstGraduateSchool', width: 100 },
                { display: '最后学位', name: 'lastDegree', width: 100 },
                { display: '最后学位专业名称', name: 'lastProfessionalName', width: 150 },
                { display: '最后学位毕业学校', name: 'lastGraduateSchool', width: 100 },
                { display: '最后学历', name: 'lastAcademic', width: 150 }
                ], dataAction: 'server', data: rows, sortName: 'userId',
                width: '100%', height: '100%', pageSize: 30,rownumbers:true,
                checkbox : false,
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6
            });
            
            show(); 
            gridManager = $("#maingrid").ligerGetGridManager();

            $("#pageloading").hide();


        });

        function showDetail(id)
        {
        	alert("您选中的ID是" + id);
        }
        
        function itemclick(item)
        { 
            location.href="viewAddUser.action";
        }
       
        function GetxmlhttpObject()
    	{
    	   var xmlhttp;
    	   try
		   {
    		   // Firefox, Opera 8.0+, Safari
    		   xmlhttp = new XMLHttpRequest();
		   }
    	   catch (e)
		   {
    		   // Internet Explorer
    		   try
    		   {
    			   xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    		   }
    		   catch (e)
    		   {
    			   try
    			   {
    				   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    			   }
    			   catch (e)
    			   {
    				   alert("您的浏览器不支持AJAX！");
    				   xmlhttp = false;
    			   }
    		   }
		   }
    	   return xmlhttp;
    	}
        
        function deleteInfo(url)
        {
        	xmlhttp = GetxmlhttpObject();
            xmlhttp.open("POST",url,true);
            xmlhttp.onreadystatechange = showMessage;
            xmlhttp.send(null);
        }
        
        function showMessage(){
        	if(xmlhttp.readyState == 4){
        		$.ligerDialog.waitting('删除中，请稍候...');
        		setTimeout(function ()
                {
        			$.ligerDialog.closeWaitting();
        			if(xmlhttp.responseText == "true"){
        				gridManager.deleteSelectedRow();
        				$.ligerDialog.success('删除成功！');
        			}else{
        				$.ligerDialog.error('删除失败！');
        			}
                },1500);
        	}
        }
        
        function downloadExcel(link)
        {
        	xmlhttp = GetxmlhttpObject();
        	xmlhttp.open("POST",link,true);
        	xmlhttp.send(null);
        }
        
        function getSelected()

        { 

            var row = manager.getSelectedRow();

            if (!row) { alert('请选择行'); return; }


        }
       
	 function show()
     {
         var jsonObj = {};
         
         jsonObj.Rows = rows;
         $grid.set({ data: jsonObj });
     }
	 
	 var rows = [];