<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>Visualizza Report Default</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
	<center>
		<h1>Visualizza Report Default</h1>
		<html:form action="/VisualizzaReportDefault">
			<table border="1">
				<tbody>
					<tr>
						<td align="center" colspan="4">Ordinamento:
							<html:checkbox property="matricola">Matricola</html:checkbox>
							<html:checkbox property="email">Email</html:checkbox>
							<html:checkbox property="nome">Nome</html:checkbox>
							<html:checkbox property="cognome">Cognome</html:checkbox>
						</td>
					</tr>
					<tr><td colspan="4">
					<logic:present name="elencoAccountStudenti">
         			<table border="0" cellspacing="1" cellpadding="1" align="center" width="70%" style="border-collapse:collapse;">
         				<tr bgcolor="#98AFCC">
         					<th>N°</th>
            				<th>Matricola</th>
            				<th>Email</th>
            				<th>Nome</th>
            				<th>Cognome</th>                     
	    				</tr>
	    				<%boolean even = false; %>
	    				<%int counter = 1; %>
	    				<logic:iterate id="user" name="elencoAccountStudenti">
	    				<% even = !even; %>
	    				<tr bgcolor='<%=even?"#B7D3F5":"#D6E0F5" %>'>
	    					<td><%=counter++%></td>
	    					<td><bean:write name="user" property="matricola" /></td>
	    					<td><bean:write name="user" property="email" /></td>
    						<td><bean:write name="user" property="nome" /></td>
    						<td><bean:write name="user" property="cognome" /></td>
    					</tr>
     					</logic:iterate>
     				</table>
					</logic:present>
					</td>
					</tr>
					<tr>
						<td colspan="4">
							<html:cancel></html:cancel>
							<html:reset></html:reset>
							<html:submit></html:submit>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<html:messages id="msg" message="true">
								<bean:write name="msg"/><br>
							</html:messages>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<html:errors/>
						</td>
					</tr>
				</tbody>
			</table>
		</html:form>
	</center>

</body>
</html:html>
