<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url value="/entrada" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alteração de Mercadoria</title>
</head>
<body>
<h1>Alterando mercadoria: ${mercadoria.nome} </h1>
	<a href="/tartarugaCometa/entrada?acao=ListaMercadoria">Voltar</a>
    <form action="${linkEntradaServlet}" method="post">
    
        <input type="hidden" name="acao" value="AlteraMercadoria">
        
        <input type="hidden" name="idMercadoria" value="${mercadoria.idMercadoria}">
    
        <label>Mercadoria:*</label>
        <input type="text" name="nome" value="${mercadoria.nome}" required/><br>
        
        <label>Peso:*</label>
        <input type="text" name="peso" value="${mercadoria.peso}" required/><br>
        
        <label>Preco:*</label>
        <input type="text" name="preco" value="${mercadoria.preco}" required/><br>
        
        <label>Volume:*</label>
        <input type="text" name="volume" value="${mercadoria.volume}" required/><br>

        <br>
        <input type="submit" value="Salvar Alterações" />
    
    </form>

</body>
</html>