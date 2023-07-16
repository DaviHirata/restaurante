package controller;

import dao.ReservaDAO;
import model.Mesa;
import model.Reserva;
import service.ReservaService;
import java.sql.Timestamp;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("reserva")
public class ReservaController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null && action.equals("editar")) {
            exibirFormularioEdicao(req, resp);
        } else if (action != null && action.equals("atualizar")) {
            editarReserva(req, resp);
        } else if (action != null && action.equals("excluir")) {
            excluirReserva(req, resp);
        } else {
            cadastrarReserva(req, resp);
        }
    }

    public void cadastrarReserva(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Bateu no cadastro");

        int idMesa = Integer.parseInt(req.getParameter("idMesa"));
        String nomeCliente = req.getParameter("nomeCliente");
        int numeroPessoas = Integer.parseInt(req.getParameter("numeroPessoas"));

        Mesa mesa = new Mesa();
        mesa.setId(idMesa);

        Reserva reserva = new Reserva();
        reserva.setMesa(mesa);
        reserva.setNomeCliente(nomeCliente);
        reserva.setNumeroPessoas(numeroPessoas);

        RequestDispatcher dispatcher;

        if (new ReservaService().cadastrar(reserva)) {
            dispatcher = req.getRequestDispatcher("/gerenciarReservas.jsp");
            System.out.println("Cadastro feito");
            req.setAttribute("cadatroFeito", reserva);
            req.setAttribute("Reservas", new ReservaDAO().getReservas());
            dispatcher.forward(req, resp);
        } else {
            dispatcher = req.getRequestDispatcher("/cadastrarReserva.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void editarReserva(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idReserva = Integer.parseInt(req.getParameter("id"));
        String idMesaStr = req.getParameter("idMesa");
        String nomeCliente = req.getParameter("nomeCliente");
        String numeroPessoasStr = req.getParameter("numeroPessoas");

        int idMesa = Integer.parseInt(idMesaStr);
        int numeroPessoas = Integer.parseInt(numeroPessoasStr);

        Mesa mesa = new Mesa();
        mesa.setId(idMesa);

        Reserva reserva = new Reserva();
        reserva.setId(idReserva);
        reserva.setMesa(mesa);
        reserva.setNomeCliente(nomeCliente);
        reserva.setNumeroPessoas(numeroPessoas);

        RequestDispatcher dispatcher;

        if (new ReservaService().editarReserva(reserva)) {
            dispatcher = req.getRequestDispatcher("/gerenciarReservas.jsp");
            req.setAttribute("reservaAtualizada", reserva);
            req.setAttribute("reservas", new ReservaDAO().getReservas());
        } else {
            dispatcher = req.getRequestDispatcher("/editarReserva.jsp");
        }

        dispatcher.forward(req, resp);
    }

    public void excluirReserva(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idReserva = Integer.parseInt(req.getParameter("id"));

        ReservaService reservaService = new ReservaService();
        boolean apagado = reservaService.deletarReserva(idReserva);

        if (apagado) {
            req.setAttribute("Reservas", new ReservaDAO().getReservas());
            req.getRequestDispatcher("/gerenciarReservas.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/gerenciarReservas.jsp").forward(req, resp);
        }
    }

    public void exibirFormularioEdicao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idReserva = Integer.parseInt(req.getParameter("id"));

        ReservaService reservaService = new ReservaService();
        Reserva reserva = reservaService.getReservaById(idReserva);

        req.setAttribute("Reserva", reserva);
        req.getRequestDispatcher("/editarReserva.jsp").forward(req, resp);
    }
}
