package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import MailCode.MailSendCode;



/**
 * Servlet implementation class CompanyRegister
 */
@MultipartConfig
@WebServlet("/CompanyRegister")
public class CompanyRegister extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String email=request.getParameter("email");
	String name=request.getParameter("name");
	String phone=request.getParameter("phone");
	String reg_no=request.getParameter("reg_no");
	String password=request.getParameter("password");
	String address=request.getParameter("address");
	Part p=request.getPart("logo");
	 int cid=(int)(Math.random()*10000);
	InputStream logo=p.getInputStream();
	try {
		HttpSession session=request.getSession();
		
		
		dao.DbConnect db=new dao.DbConnect();
		HashMap<String,Object> company=new HashMap<String,Object>();
		
		company.put("email",email);
		company.put("name",name);
		company.put("phone",phone);
		company.put("reg_no",reg_no);
		company.put("password",password);
		company.put("address",address);
		company.put("logo",logo);
		company.put("cid",cid);
		
		boolean result=db.addCompanny(company);
		if(result) {
			String r=MailSendCode.sendMail(email,"Company Registration","Your Company Successfully Register with us. Your id is "+cid+". Use this id for login");
			session.setAttribute("msg","Company Regestration Successfully."+r);
//			session.setAttribute("c_name",name);
			response.sendRedirect("Company.jsp");
			
		}
		else {
			session.setAttribute("msg","Company Already Register!");
			response.sendRedirect("Company.jsp");
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}

}
