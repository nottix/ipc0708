<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>Gestione Query</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<logic:notPresent name="email" scope="session">
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
	<jsp:include page="sessionLogin.jsp" flush="false"></jsp:include>
	<center>
		<h1>Gestione Query</h1>
		<table border="1">
			<tbody>
				<tr>
					<td><html:link action="/VisualizzaReportDefaultElenco">Visualizza Report Default</html:link></td>
				</tr>
				<tr>
					<td></td>
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
</body>
</html:html>
