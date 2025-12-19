<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Mercadorias</title>
</head>
<body>
    <a href="/tartarugaCometa/entrada?acao=Menu">Voltar</a>

    <h2>Lista de Mercadorias:</h2>
    <br>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome da Mercadoria</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${mercadoria}">
                <tr>
                    <td>${m.idMercadoria}</td>
                    <td>${m.nome}</td>
                    <td>
                        <a href="entrada?acao=MostraMercadoria&id=${m.idMercadoria}">Editar</a>
                        <a href="entrada?acao=RemoveMercadoria&id=${m.idMercadoria}" 
                           onclick="return confirm('Deseja excluir esta mercadoria?')">Remover</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>
    <a href="/tartarugaCometa/entrada?acao=NovaMercadoriaForm">Cadastrar Mercadoria</a>
</body>
</html>