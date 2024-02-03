<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Modifica Nota</title>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container">
	<h2>Modifica Nota</h2>

	<form action="updateNote" method="POST">
		<input type="hidden" name="id" value="${id}"> 
		<label for="title">Titolo:</label>
		<input type="text" id="title" name="title" class="form-control" value="${title}"><br>
		
		<label for="content">Contenuto:</label>
		<textarea rows="4" cols="50" id="content" name="content" class="form-control">${content}</textarea><br>

		<input type="submit" value="Aggiorna" class="btn btn-primary">
	</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>