package br.com.tartarugaCometa.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.MercadoriaDAO;
import br.com.tartarugaCometa.model.Mercadoria;




public class NovaMercadoria implements Acao{
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Cadastrando nova mercadoria");
		
		String nomeMercadoria = request.getParameter("nome");
		Double peso = Double.valueOf(request.getParameter("peso"));
		Double preco = Double.valueOf(request.getParameter("preco"));
		Double volume = Double.valueOf(request.getParameter("volume"));
		
		
		
		Mercadoria mercadoria = new Mercadoria();
		mercadoria.setNome(nomeMercadoria);
		mercadoria.setPeso(peso);
		mercadoria.setPreco(preco);
		mercadoria.setVolume(volume);
		
		MercadoriaDAO dao = new MercadoriaDAO();
		dao.salvarMercadoria(mercadoria);
		
		request.setAttribute("mercadoria", mercadoria.getNome());
		
		return "redirect:entrada?acao=ListaEmpresas";
	}

}