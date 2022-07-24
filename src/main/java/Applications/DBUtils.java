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

	public static void createDB(Connection conn, String path) {
		Statement create = null;
		ResultSet table = null;
		StringBuilder builder = new StringBuilder();
		try {
			table = conn.getMetaData().getTables(null, null, "users", new String[] {"TABLE"});
			if(table.next() == false) {
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
}
