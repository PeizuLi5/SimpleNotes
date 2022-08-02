package servlets;

import java.io.IOException;
import java.sql.Timestamp;

import Applications.DBUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImportantServlet
 */
public class ImportantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("nid");
		int Nid = Integer.parseInt(id);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String user = request.getParameter("user");
		String author = request.getParameter("author");
		String isAuthor = request.getParameter("isAuthor");
		Timestamp time = DBUtils.getEditTime(Nid);
		
		DBUtils.setImportant(user, Nid);
		
		if(isAuthor.equals("true")) {
			request.setAttribute("user", user);
			request.setAttribute("Nid", Nid);
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("time", time);
			
			String path = "note_own_important.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("user", user);
			request.setAttribute("Nid", Nid);
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("time", time);
			request.setAttribute("author", author);
			
			String path = "note_share_important.jsp";
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
