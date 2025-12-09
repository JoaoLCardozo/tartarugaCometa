package br.com.tartarugaCometa.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.ClienteDAO;
import br.com.tartarugaCometa.model.Cliente;
import br.com.tartarugaCometa.model.Endereco;
import br.com.tartarugaCometa.model.Mercadoria;


public class NovoProduto implements Acao{
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Cadastrando nova empresa");
		
		String nome = request.getParameter("nome");
		String documento = request.getParameter("documento");
		String endereco = request.getParameter("endereco");
		
		Date dataAbertura = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(paramDataEmpresa);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		
		Mercadoria mercadoria = new Mercadoria();
		mercadoria.setNome(nome);
		mercadoria.setPeso(peso);
		mercadoria.setPreco(preco);
		
		ClienteDAO dao = new ClienteDAO();
		dao.salvarCliente(cliente);
		
		request.setAttribute("cliente", cliente.getNomeRazao());
		
		return "redirect:entrada?acao=ListaEmpresas";
	}

}