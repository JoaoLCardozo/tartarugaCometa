package br.com.tartarugaCometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.tartarugaCometa.db.ConnectionFactory;
import br.com.tartarugaCometa.model.Entrega;

public class EntregaDAO {

    private static final String INSERT_SQL =
        "INSERT INTO entrega (id_cliente) VALUES (?) RETURNING id_entrega";

    public int salvarEntrega(Entrega entrega) {
        int idEntrega = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, entrega.getIdCliente());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idEntrega = rs.getInt("id_entrega");
            }

            System.out.println("Entrega salva com sucesso.");
            return idEntrega;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar entrega", e);
        }
    }
}

