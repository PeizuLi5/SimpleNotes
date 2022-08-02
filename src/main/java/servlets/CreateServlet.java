package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import Applications.DBUtils;
import Applications.Note;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateServlet
 */
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		PrintWriter out = response.getWriter();
		
		if(title.isEmpty() && content.isEmpty()) {
			request.setAttribute("user", username);
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert('No notes inserted.');");
			out.println("location='create.jsp';");
			out.println("</script>");
			
			List<Note> notes = DBUtils.getNotes(username);
			if(notes != null) {
				request.setAttribute("notes", notes);
			}
			
			String path = "main.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
		else if(content.isEmpty()) {
			content = "";
			DBUtils.createNote(username, title, content);	
			
			request.setAttribute("user", username);
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert('Note successfully creates.');");
			out.println("location='create.jsp';");
			out.println("</script>");
			
			List<Note> notes = DBUtils.getNotes(username);
			if(notes != null) {
				request.setAttribute("notes", notes);
			}
			
			String path = "main.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
		else {
			DBUtils.createNote(username, title, content);
			
			request.setAttribute("user", username);
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert('Note successfully creates.');");
			out.println("location='create.jsp';");
			out.println("</script>");
			
			List<Note> notes = DBUtils.getNotes(username);
			if(notes != null) {
				request.setAttribute("notes", notes);
			}
			
			String path = "main.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
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
