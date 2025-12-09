package br.com.tartarugaCometa.acao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {

    public static void testar() throws ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/TartarugaCometa";
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
    }
}
