<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<script language="JavaScript" src="/admin/js/jquery-1.8.3.js"></script>
<script language="JavaScript">

  $(function () {

      //发送异步请求,获取房屋类型
      $.post("getAllType",null,function (data) {
          //循环获取类型数据
          for (var i = 0; i < data.length; i++) {

              //拼接数据和option标签
              var option=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
              //将option数据，拼接到下拉选项中
              $("#typeId").append(option);
          }

          //判断查询房屋类型的条件是否为空,不为空就进行回显
          if (${houseCondition.tid!=null}){
              $("#typeId").val(${houseCondition.tid})}

      },"json")



      //发送异步请求获取所有区域
      $.post("getAllDistrict",null,function (data) {
          //循环获取所有区域
          for (var i=0;i<data.length;i++){
              //创建option标签和拼接数据
              var option=$("<option value="+data[i].id+">"+data[i].name+"</option>")

              //将option标签追加到select中
              $("#districtId").append(option)
          }

          //判断查询区域的条件是否为空,不为空就进行回显
          if (${houseCondition.did!=null}) {
              $("#districtId").val(${houseCondition.did})
          }

      },"json")



      //给区域添加单击改变事件,显示对应下的街道
      $("#districtId").change(function () {
          //获取当前区域的id
          var did=$(this).val()
          //将did给showStreet()方法，发送异步请求获取对应的街道
          showStreet(did)

      })

      //显示街道的方法
      function showStreet(did) {
          //移除之前option标签里的记录
          $("#streetId>option").remove()
          //发送异步请求
          $.post("getStreet",{"did":did},function (data) {
              //循环获取街道数据
              for (var i = 0; i < data.length; i++) {
                  //创建option标签和填充数据
                  var option=$("<option value="+data[i].id+">"+data[i].name+"</option>")
                  //将option标签追加到select中
                  $("#streetId").append(option)
              }

              //判断查询街道的条件是否为空,不为空就进行回显
              if (${houseCondition.sid!=null}){
                  $("#streetId").val(${houseCondition.sid})
              }

          },"json")
      }

      //回显面积
      $("#flooaId").val("${houseCondition.flooa}")

  })


  //设置页码
  function goPage(page) {

      $("#showPage").val(page)

      //再次提交表单 发送查询请求
      $("#sform").submit()
  }




</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform method=post action="goList">
    <%-- 传递页码--%>
    <input type="hidden" value="1" name="page" id="showPage">
    标题：<INPUT class=text type=text name=title value="${houseCondition.title}">

    开始价格<input name=startPrice id="startPrice" value="${houseCondition.startPrice}">

    结束价格<input name=endPrice id="startPrice" value="${houseCondition.endPrice}">

    区域<SELECT id=districtId name=did> <OPTION selected value="">请选择</OPTION></SELECT>

    街道<SELECT id=streetId name=sid> <OPTION selected value="">请选择</OPTION> </SELECT>

    类型<SELECT id="typeId" name=tid><OPTION selected value="">请选择</OPTION></SELECT>

    <br/>

    面积<SELECT id="flooaId" name=flooa value=""> <OPTION selected value="">请选择</OPTION>
      <OPTION value=0-40>40以下</OPTION><OPTION value=40-500>40-500</OPTION>
      <OPTION value=500-100000>500以上</OPTION></SELECT>
    <%--  <script language="JavaScript">
          $("#flooaId").val("${houseCondition.flooa}");
      </script>--%>
  <%-- <input type="text" value="${houseCondition.flooa}">--%>
    <LABEL class=ui-blue><INPUT  value=搜索房屋 type=submit name=search></LABEL></FORM></DL></DIV>

<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="h">
  <TR>
    <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></a></span></TD>
    <TD>
      <DL>
        <DT><A href="details.jsp" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}-${h.sname},${h.floorage}平米<BR>联系方式:${h.contact} </DD></DL></TD>
    <TD class=house-type>${h.tname}</TD>
    <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD></TR>
  </c:forEach>
  </TBODY>
</TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.prePage==0?1:pageInfo.prePage})">上一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pageNum==pageInfo.pages?pageInfo.pages:pageInfo.nextPage})">下一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL><SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
