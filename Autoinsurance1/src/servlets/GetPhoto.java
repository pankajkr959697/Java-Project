package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/GetPhoto")
public class GetPhoto extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u_email=request.getParameter("u_email");
		
		
		try{
		dao.DbConnect db=new dao.DbConnect();
		
		byte[] photo=db.getPhoto(u_email);
		response.getOutputStream().write(photo);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
