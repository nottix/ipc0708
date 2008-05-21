<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>modificaVoti</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
	<center>
		<h1>Modifica Voto</h1>
		<html:form action="/ModificaVotiDone">
			<table border="1">
				<tbody>
					<tr>
						<td>Email:</td>
						<td><html:text property="idStudente"></html:text></td>
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
				<td>Presenza esame:</td>
				<td><html:checkbox property="presenzaEsame" value="on"></html:checkbox></td>
			</tr>
			<tr>
				<td>Voto</td>
				<td><html:text property="votoEsame"></html:text></td>
			</tr>
			<tr>
				<td>Voto accettato:</td>
				<td><html:checkbox property="votoAccettato" value="on"></html:checkbox></td>
			</tr>
			<tr>
				<td>Esaminatore:</td>
				<td><html:text property="esaminatore"></html:text></td>
			</tr>
			<tr>
				<td>Nota:</td>
				<td><html:textarea property="nota"></html:textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><html:cancel></html:cancel><html:reset></html:reset><html:submit></html:submit></td></tr>
			<tr>
				<td colspan="2"><html:errors /></td></tr>
		</tbody>
			</table>


</html:form>
	</center>

</body>
</html:html>
