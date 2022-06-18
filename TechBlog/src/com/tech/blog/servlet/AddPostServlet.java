package com.tech.blog.servlet;
import java.time.LocalDateTime;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.teck.blog.dao.PostDao;
import com.teck.blog.entities.Post;
import com.teck.blog.entities.User;
import com.teck.blog.helper.DbConnect;
import com.teck.blog.helper.Helper;

/**
 * Servlet implementation class AddPostServlet
 */
@MultipartConfig
@WebServlet("/AddPostServlet")
public class AddPostServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
	int cid=Integer.parseInt(request.getParameter("cid"));
	String pTitle=request.getParameter("pTitle");
	String pContent=request.getParameter("pContent");
	String pCode=request.getParameter("pCode");	
	Part part=request.getPart("pPic");
	   java.util.Date date = new java.util.Date();  
	  
	//Getting UserId
	HttpSession session=request.getSession();
	User user=(User)session.getAttribute("currentUser");
	String s=part.getSubmittedFileName();
	
	
	Post p=new Post(pTitle, pContent, pCode, part.getSubmittedFileName(),null,cid,user.getId());
	PostDao pa=new PostDao(DbConnect.getConnection());
	if(pa.savePost(p)) {
		
		String path3=request.getRealPath("/")+"blog-pic"+File.separator+s;
		Helper.saveFile(part.getInputStream(), path3);
		out.println("Done");
	}
	else {
		System.out.println("Error");
	}
	
	}

}
