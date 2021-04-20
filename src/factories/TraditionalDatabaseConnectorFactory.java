package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TraditionalDatabaseConnectorFactory {

	private static Connection connection = null;
	private static String connectionString = "jdbc:mysql://localhost:3306/micro_star";

	public static Connection getDatabaseConnection() throws SQLException {

		if (connection == null) {

			try {

				connection = DriverManager.getConnection(connectionString, "root", "1234567890");


			} catch (SQLException e) {
				throw e;
			}
		}
		return connection;
	}
}