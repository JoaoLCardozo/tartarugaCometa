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
    
    <a href="/tartarugaCometa/entrada?acao=ListaMercadoria">Voltar</a>
    
	
        <fieldset>
            <legend>Dados da Mercadoria</legend>
            
           <div class="row">
  				<label for="nome">Nome:*</label>
				<input type="text" id="nome" name="nome" required
         			minlength="2" maxlength="80"
         			pattern="^[A-Za-zÀ-ÿ0-9 ]{2,80}$"
         			title="Use 2 a 80 caracteres (letras/números/espaço).">
			</div>
            
            
            <div class="row">
  				<label for="peso">Peso (kg):</label>
  				<input type="text" id="peso" name="peso" required
         			inputmode="decimal"
         			pattern="^[0-9]+([.,][0-9]{1,3})?$"
         			title="Use números. Ex: 2 ou 2,5">
			</div>

			<div class="row">
  			<label for="preco">Preço (R$):</label>
  			<input type="text" id="preco" name="preco" required
        		inputmode="decimal"
         		pattern="^[0-9]+([.,][0-9]{1,2})?$"
         		title="Use números. Ex: 10 ou 10,90">
			</div>

			<div class="row">
  				<label for="volume">Volume:</label>
 				<input type="text" id="volume" name="volume" required
         			inputmode="decimal"
         			pattern="^[0-9]+([.,][0-9]{1,3})?$"
         			title="Use números. Ex: 1 ou 1,250">
			</div>

        </fieldset>
        
        

        
        <input type="hidden" name="acao" value="NovaMercadoria">
        
        <input type="submit" value="Cadastrar" />
	
	</form>
    
    
    <br/>
    
    <script>
  const nome = document.getElementById('nome');
  const peso = document.getElementById('peso');
  const preco = document.getElementById('preco');
  const volume = document.getElementById('volume');
  const form = document.querySelector('form');

  
  nome.addEventListener('input', () => {
    
    nome.value = (nome.value || '').replace(/\s+/g, ' ').trimStart();
    nome.setCustomValidity('');
  });

  
  function sanitizeDecimalInput(value) {
    value = (value || '').replace(/[^\d.,]/g, '');

    const firstComma = value.indexOf(',');
    const firstDot = value.indexOf('.');

    let sepIndex = -1;
    if (firstComma !== -1 && firstDot !== -1) sepIndex = Math.min(firstComma, firstDot);
    else sepIndex = Math.max(firstComma, firstDot);

    if (sepIndex !== -1) {
      const intPart = value.slice(0, sepIndex).replace(/[^\d]/g, '');
      const decPart = value.slice(sepIndex + 1).replace(/[^\d]/g, '');
      return intPart + value[sepIndex] + decPart; 
    }

    return value.replace(/[^\d]/g, '');
  }

  
  function limitDecimals(value, maxDecimals) {
    value = sanitizeDecimalInput(value);

    
    const comma = value.indexOf(',');
    const dot = value.indexOf('.');
    const sepIndex = (comma !== -1) ? comma : dot;

    if (sepIndex === -1) return value;

    const sep = value[sepIndex];
    const intPart = value.slice(0, sepIndex);
    let decPart = value.slice(sepIndex + 1);

    decPart = decPart.slice(0, maxDecimals);
    return intPart + sep + decPart;
  }

  function attachDecimalMask(input, maxDecimals) {
    input.addEventListener('input', () => {
      const old = input.value;
      const masked = limitDecimals(old, maxDecimals);
      if (old !== masked) input.value = masked;
      input.setCustomValidity('');
    });
  }

  
  attachDecimalMask(peso, 3);
  attachDecimalMask(volume, 3);
  attachDecimalMask(preco, 2);

  function toNumberForBackend(value) {
    
    return sanitizeDecimalInput(value).replace(',', '.');
  }

  function isPositiveNumberString(v) {
    if (!v) return false;
    
    if (!/^\d+(\.\d+)?$/.test(v)) return false;
    const n = Number(v);
    return Number.isFinite(n) && n > 0;
  }

  form.addEventListener('submit', (e) => {
    
    nome.value = (nome.value || '').trim();
    if (nome.value.length < 2) {
      nome.setCustomValidity('Nome muito curto (mínimo 2 caracteres).');
      nome.reportValidity();
      e.preventDefault();
      return;
    }

    
    const pesoN = toNumberForBackend(peso.value);
    const precoN = toNumberForBackend(preco.value);
    const volumeN = toNumberForBackend(volume.value);

    if (!isPositiveNumberString(pesoN)) {
      peso.setCustomValidity('Peso inválido. Use um número maior que zero (ex: 2 ou 2,5).');
      peso.reportValidity();
      e.preventDefault();
      return;
    }

    if (!isPositiveNumberString(precoN)) {
      preco.setCustomValidity('Preço inválido. Use um número maior que zero (ex: 10 ou 10,90).');
      preco.reportValidity();
      e.preventDefault();
      return;
    }

    if (!isPositiveNumberString(volumeN)) {
      volume.setCustomValidity('Volume inválido. Use um número maior que zero.');
      volume.reportValidity();
      e.preventDefault();
      return;
    }

    
    if (pesoN.includes('.') && pesoN.split('.')[1].length > 3) {
      peso.setCustomValidity('Peso: no máximo 3 casas decimais.');
      peso.reportValidity();
      e.preventDefault();
      return;
    }
    if (volumeN.includes('.') && volumeN.split('.')[1].length > 3) {
      volume.setCustomValidity('Volume: no máximo 3 casas decimais.');
      volume.reportValidity();
      e.preventDefault();
      return;
    }
    if (precoN.includes('.') && precoN.split('.')[1].length > 2) {
      preco.setCustomValidity('Preço: no máximo 2 casas decimais.');
      preco.reportValidity();
      e.preventDefault();
      return;
    }

    
    peso.value = pesoN;
    preco.value = precoN;
    volume.value = volumeN;
  });
</script>


    

</body>
</html>	