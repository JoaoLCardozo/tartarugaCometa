package br.com.tartarugaCometa.acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.tartarugaCometa.dao.MercadoriaDAO;
import br.com.tartarugaCometa.model.Mercadoria;

public class MostraMercadoria implements Acao {

    public String executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);

        
        MercadoriaDAO dao = new MercadoriaDAO();
        Mercadoria mercadoria = dao.buscaMercadoriaPeloId(id); 

        request.setAttribute("mercadoria", mercadoria);

        return "forward:formAlteraMercadoria.jsp"; 
    }
}