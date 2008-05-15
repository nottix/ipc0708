<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>creazioneEsame</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<center>
<h1>Creazione Esame</h1>
<html:form action="/CreazioneEsameDone">
	<table border="1">
		<tbody>
			<tr>
				<td>Corso:</td>
				<td><html:text property="acronimo" readonly="true"></html:text></td>
			</tr>
			<tr>
				<td>Data sessione:</td>
				<td><html:text property="dataEsame"></html:text></td>
			</tr>
			<tr>
				<td>Data inizio prenotazione:</td>
				<td><html:text property="dataInizio"></html:text></td>
			</tr>
			<tr>
				<td>Data fine prenotazione:</td>
				<td><html:text property="dataFine"></html:text></td>
			</tr>
			<tr>
				<td>Aule:</td>
				<td><html:text property="aule"></html:text></td>
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
