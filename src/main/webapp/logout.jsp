<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
       
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退出</title>
<script type="text/javascript">     
function countDown(secs,surl){     
 //alert(surl);     
 var jumpTo = document.getElementById('jumpTo');
 jumpTo.innerHTML=secs;  
 if(--secs>0){     
     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
     }     
 else{       
     location.href=surl;     
     }     
 }     
</script> 
</head>
<body>
	<%
		session = request.getSession(false);
		if(session != null){
			//session.removeAttribute("userId");
			session.invalidate();
		}
	%>
	<center>
        <h1>退出成功！</h1>
        <span id="jumpTo">3</span>秒后自动跳转
		<script type="text/javascript">countDown(3,'${login.jsp}');</script>
        <p>
        	如果没有跳转，请点<a href="login.jsp">这里</a>
        </p>
    </center>
</body>
</html>