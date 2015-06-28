<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String url = (String)request.getAttribute("url")==null?"":(String)request.getAttribute("url");
String warning = (String)request.getAttribute("warning");


%>
<s:property value="alert"/>