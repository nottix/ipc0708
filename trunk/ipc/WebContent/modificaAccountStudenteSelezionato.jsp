<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>modificaAccountStudenteSelezionato</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
	<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
	<center><h1>Modifica Account Studente</h1></center>
	<html:form action="/ModAccStud">
	<center><table border="1">
		<tbody>
			<tr>
				<td>Nome:</td>
				<td><html:text property="nome"></html:text></td>
			</tr>
			<tr>
				<td>Cognome:</td>
				<td><html:text property="cognome"></html:text></td>
			</tr>
			<tr>
				<td>Matricola:</td>
				<td><html:text property="matricola"></html:text></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><html:text property="email" readonly="true"></html:text></td>
			</tr>
			<tr>
				<td>Note:</td>
				<td><html:textarea property="note"></html:textarea></td>
			</tr>
			<tr>
				<td><html:errors/></td>
				<td><html:reset></html:reset></td>
				<td><html:submit></html:submit></td>
			</tr>
		</tbody>
	</table></center>
	
	</html:form>

</body>
</html:html>
