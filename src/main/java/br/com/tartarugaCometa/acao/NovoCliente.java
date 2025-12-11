package br.com.tartarugaCometa.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.ClienteDAO;
import br.com.tartarugaCometa.model.Cliente;
import br.com.tartarugaCometa.model.Endereco;

public class NovoCliente implements Acao {

    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Cadastrando novo Cliente");

        
        String nomeRazao = request.getParameter("nome_razao");
        String documento = request.getParameter("documento");
        String tpDocumento = request.getParameter("tp_documento"); 

        Endereco enderecoObj = new Endereco();        

        
        Cliente cliente = new Cliente();
        cliente.setNomeRazao(nomeRazao);
        cliente.setDocumento(documento);
        cliente.setTpDocumento(tpDocumento);; 
        
        
        cliente.setEndereco(enderecoObj); 

        
        ClienteDAO dao = new ClienteDAO();
        dao.salvarCliente(cliente);

        
        request.setAttribute("cliente", cliente.getNomeRazao());

        return "redirect:entrada?acao=ListaClientes";
    
	}
}