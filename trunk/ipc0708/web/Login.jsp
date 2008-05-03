<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
  <title>Login</title>
  <link href="style/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<table align="center">
<s:form action="Login"> 
  <s:textfield label="User Name" name="username"/>
  <s:password label="Password" name="password" />
  <s:submit/>
</s:form>
</table>
</body>
</html>
