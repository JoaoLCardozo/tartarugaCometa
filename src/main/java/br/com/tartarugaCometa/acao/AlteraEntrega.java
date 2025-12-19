package br.com.tartarugaCometa.acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.EntregaDAO;
import br.com.tartarugaCometa.model.Entrega;

public class AlteraEntrega implements Acao {
    
    public String executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        
        String paramIdEntrega = request.getParameter("idEntrega");
        
        
        String paramIdRemetente = request.getParameter("idRemetente");
        String paramIdDestinatario = request.getParameter("idDestinatario");
        String paramIdMercadoria = request.getParameter("idMercadoria");
        String paramQuantidade = request.getParameter("quantidade");
        String status = request.getParameter("status");

        try {
            
            int idEntrega = Integer.parseInt(paramIdEntrega);
            int idRemetente = Integer.parseInt(paramIdRemetente);
            int idDestinatario = Integer.parseInt(paramIdDestinatario);
            int idMercadoria = Integer.parseInt(paramIdMercadoria);
            int quantidade = Integer.parseInt(paramQuantidade);

            
            Entrega entrega = new Entrega();
            entrega.setIdEntrega(idEntrega);
            entrega.getRemetente().setId(idRemetente);
            entrega.getDestinatario().setId(idDestinatario);
            entrega.getMercadoria().setIdMercadoria(idMercadoria);
            entrega.setQuantidade(quantidade);
            entrega.setStatus(status);

            
            EntregaDAO dao = new EntregaDAO();
            dao.updateEntrega(entrega);

            System.out.println("Entrega " + idEntrega + " atualizada com sucesso.");

        } catch (Exception e) {
            System.err.println("Erro ao converter dados para alteração: " + e.getMessage());
            return "redirect:entrada?acao=ListaEntrega&msg=Erro ao atualizar";
        }

        return "redirect:entrada?acao=ListaEntrega";
    }
}