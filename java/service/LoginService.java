package service;

import dao.FuncionarioDAO;
import model.Funcionario;

public class LoginService {

    public boolean autenticar(String login, String senha) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.getFuncionario(login);

        if (funcionario == null || funcionario.getLogin() == null) {
            System.out.println("Funcionário não encontrado.");
            return false;
        } else {
            if (funcionario.getLogin().equals(login) && funcionario.getSenha().equals(senha)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
