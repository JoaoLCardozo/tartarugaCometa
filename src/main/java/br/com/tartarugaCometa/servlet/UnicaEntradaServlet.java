package br.com.tartarugaCometa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.acao.Acao;
import br.com.tartarugaCometa.acao.TesteConexao;

@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
		
		String nomeDaClasse = "br.com.tartarugaCometa.acao." + paramAcao;
		
		String nome;
		try {

            TesteConexao.testar();
        } catch (ClassNotFoundException e) {

            System.err.println("❌ ERRO: Driver JDBC não encontrado!");
            throw new ServletException("Falha ao carregar o driver JDBC.", e);
        }
		try {
			Class classe = 	Class.forName(nomeDaClasse);
			Acao acao = (Acao) classe.newInstance();
			nome = acao.executa(request,response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException
				| IOException e) {
			throw new ServletException(e);
			
		}
		
		String[] tipoEEndereco = nome.split(":");
		if(tipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
			rd.forward(request, response);
		} else {
			response .sendRedirect(tipoEEndereco[1]);
		}
	
		
	}
}