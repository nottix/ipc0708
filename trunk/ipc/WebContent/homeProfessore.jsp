<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>

<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>homeProfessore</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<jsp:include page="sessionLogin.jsp" flush="true"></jsp:include>
	<center><h1>Home Professore</h1></center>
	<center><table border="1" width="30%">
		<tbody>
			<tr>
				<td align="center"><html:link page="/creazioneProfessore.jsp">Creazione Professore</html:link></td>
			</tr>
			<tr>
				<td align="center"><html:link action="/CreazioneCorso">Creazione Corso</html:link></td>
			</tr>
			<%
			if(session.getAttribute("isGestore") != null && session.getAttribute("isGestore").equals("true")) {
			%>
				<tr>
					<td align="center"><html:link page="/gestioneAccount.jsp">Gestione Account</html:link></td>
				</tr>
			<%
			}
			if((session.getAttribute("isDirettore") != null && session.getAttribute("isDirettore").equals("true")) ||
				(session.getAttribute("isGestore") != null && session.getAttribute("isGestore").equals("true"))) {
			%>
				<tr>
					<td align="center">Modifica Titolare</td>
				</tr>
			<%
			}
			if((session.getAttribute("isTitolare") != null && session.getAttribute("isTitolare").equals("true")) ||
				(session.getAttribute("isGestore") != null && session.getAttribute("isGestore").equals("true"))) {
			%>
				<tr>
					<td align="center"><html:link action="/GestioneCorsoElenco">Gestione Corso</html:link></td>
				</tr>
			<%
			}
			%>
			<%
			if((session.getAttribute("isCollaboratore") != null && session.getAttribute("isCollaboratore").equals("true")) ||
				(session.getAttribute("isTitolare") != null && session.getAttribute("isTitolare").equals("true")) ||
				(session.getAttribute("isGestore") != null && session.getAttribute("isGestore").equals("true"))) {
			%>
				<tr>
					<td align="center"><html:link action="/GestioneEsameElenco">Gestione Esame</html:link></td>
				</tr>
				<tr>
					<td align="center"><html:link action="/ConfermaIscrizioneElenco">Conferma Iscrizione</html:link></td>
				</tr>
			<%
			}
			%>
			<tr>
				<td align="center"><html:link page="/gestioneQuery.jsp">Gestione Query</html:link></td>
			</tr>
		</tbody>
	</table></center>
	</body>
</html:html>
