<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>login</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<center>
	<h1>IPC</h1>
</center>
<html:form action="Login">
	<div align="center">
	<table border="1" width="30%">
		<tbody>
			<tr>
				<td>Email:</td>
				<td><html:text property="email"></html:text></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><html:password property="password"></html:password></td>
			</tr>
			<tr>
				<td><html:reset value="Reset"></html:reset></td>
				<td align="center"><html:submit value="Login"></html:submit></td>
			</tr>
			<tr>
				<td colspan="2"><font color=red><html:errors /></font></td></tr>
			<tr>
				<td colspan="2" align="center"><html:link page="/richiestaRegStudente.jsp">Registrazione</html:link></td>
			</tr>
		</tbody>
	</table>
	</div>
</html:form>
<center>

</center>
</body>
</html:html>
