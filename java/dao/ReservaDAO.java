package dao;

import model.Mesa;
import model.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Reserva;
import model.Mesa;
import dao.ConectaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Reserva getReserva(int id) {
        Reserva reserva = null;
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "SELECT r.id_reserva, r.id_mesa, r.dataHora_reserva, r.numero_pessoas, r.nome_cliente, m.localizacao " +
                    "FROM reservas r " +
                    "JOIN mesa m ON r.id_mesa = m.id_mesa " +
                    "WHERE r.id_reserva = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            System.out.println("sql: " + this.preparedStatement.toString());
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                reserva = new Reserva();
                reserva.setId(this.resultSet.getInt("id_reserva"));

                Mesa mesa = new Mesa();
                mesa.setId(this.resultSet.getInt("id_mesa"));
                mesa.setLocalizacao(this.resultSet.getString("localizacao"));

                reserva.setMesa(mesa);

                reserva.setNomeCliente(this.resultSet.getString("nome_cliente"));
                reserva.setDataHoraReserva(this.resultSet.getTimestamp("dataHora_reserva"));
                reserva.setNumeroPessoas(this.resultSet.getInt("numero_pessoas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Id da reserva: " + (reserva != null ? reserva.getId() : "null"));

        return reserva;
    }

    public static List<String> getNomesClientes() {
        List<String> nomesClientes = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            String sql = "SELECT DISTINCT nome_cliente FROM reservas";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nomeCliente = resultSet.getString("nome_cliente");
                nomesClientes.add(nomeCliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nomesClientes;
    }

    public ArrayList<Reserva> getReservas() {
        ArrayList<Reserva> reservas = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            String sql = "SELECT r.id_reserva, r.id_mesa, r.dataHora_reserva, r.numero_pessoas, r.nome_cliente, m.localizacao " +
                    "FROM reservas r " +
                    "JOIN mesa m ON r.id_mesa = m.id_mesa";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(resultSet.getInt("id_reserva"));

                Mesa mesa = new Mesa();
                mesa.setId(resultSet.getInt("id_mesa"));
                mesa.setLocalizacao(resultSet.getString("localizacao"));

                reserva.setMesa(mesa);

                reserva.setNomeCliente(resultSet.getString("nome_cliente"));
                reserva.setDataHoraReserva(resultSet.getTimestamp("dataHora_reserva"));
                reserva.setNumeroPessoas(resultSet.getInt("numero_pessoas"));

                reservas.add(reserva);
            }

            System.out.println("Número de reservas recuperadas do banco de dados: " + reservas.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservas;
    }

    private Mesa getMesaById(int id) {
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

    public Reserva cadastrarReserva(Reserva reserva) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO reservas (id_mesa, nome_cliente, dataHora_reserva, numero_pessoas) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, reserva.getMesa().getId());
            this.preparedStatement.setString(2, reserva.getNomeCliente());
            this.preparedStatement.setInt(3, reserva.getNumeroPessoas());
            this.preparedStatement.executeUpdate();

            this.resultSet = this.preparedStatement.getGeneratedKeys();
            if (this.resultSet.next()) {
                int id = this.resultSet.getInt(1);
                reserva.setId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reserva;
    }

    public boolean atualizarReserva(Reserva reserva) {
        boolean sucesso = false;

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "UPDATE reservas SET id_mesa = ?, nome_cliente = ?, dataHora_reserva = CURRENT_TIMESTAMP, numero_pessoas = ? WHERE id_reserva = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, reserva.getMesa().getId());
            this.preparedStatement.setString(2, reserva.getNomeCliente());
            this.preparedStatement.setInt(3, reserva.getNumeroPessoas());
            this.preparedStatement.setInt(4, reserva.getId());
            int rowsAffected = this.preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                sucesso = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public boolean excluirReserva(int id) {
        boolean sucesso = false;

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "DELETE FROM reservas WHERE id_reserva = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            int rowsAffected = this.preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                sucesso = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}