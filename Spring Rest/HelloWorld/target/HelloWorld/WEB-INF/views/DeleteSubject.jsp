<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
</head>
<body>
	<center>
		<h2>
			${message}
		</h2>
	<form:form method="POST"
          action="/HelloWorld/deleteSubject" modelAttribute="subject">
             <table>
                <tr>
                    <td><form:label path="subTitle">Name of the Subject to delete</form:label></td>
                    <td border=1><form:input path="subTitle"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="submitDeleteSubject"/></td>
                </tr>
            </table>
        </form:form>
	  </center>
</body>
</html>