<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/entrada" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Cliente</title>
    <style>

        fieldset { margin-bottom: 20px; padding: 15px; border: 1px solid #ccc; }
        legend { font-weight: bold; padding: 0 10px; }
        label { display: inline-block; width: 150px; margin-bottom: 5px; }
        input[type="text"], input[type="number"] { width: 250px; padding: 5px; }
        .row { margin-bottom: 10px; }
    </style>
</head>
<body>

    <h2>Cadastro de Novo Cliente	</h2>
    
    <form action="${linkEntradaServlet}" method="post">
    
    <a href="/tartarugaCometa/entrada?acao=ListaClientes">Voltar</a>
    
	
        <fieldset>
            <legend>Dados do Cliente</legend>
            
            <div class="row">
                <label for="nomeRazao">Nome/Razão Social:*</label> 
                <input type="text" id="nomeRazao" name="nomeRazao" required>
            </div>
            
            <div class="row">
                <label for="tpDocumento">Tipo de Documento (CPF/CNPJ):*</label>  
                <select id="tpDocumento" name="tpDocumento" required>
  				<option value="">Selecione...</option>
  				<option value="CPF">CPF</option>
  				<option value="CNPJ">CNPJ</option>
			</select>
            </div>
            
            <div class="row">
                <label for="documento">Documento:* </label> 
                <input type="text" id="documento" name="documento" required inputmode="numeric" autocomplete="off">
            </div>
        </fieldset>
        
        <fieldset>
            <legend>Dados do Endereço (Tabela endereco)</legend>
            
            <div class="row">
                <label for="logradouro">Logradouro:*</label> 
                <input type="text" id="logradouro" name="logradouro" required>
            </div>

            <div class="row">
                <label for="numero">Número:*</label> 
                <input type="text" id="numero" name="numero" required>
            </div>

            <div class="row">
                <label for="cep">CEP:*</label> 
                <input type="text" id="cep" name="cep" required inputmode="numeric" maxlength="9"
       			  pattern="[0-9]{5}-[0-9]{3}" title="Formato: 00000-000">
            </div>

            <div class="row">
                <label for="bairro">Bairro:*</label> 
                <input type="text" id="bairro" name="bairro" required>
            </div>

            <div class="row">
                <label for="cidade">Cidade:*</label> 
                <input type="text" id="cidade" name="cidade" required>
            </div>

            <div class="row">
                <label for="estadoUf">Estado (UF):*</label> 
                <input type="text" id="estado_uf" name="estado_uf" required maxlength="2"
       			pattern="[A-Za-z]{2}" title="UF com 2 letras (ex: PE)">
            </div>

            <div class="row">
                <label for="complemento">Complemento:</label> 
                <input type="text" id="complemento" name="complemento" >
            </div>
        </fieldset>

        
        <input type="hidden" name="acao" value="NovoCliente">
        
        <input type="submit" value="Cadastrar" />
	
	</form>
    
    <br/>
    
<script>
  const tpDocumento = document.getElementById('tpDocumento');
  const documento = document.getElementById('documento');
  const cep = document.getElementById('cep');
  const uf = document.getElementById('estado_uf');

  function onlyDigits(v) {
    return (v || '').replace(/\D/g, '');
  }

  function maskCPF(d) {
    d = onlyDigits(d).slice(0, 11);
    d = d.replace(/(\d{3})(\d)/, '$1.$2');
    d = d.replace(/(\d{3})(\d)/, '$1.$2');
    d = d.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    return d;
  }

  function maskCNPJ(d) {
    d = onlyDigits(d).slice(0, 14);
    d = d.replace(/(\d{2})(\d)/, '$1.$2');
    d = d.replace(/(\d{3})(\d)/, '$1.$2');
    d = d.replace(/(\d{3})(\d)/, '$1/$2');
    d = d.replace(/(\d{4})(\d{1,2})$/, '$1-$2');
    return d;
  }

  function maskCEP(v) {
    v = onlyDigits(v).slice(0, 8);
    if (v.length > 5) return v.slice(0,5) + '-' + v.slice(5);
    return v;
  }

  function isValidCPF(cpf) {
    cpf = onlyDigits(cpf);
    if (cpf.length !== 11) return false;
    if (/^(\d)\1+$/.test(cpf)) return false;

    let sum = 0;
    for (let i = 0; i < 9; i++) sum += parseInt(cpf[i]) * (10 - i);
    let d1 = (sum * 10) % 11;
    if (d1 === 10) d1 = 0;
    if (d1 !== parseInt(cpf[9])) return false;

    sum = 0;
    for (let i = 0; i < 10; i++) sum += parseInt(cpf[i]) * (11 - i);
    let d2 = (sum * 10) % 11;
    if (d2 === 10) d2 = 0;
    return d2 === parseInt(cpf[10]);
  }

  function isValidCNPJ(cnpj) {
    cnpj = onlyDigits(cnpj);
    if (cnpj.length !== 14) return false;
    if (/^(\d)\1+$/.test(cnpj)) return false;

    const calc = (base) => {
      const weights = base.length === 12
        ? [5,4,3,2,9,8,7,6,5,4,3,2]
        : [6,5,4,3,2,9,8,7,6,5,4,3,2];
      let sum = 0;
      for (let i = 0; i < weights.length; i++) sum += parseInt(base[i]) * weights[i];
      const r = sum % 11;
      return (r < 2) ? 0 : 11 - r;
    };

    const d1 = calc(cnpj.slice(0, 12));
    const d2 = calc(cnpj.slice(0, 12) + d1);
    return (parseInt(cnpj[12]) === d1) && (parseInt(cnpj[13]) === d2);
  }

  function applyDocumentoMask() {
    const tipo = tpDocumento.value;
    if (tipo === 'CPF') {
      documento.value = maskCPF(documento.value);
      documento.maxLength = 14; // 000.000.000-00
    } else if (tipo === 'CNPJ') {
      documento.value = maskCNPJ(documento.value);
      documento.maxLength = 18; // 00.000.000/0000-00
    } else {
      documento.maxLength = 18;
    }
  }

  // Máscaras digitação
documento.addEventListener('input', () => {
  documento.setCustomValidity('');   // limpa erro antigo
  applyDocumentoMask();
});

tpDocumento.addEventListener('change', () => {
  documento.value = '';
  documento.setCustomValidity('');
});


  cep.addEventListener('input', () => {
    cep.value = maskCEP(cep.value);
    cep.setCustomValidity('');
  });

  uf.addEventListener('input', () => {
    uf.value = (uf.value || '').replace(/[^a-zA-Z]/g, '').toUpperCase().slice(0, 2);
    uf.setCustomValidity('');
  });

  // Validação na confirmação(pra bloquear se algum dado for invalido)
  document.querySelector('form').addEventListener('submit', (e) => {
	documento.setCustomValidity('');
    const tipo = tpDocumento.value;
    const docVal = documento.value;

    if (tipo === 'CPF' && !isValidCPF(docVal)) {
      documento.setCustomValidity('CPF inválido.');
      documento.reportValidity();
      e.preventDefault();
      return;
    }

    if (tipo === 'CNPJ' && !isValidCNPJ(docVal)) {
      documento.setCustomValidity('CNPJ inválido.');
      documento.reportValidity();
      e.preventDefault();
      return;
    }

    // Validação da formatação do CEP com "-" no meio
    if (!/^\d{5}-\d{3}$/.test(cep.value)) {
      cep.setCustomValidity('CEP inválido. Use 00000-000.');
      cep.reportValidity();
      e.preventDefault();
      return;
    }

    if (!/^[A-Z]{2}$/.test(uf.value)) {
      uf.setCustomValidity('UF inválida. Use 2 letras (ex: PE).');
      uf.reportValidity();
      e.preventDefault();
      return;
    }
  });
</script>

</body>
</html>	