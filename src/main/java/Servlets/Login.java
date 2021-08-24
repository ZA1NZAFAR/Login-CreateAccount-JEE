package Servlets;

import JavaClasses.ConnectionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author z.zafar
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + request.getAttribute("msg") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionDB con = new ConnectionDB();
        try {
            if (con.checkExist(request.getParameter("idJ"), request.getParameter("pwdJ"))) {
                request.setAttribute("msg", "Login success! ");
                processRequest(request, response);
                return;
            }
        } catch (SQLException ex) {
            request.setAttribute("msg", ex.getMessage());
            processRequest(request, response);
            return;
        }
        request.setAttribute("msg", "Login failed!");
        processRequest(request, response);
    }
}
