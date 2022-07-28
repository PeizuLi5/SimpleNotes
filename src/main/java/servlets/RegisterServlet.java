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
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String question = request.getParameter("securityQuestion");
		String answer = request.getParameter("securityAnswer");
		
        int result = DBUtils.createUser(username, password, question, answer);
        PrintWriter out = response.getWriter();
        
        switch(result) {
        	case 0:
        		String path = "home.jsp";
        		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        		dispatcher.forward(request, response);
                break;
        	case 1: 
        		out.println("<script type=\"text/javascript\">"); 
        		out.println("alert('Please enter username in the length between 1 to 25 characters!');"); 
        		out.println("location='register.jsp';"); 
        		out.println("</script>"); 
        		break;
        	case 2: 
        		out.println("<script type=\"text/javascript\">"); 
        		out.println("alert('Please enter password in the length between 1 to 20 characters!');"); 
        		out.println("location='register.jsp';"); 
        		out.println("</script>"); 
        		break;
        	case 3:
        		out.println("<script type=\"text/javascript\">"); 
        		out.println("alert('Please enter question in the length between 1 to 100 characters!');"); 
        		out.println("location='register.jsp';"); 
        		out.println("</script>"); 
        		break;
        	case 4:
        		out.println("<script type=\"text/javascript\">"); 
        		out.println("alert('Please enter answer in the length between 1 to 50 characters!');"); 
        		out.println("location='register.jsp';"); 
        		out.println("</script>"); 
        		break;
        	case 5:
        		out.println("<script type=\"text/javascript\">"); 
        		out.println("alert('Username already exists, please try another username!');"); 
        		out.println("location='register.jsp';"); 
        		out.println("</script>"); 
        		break;
        	default:
        		out.println("<script type=\"text/javascript\">"); 
        		out.println("alert('Error!');"); 
        		out.println("location='register.jsp';"); 
        		out.println("</script>"); 
        		break;
        }
		
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(question);
//		System.out.println(answer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
