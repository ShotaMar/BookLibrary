<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat"%>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
String date = sdf.format(new Date());
int dateB = Integer.parseInt(date)-5;
%>
<div class="footer">
<p><%=dateB %>-<%=date %> Copyright ICTエンジニア3班 All Rights Reserved.</p>
</div>