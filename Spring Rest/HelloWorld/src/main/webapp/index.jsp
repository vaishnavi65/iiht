<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - HelloWorld Index Page</title>
</head>
<body>

	<center>
		<h2>Hello User</h2>
		<h3>
			<a href="hello?name=AddBook">Add a Book</a>
			</br>
			<a href="hello?name=DeleteSubject">Delete a Subject</a>
			</br>
			<a href="hello?name=DeleteBook">Delete a book</a>
			</br>
			<a href="hello?name=SearchBook">Search for a book</a>
			</br>
			<a href="hello?name=SearchSubject">Search for a subject</a>
			</br>
			<a href="hello?name=SearchBookByTitle">Search for a book by Title</a>
			</br>
			<a href="hello?name=SearchSubjectByDuration">Search for a subject By Duration</a>
			</br>
			<a href="hello?name=Exit">Exit</a>
		</h3>
	</center>
</body>
</html>