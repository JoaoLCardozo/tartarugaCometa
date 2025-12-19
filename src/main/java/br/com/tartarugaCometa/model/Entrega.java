package br.com.tartarugaCometa.model;

public class Entrega {

    private int idEntrega;
    
    private Cliente destinatario = new Cliente();
    private Cliente remetente = new Cliente();
    private Mercadoria mercadoria = new Mercadoria(); 
    private int quantidade;
    private String status; 

    

    public int getIdEntrega() {
        return idEntrega;
    }
    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }

    public Cliente getRemetente() {
        return remetente;
    }
    public void setRemetente(Cliente remetente) {
        this.remetente = remetente;
    }

    public Mercadoria getMercadoria() {
        return mercadoria;
    }
    public void setMercadoria(Mercadoria mercadoria) {
        this.mercadoria = mercadoria;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}