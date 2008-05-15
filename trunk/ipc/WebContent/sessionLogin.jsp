<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<table width="100%" border="0">
	<tbody>
		<tr>
			<td align=left>Ciao <%=session.getAttribute("email")%></td>
			<td align=right><html:link action="/Logout">Logout</html:link></td>
		</tr>
	</tbody>
</table>