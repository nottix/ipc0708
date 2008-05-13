<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<logic:notPresent name="email" scope="session"  >
	<logic:redirect page="/login.jsp" />
</logic:notPresent>
<table width="100%" border="0">
	<tbody>
		<tr>
			<td align=left>Ciao <%=session.getAttribute("email")%></td>
			<td align=right><html:link action="Logout">Logout</html:link></td>
		</tr>
	</tbody>
</table>