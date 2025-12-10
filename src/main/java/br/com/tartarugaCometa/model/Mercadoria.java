package br.com.tartarugaCometa.model;

public class Mercadoria {

    private String nome;
    private double peso;
    private double preco;
    private double volume;
    private int quantidade;
    private int idMercadoria;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getIdMercadoria() {
        return idMercadoria;
    }
    public void setIdMercadoria(int idMercadoria) {
        this.idMercadoria = idMercadoria;
    }    
}
