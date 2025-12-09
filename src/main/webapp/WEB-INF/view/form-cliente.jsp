
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/entrada?acao=NovoCadastro" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Cliente</title>
</head>
<body>

	<form action= "${linkEntradaServlet}" method = "post">
	
		Nome: <input type= "text" name= "nome" />
		Data Abertura: <input type= "text" name = "data" />
		
		<input type= "hidden" name= "acao" value= "NovoCadastro">
		
		<input type= "submit" value="Cadastrar" />
	
	</form>

</body>
</html>