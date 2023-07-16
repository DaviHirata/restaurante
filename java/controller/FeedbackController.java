package controller;

import dao.FeedbackDAO;
import model.Feedback;
import service.FeedbackService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/feedback")
public class FeedbackController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null && action.equals("editar")) {
            exibirFormularioEdicao(req, resp);
        } else if (action != null && action.equals("atualizar")) {
            atualizarFeedback(req, resp);
        } else if (action != null && action.equals("excluir")) {
            excluirFeedback(req, resp);
        } else {
            cadastrarFeedback(req, resp);
        }
    }

    public void cadastrarFeedback(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeCliente = req.getParameter("nomeCliente");
        String dataFeedback = req.getParameter("dataFeedback");
        int classificacao = Integer.parseInt(req.getParameter("classificacao"));

        Feedback feedback = new Feedback();
        feedback.setNomeCliente(nomeCliente);
        feedback.setDataFeedback(dataFeedback);
        feedback.setClassificacao(classificacao);

        FeedbackService feedbackService = new FeedbackService();
        boolean cadastrado = feedbackService.cadastrar(feedback);

        if (cadastrado) {
            req.setAttribute("mensagem", "Feedback cadastrado com sucesso");
        } else {
            req.setAttribute("mensagem", "Erro ao cadastrar o feedback");
        }

        resp.sendRedirect(req.getContextPath() + "/gerenciarFeedbacks.jsp");
    }

    public void atualizarFeedback(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nomeCliente = req.getParameter("nomeCliente");
        int classificacao = Integer.parseInt(req.getParameter("classificacao"));

        Feedback feedback = new Feedback();
        feedback.setId(id);
        feedback.setNomeCliente(nomeCliente);
        feedback.setClassificacao(classificacao);

        FeedbackService feedbackService = new FeedbackService();
        boolean atualizado = feedbackService.atualizar(feedback);

        if (atualizado) {
            req.setAttribute("mensagem", "Feedback atualizado com sucesso");
        } else {
            req.setAttribute("mensagem", "Erro ao atualizar o feedback");
        }

        resp.sendRedirect(req.getContextPath() + "/gerenciarFeedbacks.jsp");
    }

    public void exibirFormularioEdicao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        FeedbackService feedbackService = new FeedbackService();
        Feedback feedback = feedbackService.getFeedbackById(id);

        req.setAttribute("feedback", feedback);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editarFeedback.jsp");
        dispatcher.forward(req, resp);
    }

    public void excluirFeedback(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        FeedbackService feedbackService = new FeedbackService();
        boolean excluido = feedbackService.excluirFeedback(id);

        if (excluido) {
            req.setAttribute("mensagem", "Feedback excluído com sucesso");
        } else {
            req.setAttribute("mensagem", "Erro ao excluir o feedback");
        }

        resp.sendRedirect(req.getContextPath() + "/gerenciarFeedbacks.jsp");
    }
}