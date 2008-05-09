<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>Home Studente</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<body>
<table width="100%" border="0">
	<tbody>
		<tr>
			<td align=left>Ciao $nome_studente</td>
			<td align=right>Logout</td>
		</tr>
	</tbody>
</table>
<center>
<table border="1">
	<tbody>
		<tr>
			<td align="center"><html:button property="iscrizioneCorso">Iscrizione Corso<br>
			</html:button></td>
			<td align="center"><html:button property="iscrizioneEsame">Iscrizione Esame<br>
			</html:button></td>
		</tr>
		<tr>
			<td align="center"><html:button property="listaCorsi">Lista Corsi<br>
			</html:button></td>
			<td align="center"><html:button property="listaEsami">Lista Esami<br>
			</html:button></td>
		</tr>
	</tbody>
</table>
</center>
<p><br>Il frame + dinamico del mondo!
</p>
</body>
</html:html>
