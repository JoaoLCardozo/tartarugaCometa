<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/entrada" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Alteração de cliente</title>
</head>
<body>
  <h1>Alterando cliente: ${cliente.nomeRazao}</h1>
  <a href="/tartarugaCometa/entrada?acao=ListaClientes">Voltar</a>

  <form action="${linkEntradaServlet}" method="post">
    <input type="hidden" name="acao" value="AlteraClientes">
    <input type="hidden" name="id" value="${cliente.id}">
    <input type="hidden" name="idEndereco" value="${cliente.endereco.idEndereco}">

    <label>Nome/Razão Social:*</label>
    <input type="text" name="nomeRazao" id="nomeRazao" value="${cliente.nomeRazao}" required><br>

    <label>Tipo de Documento:*</label>
    <select name="tpDocumento" id="tpDocumento" required>
      <option value="">Selecione...</option>
      <option value="CPF"  ${cliente.tpDocumento == 'CPF'  ? 'selected' : ''}>CPF</option>
      <option value="CNPJ" ${cliente.tpDocumento == 'CNPJ' ? 'selected' : ''}>CNPJ</option>
    </select><br>

    <label>Documento:*</label>
    <input type="text" name="documento" id="documento" value="${cliente.documento}"
           required inputmode="numeric" autocomplete="off"><br>

    <label>Logradouro:*</label>
    <input type="text" name="logradouro" id="logradouro" value="${cliente.endereco.logradouro}" required><br>

    <label>Número:*</label>
    <input type="text" name="numero" id="numero" value="${cliente.endereco.numero}" required><br>

    <label>CEP:*</label>
    <input type="text" name="cep" id="cep" value="${cliente.endereco.cep}"
           required inputmode="numeric" maxlength="9"
           pattern="[0-9]{5}-[0-9]{3}" title="Formato: 00000-000"><br>

    <label>Bairro:*</label>
    <input type="text" name="bairro" id="bairro" value="${cliente.endereco.bairro}" required><br>

    <label>Cidade:*</label>
    <input type="text" name="cidade" id="cidade" value="${cliente.endereco.cidade}" required><br>

    <label>UF:*</label>
    <input type="text" name="estado_uf" id="estado_uf" value="${cliente.endereco.uf}"
           required maxlength="2" pattern="[A-Za-z]{2}" title="UF com 2 letras (ex: PE)"><br>

    <label>Complemento:</label>
    <input type="text" name="complemento" id="complemento" value="${cliente.endereco.complemento}"><br>

    <br>
    <input type="submit" value="Salvar Alterações">
  </form>

  <script>
    const tpDocumento = document.getElementById('tpDocumento');
    const documento = document.getElementById('documento');
    const cep = document.getElementById('cep');
    const uf = document.getElementById('estado_uf');
    const form = document.querySelector('form');

    function onlyDigits(v) { return (v || '').replace(/\D/g, ''); }

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
        documento.maxLength = 14;
      } else if (tipo === 'CNPJ') {
        documento.value = maskCNPJ(documento.value);
        documento.maxLength = 18;
      } else {
        documento.maxLength = 18;
      }
      documento.setCustomValidity('');
    }

    // aplica máscaras no carregamento (pra valores já preenchidos)
    documento.value = (tpDocumento.value === 'CNPJ') ? maskCNPJ(documento.value) : maskCPF(documento.value);
    cep.value = maskCEP(cep.value);
    uf.value = (uf.value || '').replace(/[^a-zA-Z]/g, '').toUpperCase().slice(0, 2);

    // listeners
    tpDocumento.addEventListener('change', () => {
      documento.value = '';
      documento.setCustomValidity('');
    });
    documento.addEventListener('input', applyDocumentoMask);
    cep.addEventListener('input', () => {
      cep.value = maskCEP(cep.value);
      cep.setCustomValidity('');
    });
    uf.addEventListener('input', () => {
      uf.value = (uf.value || '').replace(/[^a-zA-Z]/g, '').toUpperCase().slice(0, 2);
      uf.setCustomValidity('');
    });

    form.addEventListener('submit', (e) => {
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
