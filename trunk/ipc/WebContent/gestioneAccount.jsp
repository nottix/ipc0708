<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>gestioneAccount</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
<center><h1>Gestione Account</h1></center>
<center><table border="1">
	<tbody>
		<tr>
			<td align="center"><html:link action="/AttivazioneAccountStudente">Attivazione Account Studente</html:link></td>
		</tr>
		<tr>
			<td align="center"><html:link action="/VisualizzaAccountElenco">Visualizza Account</html:link></td>
		</tr>
		<tr>
			<td align="center"><html:link page="/creazioneAccountGestore.jsp">Creazione Account Gestore di Sistema</html:link></td>
		</tr>
		<tr>
			<td align="center"><html:link action="/ModificaAccountStudente">Modifica Account Studente</html:link></td>
		</tr>
		<tr>
			<td align="center"><html:link action="/RipristinaPasswordAccount">Ripristina Password Account</html:link></td>
		</tr>
		<tr>
			<td align="center"><html:errors /></td>
		</tr>
	</tbody>
</table></center>
</body>
</html:html>
