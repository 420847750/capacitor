<%@ page language="java" pageEncoding="UTF-8" %>
<% response.setStatus(200);%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<script language="JavaScript" type="text/javascript"> 

  // this page should never load inside of another frame
  if (top.location != self.location){
    top.location = self.location;
  }
</SCRIPT>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<style type="text/css">
<!--
body,td,th {
	font-size: 14px;
	color: #000000;
	line-height:24px;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #f8fbfd;
}
-->
</style></head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=basePath%>images/images_002.gif">
  <tr>
    <td height="130" align="left"><img src="<%=basePath%>images/images_001.gif" width="681" height="130" /></td>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="679" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:40px;">
  <tr>
    <td><img src="<%=basePath%>images/images_003.gif" width="679" height="14" /></td>
  </tr>
  <tr>
    <td background="<%=basePath%>images/images_004.gif"><p>&nbsp;</p>
      <table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><img src="<%=basePath%>images/images_006.gif" width="76" height="72" /></td>
          <td>未登录或连接已超时，请重新登录。<br/>
          回到<a href="<%=basePath%>index.action">首页面</a>
          </td>
        </tr>
      </table>
      <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p></td>
  </tr>
  <tr>
    <td><img src="<%=basePath%>images/images_005.gif" width="679" height="7" /></td>
  </tr>
</table>
</body>
</html>
