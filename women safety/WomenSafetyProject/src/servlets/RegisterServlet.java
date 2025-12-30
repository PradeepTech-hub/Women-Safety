package servlets;

import db.DBConnection;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("text/html");

        try {
            Connection con = DBConnection.getConnection();
            if (con == null) {
                throw new Exception("Database connection failed");
            }

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name, phone, email, password) VALUES (?,?,?,?)"
            );

            ps.setString(1, req.getParameter("name"));
            ps.setString(2, req.getParameter("phone"));
            ps.setString(3, req.getParameter("email"));
            ps.setString(4, req.getParameter("password"));

            ps.executeUpdate();

            // IMPORTANT: redirect or print something
            res.sendRedirect("login.html?registered=1");

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("Registration Failed: " + e.getMessage());
        }
    }
}
