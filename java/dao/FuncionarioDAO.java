package dao;

import model.Funcionario;

import java.sql.*;
import java.util.ArrayList;

public class FuncionarioDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public boolean cadastrar(Funcionario funcionario){
        boolean salvou = true;

        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);
            this.sql = "INSERT INTO funcionarios (nome, login, senha) VALUES (?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql, preparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, funcionario.getNome());
            this.preparedStatement.setString(2, funcionario.getLogin());
            this.preparedStatement.setString(3, funcionario.getSenha());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();
            if(this.resultSet.getInt(1) > 0){
                funcionario.setId(this.resultSet.getInt(1));
                connection.commit();
                this.status = "OK";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "PROBLEMA";
            salvou = false;
        }

        return salvou;
    }

    public Funcionario getFuncionario(String login) {
        Funcionario funcionario = null;
        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM funcionarios WHERE login = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, login);
            System.out.println("sql: " + this.preparedStatement.toString());
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                funcionario = new Funcionario();
                funcionario.setId(this.resultSet.getInt("id_funcionario"));
                funcionario.setNome(this.resultSet.getString("nome"));
                funcionario.setLogin(this.resultSet.getString("login"));
                funcionario.setSenha(this.resultSet.getString("senha"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Nome do funcionario: " + (funcionario != null ? funcionario.getLogin() : "null"));

        return funcionario;
    }

    public Funcionario getFuncionarioById(int id) {
        Funcionario funcionario = null;
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            System.out.println("sql: " + this.preparedStatement.toString());
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setId(this.resultSet.getInt("id_funcionario"));
                funcionario.setNome(this.resultSet.getString("nome"));
                funcionario.setLogin(this.resultSet.getString("login"));
                funcionario.setSenha(this.resultSet.getString("senha"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Nome do funcionário: " + (funcionario != null ? funcionario.getLogin() : "null") + "Id do funcionário: " + (funcionario != null ? funcionario.getId() : "null"));

        return funcionario;
    }


    public ArrayList<Funcionario> getFuncionarios(){
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM funcionarios";
            this.statement = connection.createStatement();
            this.resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id_funcionario"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setLogin(resultSet.getString("login"));

                funcionarios.add(funcionario);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return funcionarios;
    }

    public boolean apagarFuncionario(int id) {
        boolean apagou = false;

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "DELETE FROM funcionarios WHERE id_funcionario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);

            int rowsDeleted = this.preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                apagou = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apagou;
    }

    public boolean atualizar(Funcionario funcionario) {
        boolean atualizado = false;
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "UPDATE funcionarios SET nome = ?, login = ?, senha = ? WHERE id_funcionario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, funcionario.getNome());
            this.preparedStatement.setString(2, funcionario.getLogin());
            this.preparedStatement.setString(3, funcionario.getSenha());
            this.preparedStatement.setInt(4, funcionario.getId());

            int rowsAffected = this.preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                atualizado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atualizado;
    }
}
