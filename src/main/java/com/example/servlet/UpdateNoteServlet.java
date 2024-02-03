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

public class UpdateNoteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String noteId=request.getParameter("id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String url="jdbc:mariadb://localhost:3306/note_db";
		String username="root";
		String psw="";
		
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, username, psw);
			String query="UPDATE notes SET title=?, content=? WHERE id=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setString(2, content);
			statement.setString(3, noteId);
			int rowsUpdated=statement.executeUpdate();
			if(rowsUpdated >0) {
				response.sendRedirect("update_success.jsp");
				
			}else {
				response.sendRedirect("update_error.jsp");
			}
			statement.close();
			connection.close();
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
