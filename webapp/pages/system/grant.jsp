<%@ page import="com.cqupt.mis.rms.utils.RequestConstant" %>
<%@ page import="com.cqupt.mis.rms.model.CQUPTRole" %>
<%@ page import="com.cqupt.mis.rms.utils.JSONUtils" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/5
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <title>角色权限授权</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap-3.3.5/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/SB-admin-2-1.0.8/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="css/SB-admin-2-1.0.8/bower_components/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Ztree CSS -->
    <link href="css/ztree-v3/metroStyle/metroStyle.css" rel="stylesheet">
    <!-- Bern Custom CSS -->
    <link href="css/ifram-common.css" rel="stylesheet">

</head>
<body>
<%
    CQUPTRole role = (CQUPTRole) request.getAttribute(RequestConstant.ROLE);
%>
<textarea hidden="true" id="roleJSON"><%=JSONUtils.toJSONString(role) %></textarea>

<!-- Modal -->
<div class="modal fade" id="grantModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title"><label class="text-danger">警告：</label>您正在执行一个严肃的操作！</h5>
            </div>
            <div class="modal-body">
                <p class="text-danger">你确定要修改这个角色的权限么？</p>
                <p class="text-info">增加的静态资源权限有：<label id="fAdd"></label></p>
                <p class="text-info">删除的静态资源权限有：<label id="fDel"></label></p>
                <p class="text-info">增加的动态态资源权限有：<label id="dAdd"></label></p>
                <p class="text-info">删除的动态资源权限有：<label id="dDel"></label></p>
            </div>
            <div class="modal-footer">
                <form action="roleandauth/grant.do" method="post">
                <button type="button" class="btn btn-default" data-dismiss="modal">再改改</button>

                    <input name="json" id="changedAuthJson" type="hidden" required>
                    <button class="btn btn-danger" type="submit">确定修改</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <h2 class="page-header">角色权限分配</h2>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-red">
            <div class="panel-heading">
                '系统管理员功能'中'角色权限管理'是系统中的最高权限，如果勾选，则视为这个角色拥有最高权限！慎待之！
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="alert alert-success">
                    <h4 id="roleNameShow" style="color:red">您当前正在进行授权的角色名为：</h4>
                </div>

                <div class="row" align="center" style="margin-bottom: 20px">
                    <button class="btn btn-danger" onclick="grantModal()">分配权限</button>
                </div>

                <div style="clear: both"></div>
                <div class="panel panel-yellow" style="float: left; width: 45%; margin-right: 10%;">
                    <div class="panel-heading">
                        静态资源
                    </div>
                    <div class="panel-body">
                        <ul id="FTree" class="ztree"></ul>
                    </div>
                </div>

                <div class="panel panel-yellow" style="float: left; width: 45%">
                    <div class="panel-heading">
                        动态资源
                    </div>
                    <div class="panel-body">
                        <ul id="DTree" class="ztree"></ul>
                    </div>
                </div>

                <div style="clear: both"></div>

                <div class="alert alert-warning alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4>分配操作的按钮在顶部哦</h4>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-6 -->
</div>
<!-- /.row -->

<!-- jQuery -->
<script src="js/jquery-1.12.3/jquery-1.12.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap-3.3.5/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/SB-admin-2-1.0.8/sb-admin-2.js"></script>

<!-- Ztree JavaScript -->
<script src="js/ztree-v3/jquery.ztree.core.min.js"></script>

