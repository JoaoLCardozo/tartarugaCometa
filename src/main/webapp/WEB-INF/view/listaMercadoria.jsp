<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,br.com.tartarugaCometa.model.Cliente"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java Standard Taglib</title>
</head>
<body>
<a href="javascript:history.back()">Voltar</a>

		<c:if test= "${not empty empresa}"> 
			Mercadoria ${mercadoria} cadastrada com sucesso!
		</c:if>

	<h2>Lista de Mercadorias:</h2> <br />
	
	<ul>
		<c:forEach var="cliente" items="${cliente}">
			<li>
			${mercadoria.id} - ${mercadoria.nome}
			<a href= "/tartarugaCometa/entrada?acao=AlteraCliente&id=${mercadoria.id}">Editar</a>
			<a href= "/tartarugaCometa/entrada?acao=RemoveCliente&id=${mercadoria.id}">Remover</a>
			</li>
		</c:forEach>	
	</ul>
	<a href= "/tartarugaCometa/entrada?acao=NovaMercadoriaForm">Cadastrar Mercadoria</a>
</body>
</html>