package br.com.tartarugaCometa.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.ClienteDAO;
import br.com.tartarugaCometa.dao.EnderecoDAO;
import br.com.tartarugaCometa.model.Cliente;
import br.com.tartarugaCometa.model.Endereco;

public class NovoCliente implements Acao {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Cadastrando novo Cliente");

        
        String nomeRazao = request.getParameter("nomeRazao");
        String documento = request.getParameter("documento");
        String tpDocumento = request.getParameter("tpDocumento");
        String logradouro = request.getParameter("logradouro"); 
        String numero = request.getParameter("numero"); 
        String cep = request.getParameter("cep"); 
        String bairro = request.getParameter("bairro"); 
        String cidade = request.getParameter("cidade"); 
        String estado_uf = request.getParameter("estado_uf"); 
        String complemento = request.getParameter("complemento"); 
        
        
        Cliente cliente = new Cliente();
        cliente.setNomeRazao(nomeRazao);
        cliente.setDocumento(documento);
        cliente.setTpDocumento(tpDocumento);
        
        ClienteDAO cliDao = new ClienteDAO();
        cliDao.salvarCliente(cliente);
        
        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(estado_uf);
        endereco.setComplemento(complemento);
        
        EnderecoDAO endDAO = new EnderecoDAO();
        endDAO.salvarEndereco(endereco, cliente.getId());;
        cliente.setEndereco(endereco);
        

        
        request.setAttribute("cliente", cliente.getNomeRazao());

        return "redirect:entrada?acao=ListaClientes";
    
	}
}