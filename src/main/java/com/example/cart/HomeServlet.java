package com.example.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pr= response.getWriter();
		
		String DB_URL =  System.getenv("DB_URL");
		String DB_NAME = System.getenv("DB_NAME");
		String DB_PASS = System.getenv("DB_PASS");
		List<Product> products = new ArrayList<>();
		
		try
		{
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL , DB_NAME , DB_PASS);
			Statement st = con.createStatement();
			String query = "select * from products";			
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				products.add(new Product(
	                    rs.getInt("product_id"),
	                    rs.getString("product_name"),
	                    rs.getFloat("product_price")
	                ));

			}
		}
		catch(Exception e)
		{
			e.printStackTrace(pr);
		}
		
		
		HttpSession session = request.getSession(false);
		session.setAttribute("products", products);
		
		response.sendRedirect("Home_Page.jsp");

		pr.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
