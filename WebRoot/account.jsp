<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>模拟转账</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<h1 align="center">模拟转账</h1>
  	${account_info}
    <form action="account.do" method="post">
    	<table>
			<tr>
				<td>转出账户：</td>
				<td><input type="text" name="account.out"/></td>
			</tr>    	
    		<tr>
				<td>转入账户：</td>
				<td><input type="text" name="account.in"/></td>
			</tr>
			<tr>
				<td>转出金额:</td>
				<td><input type="number" name="amount"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="转账"/></td>
			</tr>
    	</table>
    </form>
  </body>
</html>
