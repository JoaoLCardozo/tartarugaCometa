package br.com.tartarugaCometa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.acao.TesteConexao;

@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

            TesteConexao.testar();
        } catch (ClassNotFoundException e) {

            System.err.println("❌ ERRO: Driver JDBC não encontrado!");
            throw new ServletException("Falha ao carregar o driver JDBC.", e);
        }
	}
	}

