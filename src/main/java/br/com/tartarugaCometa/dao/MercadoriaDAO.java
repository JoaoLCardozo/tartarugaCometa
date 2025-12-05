package br.com.tartarugaCometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.tartarugaCometa.db.ConnectionFactory;
import br.com.tartarugaCometa.model.Mercadoria;

public class MercadoriaDAO {

    private static final String INSERT_SQL = "INSERT INTO mercadoria (nome, peso, preco, volume) VALUES (?,?,?,?) RETURNING id_mercadoria";

    public int salvarMercadoria(Mercadoria mercadoria){
        int idProduto = 0;
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
                ps.setString(1, mercadoria.getNome());
                ps.setDouble(2, mercadoria.getPeso());
                ps.setDouble(3, mercadoria.getPreco());
                ps.setDouble(4, mercadoria.getVolume());

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    idProduto = rs.getInt("id_mercadoria");
                }
                
                System.out.println("Mercadoria salva com sucesso.");
                return idProduto;

            } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar endereço", e);
            }
    }
}
