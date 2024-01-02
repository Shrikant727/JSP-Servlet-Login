<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<%
    HttpSession session1 = request.getSession(false);
    if (session1 == null || session1.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<script>
	function showAlert(message){
		if(message!="null")
		alert(message);
	}
</script>
</head>
<body>
<%
	String message=null;
	if(session1.getAttribute("message")!=null){
		message=session1.getAttribute("message").toString();
	}
	%>
	<body onload="showAlert('<%=message %>')">
	 <h2>Welcome, <%= session.getAttribute("username") %></h2>
    <form action="/LoginRegister/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>