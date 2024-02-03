<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Note App</title>
<!-- Includi i file CSS di Bootstrap 5.3.0 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<!-- Includi il file di icone di Bootstrap 5.3.0 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<link rel="stylesheet" href="mycss.css">
</head>
<body>
<video id="video-background" src="video3.mp4" autoplay muted loop></video>
<div class="container text-center">
<div class="row">
<div class="col">
		<h1>Note App</h1>
<form action="addNote" method="POST">
<h4>TITOLO:</h4>
<input type="text" id="title" name="title" class="form-control"><br>
<h5>CONTENUTO:</h5>
<textarea id="content" name="content" rows="4" cols="50" class="form-control"></textarea><br>
<input type="submit" value="Aggiungi" class="btn btn-success">
</form>
	<br>
<%-- Connessione al database --%>
<%
String url = "jdbc:mariadb://localhost:3306/note_db";
String username = "root";
String password = "";
try {
Class.forName("org.mariadb.jdbc.Driver");
Connection connection = DriverManager.getConnection(url, username, password);
Statement statement = connection.createStatement();
String query = "SELECT * FROM notes";
ResultSet resultSet = statement.executeQuery(query);
%>
<table class="table table-striped">
<thead>
<tr>
<th>ID</th>
<th>Titolo</th>
<th>Contenuto</th>
<th>Azioni</th>
</tr>
</thead>
<tbody>
<% while (resultSet.next()) {
int id = resultSet.getInt("id");
String title = resultSet.getString("title");
String content = resultSet.getString("content");
%>
<tr>
<td><%= id %></td>
<td><%= title %></td>
<td><%= content %></td>
<td>
<form action="editNote" method="POST" style="display: inline;">
<input type="hidden" name="id" value="<%= id %>">
<button type="submit" class="btn btn-primary"><i class="bi bi-pencil"></i></button>
</form>
<form action="deleteNote" method="POST" style="display: inline;">
<input type="hidden" name="noteId" value="<%= id %>">
<button type="submit" class="btn btn-danger"><i class="bi bi-x"></i></button>
</form>
</td>
</tr>
<% } %>
</tbody>
</table>
<%
resultSet.close();
statement.close();
connection.close();
} catch (ClassNotFoundException | SQLException e) {
e.printStackTrace();
}
%>
	</div>
</div>
</div>
<!-- Includi i file JavaScript di Bootstrap 5.3.0 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


