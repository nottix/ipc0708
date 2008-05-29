<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>

<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>modificaCorso</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
	<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
	<center><h1>Modifica Corso</h1></center>
	<html:form action="/ModificaCorsoDone">
	<center><table border="1">
		<tbody>
		<logic:present name="corso">
			<tr>
				<td>Nome:</td>
				<td><html:text name="corso" property="nome"></html:text></td>
			</tr>
			<tr>
				<td>Acronimo:</td>
				<td><html:text name="corso" property="acronimo"></html:text></td>
			</tr>
			<tr>
				<td>Descrizione:</td>
				<td><html:text name="corso" property="descrizione"></html:text></td>
			</tr>
			<tr>
				<td>Data Apertura Iscrizioni:</td>
				<td><html:text name="corso" property="dataApertura"></html:text></td>
			</tr>
			<tr>
				<td>Data Chiusura Iscrizioni:</td>
				<td><html:text name="corso" property="dataChiusura"></html:text></td>
			</tr>
			<tr>
				<td>Comunicazioni:</td>
				<td><html:textarea name="corso" property="comunicazioni"></html:textarea></td>
			</tr>
		</logic:present>
			<tr>
			<td colspan="2">
			<center>
				<logic:present name="elencoProfessori">
         			<table border="0" cellspacing="1" cellpadding="1" align="center" width="70%" style="border-collapse:collapse;">
         				<tr bgcolor="#98AFCC">
         					<th>N°</th>
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
		</tbody>
	</table>
	<table>
			<tr>
				<td>
					<font color=red>
						<html:errors/>
					</font>
				</td>
			</tr>
			<tr>
				<td colspan=3 align=center>
					<html:cancel />
					<html:reset />
					<html:submit />
				</td>
			</tr>
			
	</table>
	</center>
	
	</html:form>

</body>
</html:html>
