<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>modificaVotiElenco</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
<jsp:include page="sessionLogin.jsp" flush="true"></jsp:include>
<center>
		<h1>Gestione Esame</h1>
		<html:form action="/ModificaVoti">
		<logic:present name="elencoPrenotazioniEsami">
         			<table border="0" cellspacing="1" cellpadding="1" align="center" width="70%" style="border-collapse:collapse;">
         				<tr bgcolor="#98AFCC">
         					<th>N°</th>
            				<th>Email</th>
            				<th>Data esame</th>
            				<th>Data prenotazione</th>
            				<th>Presenza esame</th>
            				<th>Voto</th>
            				<th>Voto accettato?</th>
            				<th>Nota</th>
	    				</tr>
	    				<%boolean even = false; %>
	    				<%int counter = 1; %>
	    				<logic:iterate id="user" name="elencoPrenotazioniEsami">
	    				<% even = !even; %>
	    				<tr bgcolor="<%=even?"#B7D3F5":"#D6E0F5" %>">
	    					<td><%=counter++%></td>
	    					<td><bean:write name="user" property="idStudente" /></td>
    						<td><bean:write name="user" property="dataEsame" /></td>
    						<td><bean:write name="user" property="dataPrenotazione" /></td>
    						<td><bean:write name="user" property="presenzaEsame" /></td>
    						<td><bean:write name="user" property="votoEsame" /></td>
    						<td>
    							<html:checkbox name="user" property="votoAccettato" value="on"/>
							</td>
    						<td><bean:write name="user" property="nota" /></td>
    						<td>
    							<input type="radio" name="radio" value='<bean:write name="user" property="id" />'  /> 
    						</td>
    					</tr>
     					</logic:iterate>
     					<tr>
     						<td colspan="6" align="center">
     							<html:submit value="Avanti"></html:submit>
     						</td>
     					</tr>
     				</table>
     				<table>
     					<tr>
     						<td align=center>
     							<html:errors />
     						</td>
     					</tr>
     				</table>
		</logic:present>
		</html:form>
	</center>
</body>
</html:html>
