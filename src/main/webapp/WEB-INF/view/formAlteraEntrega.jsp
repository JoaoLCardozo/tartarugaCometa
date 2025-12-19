<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <h2>Alterar Entrega #${entrega.idEntrega}</h2>

    <form action="entrada" method="post">
        <input type="hidden" name="acao" value="AlteraEntrega">
        
        <input type="hidden" name="idEntrega" value="${entrega.idEntrega}">

        <p>Remetente:<br>
        <select name="idRemetente">
            <c:forEach var="c" items="${clientes}">
                <option value="${c.id}" ${c.id == entrega.remetente.id ? 'selected' : ''}>
                    ${c.nomeRazao}
                </option>
            </c:forEach>
        </select></p>

        <p>Destinatário:<br>
        <select name="idDestinatario">
            <c:forEach var="c" items="${clientes}">
                <option value="${c.id}" ${c.id == entrega.destinatario.id ? 'selected' : ''}>
                    ${c.nomeRazao}
                </option>
            </c:forEach>
        </select></p>

        <p>Mercadoria:<br>
        <select name="idMercadoria">
            <c:forEach var="m" items="${mercadorias}">
                <option value="${m.idMercadoria}" ${m.idMercadoria == entrega.mercadoria.idMercadoria ? 'selected' : ''}>
                    ${m.nome}
                </option>
            </c:forEach>
        </select></p>

        <p>Quantidade:<br>
        <input type="number" name="quantidade" value="${entrega.quantidade}"></p>

        <p>Status:<br>
        <select name="status">
            <option value="CRIADA" ${entrega.status == 'CRIADA' ? 'selected' : ''}>CRIADA</option>
            <option value="EM_TRANSITO" ${entrega.status == 'EM_TRANSITO' ? 'selected' : ''}>EM_TRANSITO</option>
            <option value="ENTREGUE" ${entrega.status == 'ENTREGUE' ? 'selected' : ''}>ENTREGUE</option>
            <option value="CANCELADA" ${entrega.status == 'CANCELADA' ? 'selected' : ''}>CANCELADA</option>
        </select></p>

        <button type="submit">Salvar Alterações</button>
        <a href="entrada?acao=ListaEntrega">Cancelar</a>
    </form>
</body>
</html>