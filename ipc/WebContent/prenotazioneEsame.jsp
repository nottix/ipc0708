<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>prenotazioneEsame</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
	<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
	<center>
		<h1>Prenotazione Esame</h1>
		<html:form action="/PrenotazioneEsameDone">
	<table border="1">
		<tbody>
			<tr>
				<td>Data apertura:</td>
				<td><html:text property="dataInizioPeriodoPrenotazione"></html:text></td>
			</tr>
			<tr>
				<td>Data chiusura:</td>
				<td><html:text property="dataFinePeriodoPrenotazione"></html:text></td>
			</tr>
			<tr>
				<td>Data:</td>
				<td><html:text property="dataEsame"></html:text></td>
			</tr>
			<tr>
				<td>Aule:</td>
				<td><html:text property="auleEsame"></html:text></td>
			</tr>
			<tr>
				<td colspan="2"><font color="red"><html:errors /></font></td></tr>
			<tr>
				<td><html:reset></html:reset></td>
				<td><html:submit></html:submit></td>
			</tr>
		</tbody>
	</table>
	<logic:present name="idEsame">
        
		</logic:present>
		</html:form>
	</center>
</body>
</html:html>
