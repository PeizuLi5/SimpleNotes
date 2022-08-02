package Applications;

import java.sql.Timestamp;
import java.util.List;

public class User {
	private String username;
	private String password;
	private String question;
	private String answer;
	private List<Timestamp> logins;
	private List<Timestamp> logouts;
	
	public User(String username, String password, String question, String answer, List<Timestamp> logins, List<Timestamp> logouts) {
		this.username = username;
		this.password = password;
		this.question = question;
		this.answer = answer;
		this.logins = logins;
		this.logouts = logouts;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public List<Timestamp> getLogins() {
		return logins;
	}

	public List<Timestamp> getLogouts() {
		return logouts;
	}
}
