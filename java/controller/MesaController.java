package controller;

import dao.MesaDAO;
import model.Mesa;
import service.MesaService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("mesa")
public class MesaController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null && action.equals("editar")) {
            exibirFormularioEdicao(req, resp);
        } else if (action != null && action.equals("atualizar")) {
            editarMesa(req, resp);
        } else {
            cadastrarMesa(req, resp);
        }
    }

    public void cadastrarMesa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Bateu no cadastro");

        String localizacao = req.getParameter("localizacao");

        Mesa mesa = new Mesa();
        mesa.setLocalizacao(localizacao);

        RequestDispatcher dispatcher;

        if (new MesaService().cadastrar(mesa)) {
            dispatcher = req.getRequestDispatcher("/gerenciarMesas.jsp");
            System.out.println("Cadastro feito");
            req.setAttribute("cadatroFeito", mesa);
            req.setAttribute("Mesas", new MesaDAO().getMesas());
            dispatcher.forward(req, resp);
        } else {
            dispatcher = req.getRequestDispatcher("/cadastrarMesa.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void editarMesa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String localizacao = req.getParameter("localizacao");

        Mesa mesa = new Mesa();
        mesa.setId(id);
        mesa.setLocalizacao(localizacao);

        MesaService mesaService = new MesaService();
        boolean atualizado = mesaService.editarMesa(mesa);

        if (atualizado) {
            req.setAttribute("mensagem", "Mesa atualizada");
        } else {
            req.setAttribute("mensagem", "Erro ao atualizar mesa");
        }

        resp.sendRedirect(req.getContextPath() + "/gerenciarMesas.jsp");
    }

    public void exibirFormularioEdicao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        MesaService mesaService = new MesaService();
        Mesa mesa = mesaService.getMesaById(id);

        req.setAttribute("mesa", mesa);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editarMesa.jsp");
        dispatcher.forward(req, resp);
    }
}