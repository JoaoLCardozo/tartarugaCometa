<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,br.com.tartarugaCometa.model.Entrega"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java Standard Taglib</title>
</head>
<body>

		<c:if test= "${not empty empresa}"> 
			Empresa ${empresa} cadastrada com sucesso!
		</c:if>

	<h2>Lista de entregas:</h2> <br />
	
	<ul>
		<c:forEach items="${empresas}" var="empresa">
			<li>${empresa.nome} - <fmt:formatDate value="${empresa.dataAbertura}" pattern= "dd/MM/yyyy"/>
			<a href= "/gerenciador/entrada?acao=AlteraEmpresa&id=${empresa.id}">Editar</a>
			<a href= "/gerenciador/entrada?acao=RemoveEmpresa&id=${empresa.id}">Remover</a>
			</li>
		</c:forEach>
	</ul>
	<a href= "/gerenciador/entrada?acao=NovaEmpresaForm">Nova empresa</a>
</body>
</html>