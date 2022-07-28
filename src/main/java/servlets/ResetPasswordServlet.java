package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import Applications.DBUtils;

/**
 * Servlet implementation class ResetPasswordServlet
 */
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String username = (String) servletContext.getAttribute("VerifyUser");
		String newPassword = (String) request.getParameter("newPassword");
		String reNewPassword = (String) request.getParameter("reNewpassword");
		PrintWriter out = response.getWriter();
		
		if(newPassword.equals(reNewPassword)) {
			DBUtils.changePassword(username, newPassword);
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert('Password successfully changes.');"); 
			out.println("location='home.jsp';"); 
			out.println("</script>");
		}
		else {
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert('Two passwords do not match, please try again.');"); 
			out.println("location='reset.jsp';"); 
			out.println("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
