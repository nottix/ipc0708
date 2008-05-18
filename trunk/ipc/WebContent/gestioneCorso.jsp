<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>gestioneCorso</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>

<center>
		<h1>Gestione Corso</h1>
		<logic:present name="acronimo">
		<h3>Corso: <bean:write name="acronimo"/></h3>
		<table border="1">
			<tbody>
				<tr>
					<td><a href="/ipc/CreazioneEsame.do?acronimo=<bean:write name="acronimo"/>">Creazione Esame</a></td>
				</tr>
				<tr>
					<td><html:errors></html:errors></td>
				</tr>
				<tr>
					<td><html:submit></html:submit></td>
				</tr>
			</tbody>
		</table>
		</logic:present>
	</center>
</body>
</html:html>
