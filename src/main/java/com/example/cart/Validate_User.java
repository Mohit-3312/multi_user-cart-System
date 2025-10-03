package com.example.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Validate_User")
public class Validate_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public Validate_User() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pr= response.getWriter();
		
		String DB_URL =  System.getenv("DB_URL");
		String DB_NAME = System.getenv("DB_NAME");
		String DB_PASS = System.getenv("DB_PASS");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL , DB_NAME , DB_PASS);
			
			String user = request.getParameter("username");
			String pass = request.getParameter("password");
			String query = "select * from user_table where user_name=? AND user_pass=?";
			ps = con.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs= ps.executeQuery();
			
			if(rs.next())
			{
				int User_Id = Integer.parseInt( rs.getString("user_id"));
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				session.setAttribute("User_Id", User_Id);
				
				response.sendRedirect("HomeServlet");
			}
			else
			{
				pr.println("UserName Or Password Incorrect! Login Again!<a href=Login.jsp>Login</a>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace(pr);
		}
		finally
		{
			try {
				if (ps != null) ps.close();
				if (rs != null) rs.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		pr.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
