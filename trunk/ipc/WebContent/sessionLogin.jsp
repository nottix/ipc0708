<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<table width="100%" border="0">
	<tbody>
		<tr>
			<td align=left>Ciao <%=session.getAttribute("email")%></td>
			<%
			if(session.getAttribute("tipologia").equals("professore")) {
				out.print("<td align=center><a href=\"/ipc/homeProfessore.jsp\">Home</a></td>");
			}
			else if(session.getAttribute("tipologia").equals("studente")) {
				out.print("<td align=center><a href=\"/ipc/homeStudente.jsp\">Home</a></td>");
			} 
			%>
			<td align=right><html:link action="/Logout">Logout</html:link></td>
		</tr>
	</tbody>
</table>