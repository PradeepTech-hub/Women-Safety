package servlets;

import db.DBConnection;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.http.*;

public class AlertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO alerts(user_id, location) VALUES (1, ?)"
            );

            ps.setString(1, req.getParameter("location"));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
