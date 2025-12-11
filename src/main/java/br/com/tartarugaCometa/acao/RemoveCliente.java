package br.com.tartarugaCometa.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.ClienteDAO;



public class RemoveCliente implements Acao{
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Acao removendo empresa");
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		System.out.println(id);
		
		ClienteDAO dao = new ClienteDAO();
		dao.deletarCliente(id);
		
		return "redirect:entrada?acao=ListaClientes";
	}

}