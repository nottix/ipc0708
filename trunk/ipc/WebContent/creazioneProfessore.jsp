<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>Creazione Professore</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>

<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
	<center><h1>Creazione Professore</h1></center>
	<html:form action="/CreazioneProfessore">
	<center><table border="1" width="40%">
		<tbody>
			<tr>
				<td width="181">Nome:</td>
				<td width="176" align="center"><html:text property="nome"></html:text></td>
			</tr>
			<tr>
				<td width="181">Cognome:</td>
				<td width="176" align="center"><html:text property="cognome"></html:text></td>
			</tr>
			<tr>
				<td width="181">Email:</td>
				<td width="176" align="center"><html:text property="email"></html:text></td>
			</tr>
			<tr>
				<td width="181">Password:</td>
				<td width="176" align="center"><html:password property="password"></html:password></td>
			</tr>
			<tr>
				<td width="181">Conferma password:</td>
				<td width="176" align="center"><html:password property="confPassword"></html:password></td>
			</tr>
			<tr>
				<td width="181"><html:checkbox property="isDirettore" value="on">Direttore di Dipartimento</html:checkbox></td>
				<td width="176" align="center"><html:checkbox property="isGestore" value="on">Gestore di Sistema</html:checkbox></td>
			</tr>
			<tr>
				<td width="181" align="center"><html:reset value="Reset"></html:reset></td>
				<td width="176" align="center"><html:submit value="Conferma"></html:submit></td>
			</tr>
			<tr>
				<td width="181" colspan="2"><html:errors /></td></tr>
			<tr>
				<td width="181"></td>
				<td width="176"></td>
			</tr>
		</tbody>
	</table></center>

</html:form>
</body>
</html:html>
