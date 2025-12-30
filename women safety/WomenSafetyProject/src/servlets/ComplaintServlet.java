package servlets;

import db.DBConnection;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.http.*;

public class ComplaintServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO complaints(user_id, complaint_type, description, location) VALUES (1,?,?,?)"
            );

            ps.setString(1, req.getParameter("type"));
            ps.setString(2, req.getParameter("description"));
            ps.setString(3, req.getParameter("location"));

            ps.executeUpdate();
            res.sendRedirect("dashboard.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
