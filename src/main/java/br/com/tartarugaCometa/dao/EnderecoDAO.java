			package br.com.tartarugaCometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.tartarugaCometa.db.ConnectionFactory;
import br.com.tartarugaCometa.model.Endereco;

public class EnderecoDAO {

    private static final String INSERT_SQL =
        "INSERT INTO endereco (logradouro,numero,cep,bairro,cidade,complemento,estado_uf) VALUES(?,?,?,?,?,?,?) RETURNING id_endereco ";

    public int salvarEndereco(Endereco endereco) {
        int idEndereco = 0;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
                ps.setString(1, endereco.getLogradouro());
                ps.setString(2, endereco.getNumero());
                ps.setString(3, endereco.getCep());
                ps.setString(4, endereco.getBairro());
                ps.setString(5, endereco.getCidade());
                ps.setString(6, endereco.getComplemento());
                ps.setString(7, endereco.getUf());
                 
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                   idEndereco = rs.getInt("id_endereco");
                }
                System.out.println("Endereço salvo com sucesso.");

                return idEndereco;
            } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar endereço", e);
            }
    }
}