package com.example.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Add_Cart")
public class Add_Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public Add_Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pr= response.getWriter();
		
		String DB_URL =  System.getenv("DB_URL");
		String DB_NAME = System.getenv("DB_NAME");
		String DB_PASS = System.getenv("DB_PASS");
		HttpSession session = request.getSession();
		
		if(session==null || session.getAttribute("user")==null)
		{
			pr.print("<h2>Cannot Find User Login First:<a href=Login.jsp>Login</a><h2>");
		}
		else
		{
			try
			{
				Connection con = null;
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(DB_URL , DB_NAME , DB_PASS);
				int pid = Integer.parseInt(request.getParameter("productId"));
				int uid = (int) session.getAttribute("User_Id");
				
				
				
				
				String query = "insert into cart_table(user_id, product_id) values(?,?)";
				
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, uid);
				ps.setInt(2, pid);
				ps.executeUpdate();
				session.setAttribute("cartMessage", "Item added to cart!");
				response.sendRedirect("Home_Page.jsp");
				
			}
			catch(Exception e)
			{
				e.printStackTrace(pr);
			}
		}
		
		
		
	}

}
