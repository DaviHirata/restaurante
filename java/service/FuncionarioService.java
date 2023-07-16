package service;

import dao.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioService {
    public boolean cadastrar(Funcionario funcionario) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        String login = funcionario.getLogin();

        // Verifica se já existe um funcionário com o mesmo login
        Funcionario funcionarioExistente = funcionarioDAO.getFuncionario(login);
        if (funcionarioExistente == null) {
            // Realiza o cadastro do funcionário
            boolean cadastrado = funcionarioDAO.cadastrar(funcionario);
            System.out.println(cadastrado);
            return cadastrado;
        } else{
            System.out.println(funcionarioExistente);
            return false; // Impede o cadastro, pois já existe um funcionário com o mesmo login
        }
    }

    public boolean editarFuncionario(Funcionario funcionario) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.atualizar(funcionario);
    }

    public Funcionario getFuncionarioById(int id) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.getFuncionarioById(id);
    }

    public boolean deletarFuncionario(int id) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.apagarFuncionario(id);
    }

}
