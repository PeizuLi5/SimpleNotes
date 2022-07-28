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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @param password 
	 * @param username 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Object result = DBUtils.login(username, password);
		PrintWriter out = response.getWriter();
		if(result instanceof Integer) {
			switch((int) result) {
				case 1:
					out.println("<script type=\"text/javascript\">"); 
					out.println("alert('Cannot find user. Please try again.');"); 
					out.println("location='login.jsp';"); 
					out.println("</script>"); 
					break;
				case 2:
					out.println("<script type=\"text/javascript\">"); 
					out.println("alert('Password does not match. Please try again.');"); 
					out.println("location='login.jsp';"); 
					out.println("</script>"); 
					break;
				default:
					out.println("<script type=\"text/javascript\">"); 
					out.println("alert('Error!');"); 
					out.println("location='login.jsp';"); 
					out.println("</script>"); 
					break;
			}
		}
		if(result instanceof String) {
			String resultString = (String) result;
			request.setAttribute("user", resultString);
			String path = "main.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
