package br.com.tartarugaCometa.model;

public class Entrega {

    private Cliente destinatario = new Cliente();
    private Cliente remetente = new Cliente();
    private int idCliente;

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
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
