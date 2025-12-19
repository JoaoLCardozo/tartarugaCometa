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

public class ListaEntrega implements Acao {

    public String executa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Listando entregas do Banco de Dados");

        EntregaDAO entregaDAO = new EntregaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        MercadoriaDAO mercadoriaDAO = new MercadoriaDAO();

        List<Entrega> lista = entregaDAO.selecionarTodas();

        for (Entrega entrega : lista) {
            Cliente rem = clienteDAO.buscaClientePeloId(entrega.getRemetente().getId());
            Cliente dest = clienteDAO.buscaClientePeloId(entrega.getDestinatario().getId());
            Mercadoria mercadoria = mercadoriaDAO.buscaMercadoriaPeloId(entrega.getMercadoria().getIdMercadoria());

            if (rem != null) entrega.setRemetente(rem);
            if (dest != null) entrega.setDestinatario(dest);
            if (mercadoria != null) entrega.setMercadoria(mercadoria);
        }

        request.setAttribute("entregas", lista);
        return "forward:listaEntrega.jsp";
    }
}
