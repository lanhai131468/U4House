<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  //判断当前用户有没有登入
  Object o=session.getAttribute("users");
  if(o==null){
   //回到登录页面
    out.print("<script>alert('你还没有登入，超时,可以滚啦');location.href='login.jsp';</script>");
  }

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0050)http://localhost:8080/HouseRent/manage!list.action -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>青鸟租房 - 用户管理</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">

<META name=GENERATOR ></HEAD>
<script language="JavaScript" src="/admin/js/jquery-1.8.3.js" ></script>
<script language="JavaScript">

  function delhouse(obj,hid) {

      /*找到TR标签*/
      var delNode=$(obj).parent().parent().parent();

      //发送异步请求，根据控制器返回值判断是否删除TR标签
      $.post("delhouse",{"hid":hid},function (data) {

          if (data.result>0){
              //删除TR标签
             /* alert(data.result)*/
              delNode.remove()
          }

      },"json")

  }
</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV>
<DIV class=search>【欢迎:${users.name}】<LABEL class="ui-green searchs"><a href="goFaBu">发布房屋信息</a></LABEL> <LABEL class="ui-green searchs"><a href="getUserHouse">管理房屋信息</a></LABEL>
<LABEL class=ui-green><INPUT onclick='document.location="index.jsp"' value="退       出" type=button name=search></LABEL> 
</DIV></DIV>
<DIV class="main wrap">
<DIV id=houseArea>
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${houseExtPageInfo.list}" var="h">
  <TR>
    <TD class=house-thumb><SPAN><A href="details.jsp" target="_blank"><img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></A></SPAN></TD>
    <TD>
      <DL>
        <DT><A href="details.jsp" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}${h.sname},${h.floorage}平米<BR>联系方式:${h.contact}</DD></DL></TD>
    <TD class=house-type><LABEL class="ui-green searchs"><a href="upfabu?huoseid=${h.id}" >修改</a></LABEL>
    <LABEL class=ui-green><INPUT onclick="location:delhouse(this,${h.id})" value="删    除" type=button name=search></LABEL>
    </TD></TR>
  </c:forEach>
    </TBODY></TABLE></DIV>
<DIV class=pager>
<UL>
  <c:forEach  begin="1" end="${houseExtPageInfo.pages}" step="1" var="i">
  <LI class=current><A id=widget_338868862 
  href="getUserHouse?page=${i}"
  parseContent="true" showError="true" targets="houseArea" 
  ajaxAfterValidation="false" validate="false" 
dojoType="struts:BindAnchor">${i}</A>
   </LI></c:forEach>
</UL><SPAN class=total>${houseExtPageInfo.pageNum}/${houseExtPageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