<!-- Ztree JavaScript -->
<script src="js/ztree-v3/jquery.ztree.excheck.min.js"></script>
<script>
    var role = JSON.parse($('#roleJSON').val());
    $('#roleNameShow').append(role.roleName);

    var map = null;    //各种资源和权限map

    $.ajax({       //获取当前系统中所有角色信息
        url:"roleandauth/authority/"+role.roleId+".do",
        type:"get",
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        async: false,       //必须同步执行，否则ajax后面的代码执行会有问题
        success:function(data){
            if(data == null) {
                alert("获取权限信息失败！");
            }else {
                map = data;
            }
        },
        error:function() {
            alert("获取权限信息失败！");
        }
    });

    var fResList = map.fixedRes;
    var dResList = map.dynamicRes;
    var fAuthList = map.fixedAuth;
    var dAuthList = map.dynamicAuth;
    var fResMap = {};       //静态资源列表
    var dResMap = {};       //动态资源列表
    var fAuthMap = {};      //静态权限的Map
    var dAuthMap = {};      //动态权限的Map

    //构造静态资源列表,形如{ $pId:{ "name":'', "cIds":[ { "id":1, "name":"" } ] } }
    for(var i=0; i<fResList.length; i++) {
        var fr = fResList[i];
        var id = fr.resourceId;
        var pId = fr.parentId;
        var name = fr.resourceName;
        if(pId == 0) {      //资源父节点
            if(typeof(fResMap[id+'']) == "undefined") {   //父节点没有创建
                fResMap[id+''] = {'name': name, 'cIds':[]};
            }else {
                fResMap[id+'']['name'] = name;
            }

        }else {     //子节点
            if(typeof(fResMap[pId+'']) == "undefined") {   //父节点不存在，创建父节点
                fResMap[pId+''] = {'cIds':[{"id":id, "name":name}]};
            }else {
                fResMap[pId+'']['cIds'].push( {"id":id, "name":name} );
            }
        }
    }

    //构造动态资源列表,形如{ $pId:{ "name":'', "cIds":[ { "id":1, "name":"" } ] } }
    for(var i=0; i<dResList.length; i++) {
        var dr = dResList[i];
        var id = dr.classId;
        var pId= dr.parentId;
        var name = dr.className;
        if(pId == 0) {      //资源父节点
            //console.log(typeof(dResMap[id+'']));
            if(typeof(dResMap[id+'']) == "undefined") {   //父节点没有创建
                dResMap[id+''] = {'name': name, 'cIds':[]};
            }else {
                dResMap[id+'']['name'] = name;
            }

        }else {     //子节点
            if(typeof(dResMap[pId+'']) == "undefined") {   //父节点不存在，创建父节点
                dResMap[pId+''] = {'cIds':[{"id":id, "name":name}]};
            }else {
                dResMap[pId+'']['cIds'].push( {"id":id, "name":name} );
            }
        }
    }

    //构造静态权限的Map,形如 {静态资源id1:true, 静态资源id2:true}
    for(var i=0; i<fAuthList.length; i++) {
        var fa = fAuthList[i];
        var resId = fa.purviewinfo.resourceId +'';
        fAuthMap[resId] = true;
    }

    //构造动态权限的Map,形如{ 动态资源id1:{input:true, manage:false, approve:true, statistics:false} }
    for(var i=0; i<dAuthList.length; i++) {
        var da = dAuthList[i];
        var classId = da.researchClass.classId +'';
        var obj = {};
        obj['input'] = da.input;
        obj['manage'] = da.manage;
        obj['approve'] = da.approve;
        obj['statistics'] = da.statistics;
        dAuthMap[classId+''] = obj;
    }


    //构造静态资源的节点数据
    var fNodes = [];
    for(var pId in fResMap) {      //遍历属性
        var pRes = fResMap[pId];
        fNodes.push({id:pId, pId:0, name:pRes.name});
        for(var i=0; i<pRes.cIds.length; i++) {
            var cRes = pRes.cIds[i];
            if(fAuthMap[cRes.id+''] == true) {      //已经授权
                fNodes.push({id:cRes.id, pId:pId, name:cRes.name, checked:true});
            }else {
                fNodes.push({id:cRes.id, pId:pId, name:cRes.name});
            }
        }
    }

    //构造动态资源的节点数据
    var dNodes = [];
    for(var pId in dResMap) {      //遍历属性
        var pRes = dResMap[pId];
        dNodes.push({id:pId, pId:0, name:pRes.name});
        for(var i=0; i<pRes.cIds.length; i++) {
            var cRes = pRes.cIds[i];
            dNodes.push({id:cRes.id, pId:pId, name:cRes.name});
            var dAuth = dAuthMap[cRes.id+''];
            var id = pId + '-' + cRes.id;
            if(typeof(dAuth) == "undefined") {      //未经授权
                dNodes.push({id:id, pId:cRes.id, name:'录入'});
                dNodes.push({id:id, pId:cRes.id, name:'管理个人'});
                dNodes.push({id:id, pId:cRes.id, name:'审批'});
                dNodes.push({id:id, pId:cRes.id, name:'统计查询'});
            }else {
                if(dAuth.input) {
                    dNodes.push({id:id, pId:cRes.id, name:'录入',checked:true});
                }else {
                    dNodes.push({id:id, pId:cRes.id, name:'录入'});
                }

                if(dAuth.manage) {
                    dNodes.push({id:id, pId:cRes.id, name:'管理个人',checked:true});
                }else {
                    dNodes.push({id:id, pId:cRes.id, name:'管理个人'});
                }

                if(dAuth.approve) {
                    dNodes.push({id:id, pId:cRes.id, name:'审批',checked:true});
                }else {
                    dNodes.push({id:id, pId:cRes.id, name:'审批'});
                }

                if(dAuth.statistics) {
                    dNodes.push({id:id, pId:cRes.id, name:'统计查询',checked:true});
                }else {
                    dNodes.push({id:id, pId:cRes.id, name:'统计查询'});
                }
            }
        }
    }

    /*
    var zNodes =[
        { id:1, pId:0, name:"父节点1", open:true},
        { id:11, pId:1, name:"父节点11", open:true},
        { id:111, pId:11, name:"叶子节点111"},
        { id:112, pId:11, name:"叶子节点112"},
        { id:113, pId:11, name:"叶子节点113"},
        { id:114, pId:11, name:"叶子节点114"}
    ];
    */
    //zTree 配置
    var setting = {
        view: {
            selectedMulti: false,
            fontCss: setFontCss
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        callback: {
            onExpand: window.parent.iFrameHeight
        }
    };

    $.fn.zTree.init($("#FTree"), setting, fNodes);      //初始化静态权限树
    $.fn.zTree.init($("#DTree"), setting, dNodes);      //初始化动态权限树
    window.parent.iFrameHeight();   //iframe自适应高度

    var newCount = 1;

    /**
     * 设置字体颜色
     * @param treeId
     * @param treeNode
     * @returns {*}
     */
    function setFontCss(treeId, treeNode) {
        if(treeNode.level == 0) {
            return { color:"red" ,"font-weight":"bold"}
        }else if(treeNode.level == 1){
            return { color:"blue" }
        }else {
            return {};
        }
    }

    /**
     * 弹出确认的模态框 并且 获取改变的权限
     */
    function grantModal() {
        var fChangedNodes = $.fn.zTree.getZTreeObj("FTree").getChangeCheckedNodes();    //获取静态权限改变的节点
        var dChangedNodes = $.fn.zTree.getZTreeObj("DTree").getChangeCheckedNodes();    //获取动态权限改变的节点

        //静态权限改变解析
        var fAddStr = '';
        var fDelStr = '';
        var fAdd = [];  //增加的静态权限，id数组
        var fDel = [];   //删除的静态权限，id数组
        for(var i=0; i<fChangedNodes.length; i++) {
            var n = fChangedNodes[i];
            if(n.level == 1) {      //只收集静态权限树二级状态改变
                if(n.checked) {     //checked为true，表示之前是false，则为添加
                    fAdd.push(n.id);
                    fAddStr += n.name+"，";
                }else{      //checked为false，表示之前是true，则为删除
                    fDel.push(n.id);
                    fDelStr += n.name+"，";
                }
            }
        }
        $('#fAdd').empty();
        $('#fAdd').append(fAddStr);
        $('#fDel').empty();
        $('#fDel').append(fDelStr);


        var dAddStr = '';
        var dDelStr = '';
        var changedDyn = [];    //变更的动态权限，形如[{classId:1, input:1, manage:1, approve:0, statistics:0}]
        //动态权限改变解析
        for(var i=0; i<dChangedNodes.length; i++) {     //for1
            var node = dChangedNodes[i];

            if(node.level == 2) {      //只检测叶子节点的状态改变
                var pId = node.pId;
                var pNode = $.fn.zTree.getZTreeObj("DTree").getNodeByParam("id", pId, null);       //根据pId查找父节点
                var pName = pNode.name;
                var duplicateFlag = false;
                for(var j=0; j<changedDyn.length; j++) {        //重复检测，避免重复添加
                    if(changedDyn[j].classId == pId) {
                        duplicateFlag = true;
                        break;      //终止重复检测的循环
                    }
                }

                if(duplicateFlag)       //该叶子节点的父节点代表的权限已被添加，跳过这个叶子节点的检测
                        continue;

                var obj = {classId:pId};
                changedDyn.push(obj);
                for(var j=0; j<pNode.children.length; j++) {        //for2
                    var c = pNode.children[j];
                    var flag = c.checked == c.checkedOld;     //flag为true表示没有修改，否则为修改
                    var val = c.checked ? 1 : 0;
                    if(c.name == "录入") {
                        obj['input'] = val;
                        if(!flag && val==1) {       //表示修改且增加权限
                            dAddStr += pName+"->录入，";
                        }else if(!flag && val==0) {
                            dDelStr += pName+"->录入，";
                        }
                    }else if(c.name == "管理个人") {
                        obj['manage'] = val;
                        if(!flag && val==1) {       //表示修改且增加权限
                            dAddStr += pName+"->管理个人，";
                        }else if(!flag && val==0) {
                            dDelStr += pName+"->管理个人，";
                        }
                    }else if(c.name == "审批") {
                        obj['approve'] = val;
                        if(!flag && val==1) {       //表示修改且增加权限
                            dAddStr += pName+"->审批，";
                        }else if(!flag && val==0) {
                            dDelStr += pName+"->审批，";
                        }
                    }else if(c.name == "统计查询") {
                        obj['statistics'] = val;
                        if(!flag && val==1) {       //表示修改且增加权限
                            dAddStr += pName+"->统计查询，";
                        }else if(!flag && val==0) {
                            dDelStr += pName+"->统计查询，";
                        }
                    }
                }       //for2
            }
        }   //for1
        $('#dAdd').empty();
        $('#dAdd').append(dAddStr);
        $('#dDel').empty();
        $('#dDel').append(dDelStr);

        var param = {};
        param['roleId'] = role.roleId;
        param['addfixedAuth'] = fAdd;
        param['delFixedAuth'] = fDel;
        param['changedDynAuth'] = changedDyn;
        $('#changedAuthJson').val(JSON.stringify(param));
        $('#grantModal').modal({backdrop: 'static', keyboard: false});
    }
</script>
</body>
</html>
