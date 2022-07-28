package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import Applications.DBUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckUsernameServlet
 */
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUsernameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		getServletContext().setAttribute("VerifyUser", username);
		int result = DBUtils.checkUser(username);
		PrintWriter out = response.getWriter();
		if(result == 0) {
			request.setAttribute("user", username);
			String path = "forget2.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
		else if(result == 1) {
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert('Cannot find user. Please try again.');"); 
			out.println("location='forget1.jsp';"); 
			out.println("</script>"); 
		}
		else {
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert('Error.');"); 
			out.println("location='forget1.jsp';"); 
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
