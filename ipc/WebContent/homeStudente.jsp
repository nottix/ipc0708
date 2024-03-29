<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>Home Studente</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
<center>
<h1>Home Studente</h1>
<table border="1">
	<tbody align=center>
		<tr>
			<td align="center"><html:link action="/IscrizioneCorsoElenco">Iscrizione Corso</html:link></td>
			<td align="center"><html:link action="/PrenotazioneEsameElencoCorsi">Prenotazione Esame</html:link></td>
			<td align="center"><html:link action="/VisualizzaCorsoElenco">Visualizza Corsi</html:link></td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<html:messages id="msg" message="true"><bean:write name="msg" /><br></html:messages>
			</td>
		</tr>
		<tr>
			<td colspan="3"><font color="red"><html:errors /></font></td>
		</tr>
	</tbody>
</table>
</center>
</body>
</html:html>
