<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="entrada" method="post">
  <input type="hidden" name="acao" value="NovaEntrega">

  <h2>Cadastro de Entrega</h2>
  <h3>Remetente</h3>
  <select name="idRemetente" required>
    <c:forEach var="c" items="${clientes}">
      <option value="${c.id}">${c.nomeRazao} - ${c.documento}</option>
    </c:forEach>
  </select>

  <h3>Destinatário</h3>
  <select name="idDestinatario" required>
    <c:forEach var="c" items="${clientes}">
      <option value="${c.id}">${c.nomeRazao} - ${c.documento}</option>
    </c:forEach>
  </select>

  <h3>Mercadoria</h3>
  <select name="idMercadoria" required>
    <c:forEach var="m" items="${mercadorias}">
      <option value="${m.idMercadoria}">${m.nome}</option>
    </c:forEach>
  </select>

  <h3>Quantidade:</h3>
  <input type="number" name="quantidade" min="1" required>
  
 

  <button type="submit">Salvar</button>
</form>
