package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loggedIn = (String) req.getSession().getAttribute("login");

        if (loggedIn != null) {
            resp.sendRedirect(req.getContextPath() + "principal.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}