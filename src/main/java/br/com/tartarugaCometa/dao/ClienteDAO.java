package br.com.tartarugaCometa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tartarugaCometa.db.ConnectionFactory;
import br.com.tartarugaCometa.model.Cliente;

public class ClienteDAO {

    private static final String INSERT_SQL =
        "INSERT INTO cliente (nomeRazao, documento, tpDocumento,data_cadastro,id_endereco) VALUES (?,?,?,?,?)";
    private static final String SELECT_SQL = 
    		"SELECT * FROM cliente";
   // private static final String SELECT_BY_ID_SQL = 
   //         "SELECT id_cliente, nomeRazao, data_cadastro FROM empresa WHERE id_cliente = ?";
    private static final String UPDATE_SQL =
   		"UPDATE cliente SET nomeRazao = ?, data_cadastro = ? WHERE id_cliente = ?";
   // private static final String DELETE_SQL=
   // 		"DELETE FROM empresa WHERE id_cliente = ?";

    public void salvarCliente(Cliente cliente) {
        try (Connection conn = ConnectionFactory.getConnection();
        		 
             PreparedStatement ps  = conn.prepareStatement(INSERT_SQL)) {
        	
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
    
    public List<Cliente> selecionarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_SQL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNomeRazao("nome_razao");
                cliente.setDocumento("documento");
                cliente.setTpDocumento("tpDocumento");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os clientes", e);
        }
        return clientes;
    }
    public int updateCliente(String novoNomeRazao, java.sql.Date novaDataCadastro, int idCliente, String novoTpDocumento) {
    	int linhasAfetadas = 0;
    	try (Connection conn = ConnectionFactory.getConnection();
    	PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
    		ps.setString(1, novoNomeRazao);
    		ps.setDate(2, novaDataCadastro);
    		ps.setInt(3, idCliente);
    		ps.setString(4,novoTpDocumento);
    		
    		linhasAfetadas = ps.executeUpdate();
    	} catch (SQLException e) {
    		System.err.println("Erro ao atualizar o cliente: " + e.getMessage());
    	}
    	return linhasAfetadas;
    }
    
    
    
    
}