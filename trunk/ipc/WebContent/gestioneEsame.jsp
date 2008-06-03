<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>gestioneEsame</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
<jsp:include page="sessionLogin.jsp" flush="true"></jsp:include>
<center>
	<h1>Gestione Esame</h1>
		<logic:present name="acronimo">
		<h3>Corso: <bean:write name="acronimo"/></h3>
		<table border="1">
			<tbody>
				<tr>
					<td><a href="/ipc/CreazioneProgetto.do?acronimo=<bean:write name="acronimo"/>">Creazione Progetto</a></td>
				</tr>
				<tr>
					<td><html:link action="/ModificaVotiElenco">Modifica Voti</html:link></td>
				</tr>
				<tr>
					<td align="center">
						<html:messages id="msg" message="true">
							<bean:write name="msg"/><br>
						</html:messages>
					</td>
				</tr>
				<tr>
					<td><font color="red"><html:errors /></font></td>
				</tr>
			</tbody>
		</table>
		<table>
			<tr>
				<td align=center>
					<html:errors />
				</td>
			</tr>
		</table>
		</logic:present>
	</center>
</body>
</html:html>
