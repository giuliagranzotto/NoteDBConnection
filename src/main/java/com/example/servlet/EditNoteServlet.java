package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class EditNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//Ottieni l'ide della nota da modificare
		String noteId=request.getParameter("id");
	
		
		String url="jdbc:mariadb://localhost:3306/note_db";
		String username="root";
		String psw="";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, username, psw);
			String query="SELECT * FROM notes WHERE id=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, noteId);
			ResultSet resultSet=statement.executeQuery();
			
			if(resultSet.next()) {
				//Recupera i dati della nota
				int id=resultSet.getInt("id");
				String title=resultSet.getString("title");
				String content= resultSet.getString("content");
				
				//Imposta gli attributi della richiesta per passare i dati della pagina di modifica
				request.setAttribute("id", id);
				request.setAttribute("title", title);
				request.setAttribute("content", content);
				
				//reindirizza della pagina di modifica
				request.getRequestDispatcher("edit_note.jsp").forward(request, response);
				
			}else {
				//Se la nota non esiste gestisci l'errore o reindirizza a una pagina di errore
				response.sendRedirect("edit_error.jsp");
				
			}
			resultSet.close();
			statement.close();
			connection.close();
			
		}catch(ClassNotFoundException | SQLException e) {
			
			
			e.printStackTrace();
	}
		}
		
		
	}



