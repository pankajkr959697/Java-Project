package com.tech.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teck.blog.dao.LikeDao;
import com.teck.blog.helper.DbConnect;

/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PrintWriter out=response.getWriter();
String operation=request.getParameter("operation");
int uid=Integer.parseInt(request.getParameter("uid"));
int pid=Integer.parseInt(request.getParameter("pid"));
//out.println("Data from server");
//out.println(operation);
//out.println(pid);
//out.println(uid);
LikeDao ldao=new LikeDao(DbConnect.getConnection());

if(operation.equals("like")) {
	boolean f=ldao.insertLike(pid, uid);
	out.println(f);
}
	}

}
