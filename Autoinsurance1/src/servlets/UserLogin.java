package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		try {
			HttpSession session=request.getSession();
			dao.DbConnect db=new dao.DbConnect();
			String u_name=db.getUserLogin(email, password);
			if(u_name!=null) {
				session.setAttribute("u_email",email);
				session.setAttribute("u_name",u_name);
				response.sendRedirect("UserHome.jsp");
			}else {
				session.setAttribute("msg","Wrong entries!");
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
