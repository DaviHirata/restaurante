package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDB {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/restaurante_teste";
    private static final String LOGIN = "postgres";
    private static final String SENHA = "1234";


    public static void main(String[] args) {
        new ConectaDB().getConexao();
        System.out.println("Conexão executada.");
    }

    public Connection getConexao(){
        Connection connection = null;
        try{
            Class.forName(this.DRIVER);
            connection = DriverManager.getConnection(this.URL, this.LOGIN, this.SENHA);
        }catch(ClassNotFoundException e){
            System.out.println("Classe de conexão com o banco não configurada");
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println("URL/USUÁRIO/SENHA do banco errados");
            e.printStackTrace();
        }

        return connection;
    }
}
