package br.com.liape.sistemaGerenciamento.modelView;

public class NovaOrdem extends NovaPendencia {
	
	private String descricao;
	private int tipo;
	private int idTipo;
	private int idComputador;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public int getIdComputador() {
		return idComputador;
	}
	public void setIdComputador(int idComputador) {
		this.idComputador = idComputador;
	}
	
		

}
