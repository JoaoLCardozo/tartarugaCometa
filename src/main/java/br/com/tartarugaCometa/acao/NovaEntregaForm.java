package br.com.tartarugaCometa.acao;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.ClienteDAO;
import br.com.tartarugaCometa.dao.MercadoriaDAO;
import br.com.tartarugaCometa.model.Cliente;
import br.com.tartarugaCometa.model.Mercadoria;

public class NovaEntregaForm implements Acao {

    public String executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("Carregando dados para o formulário de entrega...");

        
        ClienteDAO clienteDao = new ClienteDAO();
        List<Cliente> listaClientes = clienteDao.selecionarTodos();
        
        
        MercadoriaDAO mercadoriaDao = new MercadoriaDAO();
        List<Mercadoria> listaMercadorias = mercadoriaDao.selecionarTodos();
        
        
        System.out.println("Clientes encontrados: " + listaClientes.size());
        System.out.println("Mercadorias encontradas: " + listaMercadorias.size());

        
        request.setAttribute("clientes", listaClientes);
        request.setAttribute("mercadorias", listaMercadorias);
        
        
        return "forward:formEntrega.jsp";
    }
}