<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>visualizzaInformazioniCorso</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
<jsp:include page="sessionLogin.jsp" flush="true"></jsp:include>
<center>
	<logic:present name="elencoTitolari">
	Titolari del Corso:
    <table border="0" cellspacing="1" cellpadding="1"
		   align="center" width="70%" style="border-collapse:collapse;">
		<tr bgcolor="#98AFCC">
			<th>N°</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Email</th>
		</tr>
		<%boolean even = false; %>
		<%int counter = 1; %>
		<logic:iterate id="titolare" name="elencoTitolari">
			<% even = !even; %>
			<tr bgcolor="<%=even?"#B7D3F5":"#D6E0F5" %>">
				<td><%=counter++%></td>
				<td><bean:write name="titolare" property="nome" /></td>
				<td><bean:write name="titolare" property="cognome" /></td>
				<td><bean:write name="titolare" property="email" /></td>
			</tr>
		</logic:iterate>
	</table>
	</logic:present>
	<logic:present name="elencoCollaboratori">
	Collaboratori del Corso:
    <table border="0" cellspacing="1" cellpadding="1"
		   align="center" width="70%" style="border-collapse:collapse;">
		<tr bgcolor="#98AFCC">
			<th>N°</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Email</th>
		</tr>
		<% boolean even1 = false; %>
		<% int counter1 = 1; %>
		<logic:iterate id="collaboratore" name="elencoCollaboratori">
			<% even1 = !even1; %>
			<tr bgcolor="<%=even1?"#B7D3F5":"#D6E0F5" %>">
				<td><%=counter1++%></td>
				<td><bean:write name="collaboratore" property="nome" /></td>
				<td><bean:write name="collaboratore" property="cognome" /></td>
				<td><bean:write name="collaboratore" property="email" /></td>
			</tr>
		</logic:iterate>
	</table>
	</logic:present>
	<table align=center>
		<tr>
			<td><font color="red"> <html:errors /> </font></td>
		</tr>
	</table>	
</body>
</html:html>
