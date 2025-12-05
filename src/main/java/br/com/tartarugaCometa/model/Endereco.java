package br.com.tartarugaCometa.model;

public class Endereco {
    
    private String rua;
    private String numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String complemento;
    private String uf;
    private int idEndereco;
    
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public int getIdEndereco() {
        return idEndereco;
    }
    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
    
    public void salvarEndereco(Cliente remetente) {
        throw new UnsupportedOperationException("Unimplemented method 'salvarEndereco'");
    }
    
}