package dao;

import model.Feedback;
import java.sql.*;
import java.util.ArrayList;

public class FeedbackDAO {
    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public boolean cadastrar(Feedback feedback) {
        boolean salvou = true;

        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);
            this.sql = "INSERT INTO FeedbackClientes (nome_cliente, data_feedback, classificacao) VALUES (?, CURRENT_DATE, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, feedback.getNomeCliente());
            this.preparedStatement.setInt(2, feedback.getClassificacao());

            int rowsAffected = this.preparedStatement.executeUpdate();

            if (rowsAffected <= 0) {
                salvou = false;
                connection.rollback();
            } else {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            salvou = false;
        }

        return salvou;
    }

    public boolean atualizar(Feedback feedback) {
        boolean atualizado = false;

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "UPDATE FeedbackClientes SET nome_cliente = ?, classificacao = ? WHERE id_feedback = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, feedback.getNomeCliente());
            this.preparedStatement.setInt(2, feedback.getClassificacao());
            this.preparedStatement.setInt(3, feedback.getId());

            int rowsAffected = this.preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                atualizado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atualizado;
    }

    public Feedback getFeedback(int id) {
        Feedback feedback = null;

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM FeedbackClientes WHERE id_feedback = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                feedback = new Feedback();
                feedback.setId(this.resultSet.getInt("id_feedback"));
                feedback.setNomeCliente(this.resultSet.getString("nome_cliente"));
                feedback.setDataFeedback(this.resultSet.getDate("data_feedback").toString());
                feedback.setClassificacao(this.resultSet.getInt("classificacao"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return feedback;
    }

    public ArrayList<Feedback> getFeedbacks() {
        ArrayList<Feedback> feedbacks = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM FeedbackClientes";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(this.resultSet.getInt("id_feedback"));
                feedback.setNomeCliente(this.resultSet.getString("nome_cliente"));
                feedback.setDataFeedback(this.resultSet.getDate("data_feedback").toString());
                feedback.setClassificacao(this.resultSet.getInt("classificacao"));

                feedbacks.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return feedbacks;
    }

    public boolean excluirFeedback(int id) {
        boolean excluido = false;

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "DELETE FROM FeedbackClientes WHERE id_feedback = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);

            int rowsDeleted = this.preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                excluido = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return excluido;
    }
}