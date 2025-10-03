package com.example.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Logout() {
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
		else
		{
			session.invalidate();
			response.sendRedirect("Login.jsp");
		}
		
		pr.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
