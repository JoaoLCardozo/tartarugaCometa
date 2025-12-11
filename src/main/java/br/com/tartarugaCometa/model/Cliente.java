package br.com.tartarugaCometa.model;

public class Cliente {
	private int id;
    private String nomeRazao;
    private String documento;
    private String tpDocumento;
    private Endereco endereco;
    
    
    
    public String getNomeRazao() {
        return nomeRazao;
    }
    public void setNomeRazao(String nomeRazao) {
        this.nomeRazao = nomeRazao;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getTpDocumento() {
        return tpDocumento;
    }
    public void setTpDocumento(String tpDocumento) {
        this.tpDocumento = tpDocumento;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}