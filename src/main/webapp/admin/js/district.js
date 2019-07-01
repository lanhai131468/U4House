$(function(){
    //使用datagrid绑定数据展示数据
    $('#dg').datagrid({
        url:'/admin/getDistrict',
        fitColumns: true,  //自动使列适应表格的宽度
        pagination: true,   //分页工具栏
        pageList: [5, 10, 15, 20],
        toolbar:"#ToolBar",  //添加删除修改工具栏
        //只选中1行   singleSelect:true,
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'name', title: '名称', width: 50, align: "center"},
            { field: 'uu', title: '操作', width: 50, align: "center",

                formatter: function(value,row,index){

                    /*   row代表选中行的数据*/
                    return "<a href='javascript:deleteById("+row.id+")'>删除</a> | <a href='javascript:streetById("+row.id+")'>查看街道</a>"

                }

            }
        ]]
    });
});

//点击工具栏上方的添加按钮--》打开添加窗口
function Add() {
    // alert("打开窗口");
    //'setTitle',"添加区域" 为添加标题
    $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
}

//点击取消按钮--》关闭添加窗口
function CloseDialog(){
    $("#AddDialog").dialog("close");
}

//点击确认按钮提交表单--》添加数据
function SaveDialog() {
    $('#SaveDialogForm').form('submit',{
        //提交到服务器的地址
        url:'/admin/add',

        //成功后的回调函数
        success:function (data) {
            //data获取服务器返回的字符串

            //使用jquery的方法  将json字符串转换为json对象
            var obj=$.parseJSON(data);

            if (obj.result>0){

                //添加数据成功后,关闭添加窗口
                $("#AddDialog").dialog("close");
                //提示框
                $.messager.alert('提示框','恭喜添加成功');
            }else
            {
                //添加数据后 关闭窗口
                // $("#AddDialog").dialog("close");
                $.messager.alert('提示框','系统正在维护');
            }
        }
    })
}


/* //弹出修改对话框  点击修改按钮弹出的对话框
 function UpfyBySelect() {

     //'setTitle',"添加区域" 为添加标题
     $("#UpDialog").dialog("open").dialog('setTitle',"修改区域");

     }*/

//点击取消按钮，关闭修改对话窗口
function CloseDialog2() {

    $("#UpDialog").dialog("close")

}

//点击修改的按钮-打开修改窗口-在修改框中还原数据
function UpfyBySelect(){

    //判断有没有返回的数据  获取选中行的值

    //获取页面上所有选中的行的数据，如果没有选中，则返回为空数组
    var selectRows = $('#dg').datagrid('getSelections');

    //没有选中得出提示信息
    if (selectRows.length!=1){

        $.messager.alert('提示框','你选中了多行，或者没有选中');

        //回到方法调用处  不再继续向下执行
        return
    }

    //选中一行  还原数据  还原当前选中行的数据
    var select=selectRows[0];

    //打开文本框
    $("#UpDialog").dialog("open").dialog('setTitle',"修改区域");

    //获得到行数据 加载显示到表单中显示
    //select是json对象  可以直接还原  只要键对的上就能还原
    $('#UpDialogForm').form('load',select);

}

//点击修改按钮的确认按钮  提交修改的数据
function SaveDialog2() {

    $('#UpDialogForm').form('submit',{

            url:"/admin/up",  //提交到服务器的地址
            success:function (data) {    //success: 提交成功之后的回调函数
                //data是服务器返回来的是json类型的字符串

                //将json类型的字符串转换成json对象
                var obj=$.parseJSON(data)

                if (obj.result>0){

                    //修改成功后关闭对话窗口
                    $('#UpDialog').dialog('close');

                    //重新加载表格 并刷新
                    $('#dg').datagrid('reload');

                    $.messager.alert("提示框","恭喜修改成功")
                } else {

                    $.messager.alert("提示框",'系统正在维护')

                }

            }

        }
    )
}

//删除单条数据
function deleteById(id) {

    //修改提示框 当用户点击确认按钮就返回true到function
    $.messager.confirm('确认提示框', '你真的想删除我吗', function(tru){
        //返回true时执行if语句
        if (tru){

            // 发送异步请求  实现删除
            //$.post("服务器的地址",给服务器传参，回调函数，"json");
            //传参的格式：{"参数的名称":值,"参数的名称":值}
            $.post("deleteById",{"id":id},function (data) {

                if (data.result>0){

                    //修改成功后 重新加载页面
                    $('#dg').datagrid('reload')  //刷新

                } else {

                    //弹出修改失败窗口
                    $.messager.alert("提示框","系统正在维护")

                }
            },"json");

        }
    });


}

//删除多条
function DeleteAll(){

    //判断是否选中了多行  如果没有选中则返回一个空数组  获取选中行的值
    var selections=$("#dg").datagrid("getSelections");

    if (selections.length == 0) {

        $.messager.alert("提示框","你没有选中多行");
        //返回不再继续向下执行
        return;
    }

    var devalue="";
    for (var i=0;i<selections.length;i++ ){

        //拼接多行的值
        devalue=devalue+selections[i].id+",";

    }

    //截取字符串最后的一个逗号
    devalue=devalue.substring(0,devalue.length-1);

    //发送异步请求到服务器
    $.post("deleteAll",{"ids":devalue},function (data) {

        if (data.result > 0) {

            $("#dg").datagrid("reload"); //刷新

            $.messager.alert("提示框","恭喜删除成功"+data.result+"行")
        }else {

            $.messager.alert("提示框","系统正在维护")
        }
    },"json")
}

//查询当前区域的街道
function streetById(obj) {

    //弹出显示街道的窗口
    $("#streetById").dialog("open").dialog("setTitle","街道信息")
   /* alert(obj)*/

    //在表格中显示对应的街道信息
    $("#st").datagrid({
        title:"街道信息", //标题
        url:'/street/streetGetAll?id='+obj,  //服务器地址
        fitColumns:true, //自适应宽度
        pagination:true,  //显示工具栏在上方
        pageList:[3,5,6], //每页记录数列表
        pageSize:5, //每页初始化记录数
        singleSelect:true,

        columns:[[
                {field:'id',title:'编号',width:100},
                {field:'name',title:'街道名称',width:100},
                {
                    field: 'opt', title: '操作', width: 100,
                    formatter: function (value, row, index) {

                        //同步跳转
                        return "<a href='/street/deleteStreetId?id="+row.id+"'>删除</a>";

                    }
                }
            ]]



        }
    )
}