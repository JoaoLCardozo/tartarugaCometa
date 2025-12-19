package br.com.tartarugaCometa.acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.tartarugaCometa.dao.ClienteDAO;
import br.com.tartarugaCometa.model.Cliente;

public class MostraCliente implements Acao {

    public String executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);

        
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = dao.buscaClientePeloId(id); 

        request.setAttribute("cliente", cliente);

        return "forward:formAlteraCliente.jsp"; 
    }
}