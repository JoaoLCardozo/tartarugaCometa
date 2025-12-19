package br.com.tartarugaCometa.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.tartarugaCometa.dao.MercadoriaDAO;
import br.com.tartarugaCometa.model.Mercadoria; 

public class AlteraMercadoria implements Acao{
    
    public String executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        
        String paramIdMercadoria = request.getParameter("idMercadoria"); 
        
        Integer idMercadoria;
        
        try {
            idMercadoria = Integer.valueOf(paramIdMercadoria);
        } catch (Exception e) {
            System.err.println("ID inválido ou ausente.");
            return "redirect:entrada?acao=ListaMercadoria&msg=ID Inválido"; 
        }

        
        String nomeMercadoria = request.getParameter("nome");
        String mPeso = request.getParameter("peso");
        String mPreco = request.getParameter("preco");
        String mVolume = request.getParameter("volume");

        double peso = 0.0;
        double preco = 0.0;
        double volume = 0.0;

        
        try {
            if (mPeso != null && !mPeso.isEmpty()) peso = Double.parseDouble(mPeso);
            if (mPreco != null && !mPreco.isEmpty()) preco = Double.parseDouble(mPreco);
            if (mVolume != null && !mVolume.isEmpty()) volume = Double.parseDouble(mVolume);
        } catch (NumberFormatException e) {
            
            return "redirect:entrada?acao=ListaMercadoria&msg=Erro: Use ponto em vez de virgula";
        }
        
        Mercadoria mercadoria = new Mercadoria();
        mercadoria.setIdMercadoria(idMercadoria);
        mercadoria.setNome(nomeMercadoria);
        mercadoria.setPeso(peso);
        mercadoria.setPreco(preco);
        mercadoria.setVolume(volume);

        
        MercadoriaDAO dao = new MercadoriaDAO();
        
        try {
            int linhasAfetadas = dao.updateMercadoria(mercadoria);
            
            if (linhasAfetadas > 0) {
                return "redirect:entrada?acao=ListaMercadoria&msg=Alterada com sucesso";
            } else {
                return "redirect:entrada?acao=ListaMercadoria&msg=Nenhuma alteracao feita";
            }
        } catch (RuntimeException e) {
             return "redirect:entrada?acao=ListaMercadoria&msg=Erro no Banco de Dados";
        }
    }
}