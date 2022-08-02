package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import Applications.DBUtils;
import Applications.User;

/**
 * Servlet implementation class SettingServlet
 */
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		
		User userInfo = DBUtils.getUserInfo(user);
		String password = userInfo.getPassword();
		String question = userInfo.getQuestion();
		String answer = userInfo.getAnswer();
		List<Timestamp> logins = userInfo.getLogins();
		List<Timestamp> logouts = userInfo.getLogouts();
		
		request.setAttribute("user", user);
		request.setAttribute("password", password);
		request.setAttribute("question", question);
		request.setAttribute("answer", answer);
		request.setAttribute("logins", logins);
		request.setAttribute("logouts", logouts);
		
		String path = "setting.jsp";
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
