﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<script language="JavaScript" src="/admin/js/jquery-1.8.3.js" ></script>
<script language="JavaScript">

  //加载事件
  $(function () {

      //网页加载完之后  取区域的id 显示对应的街道
      showStreet($("#district").val())

      //给区域添加改变事件  根据区域的ID查询街道
      $('#district').change(function () {

          //获取区域的id,根据id去查询对应的街道
          var did=$(this).val()
         /* alert(did)*/

          //将did发送给方法  异步发送请求获取街道信息
          showStreet(did);
          
      })

  });

  function showStreet(did) {

      //发送异步请求获取街道
      $.post("getStreet",{"did":did},function (data) {

          $("#street>option").remove();//清空选项
          for (var i = 0;i<data.length;i++){

              //创建一个dom节点
              var op=$("<option value="+data[i].id+">"+data[i].name+"</option>")

              //追加节点
              $('#street').append(op);


          }

          //设置街道选中项
          $("#street").val(${house.streetId});
          // alert($("#street").val())

      },"json")
  }

</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=add.action 
action="fabuHouse" enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <input type="hidden" name="id" value="${house.id}">
    <TD><INPUT id=add_action_title class=text type=text name=title value="${house.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId>
     <c:forEach items="${allType}" var="t"><OPTION <c:if test="${t.id==house.typeId}" > selected="selected" </c:if> value=${t.id}>${t.name}</OPTION></c:forEach>
    </SELECT></TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage value="${house.floorage}" class=text type=text name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>
    <TD><INPUT class=text type=date name=pubdate value="<f:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd" ></f:formatDate>" ></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT id="district" class=text name=districtid>
    <c:forEach items="${allDistrict}" var="d" >
      <OPTION <c:if test="${d.id==house.distrctid}">selected</c:if> value=${d.id}>${d.name}</OPTION>
    </c:forEach>
    </SELECT> 街：<SELECT id="street" class=text name=streetId></SELECT> </TD></TR><!--
						<tr>
							<td class="field">坐  标：</td>
							<td><input type="text" class="text" name="point" />
							</td>
						</tr>
						--><!--  <tr>
							<td class="field">Y 坐  标：</td>
							<td><input type="text" class="text" name="point.y" /></td>
						</tr>-->
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"> </TD></TR>
  <TR>
    <TD class="field">图片</TD>
    <TD><img src="http://localhost:81/${house.path}?a=<%=Math.random()%>" width="100" height="100">
      <INPUT id="picture" class="text" type=file name="pfile">
    <input type="hidden"  name="oldfile" value="${house.path}">
    </TD>
  </TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD></TR></TBODY></TABLE>
<DIV class=buttons><INPUT  value=立即发布 type=submit name="dd">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
