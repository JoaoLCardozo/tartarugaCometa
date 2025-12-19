package br.com.tartarugaCometa.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import br.com.tartarugaCometa.dao.ClienteDAO;
import br.com.tartarugaCometa.dao.EnderecoDAO; 
import br.com.tartarugaCometa.model.Cliente;
import br.com.tartarugaCometa.model.Endereco; 

public class AlteraClientes implements Acao{
    
    public String executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        
        String paramIdCliente = request.getParameter("id"); 
        String paramIdEndereco = request.getParameter("idEndereco"); 
        
        Integer idCliente;
        Integer idEndereco; 
        
        try {
            idCliente = Integer.valueOf(paramIdCliente);
            
            idEndereco = Integer.valueOf(paramIdEndereco); 
        } catch (Exception e) {
            System.err.println("ID(s) inválidos ou ausentes.");
            return "redirect:entrada?acao=ListaClientes&msg=ID Inválido"; 
        }

        
        String nomeCliente = request.getParameter("nomeRazao");
        String tipoDocumento = request.getParameter("tpDocumento");
        String documento = request.getParameter("documento");
        
        
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estadoUf = request.getParameter("estadoUf");
        String complemento = request.getParameter("complemento");

        
        
        
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        cliente.setNomeRazao(nomeCliente);
        cliente.setTpDocumento(tipoDocumento);
        cliente.setDocumento(documento);

        
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(idEndereco); 
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(estadoUf);
        endereco.setComplemento(complemento);

        
        ClienteDAO clienteDao = new ClienteDAO();
        EnderecoDAO enderecoDao = new EnderecoDAO();
        
        try {
            int linhasCliente = clienteDao.updateCliente(cliente);
            int linhasEndereco = enderecoDao.updateEndereco(endereco);
            
            if (linhasCliente > 0 || linhasEndereco > 0) {
                System.out.println("Cliente e Endereço alterados com sucesso. Cliente ID: " + idCliente);
                return "redirect:entrada?acao=ListaClientes&msg=Alterado com sucesso";
            } else {
                System.err.println("Falha ao alterar: nenhuma linha afetada no Cliente ou Endereço.");
                return "redirect:entrada?acao=ListaClientes&msg=Falha na Alteração";
            }
        } catch (RuntimeException e) {
             
             System.err.println("Erro durante a persistência: " + e.getMessage());
             return "redirect:entrada?acao=ListaClientes&msg=Erro interno no DB";
        }
        
    }
}