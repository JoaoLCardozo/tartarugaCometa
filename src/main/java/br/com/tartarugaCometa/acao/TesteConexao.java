package br.com.tartarugaCometa.acao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TesteConexao implements Acao{

    public static void testar() throws ClassNotFoundException {
    	
    	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "jdbc:postgresql://localhost:5432/BancoGerenciador";
        String user = "postgres";
        String password = "1234";

        try{
            Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException cnfe){
              System.out.println("Could not find the JDBC driver!");
              System.exit(1);
            }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection
                    (url, user, password);
        	System.out.println("Conectou");
             } catch (SQLException sqle) {
               System.out.println("Could not connect");
               System.exit(1);
             }
        return
    }
}

