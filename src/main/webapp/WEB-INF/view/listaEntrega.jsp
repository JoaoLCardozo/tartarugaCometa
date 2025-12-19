<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Entregas</title>
</head>
<body>
<a href="/tartarugaCometa/entrada?acao=Menu">Voltar</a>

    <h2>Entregas</h2>
    <br><br>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Remetente</th>
                <th>Destinatário</th>
                <th>Mercadoria</th>
                <th>Qtd</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${entregas}">
                <tr>
                    <td>${e.idEntrega}</td>
                    <td>${e.remetente.nomeRazao}</td>
                    <td>${e.destinatario.nomeRazao}</td>
                    <td>${e.mercadoria.nome}</td>
                    <td>${e.quantidade}</td>
                    <td>${e.status}</td>
                    <td>
                        <a href="entrada?acao=MostraEntrega&id=${e.idEntrega}">Editar</a>
                        <a href="entrada?acao=RemoveEntrega&id=${e.idEntrega}" onclick="return confirm('apagar esta entrega?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<a href="entrada?acao=NovaEntregaForm">Nova Entrega</a>
</body>
</html>