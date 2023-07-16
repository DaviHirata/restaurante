package dao;

import model.Mesa;

import java.sql.*;
import java.util.ArrayList;

public class MesaDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public boolean cadastrar(Mesa mesa) {
        boolean salvou = true;

        try (Connection connection = new ConectaDB().getConexao()) {
            connection.setAutoCommit(false);
            this.sql = "INSERT INTO mesa (localizacao) VALUES (?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, mesa.getLocalizacao());
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

    public boolean atualizar(Mesa mesa) {
        boolean atualizado = false;
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "UPDATE mesa SET localizacao = ? WHERE id_mesa = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, mesa.getLocalizacao());
            this.preparedStatement.setInt(2, mesa.getId());

            int rowsAffected = this.preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                atualizado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atualizado;
    }

    public Mesa getMesa(int id) {
        Mesa mesa = null;
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM mesa WHERE id_mesa = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                mesa = new Mesa();
                mesa.setId(this.resultSet.getInt("id_mesa"));
                mesa.setLocalizacao(this.resultSet.getString("localizacao"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mesa;
    }

    public ArrayList<Mesa> getMesas() {
        ArrayList<Mesa> mesas = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM mesa";
            this.statement = connection.createStatement();
            this.resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Mesa mesa = new Mesa();
                mesa.setId(resultSet.getInt("id_mesa"));
                mesa.setLocalizacao(resultSet.getString("localizacao"));

                mesas.add(mesa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mesas;
    }

    /*public ArrayList<Mesa> getMesasDisponiveis() {
        ArrayList<Mesa> mesas = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM mesa";
            this.statement = connection.createStatement();
            this.resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Mesa mesa = new Mesa();
                mesa.setId(resultSet.getInt("id_mesa"));
                mesa.setLocalizacao(resultSet.getString("localizacao"));

                mesas.add(mesa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mesas;
    }*/

    public boolean apagarMesa(int id){
        boolean apagou = false;
        try(Connection connection = new ConectaDB().getConexao()){
            String sql = "DELETE FROM mesa WHERE id_mesa = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);

            int rowsDeleted = this.preparedStatement.executeUpdate();
            if(rowsDeleted > 0){
                apagou = true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return apagou;
    }
}
