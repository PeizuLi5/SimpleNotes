package servlets;

import java.io.IOException;
import java.util.List;

import Applications.DBUtils;
import Applications.Note;
import Applications.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeQAServlet
 */
public class ChangeQAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeQAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newQuestion = request.getParameter("question");
		String newAnswer = request.getParameter("answer");
		String user = request.getParameter("user");
		
		User userInfo = DBUtils.getUserInfo(user);
		
		if(!newQuestion.equals(userInfo.getQuestion()) && !newAnswer.equals(userInfo.getAnswer())) {
			DBUtils.changeQA(user, newQuestion, newAnswer);
		}
		else if(!newQuestion.equals(userInfo.getQuestion())) {
			DBUtils.changeQA(user, newQuestion, null);
		}
		else if(!newAnswer.equals(userInfo.getAnswer())) {
			DBUtils.changeQA(user, null, newAnswer);
		}
		
		request.setAttribute("user", user);
		List<Note> notes = DBUtils.getNotes(user);
		if(notes != null) {
			request.setAttribute("notes", notes);
		}
		
		String path = "main.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
