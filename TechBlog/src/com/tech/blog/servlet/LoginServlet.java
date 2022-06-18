package com.tech.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.teck.blog.dao.userDao;
import com.teck.blog.entities.Message;
import com.teck.blog.entities.User;
import com.teck.blog.helper.DbConnect;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//fetch UserName And Password from request login page
		PrintWriter out=response.getWriter();
		String userEmail=request.getParameter("email");
		String userPassword=request.getParameter("password");
		userDao dao=new userDao(DbConnect.getConnection());
		User u=dao.getUserByEmailAndPassword(userEmail, userPassword);
		if(u==null) {
		//Login Error	
			//out.println("invalid Detial");
			 Message msg = new Message(" Invalid Details ! Try with another","error","alert alert-danger");
			 HttpSession session=request.getSession();
			 session.setAttribute("msg",msg);
			 response.sendRedirect("Login.jsp");
		}
		else
		{
			//Login Success
			HttpSession session=request.getSession();
			session.setAttribute("currentUser", u);
			response.sendRedirect("Profile.jsp");
			
		}
	}

}
