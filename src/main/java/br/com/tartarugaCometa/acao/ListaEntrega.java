package br.com.tartarugaCometa.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.EntregaDAO;
import br.com.tartarugaCometa.model.Entrega;

public class ListaEntrega implements Acao {
    
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("Listando ent do Banco de Dados");
        
        EntregaDAO dao = new EntregaDAO(); 
        
        List<Entrega> lista = dao.selecionarTodos();
        
        request.setAttribute("entrega", lista);
        
        return "forward:listaEntrega.jsp";
    }
}