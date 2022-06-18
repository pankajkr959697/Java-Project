package com.tech.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teck.blog.dao.userDao;
import com.teck.blog.entities.User;
import com.teck.blog.helper.DbConnect;

/**
 * Servlet implementation class RegesterServlet
 */
@MultipartConfig
@WebServlet("/RegesterServlet")
public class RegesterServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Fetch All Form Data
		
		PrintWriter out=response.getWriter();
		
		String check=request.getParameter("check");
		
		if(check==null) {
			out.println("box not checked");
		}
		else {
			String name=request.getParameter("u-name");
			String email=request.getParameter("u-email");
			String password=request.getParameter("u-password");
			String gender=request.getParameter("gender");
			String about=request.getParameter("about");
			
			//create user and set all Data
			User user = new User( name,email,password,gender,about);
			
			//create a user dao object
			userDao dao = new userDao(DbConnect.getConnection());
			if(dao.saveUser(user)) {
				out.println("successfull");
			}else
			{
				out.println("Error");
			}
		}

	}

}
