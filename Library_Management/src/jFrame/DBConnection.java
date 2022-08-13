package jFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection con = null;
	
	// this method connects whenever we want a connection with the database
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_manag","root","Chel4@sea");
		}
		catch (Exception e) {
            e.printStackTrace();
        }
		return con;
	}
}
