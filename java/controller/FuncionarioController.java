package controller;


import dao.FuncionarioDAO;
import model.Funcionario;
import service.FuncionarioService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("funcionario")
public class FuncionarioController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null && action.equals("editar")) {
            exibirFormularioEdicao(req, resp);
        } else if (action != null && action.equals("atualizar")) {
            editarFuncionario(req, resp);
        } else if (action != null && action.equals("excluir")) {
            excluirFuncionario(req, resp);
        } else {
            cadastrarFuncionario(req, resp);
        }
    }

    public void cadastrarFuncionario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Bateu no cadastro");

        String nome = req.getParameter("nome");
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setLogin(login);
        funcionario.setSenha(senha);

        RequestDispatcher dispatcher;

        if (new FuncionarioService().cadastrar(funcionario) == true) {
            dispatcher = req.getRequestDispatcher("/index.jsp");
            System.out.println("Cadastro feito");
            req.setAttribute("cadatroFeito", funcionario);
            req.setAttribute("Funcionários", new FuncionarioDAO().getFuncionarios());
            dispatcher.forward(req, resp);
        } else {
            dispatcher = req.getRequestDispatcher("/registrar.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void editarFuncionario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setLogin(login);
        funcionario.setSenha(senha);

        FuncionarioService funcionarioService = new FuncionarioService();
        boolean atualizado = funcionarioService.editarFuncionario(funcionario);

        if (atualizado) {
            req.setAttribute("mensagem", "Funcionário atualizado");
        } else {
            req.setAttribute("mensagem", "Erro ao atualizar funcionário");
        }

        resp.sendRedirect(req.getContextPath() + "/gerenciarFuncionarios.jsp");
    }

    public void exibirFormularioEdicao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        FuncionarioService funcionarioService = new FuncionarioService();
        Funcionario funcionario = funcionarioService.getFuncionarioById(id);

        req.setAttribute("funcionario", funcionario);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editarFuncionario.jsp");
        dispatcher.forward(req, resp);
    }


    public void excluirFuncionario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));

        FuncionarioService funcionarioService = new FuncionarioService();
        boolean excluido = funcionarioService.deletarFuncionario(id);

        if(excluido == true){
            req.setAttribute("mensagem", "Funcionário excluído");
            req.setAttribute("Funcionários", new FuncionarioDAO().getFuncionarios());
        } else{
            req.setAttribute("mensagem", "Erro ao deletar");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/gerenciarFuncionarios.jsp");
        dispatcher.forward(req, resp);
    }
}