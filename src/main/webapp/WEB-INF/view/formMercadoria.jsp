<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/entrada" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Mercadoria</title>
    <style>

        fieldset { margin-bottom: 20px; padding: 15px; border: 1px solid #ccc; }
        legend { font-weight: bold; padding: 0 10px; }
        label { display: inline-block; width: 150px; margin-bottom: 5px; }
        input[type="text"], input[type="number"] { width: 250px; padding: 5px; }
        .row { margin-bottom: 10px; }
    </style>
</head>
<body>

    <h2>Cadastro de Mercadoria	</h2>
    
    <form action="${linkEntradaServlet}" method="post">
    
    <a href="javascript:history.back()">Voltar</a>
    
	
        <fieldset>
            <legend>Dados da Mercadoria</legend>
            
            <div class="row">
                <label for="nomeRazao">Nome:</label> 
                <input type="text" id="nome" name="nome" required>
            </div>
            
            <div class="row">
                <label for="tpDocumento">Peso:</label> 
                <input type="text" id="peso" name="peso" required>
            </div>
            
            <div class="row">
                <label for="documento">Preco: </label> 
                <input type="text" id="preco" name="preco" required>
            </div>
            <div class="row">
                <label for="documento">Volume: </label> 
                <input type="text" id="volume" name="volume" required>
            </div>
        </fieldset>
        
        

        
        <input type="hidden" name="acao" value="NovaMercadoria">
        
        <input type="submit" value="Cadastrar" />
	
	</form>
    
    <br/>

</body>
</html>	