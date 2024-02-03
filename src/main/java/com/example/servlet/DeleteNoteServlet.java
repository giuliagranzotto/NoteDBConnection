package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class DeleteNoteServlet extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String noteId=request.getParameter("noteId");// Ottieni l'ide della nota da eliminare
	//connettiamoci al database
		String url="jdbc:mariadb://localhost:3306/note_db";
		String username="root";
		String psw="";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, username, psw);
			
			String query="DELETE FROM notes WHERE id=?";
			
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, noteId);
			
			
			
			int rowsDelete=statement.executeUpdate();
			response.setContentType("text/html");
			if(rowsDelete>0) {
				response.getWriter().println("<h3> La nota è stata eiminata con successo </h3>");
				
			}else {
				response.getWriter().println("<h3> Errore durante l'eliminazione della nota </h3>");
				
			}
			statement.close();
			connection.close();
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
		
	
	

}
