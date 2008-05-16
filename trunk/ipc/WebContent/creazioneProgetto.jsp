<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>Creazione Progetto</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
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
				<td colspan="2"><html:errors/></td></tr>
		</tbody>
	</table>
	<tr><td></td></tr>
</html:form>
</center>
</body>
</html:html>
