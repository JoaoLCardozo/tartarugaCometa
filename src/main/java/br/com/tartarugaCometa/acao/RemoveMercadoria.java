package br.com.tartarugaCometa.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.tartarugaCometa.dao.MercadoriaDAO; 

public class RemoveMercadoria implements Acao {
    
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("Acao removendo mercadoria");
        
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);
        
        System.out.println("ID recebido: " + id);
       
        
        MercadoriaDAO mercadoriaDao = new MercadoriaDAO();
        mercadoriaDao.deletarMercadoria(id); 
        

        return "redirect:entrada?acao=ListaMercadoria";
    }

}