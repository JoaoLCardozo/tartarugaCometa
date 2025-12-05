package br.com.tartarugaCometa.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String url = "jdbc:postgresql://localhost:5432/BancoGerenciador";
    private static final String usuario = "postgres";
    private static final String senha = "1234";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão bem sucedida!");
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com o banco", e);
        }
    }
}

