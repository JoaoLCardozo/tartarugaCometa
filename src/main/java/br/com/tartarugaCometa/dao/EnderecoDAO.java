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

public class EnderecoDAO {

    private static final String INSERT_SQL =
    		"INSERT INTO endereco (logradouro, numero, cep, bairro, cidade, complemento, estado_uf, id_cliente) VALUES (?,?,?,?,?,?,?,?)";
    
    private static final String SELECT_SQL = 
        "SELECT * FROM endereco";
    
    private static final String SELECT_BY_ID_SQL = 
        "SELECT id_endereco, logradouro, numero, cep, bairro, cidade, complemento, estado_uf FROM endereco WHERE id_cliente = ?";
            
    private static final String UPDATE_SQL =
        "UPDATE endereco SET logradouro = ?, numero = ?, cep = ?, bairro = ?, cidade = ?, complemento = ? , estado_uf = ?  WHERE id_endereco = ?";
    
    private static final String DELETE_SQL =
        "DELETE FROM endereco WHERE id_endereco = ?";

    
    public void salvarEndereco(Endereco endereco, int idCliente) {
        try (Connection conn = ConnectionFactory.getConnection();
             
             PreparedStatement ps  = conn.prepareStatement(INSERT_SQL)) {
            
          
            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getCep());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getComplemento());
            ps.setString(7, endereco.getUf()); 
            ps.setInt(8, idCliente);

            ps.executeUpdate();
            
            
            System.out.println("Endereço salvo com sucesso. ID: " + endereco.getIdEndereco());
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar endereço", e);
        }
    }
    
    
    public List<Endereco> selecionarTodos() {
        List<Endereco> enderecos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_SQL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Endereco endereco = new Endereco();
                
                endereco.setIdEndereco(rs.getInt("id_endereco"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setUf(rs.getString("estado_uf"));
                
                
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os endereços", e);
        }
        return enderecos;
    }

    
    public Endereco buscaEnderecoPeloId(int id) {
        Endereco endereco = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    endereco = new Endereco();
                    endereco.setIdEndereco(rs.getInt("id_endereco"));
                    endereco.setLogradouro(rs.getString("logradouro"));
                    endereco.setNumero(rs.getString("numero"));
                    endereco.setCep(rs.getString("cep"));
                    endereco.setBairro(rs.getString("bairro"));
                    endereco.setCidade(rs.getString("cidade"));
                    endereco.setComplemento(rs.getString("complemento"));
                    endereco.setUf(rs.getString("estado_uf"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar endereço pelo ID: " + id, e);
        }
        return endereco;
    }

    public int updateEndereco(Endereco endereco) {
        int linhasAfetadas = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            
            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getCep());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getComplemento());
            ps.setString(7, endereco.getUf());
            ps.setInt(8, endereco.getIdEndereco()); 
            
            linhasAfetadas = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o endereço: " + e.getMessage());
        }
        return linhasAfetadas;
    }
    
 
    public int deletarEndereco(int idEndereco) { 
        int linhasAfetadas = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            
            ps.setInt(1, idEndereco);
            linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Endereço com ID " + idEndereco + " deletado com sucesso.");
            } else {
                System.out.println("Nenhum endereço encontrado com o ID " + idEndereco + " para deletar.");
            }
              
            return linhasAfetadas;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar endereço com ID " + idEndereco, e);
        }
    } 
}