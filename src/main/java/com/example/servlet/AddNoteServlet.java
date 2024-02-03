package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddNoteServlet extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException  {
		// recupera dati della richiesta
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//Parametri di connessione al database
		
		String url = "jdbc:mariadb://localhost:3306/note_db";
		String username = "root";
		String psw = "";
		
		try {
			// Carica il driver jdbc
			Class.forName("org.mariadb.jdbc.Driver");
			
			// Connetti al dabase
			Connection connection = DriverManager.getConnection(url, username, psw);
			
			
			//Prepara la query di inserimento
			String query = "INSERT INTO notes (title, content) VALUES(?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setString(2, content);
			
			// esegui l'inserimento nel database
			int rowsInserted = statement.executeUpdate();
			
			//prepara la risposta
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			//verifica se l'inserimento è stato eseguito con successo
			if(rowsInserted >0) {
				out.println("<h3> La nota è stata aggiunta con successo </h3>");
				
			}else {
				out.println("<h3> Errore durante l'aggiunta della nota </h3>");
				
			}
			
			//Chiudi le risorse
			statement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
		
		}
	}
		
}


