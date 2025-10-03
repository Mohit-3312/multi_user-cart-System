package com.example.cart;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register_User")
public class Register_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Register_User() {
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
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL , DB_NAME , DB_PASS);
			st = con.createStatement();
			
			String user = request.getParameter("username");
			String pass = request.getParameter("password");
			String cpass = request.getParameter("confirmPassword");
			String query = "select * from user_table where user_name='"+user+"'";
			rs = st.executeQuery(query);
			
			if(rs.next())
			{
				pr.println("<h2>Username already exist try using different Username!<a href=Registration.jsp>Register</a></h2>");
			}
			else if(pass.equals(cpass))
			{
				query = "insert into user_table (user_name,user_pass) values(?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, user);
				ps.setString(2, pass);
				
				ps.executeUpdate();

				response.sendRedirect("Login.jsp");
			}
			else
			{
				pr.println("<h2>Password And Confirm Password Mismatch  Register Again!<a href=Registration.jsp>Register</a></h2>");
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
				if (st != null) st.close();
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
