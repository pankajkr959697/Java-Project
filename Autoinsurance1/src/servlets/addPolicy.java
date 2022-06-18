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
@WebServlet("/addPolicy")
public class addPolicy extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String c_email=(String)session.getAttribute("c_email");
	String p_name=request.getParameter("p_name");
	int p_si_amt=Integer.parseInt(request.getParameter("p_si_amt"));
	String p_desc=request.getParameter("p_desc");
	String category=request.getParameter("category");
	try {
	 
		
		
		dao.DbConnect db=new dao.DbConnect();
		HashMap<String,Object> policy=new HashMap<String,Object>();
		
		policy.put("c_email",c_email);
		policy.put("p_name",p_name);
		policy.put("p_si_amt",p_si_amt);
		policy.put("p_desc",p_desc);
	
		policy.put("category", category);
		boolean result=db.addPolicy(policy);
		if(result) {
						session.setAttribute("msg","Policy Added Successfully.");
//			session.setAttribute("c_name",name);
			response.sendRedirect("CompanyHome.jsp");
			
		}
		else {
			session.setAttribute("msg","Policy Already Exist.");
			response.sendRedirect("CompanyHome.jsp");
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}

}
