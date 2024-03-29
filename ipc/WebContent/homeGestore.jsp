<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>homeGestore</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>

<jsp:include page="sessionLogin.jsp" flush="true"></jsp:include>
	<center><h1>Home Gestore</h1></center>
	<center><table border="1" width="30%">
		<tbody>
			<tr>
				<td align="center"><html:link page="/creazioneProfessore.jsp">Creazione Professore</html:link></td>
			</tr>
			<tr>
				<td align="center"><html:link action="/CreazioneCorso">Creazione Corso</html:link></td>
			</tr>
			<tr>
				<td align="center"><html:link page="/gestioneAccount.jsp">Gestione Account</html:link></td>
			</tr>
			<tr>
				<td align="center"><html:link action="/GestioneCorsoElenco">Gestione Corso</html:link></td>
			</tr>
			<tr>
				<td align="center"><html:link action="/GestioneEsameElenco">Gestione Esame</html:link></td>
			</tr>
			<tr>
				<td align="center"><html:link action="/ConfermaIscrizioneElenco">Conferma Iscrizione</html:link></td>
			</tr>
		</tbody>
	</table>
	
     				<table>
     					<tr>
     					<td align=center>
     						<font color=red>
     							<html:errors />
     						</font>
     					</td>
     					</tr>
     				</table>
	</center>
	</body>
</html:html>
