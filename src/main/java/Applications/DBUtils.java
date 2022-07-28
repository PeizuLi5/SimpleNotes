package Applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

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
				insert = ocon.prepareStatement("INSERT INTO users (username, password, security_question, security_answer) "
						+ "VALUES (?, ?, ?, ?)");
				insert.setString(1, username);
				insert.setString(2, password);
				insert.setString(3, question);
				insert.setString(4, answer);
				insert.executeUpdate();
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
			preparedStatement = ocon.prepareStatement("SELECT security_question FROM users WHERE username = ?");
			preparedStatement.setString(1, username);
			result = preparedStatement.executeQuery();
			
			while(result.next()) {
				question = result.getString("security_question");
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
			preparedStatement = ocon.prepareStatement("SELECT security_answer FROM users WHERE username = ?");
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
}
