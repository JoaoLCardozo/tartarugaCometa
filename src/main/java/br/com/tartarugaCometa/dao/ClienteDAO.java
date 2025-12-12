package br.com.tartarugaCometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.tartarugaCometa.db.ConnectionFactory;
import br.com.tartarugaCometa.model.Cliente;

public class ClienteDAO {

    private static final String INSERT_SQL =
        "INSERT INTO cliente (nome_razao, documento, tpDocumento) VALUES (?,?,?);";
    private static final String SELECT_SQL = 
    		"SELECT * FROM cliente;";
    private static final String SELECT_BY_ID_SQL = 
            "SELECT id_cliente, nome_razao, tpDocumento, documento FROM cliente WHERE id_cliente = ?;";
    private static final String UPDATE_SQL =
    		"UPDATE cliente SET nome_razao = ?, tpDocumento = ?, documento = ? WHERE id_cliente = ?;";
    private static final String DELETE_SQL=
   		"DELETE FROM cliente WHERE id_cliente = ?;";

    public int salvarCliente(Cliente cliente) {
    	int idClienteGerado = 0;
        try (Connection conn = ConnectionFactory.getConnection();
        		 
             PreparedStatement ps  = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
        	
            ps.setString(1, cliente.getNomeRazao());
            ps.setString(2, cliente.getDocumento());
            ps.setString(3, cliente.getTpDocumento());
            
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idClienteGerado = rs.getInt(1);
                    cliente.setId(idClienteGerado);
                    System.out.println("Cliente salvo com sucesso. ID Gerado: " + idClienteGerado);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        }
        return idClienteGerado;
    }
    
    public List<Cliente> selecionarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_SQL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNomeRazao(rs.getString("nome_razao"));
                cliente.setDocumento(rs.getString("documento"));
                cliente.setTpDocumento(rs.getString("tpDocumento"));
                
                clientes.add(cliente);	
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os clientes", e);
        }
        return clientes;
    }
    public Cliente buscaClientePeloId(int id) {
        Cliente cliente = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("id_cliente"));
                    cliente.setNomeRazao(rs.getString("nome_razao"));
                    cliente.setTpDocumento(rs.getString("tpDocumento"));
                    cliente.setDocumento(rs.getString("documento"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente pelo ID: " + id, e);
        }
        return cliente;
    }
    public int updateCliente(Cliente cliente) {
    	int linhasAfetadas = 0;
    	try (Connection conn = ConnectionFactory.getConnection();
    	PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
    		ps.setString(1, cliente.getNomeRazao());
    		ps.setString(2, cliente.getTpDocumento());
    		ps.setString(3, cliente.getDocumento());
    		ps.setInt(4,cliente.getId());
    		
    		linhasAfetadas = ps.executeUpdate();
    	} catch (SQLException e) {
    		System.err.println("Erro ao atualizar o cliente: " + e.getMessage());
    	}
    	return linhasAfetadas;
    }
    
    public int deletarCliente(int idCliente) { 
        int linhasAfetadas = 0;
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, idCliente);
            linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Cliente com ID " + idCliente + " deletado com sucesso.");
              } else {
                System.out.println("Nenhum cliente encontrado com o ID " + idCliente + " para deletar.");
              }
              
              return linhasAfetadas;
        } catch (SQLException e) {

            throw new RuntimeException("Erro ao deletar cliente com ID " + idCliente, e);
        }
} 
    
    
    
    
}