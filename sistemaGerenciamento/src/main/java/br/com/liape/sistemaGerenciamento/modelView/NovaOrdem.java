package br.com.liape.sistemaGerenciamento.modelView;

public class NovaOrdem {
	private String idDestino;
	private String dataExecutada;
	private int destinada;
	private String descricao;
	private int tipo;
	private int idTipo;
	private int idComputador;
	private String dataExecutadaHora;
	public String getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
	}
	public String getDataExecutada() {
		return dataExecutada;
	}
	public void setDataExecutada(String dataExecutada) {
		this.dataExecutada = dataExecutada;
	}
	public int getDestinada() {
		return destinada;
	}
	public void setDestinada(int destinada) {
		this.destinada = destinada;
	}
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
	public String getDataExecutadaHora() {
		return dataExecutadaHora;
	}
	public void setDataExecutadaHora(String dataExecutadaHora) {
		this.dataExecutadaHora = dataExecutadaHora;
	}
	
		

}
