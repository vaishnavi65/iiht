<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
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
          action="/HelloWorld/addBook" modelAttribute="book">
             <table>
                <tr>
                    <td><form:label path="title">Name of the book</form:label></td>
                    <td border=1><form:input path="title"/></td>
                </tr>
                <tr>
                    <td><form:label path="volume">Volume</form:label></td>
                    <td><form:input path="volume"/></td>
                </tr>
                <tr>
                    <td><form:label path="price">
                      price</form:label></td>
                    <td><form:input path="price"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
	  </center>
</body>
</html>