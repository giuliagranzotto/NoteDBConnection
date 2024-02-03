<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="mycss.css">
<title>Insert title here</title>

</head>
	<body>
	<video id="video-background" src="video3.mp4" autoplay muted loop></video>
	<div class="container text-center">
	<div class="row">
		<div class="col">
		<h2> aggiungi note </h2>
		<form action="addNote" method="POST">
		<label for="title">titolo:</label>
		<input type="text" id="title" name="title"class="form-control">
		<label for="content">Contenuto:</label>
		<textarea id="content" name="content" class="form-control"></textarea><br>
		<input type="submit" value="aggiungi" class="btn btn-success"><br></br>
		<a href="tabella.jsp" class="link-info" >
		<button type="button" class="btn btn-info">Versione con tabella</button></a>
	

</form>
	<%
String url="jdbc:mariadb://localhost:3306/note_db";
String username="root";
String psw= "";


	try{
	Class.forName("org.mariadb.jdbc.Driver");
	Connection connection=DriverManager.getConnection(url, username, psw);
	Statement statement=connection.createStatement();
	String query="SELECT * FROM notes";
	ResultSet resultSet=statement.executeQuery(query);
	
	while (resultSet.next()){
		int id=resultSet.getInt("id");
		String title=resultSet.getString("title");
		String content=resultSet.getString("content");
	%>		
</div>
		<div class="col">
			<div class="card mb-3">
				<div class="card-body">
				<h3 class="card-title">ID:<%=id %> </h3>
				<h3 class="card-title">TITOLO:<%=title %> </h3>
				<p class="card-title">CONTENUTO:<%=content %> </p>
				<form action="editNote" method="POST" style="display:inline;">
		<input type="hidden" name="id" value="<%=id%>"> 
		<button type="submit" class="btn btn-primary">Modifica</button>
		</form>
		
	<form action="deleteNote" method="POST" style="display:inline;">
		<input type="hidden" name="noteId" value="<%=id%>"> 
		<button type="submit" class="btn btn-danger">Elimina</button>
	</form>
				</div>
			</div>
	<% }
	resultSet.close();
	statement.close();
	connection.close();
	}catch (ClassNotFoundException | SQLException e ){
	e.printStackTrace();
	
	} %>
		</div>
	</div>
</div>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	</body>


</html>