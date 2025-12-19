package br.com.tartarugaCometa.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.EntregaDAO;
import br.com.tartarugaCometa.model.Entrega;

public class NovaEntrega implements Acao{

public String executa(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

    // 1. Pega os parâmetros do form
    int idRemetente = Integer.parseInt(request.getParameter("idRemetente"));
    int idDestinatario = Integer.parseInt(request.getParameter("idDestinatario"));
    int idMercadoria = Integer.parseInt(request.getParameter("idMercadoria"));
    int quantidade = Integer.parseInt(request.getParameter("quantidade"));

    // 2. Monta o objeto
    Entrega entrega = new Entrega();
    entrega.getRemetente().setId(idRemetente);
    entrega.getDestinatario().setId(idDestinatario);
    entrega.getMercadoria().setIdMercadoria(idMercadoria);
    entrega.setQuantidade(quantidade);
    
    // 3. Define o status padrão AUTOMATICAMENTE aqui
    entrega.setStatus("CRIADA"); 

    // 4. Salva no banco
    EntregaDAO dao = new EntregaDAO();
    dao.salvarEntrega(entrega);

    return "redirect:entrada?acao=ListaEntrega";
}
}