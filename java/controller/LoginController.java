package controller;

import service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("login")
public class LoginController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Bateu no login");
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");

        System.out.println(login +" - "+senha);

        RequestDispatcher dispatcher;

        if(new LoginService().autenticar(login, senha)){
            req.getSession().setAttribute("login", login);
            dispatcher = req.getRequestDispatcher("/principal.jsp");
        }else{
            req.setAttribute("msgServidor", "usuario invalido");
            dispatcher = req.getRequestDispatcher("/index.jsp");

        }

        dispatcher.forward(req, resp);
    }
}