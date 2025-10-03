package com.example.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CartServlet() {
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
		ResultSet rs = null;
		
		
		List<CartItem> cartItems = new ArrayList<>();

		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("User_Id")==null)
		{
			pr.println("Can not Find User Please Lpgijn Again!<a href=Login.jsp>Login</a>");
		}
		else 
		{
			try
			{
				
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(DB_URL , DB_NAME , DB_PASS);
				Statement st = con.createStatement();
				String query = "SELECT c.cart_id, c.user_id, p.product_id, p.product_name, p.product_price " +
			               "FROM products p JOIN cart_table c ON p.product_id = c.product_id " +
			               "WHERE c.user_id = '" + session.getAttribute("User_Id") + "'";
				
				rs = st.executeQuery(query);

				while (rs.next()) {
	                Product product = new Product(
	                    rs.getInt("product_id"),
	                    rs.getString("product_name"),
	                    rs.getFloat("product_price")
	                );

	                CartItem item = new CartItem(
	                    rs.getInt("cart_id"),
	                    rs.getString("user_id"),
	                    product
	                );

	                cartItems.add(item);
					pr.println(rs.getString(1) + " " +rs.getString(2) + " " +rs.getString(3));
	            }

				
				
			}
			catch(Exception e)
			{
				e.printStackTrace(pr);
			}
			finally
			{
				try {
					if (rs != null) rs.close();
					if (con != null) con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		session.setAttribute("Cart_Items", cartItems);
		
		
		response.sendRedirect("View_Cart.jsp");
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

