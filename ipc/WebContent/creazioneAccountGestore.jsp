<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>creazioneAccountGestore</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>

<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
<center>
	<h1>Creazione Account Gestore di Sistema</h1>
	<html:form action="/CreazioneAccountGestore">
		<table border="1">
			<tbody>
				<tr>
					<td>Nome:</td>
					<td><html:text property="nome"></html:text></td>
				</tr>
				<tr>
					<td>Cognome:</td>
					<td><html:text property="cognome"></html:text></td>
				</tr>
			<tr>
				<td>Email:</td>
				<td><html:text property="email"></html:text></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><html:password property="password"></html:password></td>
			</tr>
			<tr>
				<td>Conferma Password:</td>
				<td><html:password property="confPassword"></html:password></td>
			</tr>
			<tr>
				<td><html:reset></html:reset></td>
				<td><html:submit></html:submit></td>
			</tr>
			<tr>
				<td colspan="2"><html:errors /></td></tr>
		</tbody>
		</table>
	</html:form>

</center>
</body>
</html:html>
