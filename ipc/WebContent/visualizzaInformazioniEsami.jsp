<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>visualizzaInformazioniEsami</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
<jsp:include page="sessionLogin.jsp" flush="true"></jsp:include>
<center>
	<h1>Informazioni Esami</h1>
			<logic:present name="elencoEsami">
         <table border="0" cellspacing="1" cellpadding="1" align="center" width="70%" style="border-collapse:collapse;">
         	<tr bgcolor="#98AFCC">
        		<th>N°</th>
           		<th>Acronimo</th>
           		<th>Data Esame</th>
	         	<th>Data Inizio Pren</th>
	           	<th>Data Fine Pren</th>
	           	<th>Aule</th>
	           				
	    				</tr>
	    				<%boolean even = false; %>
	    				<%int counter = 1; %>
	    				<logic:iterate id="user" name="elencoEsami">
	    				<% even = !even; %>
	    				<tr bgcolor="<%=even?"#B7D3F5":"#D6E0F5" %>">
	    					<td><%=counter++%></td>
	    					<td><%=(String)session.getAttribute("acronimo")%></td>
    						<td><bean:write name="user" property="dataEsame" /></td>
	    					<td><bean:write name="user" property="dataInizioPeriodoPrenotazione" /></td>
    						<td><bean:write name="user" property="dataFinePeriodoPrenotazione" /></td>
    						<td><bean:write name="user" property="auleEsame" /></td>
    					</tr>
     					</logic:iterate>
     				</table>
		</logic:present>
		
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
</body>
</html:html>
