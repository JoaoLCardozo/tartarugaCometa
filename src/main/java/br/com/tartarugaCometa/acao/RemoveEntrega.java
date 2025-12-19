package br.com.tartarugaCometa.acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.EntregaDAO;

public class RemoveEntrega implements Acao {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        
        String paramId = request.getParameter("id");
        int id = Integer.parseInt(paramId);

        System.out.println("Removendo entrega ID: " + id);

        
        EntregaDAO dao = new EntregaDAO();
        dao.deletarEntrega(id);

        
        return "redirect:entrada?acao=ListaEntrega";
    }
}