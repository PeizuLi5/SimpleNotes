package Applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OraclePreparedStatement;

public class DBUtils {

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver(new OracleDriver());
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "system";
            String password = "SysPassword1";
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            return conn;
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		} 
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}

	public static void createDB(OracleConnection conn, String path) {
		Statement create = null;
		ResultSet table = null;
		StringBuilder builder = new StringBuilder();
		try {
			create = conn.createStatement();
			String find = "SELECT table_name FROM user_tables where table_name = 'USERS'";
			table = create.executeQuery(find);
			if(!table.isBeforeFirst()) {
				try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
		           String str;
		           while ((str = buffer.readLine()) != null) {
		               builder.append(str).append("\n");
		           }
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				String result = builder.toString();
				String[] arrResult = result.split(";\n");
				for(String a : arrResult) {
					create = conn.createStatement();
					create.executeUpdate(a);
					create = null;
				}
			}
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		} 
	}

	public static int createUser(String username, String password, String question, String answer) {
		OracleConnection ocon = null;
		PreparedStatement insert = null;
		PreparedStatement insert2 = null;
		PreparedStatement checkExists = null;
		ResultSet result = null;
		
		if(username.length() > 25) {
			return 1;
		}
		else if(password.length() > 20) {
			return 2;
		}
		else if(question.length() > 100) {
			return 3;
		}
		else if(answer.length() > 50) {
			return 4;
		}
		
		try {
			ocon = (OracleConnection) getConnection();
			
			checkExists = ocon.prepareStatement("SELECT * FROM users WHERE username = ?");
			checkExists.setString(1, username);	
			result = checkExists.executeQuery();
			
			if(result.isBeforeFirst()) {
				return 5;
			}
			else {
				insert = ocon.prepareStatement("INSERT INTO users (username, password) "
						+ "VALUES (?, ?)");
				insert.setString(1, username);
				insert.setString(2, password);
				insert.executeUpdate();
				
				insert2 = ocon.prepareStatement("INSERT INTO userQuestion (username, question, answer) "
						+ "VALUES (?, ?, ?)");
				insert2.setString(1, username);
				insert2.setString(2, question);
				insert2.setString(3, answer);
				insert2.executeUpdate();
				return 0;
			}
		} 
		catch (SQLException e) {
			return 6;
		}
		finally {
			try {
				if(result != null) {
					result.close();
				}
				if(checkExists != null) {
					checkExists.close();
				}
				if(insert != null) {
					insert.close();
				}
				if(insert2 != null) {
					insert.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
	}

	public static Object login(String username, String password) {
		OracleConnection ocon = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement insert = null;
		ResultSet result = null;
		String dBPassword = new String();
		String user = new String();
		
		try {
			ocon = (OracleConnection) getConnection();
			preparedStatement = ocon.prepareStatement("SELECT username, password FROM users WHERE username = ?");
			preparedStatement.setString(1, username);
			result = preparedStatement.executeQuery();
			
			if(!result.isBeforeFirst()) {
				return 1;
			}
			else {
				while(result.next()) {
					user = result.getString(1);
					dBPassword = result.getString(2);
				}
				if(dBPassword.equals(password)) {
					insert = ocon.prepareStatement("INSERT INTO login (username, dates) VALUES (?, ?)");
					insert.setString(1, user);
					Timestamp time =  new Timestamp(System.currentTimeMillis());
					insert.setTimestamp(2, time);
					insert.executeUpdate();
					
					
					return user;
				}
				else {
					return 2;
				}
			}
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return 3;
		}
		finally {
			try {
				if(result != null) {
					result.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static int checkUser(String username) {
		OracleConnection ocon = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			preparedStatement = ocon.prepareStatement("SELECT username FROM users WHERE username = ?");
			preparedStatement.setString(1, username);
			result = preparedStatement.executeQuery();
			
			if(!result.isBeforeFirst()) {
				return 1;
			}
			else {
				return 0;
			}
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return 2;
		}
		finally {
			try {
				if(result != null) {
					result.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	
	public static String getQuestion(String username) {
		OracleConnection ocon = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		String question = "";
		
		try {
			ocon = (OracleConnection) getConnection();
			preparedStatement = ocon.prepareStatement("SELECT question FROM userQuestion WHERE username = ?");
			preparedStatement.setString(1, username);
			result = preparedStatement.executeQuery();
			
			while(result.next()) {
				question = result.getString("question");
			}
			return question;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		finally {
			try {
				if(result != null) {
					result.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static boolean verifyAnswer(String username, String answer) {
		OracleConnection ocon = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			preparedStatement = ocon.prepareStatement("SELECT answer FROM userQuestion WHERE username = ?");
			preparedStatement.setString(1, username);
			result = preparedStatement.executeQuery();
			String dbAnswer = "";
			
			if(result.isBeforeFirst()) {
				while(result.next()) {
					dbAnswer = result.getString(1);
				}
				if(answer.equals(dbAnswer)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
		finally {
			try {
				if(result != null) {
					result.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static void changePassword(String username, String newPassword) {
		OracleConnection ocon = null;
		PreparedStatement preparedStatement = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			preparedStatement = ocon.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
	}

	public static void logout(String username) {
		OracleConnection ocon = null;
		PreparedStatement insert = null;
		try {
			ocon = (OracleConnection) getConnection();
			insert = ocon.prepareStatement("INSERT INTO logout (username, dates) VALUES (?, ?)");
			insert.setString(1, username);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			insert.setTimestamp(2, time);
			insert.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(insert != null) {
					insert.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static void createNote(String username, String title, String content) {
		OracleConnection ocon = null;
		OraclePreparedStatement insertNote = null;
		PreparedStatement find = null;
		PreparedStatement insertTime = null;
		PreparedStatement insertAuthor = null;
		PreparedStatement insertContain = null;
		ResultSet table = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			insertNote = (OraclePreparedStatement) ocon.prepareStatement("INSERT INTO notes(title, content) VALUES (?, ?)");
			insertNote.setString(1, title);
			insertNote.setStringForClob(2, content);
			insertNote.executeUpdate();
			
			find = ocon.prepareStatement("SELECT Nid FROM notes WHERE Nid = (SELECT MAX(Nid) FROM notes)");
			table = find.executeQuery();
			int Nid = 0;
			while(table.next()) {
				Nid = table.getInt("Nid");
			}
			
			insertTime = ocon.prepareStatement("INSERT INTO edit_time VALUES (?, ?)");
			insertTime.setInt(1, Nid);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			insertTime.setTimestamp(2, time);
			insertTime.executeUpdate();
			
			insertAuthor = ocon.prepareStatement("INSERT INTO author VALUES (?, ?)");
			insertAuthor.setString(1, username);
			insertAuthor.setInt(2, Nid);
			insertAuthor.executeUpdate();
			
			insertContain = ocon.prepareStatement("INSERT INTO contain VALUES (?, ?)");
			insertContain.setString(1, username);
			insertContain.setInt(2, Nid);
			insertContain.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(table != null) {
					table.close();
				}
				if(insertContain != null) {
					table.close();
				}
				if(insertAuthor != null) {
					table.close();
				}
				if(insertTime != null) {
					table.close();
				}
				if(find != null) {
					table.close();
				}
				if(insertNote != null) {
					table.close();
				}
				if(ocon != null) {
					table.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
	}

	public static List<Note> getNotes(String username) {
		List<Note> notes = new ArrayList<Note>();
		List<Integer> contain = new ArrayList<Integer>();
		OracleConnection ocon = null;
		PreparedStatement findContains = null;
		OraclePreparedStatement findNotes = null;
		ResultSet containTable = null;
		ResultSet noteTable = null;
		
		
		try {
			ocon = (OracleConnection) getConnection();
			findContains = ocon.prepareStatement("SELECT Nid FROM contain WHERE username = ?");
			findContains.setString(1, username);
			containTable = findContains.executeQuery();
			
			while(containTable.next()) {
				contain.add(containTable.getInt("Nid"));
			}
			
			for(int i : contain) {
				findNotes = (OraclePreparedStatement) ocon.prepareStatement("SELECT * FROM notes WHERE Nid = ?");
				findNotes.setInt(1, i);
				noteTable = findNotes.executeQuery();
				while(noteTable.next()) {
					int Nid = noteTable.getInt("Nid");
					String title = noteTable.getString("title");
					Clob clob = noteTable.getClob("content");
					String content = new String();
					
					if(clob == null) {
						content = "";
					}
					else {
						BufferedReader reader = new BufferedReader(clob.getCharacterStream());
						String line = null;
						StringBuffer strBuff = new StringBuffer();
						while((line = reader.readLine()) != null) {
							strBuff.append(line);
						}	
						content = strBuff.toString();
					}
					
					Note note = new Note(Nid, title, content);
					notes.add(note);
				}
			}
		}
		catch(SQLException | IOException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(noteTable != null) {
					noteTable.close();
				}
				if(containTable != null) {
					containTable.close();
				}
				if(findNotes != null) {
					findNotes.close();
				}
				if(findContains != null) {
					findContains.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		if(notes.isEmpty()) {
			return null;
		}
		else {
			return notes;
		}
	}

	public static String getAuthor(int nid) {
		OracleConnection ocon = null;
		PreparedStatement findAuthor = null;
		ResultSet table = null;
		String author = "";
		
		try {
			ocon = (OracleConnection) getConnection();
			findAuthor = ocon.prepareStatement("SELECT username FROM author WHERE Nid = ?");
			findAuthor.setInt(1, nid);
			table = findAuthor.executeQuery();
			
			while(table.next()) {
				author = table.getString(1);
			}
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(table != null) {
					table.close();
				}
				if(findAuthor != null) {
					findAuthor.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return author;
	}

	public static Timestamp getEditTime(int nid) {
		OracleConnection ocon = null;
		OraclePreparedStatement findTime = null;
		ResultSet table = null;

		try {
			ocon = (OracleConnection) getConnection();
			findTime = (OraclePreparedStatement) ocon.prepareStatement("SELECT MAX(edit_time) FROM edit_time WHERE Nid = ? GROUP BY nid");
			findTime.setInt(1, nid);
			table = findTime.executeQuery();
			
			while(table.next()) {
				Timestamp time = table.getTimestamp(1);
				return time;
			}
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(table != null) {
					table.close();
				}
				if(findTime != null) {
					findTime.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
			
		}
		
		return null;
	}

	public static void deleteNote(int nid) {
		OracleConnection ocon = null;
		PreparedStatement delete = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			delete = ocon.prepareStatement("DELETE FROM notes WHERE Nid = ?");
			delete.setInt(1, nid);
			delete.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(delete != null) {
					delete.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static boolean isImportant(String user, int nid) {
		OracleConnection ocon = null;
		PreparedStatement findImportant = null;
		ResultSet important = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			findImportant = ocon.prepareStatement("SELECT username FROM important WHERE username = ? AND Nid = ?");
			findImportant.setString(1, user);
			findImportant.setInt(2, nid);
			important = findImportant.executeQuery();
			
			while(important.next()) {
				return true;
			}
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(important != null) {
					important.close();
				}
				if(findImportant != null) {
					findImportant.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return false;
	}

	public static void setImportant(String user, int nid) {
		OracleConnection ocon = null;
		PreparedStatement insert = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			insert = ocon.prepareStatement("INSERT INTO important VALUES (?, ?)");
			insert.setString(1, user);
			insert.setInt(2, nid);
			insert.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(insert != null) {
					insert.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static void removeImportant(String user, int nid) {
		OracleConnection ocon = null;
		PreparedStatement delete = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			delete = ocon.prepareStatement("DELETE FROM important WHERE username = ? AND Nid = ?");
			delete.setString(1, user);
			delete.setInt(2, nid);
			delete.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(delete != null) {
					delete.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}	
	}

	public static void modifyNote(int nid, String title, String content) {
		OracleConnection ocon = null;
		OraclePreparedStatement update = null;
		PreparedStatement insertTime = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			update = (OraclePreparedStatement) ocon.prepareStatement("UPDATE notes SET title = ?, content = ? WHERE Nid = ?");
			update.setString(1, title);
			update.setStringForClob(2, content);
			update.setInt(3, nid);
			update.executeUpdate();
			
			insertTime = ocon.prepareStatement("INSERT INTO edit_time VALUES (?, ?)");
			insertTime.setInt(1, nid);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			insertTime.setTimestamp(2, time);
			insertTime.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(update != null) {
					update.close();
				}
				if(insertTime != null) {
					insertTime.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static List<String> getShare(int nid) {
		OracleConnection ocon = null;
		PreparedStatement findShare = null;
		ResultSet table = null;
		List<String> share_to = new ArrayList<String>();
		
		try {
			ocon = (OracleConnection) getConnection();
			findShare = (OraclePreparedStatement) ocon.prepareStatement("SELECT share_user FROM share_notes WHERE Nid = ?");
			findShare.setInt(1, nid);
			table = findShare.executeQuery();
			
			while(table.next()) {
				share_to.add(table.getString(1));
			}
			return share_to;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(findShare != null) {
					findShare.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return null;
	}

	public static void shareNote(int nid, String shareTo) {
		OracleConnection ocon = null;
		PreparedStatement findShare = null;
		PreparedStatement insertShare = null;
		PreparedStatement insertContain = null;
		ResultSet table = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			
			findShare = ocon.prepareStatement("SELECT * FROM share_notes WHERE Nid = ? AND share_user = ?");
			findShare.setInt(1, nid);
			findShare.setString(2, shareTo);
			table = findShare.executeQuery();
			
			while(table.next()) {
				return;
			}
			
			insertShare = ocon.prepareStatement("INSERT INTO share_notes VALUES (?, ?)");
			insertShare.setInt(1, nid);
			insertShare.setString(2, shareTo);
			insertShare.executeUpdate();
			
			insertContain = ocon.prepareStatement("INSERT INTO contain VALUES (?, ?)");
			insertContain.setString(1, shareTo);
			insertContain.setInt(2, nid);
			insertContain.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(insertShare != null) {
					insertShare.close();
				}
				if(insertContain != null) {
					insertShare.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static void deleteShare(int nid, String shareTo) {
		OracleConnection ocon = null;
		PreparedStatement deleteShare = null;
		PreparedStatement deleteContain = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			deleteShare = ocon.prepareStatement("DELETE FROM share_notes WHERE Nid = ? AND share_user = ?");
			deleteShare.setInt(1, nid);
			deleteShare.setString(2, shareTo);
			deleteShare.executeUpdate();
			
			deleteContain = ocon.prepareStatement("DELETE FROM contain WHERE username = ? AND Nid = ?");
			deleteContain.setString(1, shareTo);
			deleteContain.setInt(2, nid);
			deleteContain.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(deleteShare != null) {
					deleteShare.close();
				}
				if(deleteContain != null) {
					deleteContain.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	
	public static User getUserInfo(String user) {
		OracleConnection ocon = null;
		PreparedStatement findPassword = null;
		PreparedStatement findQA = null;
		PreparedStatement findLogins = null;
		PreparedStatement findLogouts = null;
		ResultSet passwordResult = null;
		ResultSet qaResult = null;
		ResultSet loginResult = null;
		ResultSet logoutResult = null;

		String password = new String();
		String question = new String();
		String answer = new String();
		List<Timestamp> logins = new ArrayList<Timestamp>();
		List<Timestamp> logouts = new ArrayList<Timestamp>();
		
		try {
			ocon = (OracleConnection) getConnection();
			findPassword = ocon.prepareStatement("SELECT password FROM users WHERE username = ?");
			findPassword.setString(1, user);
			passwordResult = findPassword.executeQuery();
			while(passwordResult.next()) {
				password = passwordResult.getString(1);
			}
			
			findQA = ocon.prepareStatement("SELECT question, answer FROM userQuestion WHERE username = ?");
			findQA.setString(1, user);
			qaResult = findQA.executeQuery();
			while(qaResult.next()) {
				question = qaResult.getString("question");
				answer = qaResult.getString("answer");
			}
			
			findLogins = ocon.prepareStatement("SELECT dates FROM login WHERE username = ? ORDER BY login_id DESC");
			findLogins.setString(1, user);
			loginResult = findLogins.executeQuery();
			int count = 0;
			while(loginResult.next() && count < 5) {
				logins.add(loginResult.getTimestamp(1));
				count++;
			}
			count = 0;
			
			findLogouts = ocon.prepareStatement("SELECT dates FROM logout WHERE username = ? ORDER BY logout_id DESC");
			findLogouts.setString(1, user);
			logoutResult = findLogouts.executeQuery();
			while(logoutResult.next() && count < 5) {
				logouts.add(logoutResult.getTimestamp(1));
				count++;
			}
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(logoutResult != null) {
					logoutResult.close();
				}
				if(loginResult != null) {
					logoutResult.close();
				}
				if(qaResult != null) {
					logoutResult.close();
				}
				if(passwordResult != null) {
					logoutResult.close();
				}
				if(findLogouts != null) {
					findLogouts.close();
				}
				if(findLogins != null) {
					findLogins.close();
				}
				if(findQA != null) {
					findQA.close();
				}
				if(findPassword != null) {
					findPassword.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		User userInfo = new User(user, password, question, answer, logins, logouts);
		return userInfo;	
	}

	public static void changeQA(String user, String newQuestion, String newAnswer) {
		OracleConnection ocon = null;
		PreparedStatement preparedStatement = null;
		
		try {
			ocon = (OracleConnection) getConnection();
			if(newQuestion == null) {
				preparedStatement = ocon.prepareStatement("UPDATE userQuestion SET answer = ? WHERE username = ?");
				preparedStatement.setString(1, newAnswer);
				preparedStatement.setString(2, user);
				preparedStatement.executeUpdate();
			}
			else if(newAnswer == null) {
				preparedStatement = ocon.prepareStatement("UPDATE userQuestion SET question = ? WHERE username = ?");
				preparedStatement.setString(1, newQuestion);
				preparedStatement.setString(2, user);
				preparedStatement.executeUpdate();
			}
			else {
				preparedStatement = ocon.prepareStatement("UPDATE userQuestion SET question = ?, answer = ? WHERE username = ?");
				preparedStatement.setString(1, newQuestion);
				preparedStatement.setString(2, newAnswer);
				preparedStatement.setString(3, user);
				preparedStatement.executeUpdate();
			}
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(ocon != null) {
					ocon.close();
				}
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}
