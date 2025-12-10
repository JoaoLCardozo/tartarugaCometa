package br.com.tartarugaCometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.tartarugaCometa.db.ConnectionFactory;
import br.com.tartarugaCometa.model.Endereco;
import br.com.tartarugaCometa.model.Mercadoria;

public class MercadoriaDAO {

    private static final String INSERT_SQL =
        "INSERT INTO mercadoria (logradouro, numero, cep, bairro, cidade, complemento, estado_uf) VALUES (?,?,?,?,?,?,?)";
    
    private static final String SELECT_ALL_SQL = 
        "SELECT * FROM endereco";
    
    private static final String SELECT_BY_ID_SQL = 
        "SELECT id_mercadoria, nome, peso, preco, volume FROM mercadoria WHERE id_mercadoria = ?";
            
    private static final String UPDATE_SQL =
        "UPDATE mercadoria SET nome = ?, peso = ?, preco = ?, volume = ?  WHERE id_mercadoria = ?";
    
    private static final String DELETE_SQL =
        "DELETE FROM mercadoria WHERE id_endereco = ?";

    
    public void salvarMercadoria(Mercadoria mercadoria) {
        try (Connection conn = ConnectionFactory.getConnection();
             
             PreparedStatement ps  = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, mercadoria.getNome());
            ps.setDouble(2, mercadoria.getPeso());
            ps.setDouble(3, mercadoria.getPreco());
            ps.setDouble(4, mercadoria.getVolume());

            ps.executeUpdate();
            
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    mercadoria.setIdMercadoria(rs.getInt(1));
                }
            }
            System.out.println("Mercadoria salva com sucesso. ID: " + mercadoria.getIdMercadoria());
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar mercadoria", e);
        }
    }
    
    
    public List<Mercadoria> selecionarTodos() {
        List<Mercadoria> mercadorias = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Mercadoria mercadoria = new Mercadoria();
                
                mercadoria.setIdMercadoria(rs.getInt("id_mercadoria"));
                mercadoria.setNome(rs.getString("nome"));
                mercadoria.setPeso(rs.getDouble("peso"));
                mercadoria.setPreco(rs.getDouble("preco"));
                
                
                mercadorias.add(mercadoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os endereços", e);
        }
        return mercadorias;
    }

    
    public Mercadoria buscaMercadoriaPeloId(int id) {
        Mercadoria mercadoria = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mercadoria = new Mercadoria();
                    mercadoria.setIdMercadoria(rs.getInt("id_endereco"));
                    mercadoria.setNome(rs.getString("logradouro"));
                    mercadoria.setPeso(rs.getDouble("numero"));
                    mercadoria.setPreco(rs.getDouble("cep"));
                    mercadoria.setVolume(rs.getDouble("bairro"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar mercadoria pelo ID: " + id, e);
        }
        return mercadoria;
    }

    public int updateMercadoria(Mercadoria mercadoria) {
        int linhasAfetadas = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            
            ps.setString(1, mercadoria.getNome());
            ps.setDouble(2, mercadoria.getPeso());
            ps.setDouble(3, mercadoria.getPreco());
            ps.setDouble(4, mercadoria.getVolume());

            
            linhasAfetadas = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a Mercadoria: " + e.getMessage());
        }
        return linhasAfetadas;
    }
    
 
    public int deletarMercadoria(int idMercadoria) { 
        int linhasAfetadas = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            
            ps.setInt(1, idMercadoria);
            linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Mercadoria com ID " + idMercadoria + " deletado com sucesso.");
            } else {
                System.out.println("Nenhuma mercadoria encontrada com o ID " + idMercadoria + " para deletar.");
            }
              
            return linhasAfetadas;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar mercadoria com ID " + idMercadoria, e);
        }
    } 
}