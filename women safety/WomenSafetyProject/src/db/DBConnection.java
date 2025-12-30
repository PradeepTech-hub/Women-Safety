package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:h2:mem:women_safety_db;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            initializeDatabase(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    private static void initializeDatabase(Connection con) {
        try (Statement stmt = con.createStatement()) {
            // Create users table
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                "id IDENTITY PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "phone VARCHAR(15), " +
                "email VARCHAR(100) UNIQUE, " +
                "password VARCHAR(100))");

            // Create alerts table
            stmt.execute("CREATE TABLE IF NOT EXISTS alerts (" +
                "id IDENTITY PRIMARY KEY, " +
                "user_id INT, " +
                "location VARCHAR(255), " +
                "alert_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (user_id) REFERENCES users(id))");

            // Create complaints table
            stmt.execute("CREATE TABLE IF NOT EXISTS complaints (" +
                "id IDENTITY PRIMARY KEY, " +
                "user_id INT, " +
                "complaint_type VARCHAR(100), " +
                "description CLOB, " +
                "location VARCHAR(255), " +
                "complaint_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (user_id) REFERENCES users(id))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
