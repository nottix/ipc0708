<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>Creazione Progetto</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
<jsp:include page="sessionLogin.jsp" flush="true"></jsp:include>
<center>
<h1>Creazione Progetto</h1>
<html:form action="/CreazioneProgettoDone">
	<table border="1">
		<tbody>
			<tr>
				<td>Corso:</td>
				<td><html:text property="acronimo" readonly="true"></html:text></td>
			</tr>
			<tr>
				<td>Titolo:</td>
				<td><html:text property="titolo"></html:text></td>
			</tr>
			<tr>
				<td>Data consegna:</td>
				<td><html:text property="dataConsegna"></html:text></td>
			</tr>
			<tr>
				<td>Numero upload consentiti:</td>
				<td><html:text property="maxUploadPerStudente"></html:text></td>
			</tr>
			<tr>
				<td>Dimensione massima gruppo:</td>
				<td><html:text property="maxDimGruppo"></html:text></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<html:cancel></html:cancel>
					<html:reset></html:reset>
					<html:submit />
				</td>
			</tr>
			<tr>
			<td colspan="2" align="center">
				<html:messages id="msg" message="true">
					<bean:write name="msg"/><br>
				</html:messages>
			</td>
		</tr>
			<tr>
				<td colspan="2"><font color="red"><html:errors/></font></td></tr>
		</tbody>
	</table>
	<tr><td></td></tr>
</html:form>
</center>
</body>
</html:html>
