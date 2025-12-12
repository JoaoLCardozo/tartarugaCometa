package br.com.tartarugaCometa.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.MercadoriaDAO;
import br.com.tartarugaCometa.model.Mercadoria;

public class ListaMercadoria implements Acao {
    
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("Listando Mercadorias do Banco de Dados");
        
        MercadoriaDAO dao = new MercadoriaDAO(); 
        
        List<Mercadoria> lista = dao.selecionarTodos();
        
        request.setAttribute("mercadoria", lista);
        
        return "forward:listaMercadoria.jsp";
    }
}