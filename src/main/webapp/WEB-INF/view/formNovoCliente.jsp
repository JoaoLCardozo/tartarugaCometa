<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/entrada" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Cliente</title>
    <style>
        /* Apenas para melhor visualização dos blocos */
        fieldset { margin-bottom: 20px; padding: 15px; border: 1px solid #ccc; }
        legend { font-weight: bold; padding: 0 10px; }
        label { display: inline-block; width: 150px; margin-bottom: 5px; }
        input[type="text"], input[type="number"] { width: 250px; padding: 5px; }
        .row { margin-bottom: 10px; }
    </style>
</head>
<body>

    <h2>Cadastro de Novo Cliente e Endereço</h2>
    
    <form action="${linkEntradaServlet}" method="post">
	
        <fieldset>
            <legend>Dados do Cliente</legend>
            
            <div class="row">
                <label for="nomeRazao">Nome/Razão Social:</label> 
                <input type="text" id="nomeRazao" name="nomeRazao" required>
            </div>
            
            <div class="row">
                <label for="tpDocumento">Tipo de Documento (CPF/CNPJ):</label> 
                <input type="text" id="tpDocumento" name="tpDocumento" required>
            </div>
            
            <div class="row">
                <label for="documento">Documento: </label> 
                <input type="text" id="documento" name="documento" required>
            </div>
        </fieldset>
        
        <fieldset>
            <legend>Dados do Endereço (Tabela endereco)</legend>
            
            <div class="row">
                <label for="logradouro">Logradouro:</label> 
                <input type="text" id="logradouro" name="logradouro" required>
            </div>

            <div class="row">
                <label for="numero">Número:</label> 
                <input type="text" id="numero" name="numero" required>
            </div>

            <div class="row">
                <label for="cep">CEP:</label> 
                <input type="text" id="cep" name="cep" required>
            </div>

            <div class="row">
                <label for="bairro">Bairro:</label> 
                <input type="text" id="bairro" name="bairro" required>
            </div>

            <div class="row">
                <label for="cidade">Cidade:</label> 
                <input type="text" id="cidade" name="cidade" required>
            </div>

            <div class="row">
                <label for="estadoUf">Estado (UF):</label> 
                <input type="text" id="estadoUf" name="estadoUf" required>
            </div>

            <div class="row">
                <label for="complemento">Complemento:</label> 
                <input type="text" id="complemento" name="complemento">
            </div>
        </fieldset>

        
        <input type="hidden" name="acao" value="NovoCliente">
        
        <input type="submit" value="Cadastrar" />
	
	</form>
    
    <br/>
    <a href="javascript:history.back()">Voltar</a>

</body>
</html>	