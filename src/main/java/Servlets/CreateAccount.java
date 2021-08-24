package Servlets;

import JavaClasses.ConnectionDB;
import JavaClasses.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(urlPatterns = {"/CreateAccount"})
public class CreateAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + request.getAttribute("msg") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ConnectionDB con = new ConnectionDB();
            if (!con.checkExist(request.getParameter("loginNewUser"), request.getParameter("pwdNewUser"))) {
                User u = new User(request.getParameter("nomNewUser"), request.getParameter("prenomNewUser"),
                        Integer.parseInt(request.getParameter("ageNewUser")), request.getParameter("loginNewUser"), request.getParameter("pwdNewUser"));
                if (con.createAccount(u)) {
                    request.setAttribute("msg", "Création de compte réussie");
                    processRequest(request, response);
                    return;
                }
            }
        } catch (SQLException ex) {
            request.setAttribute("msg", ex.getMessage());
            processRequest(request, response);
            return;
        }
        request.setAttribute("msg", "Création du compte a échoué");
        processRequest(request, response);
    }

}
