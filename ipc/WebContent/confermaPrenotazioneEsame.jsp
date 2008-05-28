<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>confermaPrenotazioneEsame</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
<jsp:include page="sessionLogin.jsp" flush="true"></jsp:include>
	<center>
		<h1>Conferma Prenotazione Esame</h1>
		<html:form action="/ConfermaPrenotazioneEsameDone">
			<table border="1">
				<tbody>
					<tr>
						<td>Email:</td>
						<td><html:text property="email"></html:text></td>
					</tr>
					<tr>
						<td>Data esame:</td>
						<td><html:text property="dataEsame"></html:text></td>
					</tr>
			<tr>
				<td>Data prenotazione:</td>
				<td><html:text property="dataPrenotazione"></html:text></td>
			</tr>
			<tr>
				<td colspan="2">
					<html:submit property="submit" value="Conferma"></html:submit>
					<html:submit property="submit" value="Rifiuta"></html:submit>
					<html:reset></html:reset>
				</td>
			</tr>
			<tr>
				<td colspan="2"><html:errors /></td></tr>
		</tbody>
			</table>
		</html:form>
	</center>
</body>
</html:html>
