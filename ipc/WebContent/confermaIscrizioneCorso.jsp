<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>confermaIscrizioneCorso</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
	<center>
		<h1>Conferma Iscrizione Corso</h1>
		<html:form action="/ConfermaIscrizioneCorsoDone">
			<table border="1">
				<tbody>
					<tr>
						<td>Email:</td>
						<td><html:text property="email"></html:text></td>
					</tr>
					<tr>
						<td>Data:</td>
						<td><html:text property="dataIscrizione"></html:text></td>
					</tr>
			<tr>
				<td colspan="2" align=center><html:reset />
				<html:submit property="submit" value="Rifiuta" />
				<html:submit property="submit" value="Conferma" /></td>
			</tr>
			<tr>
				<td colspan="2"><font color="red"><html:errors /></font></td></tr>
		</tbody>
			</table>
		</html:form>
	</center>
</body>
</html:html>
