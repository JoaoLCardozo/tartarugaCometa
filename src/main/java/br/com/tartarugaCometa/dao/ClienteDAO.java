package br.com.tartarugaCometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.tartarugaCometa.db.ConnectionFactory;
import br.com.tartarugaCometa.model.Cliente;

public class ClienteDAO {

    private static final String INSERT_SQL =
        "INSERT INTO cliente (nomeRazao, documento, tpDocumento,id_endereco) VALUES (?,?,?,?)";

    public void salvarCliente(Cliente cliente) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, cliente.getNomeRazao());
            ps.setString(2, cliente.getDocumento());
            ps.setString(3, cliente.getTpDocumento());
            ps.setInt(4, cliente.getEndereco().getIdEndereco());

            ps.executeUpdate();
            System.out.println("Cliente salvo com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        }
    }
}