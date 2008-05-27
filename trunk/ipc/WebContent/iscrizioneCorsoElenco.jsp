<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>iscrizioneCorsoElenco</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
	<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
	<center>
		<h1>Iscrizione Corso</h1>
		<html:form action="/IscrizioneCorso">
		<logic:present name="elencoCorsi">
         			<table border="0" cellspacing="1" cellpadding="1" align="center" width="70%" style="border-collapse:collapse;">
         				<tr bgcolor="#98AFCC">
         					<th>N�</th>
            				<th>Acronimo</th>
            				<th>Nome</th>
            				<th>Descrizione</th>
            				<th>Data apertura</th>
            				<th>Data chiusura<th>
	    				</tr>
	    				<%boolean even = false; %>
	    				<%int counter = 1; %>
	    				<logic:iterate id="user" name="elencoCorsi">
	    				<% even = !even; %>
	    				<tr bgcolor="<%=even?"#B7D3F5":"#D6E0F5" %>">
	    					<td><%=counter++%></td>
	    					<td><bean:write name="user" property="acronimo" /></td>
    						<td><bean:write name="user" property="nome" /></td>
    						<td><bean:write name="user" property="descrizione" /></td>
    						<td><bean:write name="user" property="dataApertura" /></td>
    						<td><bean:write name="user" property="dataChiusura" /></td>
    						<td>
    							<input type="radio" name="radio" value='<bean:write name="user" property="acronimo" />'  /> 
    						</td>
    					</tr>
     					</logic:iterate>
     					<tr>
     						<td colspan="6" align="center">
     							<html:submit value="Iscrizione"></html:submit>
     						</td>
     					</tr>
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
		</logic:present>
		</html:form>
	</center>
</body>
</html:html>
