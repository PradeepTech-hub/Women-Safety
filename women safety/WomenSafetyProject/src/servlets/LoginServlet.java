package servlets;

import db.DBConnection;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?"
            );

            ps.setString(1, req.getParameter("email"));
            ps.setString(2, req.getParameter("password"));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res.sendRedirect("dashboard.html?login=1");
            } else {
                res.getWriter().println("Login Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
