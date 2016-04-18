﻿var indexdata = 
[
    { text: '基础',isexpand:false, children: [ 
		{url:"demos/base/resizable.htm",text:"改变大小"},
		{url:"demos/base/drag.htm",text:"拖动"},
		{url:"demos/base/drag2.htm",text:"拖动2"},
		{url:"demos/base/dragresizable.htm",text:"拖动并改变大小"},
		{url:"demos/base/tip.htm",text:"气泡"},
		{url:"demos/base/tip2.htm",text:"气泡2"}
	]
    },
    { text: '过滤器', isexpand: false, children: [
		{ url: "demos/filter/filter.htm", text: "自定义查询" },
		{ url: "demos/filter/filterwin.htm", text: "在窗口显示" },
		{ url: "demos/filter/grid.htm", text: "配合表格" } 
	]
    }, 
	{ text: '弹窗',isexpand:false, children: [ 
		{url:"demos/dialog/dialogAll.htm",text:"弹出框"},
		{url:"demos/dialog/dialogTarget.htm",text:"载入目标DIV"},
		{url:"demos/dialog/dialogUrl.htm",text:"窗口"}, 
		{url:"demos/dialog/tip.htm",text:"右下角的提示"}, 
		{url:"demos/dialog/window.htm",text:"可最小化"}
	]},
	{ text: '菜单',isexpand:false, children: [  
		{url:"demos/menu/evenmenu.htm",text:"事件支持"},
		{url:"demos/menu/menubar.htm",text:"菜单条/工具条"}, 
		{url:"demos/menu/mulmenu.htm",text:"多个菜单同时存在"}
	]},
	{ text: '下拉框',isexpand:false, children: [  
		{url:"demos/comboBox/comboBoxSelect.htm",text:"select表单"},
		{url:"demos/comboBox/comboBoxSingle.htm",text:"单选"},
		{url:"demos/comboBox/comboBoxSingleCheckBox.htm",text:"单选(复选框)"},
		{url:"demos/comboBox/comboBoxMul.htm",text:"多选"},
		{url:"demos/comboBox/comboBoxTable.htm",text:"表格"},
		{url:"demos/comboBox/comboBoxTableMul.htm",text:"表格(多选)"},
		{url:"demos/comboBox/comboBoxInterface.htm",text:"接口方法"},
		{url:"demos/comboBox/comboBoxEven.htm",text:"事件支持"},
		{url:"demos/comboBox/comboBoxCase001.htm",text:"联动效果"},
		{url:"demos/comboBox/comboBoxTree.htm",text:"下拉框 - 树"},
		{url:"demos/comboBox/comboBoxGrid.htm",text:"下拉框 - 分页表格"},
		{url:"demos/comboBox/comboBoxPop.htm",text:"下拉框 - 新页面选取数据"}
	]},
	{ text: '树',isexpand:false, children: [  
		{url:"demos/tree/icon.htm",text:"节点图标"},  
		{url:"demos/tree/draggable.htm",text:"可拖拽"}, 
		{url:"demos/tree/case/twotree.htm",text:"两个树 可拖拽"}, 
		{url:"demos/tree/expandable.htm",text:"可扩展支持"}, 
		{url:"demos/tree/treehtml.htm",text:"树(html初使化)"},
		{url:"demos/tree/treedata.htm",text:"树(data初使化)"},
		{url:"demos/tree/treeurl.htm",text:"树(url初使化)"},
		{url:"demos/tree/treeedit.htm",text:"可编辑"},
		{url:"demos/tree/treeselect.htm",text:"树 选择节点"},
		{url:"demos/tree/treemanager.htm",text:"接口方法"},
		{url:"demos/tree/treeeven.htm",text:"丰富的事件支持"},
		{url:"demos/tree/treecase.htm",text:"模拟异步动态加载节点"},
		{url:"demos/tree/treemenu.htm",text:"右键菜单"},
		{url:"demos/tree/treecheckboxinit.htm",text:"选择初始化"},
		{url:"demos/tree/treedbdata.htm",text:"ID PID数据格式"}
	]},
	{ text: '表单', isexpand: false, children: [
        { url: "demos/form/v118/autoform.htm", text: "自动创建表单(新)" },
        { url: "demos/form/v118/autoform2.htm", text: "自动创建表单2" },
		{url:"demos/form/button.htm",text:"按钮"},
		{url:"demos/form/checkbox.htm",text:"复选框"},
		{url:"demos/form/checkbox2.htm",text:"复选框2"},
		{url:"demos/form/radio.htm",text:"单选框"},
		{url:"demos/form/radio2.htm",text:"单选框2"},
		{url:"demos/form/spinner.htm",text:"调整器"},
		{url:"demos/form/spinner2.htm",text:"调整器2"},
		{url:"demos/form/dateEditor.htm",text:"日期"}, 
		{url:"demos/form/textbox.htm",text:"文本框"},
		{url:"demos/form/textbox2.htm",text:"文本框2"},
		{url:"demos/form/form1.htm",text:"综合"}, 
		{url:"demos/form/validator/errorLabelContainer.htm",text:"表单验证1"},
		{url:"demos/form/validator/form2.htm",text:"表单验证2"},
		{url:"demos/form/validator/form3.htm",text:"表单验证3"},
		{url:"demos/form/validator/form4.htm",text:"表单验证4"},
		{url:"demos/form/validator/invalidHandler.htm",text:"表单验证5"}
	]},
	{ isexpand: "false", text: "表格", children: [
        { isexpand: "false", text: "搜索支持", children: [
		    { url: "demos/filter/grid.htm", text: "高级自定义查询" },
            { url: "demos/grid/search/search.htm", text: "查询 表格" }
	    ] 
        }, 
	    {isexpand:"false",text:"固定列",children:[ 
		    {url:"demos/grid/frozen/frozengrid.htm",text:"固定列"}, 
		    {url:"demos/grid/frozen/treefrozengrid.htm",text:"兼容树"}, 
		    {url:"demos/grid/frozen/mulheaders.htm",text:"兼容多表头"}
	    ]}, 
	    {isexpand:"false",text:"可扩展支持",children:[ 
		    {url:"demos/grid/expandable/method.htm",text:"方法"}, 
		    {url:"demos/grid/expandable/editor.htm",text:"编辑器"},
		    {url:"demos/grid/expandable/editor_numberbox.htm",text:"编辑器2"},
		    {url:"demos/grid/expandable/formatter.htm",text:"格式化器"},
		    {url:"demos/grid/expandable/sorter.htm",text:"自定义排序"}
	    ]}, 
	    {isexpand:"false",text:"编辑(行)",children:[ 
		    {url:"demos/grid/editrow/editrow.htm",text:"编辑"}, 
		    {url:"demos/grid/editrow/editrow2.htm",text:"编辑2"}
	    ]},
	    {isexpand:"false",text:"编辑(明细)",children:[ 
		    {url:"demos/grid/editdetail/detail.htm",text:"编辑"}, 
		    {url:"demos/grid/editdetail/detail2.htm",text:"编辑2"}
	    ]},
	    {isexpand:"false",text:"编辑表格",children:[ 
		    {url:"demos/grid/editgrid/addrow.htm",text:"编辑支持"}, 
		    {url:"demos/grid/editgrid/addrowcombobox.htm",text:"下拉框在弹出框选择"},
		    {url:"demos/grid/editgrid/addrowwithdata.htm",text:"增加行"},
		    {url:"demos/grid/editgrid/addrowwithdata2.htm",text:"增加行2"}, 
		    {url:"demos/grid/editgrid/editgrideven.htm",text:"编辑器事件"},
		    {url:"demos/grid/editgrid/editgridupdatecell.htm",text:"更新单元格"}, 
		    {url:"demos/grid/editgrid/updaterow.htm",text:"更新行"}, 
		    {url:"demos/grid/editgrid/getdata.htm",text:"获取更新数据"}
	    ]},
	    {isexpand:"false",text:"复选框支持",children:[ 
		    {url:"demos/grid/checkbox/default.htm",text:"带复选框"}, 
		    {url:"demos/grid/checkbox/init.htm",text:"复选框初始化"}, 
		    {url:"demos/grid/checkbox/memory.htm",text:"复选框分页记忆"}
	    ]}, 
	    {isexpand:"false",text:"表头/列",children:[ 
		    {url:"demos/grid/header/changeheadertext.htm",text:"改变表头文本"}, 
		    {url:"demos/grid/header/columnpercentage.htm",text:"列 宽度百分比"}, 
		    {url:"demos/grid/header/hidecolumn.htm",text:"显示/隐藏 列"},  
		    {url:"demos/grid/header/setcolumnwidth.htm",text:"列 调整宽度"},  
		    {url:"demos/grid/mulheader/grid1.htm",text:"多表头1"},
		    {url:"demos/grid/mulheader/grid2.htm",text:"多表头2"},
		    {url:"demos/grid/mulheader/grid3.htm",text:"多表头3"}, 
		    {url:"demos/grid/header/setcolumns.htm",text:"动态设置表头"}
	    ]}, 
	    {isexpand:"false",text:"汇总",children:[ 
		    {url:"demos/grid/total/totalgrid.htm",text:"汇总表格"},
		    {url:"demos/grid/total/totalgrid2.htm",text:"汇总表格2"},
		    {url:"demos/grid/total/totalgridall.htm",text:"汇总表格3(全部数据)"},
		    {url:"demos/grid/total/group.htm",text:"带分组"}
	    ]}, 
	    {isexpand:"false",text:"行事件",children:[ 
		    {url:"demos/grid/rowgrid/allowUnSelectRow.htm",text:"选择行(支持Ctrl)"}, 
		    {url:"demos/grid/rowgrid/checkrowgrid.htm",text:"选择行支持(复选框)"}, 
		    {url:"demos/grid/rowgrid/selectRowButtonOnly.htm",text:"点击复选框才能选择行"}, 
		    {url:"demos/grid/rowgrid/selectrowgrid.htm",text:"选择行支持"},                                                  {url:"demos/grid/rowgrid/dbclickrowgrid.htm",text:"双击行支持"}, 
		    {url:"demos/grid/rowgrid/contextmenurowgrid.htm",text:"右击支持"} 
	    ]}, 
	    {isexpand:"false",text:"分组",children:[
		    { url: "demos/grid/groupable/default.htm", text: "默认" },
            { url: "demos/grid/groupable/render.htm", text: "自定义格式" },   
		    {url:"demos/grid/groupable/checkbox.htm",text:"带复选框"},   
		    {url:"demos/grid/groupable/detail.htm",text:"明细"},  
		    {url:"demos/grid/groupable/total.htm",text:"汇总"} 
	    ]}, 
	    {isexpand:"false",text:"树",children:[ 
		    {url:"demos/grid/treegrid/treegrid.htm",text:"树 表格"},
		    {url:"demos/grid/treegrid/editable.htm",text:"树 可编辑"}, 
		    {url:"demos/grid/treegrid/draggable.htm",text:"树 可拖拽"} 
	    ]}, 
	    {isexpand:"false",text:"基本功能",children:[ 
		    {url:"demos/grid/base/toolbar.htm",text:"带工具条"}, 
		    {url:"demos/grid/base/rownumbers.htm",text:"行序号"},
		    {url:"demos/grid/base/fullgrid.htm",text:"百分比高度表格"}, 
		    {url:"demos/grid/base/templategrid.htm",text:"自定义单元格"},  
		    {url:"demos/grid/base/normalgrid.htm",text:"高度宽度设置"},   
		    {url:"demos/grid/base/delaydata.htm",text:"延时加载"}, 
		    {url:"demos/grid/base/draggable.htm",text:"多行移位"},
		    {url:"demos/grid/base/draggable2.htm",text:"行移位、表头拖拽"},
		    {url:"demos/grid/base/twogriddraggable.htm",text:"两个表格拖拽"}
	    ]}, 
	    {isexpand:"false",text:"明细",children:[ 
		    {url:"demos/grid/detailgrid/default.htm",text:"明细 表格"}, 
		    {url:"demos/grid/detailgrid/height.htm",text:"设置高度"}
	    ]}
    ]},
    {isexpand:"false",text:"Tab",children:[ 
	    {url:"demos/tab/tabHtml.htm",text:"Tab"},
	    {url:"demos/tab/tabEven.htm",text:"事件"},
	    {url:"demos/tab/tabManager.htm",text:"接口"}
    ]},
    {isexpand:"false",text:"面板",children:[ 
	    {url:"demos/accordion/accordion.htm",text:"面板"}
    ]},
    {isexpand:"false",text:"布局",children:[ 
	    {url:"demos/layout/layoutMinWidth.htm",text:"最小宽度"},
	    {url:"demos/layout/layoutAutoHeight.htm",text:"自动高度"},
	    {url:"demos/layout/layoutAutoHeightAndDiff.htm",text:"高度补差"},
	    {url:"demos/layout/layoutCenterOnly.htm",text:"只显示中间部分"},
	    {url:"demos/layout/layoutFixedHeight.htm",text:"固定高度"},
	    {url:"demos/layout/layoutFullHeight.htm",text:"全屏高度"},
	    {url:"demos/layout/layoutHalfHeight.htm",text:"百分比高度"},
	    {url:"demos/layout/layoutLeftMiddleOnly.htm",text:"只显示左侧和中间"},
	    {url:"demos/layout/layoutLeftWidth.htm",text:"限制左边宽度"},
	    {url:"demos/layout/layoutLeftHide.htm",text:"左边刚开始隐藏"}, 
	    {url:"demos/layout/layoutHideToggle.htm",text:"左边右边不允许隐藏"},  
	    {url:"demos/layout/layoutResizeDisable.htm",text:"左边底部不允许调整大小"}  
    ]}
];


var indexdata2 =
[
    { isexpand: "true", text: "表格", children: [
        { isexpand: "true", text: "可排序", children: [
		    { url: "dotnetdemos/grid/sortable/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/sortable/server.aspx", text: "服务器" }
	    ]
        },
        { isexpand: "true", text: "可分页", children: [
		    { url: "dotnetdemos/grid/pager/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/pager/server.aspx", text: "服务器" }
	    ]
        },
        { isexpand: "true", text: "树表格", children: [
		    { url: "dotnetdemos/grid/treegrid/tree.aspx", text: "树表格" } 
	    ]
        }
    ]
    }
];
