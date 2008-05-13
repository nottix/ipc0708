<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>homeProfessore</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
	<table width="100%" border="0">
	<tbody>
		<tr>
			<td align=left>Ciao <%out.println(session.getAttribute("email")); %></td>
			<td align=right><html:link action="Logout">Logout</html:link></td>
		</tr>
	</tbody>
</table>
	<center><h1>Home Professore</h1></center>
	<center><table border="1" width="30%">
		<tbody>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td align="center"><html:link page="/creazioneProfessore.jsp">Creazione Professore</html:link></td>
				<td></td>
			</tr>
			<tr>
				<td align="center"><html:link page="/gestioneAccount.jsp">Gestione Account</html:link></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table></center>
	</body>
</html:html>
