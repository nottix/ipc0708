<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>creazioneCorso</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
<jsp:include page="sessionLogin.jsp" flush="true"></jsp:include>
	<html:form action="/CreazioneCorso">
	<center>
		<h1>Creazione Corso</h1>
		<table border="1">
			<tbody>
				<tr>
					<td>
					Nome:</td>
					<td><html:text property="nome"></html:text></td>
				</tr>
				<tr>
					<td>Acronimo:</td>
					<td><html:text property="acronimo"></html:text></td>
				</tr>
			<tr>
				<td>Descrizione:</td>
				<td><html:text property="descrizione"></html:text></td>
			</tr>
			<tr>
			<td>Data apertura:</td>
			<td><html:text property="dataApertura"></html:text></td>
		</tr>
		<tr>
			<td>Data chiusura:</td>
			<td><html:text property="dataChiusura"></html:text></td>
		</tr>
		<tr>
			<td colspan="2">
			<center>
				<logic:present name="elencoProfessori">
         			<table border="0" cellspacing="1" cellpadding="1" align="center" width="70%" style="border-collapse:collapse;">
         				<tr bgcolor="#98AFCC">
         					<th>N�</th>
            				<th>Nome</th>
            				<th>Cognome</th>
            				<th>Email</th>  
            				<th>Titolare</th>
            				<th>Collaboratore</th>                
	    				</tr>
	    				<%boolean even = false; %>
	    				<%int counter = 1; %>
	    				<logic:iterate id="user" name="elencoProfessori">
	    				<% even = !even; %>
	    				<tr bgcolor='<%=even?"#B7D3F5":"#D6E0F5" %>'>
	    					<td><%=counter++%></td>
	    					<td><bean:write name="user" property="nome" /></td>
    						<td><bean:write name="user" property="cognome" /></td>
    						<td><bean:write name="user" property="email" /></td>
    						<td><input type="checkbox" name='titolare-<bean:write name="user" property="email" />' value="on"  /> </td>
    						<td><input type="checkbox" name='collaboratore-<bean:write name="user" property="email" />' value="on"  /> </td>
    					</tr>
     					</logic:iterate>
     				</table>
				</logic:present>
			</center>
			</td>

		</tr>
		<tr>
			<td colspan=2 align="center">
				<html:reset />
				<html:cancel />
				<html:submit />
			</td>
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
</html:form>
</body>
</html:html>
