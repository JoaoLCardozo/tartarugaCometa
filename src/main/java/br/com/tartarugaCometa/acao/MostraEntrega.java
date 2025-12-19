package br.com.tartarugaCometa.acao;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tartarugaCometa.dao.ClienteDAO;
import br.com.tartarugaCometa.dao.EntregaDAO;
import br.com.tartarugaCometa.dao.MercadoriaDAO;
import br.com.tartarugaCometa.model.Cliente;
import br.com.tartarugaCometa.model.Entrega;
import br.com.tartarugaCometa.model.Mercadoria;

public class MostraEntrega implements Acao {

    public String executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        
        int id = Integer.parseInt(request.getParameter("id"));

        
        EntregaDAO entregaDao = new EntregaDAO();
        Entrega entrega = entregaDao.buscaEntregaPeloId(id);

        
        ClienteDAO clienteDao = new ClienteDAO();
        List<Cliente> clientes = clienteDao.selecionarTodos();

        MercadoriaDAO mercadoriaDao = new MercadoriaDAO();
        List<Mercadoria> mercadorias = mercadoriaDao.selecionarTodos();

        
        request.setAttribute("entrega", entrega);
        request.setAttribute("clientes", clientes);
        request.setAttribute("mercadorias", mercadorias);

        return "forward:formAlteraEntrega.jsp";
    }
}