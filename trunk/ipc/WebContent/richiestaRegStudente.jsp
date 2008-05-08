<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>richiestaRegStudente</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<center>
<h1>Registrazione</h1>
<html:form action="RichiestaRegStudente">
<table border="1" width="50%" height="169">
	<tbody align="center">
		<tr>
			<td>Nome:</td>
			<td><html:text property="nome"></html:text></td>
		</tr>
		<tr>
			<td>Cognome:</td>
			<td><html:text property="cognome"></html:text></td>
		</tr>
		<tr>
			<td>Matricola:</td>
			<td><html:text property="matricola"></html:text></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><html:password property="password"></html:password></td>
		</tr>
		<tr>
			<td>Ripeti Password:</td>
			<td><html:password property="passwordCheck"></html:password></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><html:text property="email"></html:text></td>
		</tr>
		<tr>
			<td><html:reset value="Reset"></html:reset></td>
			<td><html:submit value="RichiestaRegStudente"></html:submit></td>
		</tr>
	</tbody>
</table>
</html:form>
</center>
</body>
</html:html>
