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
    <style>

        fieldset { margin-bottom: 20px; padding: 15px; border: 1px solid #ccc; }
        legend { font-weight: bold; padding: 0 10px; }
        label { display: inline-block; width: 150px; margin-bottom: 5px; }
        input[type="text"], input[type="number"] { width: 250px; padding: 5px; }
        .row { margin-bottom: 10px; }
    </style>
</head>
<body>

	<h1>Menu:</h1> <br />
	<div><a href= "/tartarugaCometa/entrada?acao=ListaClientes">Cliente</a></div>
	
	<div><a href= "/tartarugaCometa/entrada?acao=ListaMercadoria">Mercadoria</a></div>
	<a href= "/tartarugaCometa/entrada?acao=ListaEntrega">Entrega</a>

</body>
</html>