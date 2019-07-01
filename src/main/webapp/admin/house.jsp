<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/18
  Time: 8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="Css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/house.js"></script>

</head>
<body>

<!--展示数据-->
<table id="dg"></table>

<!--定义工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>
        <a
                href="javascript:UpfyBySelect()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a>
        <a
                href="javascript:DeleteAll()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">删除选中多项</a>
    </div>

    <!--搜索条件-->
    <div>
        电话:<input type="text" name="tel" id="tel"/>
        开始年龄:<input type="text" name="startAge" id="startAge"/>
        结束年龄:<input type="text" name="endAge" id="endAge"/>
        <a id="btn" href="javascript:searchUser();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>

    </div>
</div>

<!--添加窗口-->
<%--buttons="#AddDialogButtons" 是给添加对话窗口添加确定和取消按钮--%>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="SaveDialogForm" method="post">
        <table>
            <tr>
                <td>区域:</td>
                <td><input type="text" name="name" id="author" /></td>
            </tr>

        </table>
    </form>
</div>



<!--给添加对话框添加按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog()"
         class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


<%--修改窗口--%>
<div id="UpDialog" class="easyui-dialog" buttons="#UpDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="UpDialogForm" method="post">
        <table>
            <tr>   <%--readonly 是不可修改--%>
                <td>编号:</td>
                <td><input type="text" readonly name="id" id="dd" /></td>
            </tr>
            <tr>
                <td>区域:</td>
                <td><input type="text" name="name" id="author" /></td>
            </tr>
        </table>
    </form>
</div>


<!--给修改对话框添加按钮-->
<div id="UpDialogButtons">
    <a href="javascript:SaveDialog2()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog2()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>




</body>
</html>
