<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
</head>
<body>
    <a href="/tartarugaCometa/entrada?acao=Menu">Voltar</a>

    <h2>Lista de clientes:</h2>
    <br>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Cliente</th>
                <th>Ações</th> </tr>
       </thead>
       <tbody>
        <c:forEach var="cliente" items="${cliente}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nomeRazao}</td>
                <td>
                    <a href="entrada?acao=MostraCliente&id=${cliente.id}">Editar</a>
                    <a href="entrada?acao=RemoveCliente&id=${cliente.id}" onclick="return confirm('Excluir cliente?')">Remover</a>
                </td>
            </tr> </c:forEach>
       </tbody>
    </table>

    <br>
    <a href="/tartarugaCometa/entrada?acao=NovoClienteForm">Cadastrar Cliente</a>
</body>
</html>