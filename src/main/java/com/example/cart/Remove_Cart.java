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

@WebServlet("/Remove_Cart")
public class Remove_Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public Remove_Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pr= response.getWriter();
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("User_Id") == null || session.getAttribute("user") == null)
		{
			pr.println("<h2>Can not Find User Login Again! <a href=Login.jsp>Login</a> </h2>");
		}
		pr.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pr= response.getWriter();
		
		String DB_URL =  System.getenv("DB_URL");
		String DB_NAME = System.getenv("DB_NAME");
		String DB_PASS = System.getenv("DB_PASS");
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("User_Id") == null || session.getAttribute("user") == null)
		{
			pr.println("<h2>Can not Find User Login Again! <a href=Login.jsp>Login</a> </h2>");
		}
		else {
		
		try {
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL , DB_NAME , DB_PASS);
			
			int cid = Integer.parseInt(request.getParameter("cartId"));
			int uid = (int) session.getAttribute("User_Id");
			String query = "delete from cart_table where cart_id = ? AND user_id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,cid);
			ps.setInt(2, uid);
			ps.executeUpdate();
			
			session.setAttribute("RemoveMessage", "Item Removed Successfull");
			response.sendRedirect("CartServlet");
			
		}
		catch(Exception e)
		{
			e.printStackTrace(pr);
		}
		
		pr.close();
		
	}
	}

}
