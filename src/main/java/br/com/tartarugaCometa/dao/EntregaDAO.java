package br.com.tartarugaCometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.tartarugaCometa.db.ConnectionFactory;
import br.com.tartarugaCometa.model.Entrega;


public class EntregaDAO {

    
	private static final String INSERT_SQL =
		    "INSERT INTO entrega (id_remetente, id_destinatario, id_mercadoria, quantidade, status) VALUES (?,?,?,?,?)";
    
	private static final String SELECT_ALL_SQL =
		    "SELECT id_entrega, id_remetente, id_destinatario, id_mercadoria, quantidade, status FROM entrega";
    
	private static final String SELECT_BY_ID_SQL =
		    "SELECT id_entrega, id_remetente, id_destinatario, id_mercadoria, quantidade, status " +
		    "FROM entrega WHERE id_entrega = ?";
            
	private static final String UPDATE_SQL =
		    "UPDATE entrega SET id_remetente=?, id_destinatario=?, id_mercadoria=?, quantidade=?, status=? WHERE id_entrega=?";
    
    private static final String DELETE_SQL =
        "DELETE FROM entrega WHERE id_entrega = ?";

    
    public void salvarEntrega(Entrega entrega) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            
        	ps.setInt(1, entrega.getRemetente().getId());
        	ps.setInt(2, entrega.getDestinatario().getId());
        	ps.setInt(3, entrega.getMercadoria().getIdMercadoria());
        	ps.setInt(4, entrega.getQuantidade());
        	ps.setString(5, entrega.getStatus());

            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    entrega.setIdEntrega(rs.getInt(1));
                }
            }
            System.out.println("Entrega salva com sucesso. ID: " + entrega.getIdEntrega());
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar entrega", e);
        }
    }
    
    
    public List<Entrega> selecionarTodas() {
        List<Entrega> entregas = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Entrega entrega = new Entrega();
                
                entrega.setIdEntrega(rs.getInt("id_entrega"));
                
                entrega.getRemetente().setId(rs.getInt("id_remetente"));
                entrega.getDestinatario().setId(rs.getInt("id_destinatario"));
                
                entrega.getMercadoria().setIdMercadoria(rs.getInt("id_mercadoria"));
                entrega.setQuantidade(rs.getInt("quantidade"));
                entrega.setStatus(rs.getString("status"));

                
                entregas.add(entrega);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as entregas", e);
        }
        return entregas;
    }

    
    public Entrega buscaEntregaPeloId(int id) {
        Entrega entrega = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    entrega = new Entrega();
                    entrega.setIdEntrega(rs.getInt("id_entrega"));
                    
                    entrega.getRemetente().setId(rs.getInt("id_remetente"));
                    entrega.getDestinatario().setId(rs.getInt("id_destinatario"));
                    entrega.getMercadoria().setIdMercadoria(rs.getInt("id_mercadoria"));
                    
                    entrega.setQuantidade(rs.getInt("quantidade"));
                    entrega.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar entrega pelo ID: " + id, e);
        }
        return entrega;
    }

    public int updateEntrega(Entrega entrega) {
        int linhasAfetadas = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            
        	ps.setInt(1, entrega.getRemetente().getId());
        	ps.setInt(2, entrega.getDestinatario().getId());
        	ps.setInt(3, entrega.getMercadoria().getIdMercadoria());
        	ps.setInt(4, entrega.getQuantidade());
        	ps.setString(5, entrega.getStatus());
        	ps.setInt(6, entrega.getIdEntrega());


            linhasAfetadas = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a Entrega: " + e.getMessage());
            
        }
        return linhasAfetadas;
    }
    
 
    public int deletarEntrega(int idEntrega) { 
        int linhasAfetadas = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            
            ps.setInt(1, idEntrega);
            linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Entrega com ID " + idEntrega + " deletada com sucesso.");
            } else {
                System.out.println("Nenhuma entrega encontrada com o ID " + idEntrega + " para deletar.");
            }
              
            return linhasAfetadas;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar entrega com ID " + idEntrega, e);
        }
    } 
}