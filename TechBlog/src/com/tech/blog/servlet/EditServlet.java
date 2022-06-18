package com.tech.blog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.teck.blog.dao.userDao;
import com.teck.blog.entities.Message;
import com.teck.blog.entities.User;
import com.teck.blog.helper.DbConnect;
import com.teck.blog.helper.Helper;

/**
 * Servlet implementation class EditServlet
 */
@MultipartConfig
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
		//Fetch All edit data
		String userEmail=request.getParameter("user-Email");
		String userName=request.getParameter("user-name");
		String userPassword=request.getParameter("user-password");
		String userAboout=request.getParameter("user-about");
		Part part=request.getPart("image");
		String imageName=part.getSubmittedFileName();
		
		//get the user from session
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("currentUser");
		user.setEmail(userEmail);
		user.setName(userName);
		user.setAbout(userAboout);
		user.setPassword(userPassword);
		String oldPhoto=user.getProfile();
		user.setProfile(imageName);
		//update database
		userDao dao=new  userDao(DbConnect.getConnection());
		boolean ans=dao.updateUser(user);
		 HttpSession s=request.getSession();
		if(ans) {
			out.println("Updated");
			
			String path=request.getRealPath("/")+"pics"+File.separator+user.getProfile();
			String oldPath=request.getRealPath("/")+"pics"+File.separator+user+oldPhoto;
		//Delete old photo
			if(!oldPath.equals("default.png")) {
			Helper.deleteFile(oldPath);
			}
				if(Helper.saveFile(part.getInputStream(), path)){
					Message msg = new Message("Profile Updated SUccessfully","Success","alert alert-success");
					
					 s.setAttribute("msg",msg);
				}
				else {
					Message msg = new Message("Something Went Wrong","Error","alert alert-danger");
				
					 s.setAttribute("msg",msg);
				}
				
			
		}
		else
		{
			out.println("Not Updated");
			Message msg = new Message("Something Went Wrong","Error","alert alert-danger");
			
			 s.setAttribute("msg",msg);
		}
		response.sendRedirect("Profile.jsp");
	}

}
