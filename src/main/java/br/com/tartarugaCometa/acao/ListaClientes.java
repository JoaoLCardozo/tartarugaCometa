package br.com.tartarugaCometa.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.ClienteDAO;
import br.com.tartarugaCometa.model.Cliente;

public class ListaClientes implements Acao {
    
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("Listando clientes do Banco de Dados");
        
        ClienteDAO dao = new ClienteDAO(); 
        
        List<Cliente> lista = dao.selecionarTodos();
        
        request.setAttribute("cliente", lista);
        
        return "forward:listaClientes.jsp";
    }
}