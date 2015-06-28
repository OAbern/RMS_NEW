// JavaScript Document

 var alert = function (content)
        {
            $.ligerDialog.alert(content);
        };
        //菜单一
        var menu1 = { width: 120, items:
            [
            { text: '保存', click: itemclick },
            { text: '列存为', click: itemclick },
            { line: true },
            { text: '关闭', click: itemclick }
            ]
        };
        //菜单二
        var menu2 = { width: 100, items:
            [
            {
                text: '文件', children:
                [
                    { text: 'Excel',id:'Excel', click: itemclick },
                    { text: 'Word', id: 'Word', click: itemclick },
                    { text: 'PDF', id: 'PDF', click: itemclick },
                    { text: 'TXT', id: 'TXT', click: itemclick },
                    { line: true },
                    { text: 'XML', id: 'XML', click: itemclick }
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
            //菜单条
            $("#topmenu").ligerMenuBar({ items: [
                { text: '文件', menu: menu1 },
                { text: '导出', menu: menu2 },
                { text: '表格风格', menu: menu3 }
            ]
            });

            //工具条
            $("#toptoolbar").ligerToolBar({ items: [
                { text: '增加', id:'add', click: itemclick },
                { text: '修改', id:'modify', click: itemclick },
                { text: '删除', id:'delete', click: itemclick }
            ]
            });
            //搜索
            //$("#ddlCountry").ligerComboBox();
            $("#searchbtn").ligerButton({ click: function ()
            {
                if (!gridManager) return;
                var Country = $("#ddlCountry").val(); 
                gridManager.setOptions(
                    { parms: [{ name: 'Country', value: Country}] }
                );
                gridManager.loadData(true);
            }
        }); 

            //表格
            $("#maingrid").ligerGrid({
                columns: [
                { display: '项目编号', name: 'Project_ID', align: 'left', width: 100, minWidth: 60 },
                { display: '项目组名称', name: 'Group_Name', align: 'left', minWidth: 240 },
                { display: '项目名称', name: 'Project_Name', align: 'left', minWidth: 140 },
                { display: '上传时间', name: 'Submit_Time' },
                { display: '操作', name: 'View', width: 60 },
                { display: '操作', name: 'Edit' , width: 60}
                ], dataAction: 'server', data: CustomersData, sortName: 'CustomerID',
                width: '100%', height: '100%', pageSize: 30,rownumbers:true,
                checkbox : true,
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6
            });
             
            gridManager = $("#maingrid").ligerGetGridManager();

            $("#pageloading").hide();


        });


        function itemclick(item)
        { 
            if(item.id)
            {
                switch (item.id)
                {
                    case "Aqua":
                        $("#maingrid").removeClass("l-grid-gray");
                        return;
                    case "Gray":
                        $("#maingrid").addClass("l-grid-gray");
                        return;
                    case "modify":
                        var rowsdata = gridManager.getCheckedRows();
                        var str = "";
                        $(rowsdata).each(function ()
                        {
                            str += this.CustomerID + ",";
                        });
                        if (!rowsdata.length) alert('请选择行');
                        else
                            alert(str);
                        return;
                    case "delete":
                        var data = gridManager.getCheckedRows();
                        if (data.length == 0)
                            alert('请选择行');
                        else
                        {
                            var checkedIds = [];
                            $(data).each(function ()
                            {
                                checkedIds.push(this.CustomerID);
                            });
                            $.ligerDialog.confirm('确定删除' + checkedIds.join(',') + '?', function ()
                            {
                                alert('演示数据，不能删除');
                            }); 
                        }
                        return;
                    case "Excel":
                    case "Word":
                    case "PDF":
                    case "TXT":
                    case "XML":
                        $.ligerDialog.waitting('导出中，请稍候...');
                        setTimeout(function ()
                        {
                            $.ligerDialog.closeWaitting();
                            if (item.id == "Excel")
                                $.ligerDialog.success('导出成功');
                            else
                                $.ligerDialog.error('导出失败');
                        }, 1000);
                        return;
                }   
            }
            alert(item.text);
        }
	jQuery().pluginName("plugin called with jQuery");
	$().pluginName("plugin called with $");