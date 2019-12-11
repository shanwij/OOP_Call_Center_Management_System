package project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionHandler {
	public static Connection connection = null;
	
	public static Connection connectDB() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/callcentermanagement",
								"root", "jp#31567618");
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public static boolean authenticate(String username, String password) {
		boolean result = false;
		try {
			Connection connection = connectDB();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?;");
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
