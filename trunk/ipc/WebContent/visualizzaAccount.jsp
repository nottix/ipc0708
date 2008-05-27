<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>visualizzaAccount</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
	<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
	<center><h1>Visualizza Account</h1></center>
	<html:form action="/VisualizzaAccount">
	<logic:present name="account">
	<center><table border="1">
		<tbody>
			<tr>
				<td>Nome:</td>
				<td><html:text property="nome" readonly="true"></html:text></td>
			</tr>
			<tr>
				<td>Cognome:</td>
				<td><html:text property="cognome" readonly="true"></html:text></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><html:text property="email" readonly="true"></html:text></td>
			</tr>
			<logic:equal name="account" property="tipologia" value="studente">
				<tr>
					<td>Matricola:</td>
					<td><html:text property="matricola" readonly="true"></html:text></td>
				</tr>
				<tr>
					<td>Note:</td>
					<td><html:textarea property="note" readonly="true"></html:textarea></td>
				</tr>
			</logic:equal>
			<logic:equal name="account" property="tipologia" value="professore">
				<tr>
					<td>Note:</td>
					<td><html:textarea property="note" readonly="true"></html:textarea></td>
				</tr>
				<tr>
					<td><html:checkbox name="account" property="isGestore" value="on">Gestore di Sistema</html:checkbox></td>
					<td><html:checkbox name="account" property="isDirettore" value="on">Direttore di Dipartimento</html:checkbox></td>
				</tr>
			</logic:equal>
			<tr>
			<td>
			<logic:present name="elencoCorsiTitolare">
					Titolare in:
         			<table border="0" cellspacing="1" cellpadding="1" align="center" width="70%" style="border-collapse:collapse;">
         				<tr bgcolor="#98AFCC">
         					<th>N°</th>
            				<th>Acronimo</th>
            				<th>Nome</th>
            				<th>Descrizione</th>
	    				</tr>
	    				<%boolean even = false; %>
	    				<%int counter = 1; %>
	    				<logic:iterate id="user" name="elencoCorsiTitolare">
	    				<% even = !even; %>
	    				<tr bgcolor="<%=even?"#B7D3F5":"#D6E0F5" %>">
	    					<td><%=counter++%></td>
	    					<td><bean:write name="user" property="acronimo" /></td>
    						<td><bean:write name="user" property="nome" /></td>
    						<td><bean:write name="user" property="descrizione" /></td>
    					</tr>
     					</logic:iterate>
     				</table>
			</logic:present>
			</td>

			<td>
			<logic:present name="elencoCorsiCollaboratore">
					Collaboratore in:
         			<table border="0" cellspacing="1" cellpadding="1" align="center" width="70%" style="border-collapse:collapse;">
         				<tr bgcolor="#98AFCC">
         					<th>N°</th>
            				<th>Acronimo</th>
            				<th>Nome</th>
            				<th>Descrizione</th>
	    				</tr>
	    				<%boolean even1 = false; %>
	    				<%int counter1 = 1; %>
	    				<logic:iterate id="user" name="elencoCorsiCollaboratore">
	    				<% even1 = !even1; %>
	    				<tr bgcolor="<%=even1?"#B7D3F5":"#D6E0F5" %>">
	    					<td><%=counter1++%></td>
	    					<td><bean:write name="user" property="acronimo" /></td>
    						<td><bean:write name="user" property="nome" /></td>
    						<td><bean:write name="user" property="descrizione" /></td>
    					</tr>
     					</logic:iterate>
     				</table>
			</logic:present>
			</td>
			</tr>
			
			<tr>
				<td><html:reset></html:reset></td>
				<td><html:submit></html:submit></td>
			</tr>
		</tbody>
	</table></center>
	
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

</body>
</html:html>
