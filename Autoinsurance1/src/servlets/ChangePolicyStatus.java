package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChangePolicyStatus")
public class ChangePolicyStatus extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid=Integer.parseInt(request.getParameter("pid"));
		String status=request.getParameter("status");
		try {
			HttpSession session=request.getSession();
			dao.DbConnect db=new dao.DbConnect();
			String m=db.updatePolicyStatus(pid, status);
			System.out.println(pid+status);
			session.setAttribute("msg",m);
			response.sendRedirect("CompanyPolicies.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
