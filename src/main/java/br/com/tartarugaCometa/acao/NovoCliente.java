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


public class NovoCliente implements Acao{
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Cadastrando nova empresa");
		
		String nomeEmpresa = request.getParameter("nome");
		String documento = request.getParameter("documento");
		String endereco = request.getParameter("endereco");
		Date dataAbertura = null;
		Endereco endereco2 = new Endereco();
		
		int idE = salvarEndereco();
		
		endereco2.setIdEndereco(idE);
		cliente.setEndereco(endereco2)
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(paramDataEmpresa);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		
		Cliente cliente = new Cliente();
		cliente.setNomeRazao(nomeEmpresa);
		cliente.setDocumento(documento);
		cliente.setEndereco(endereco);
		
		ClienteDAO dao = new ClienteDAO();
		dao.salvarCliente(cliente);
		
		request.setAttribute("cliente", cliente.getNomeRazao());
		
		return "redirect:entrada?acao=ListaEmpresas";
	}

}