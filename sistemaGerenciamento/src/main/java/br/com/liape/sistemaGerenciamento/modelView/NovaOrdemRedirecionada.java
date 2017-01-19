package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;

public class NovaOrdemRedirecionada implements Serializable{
	private static final long serialVersionUID = 7903539131104580507L;
	private String justificativa;
	private String idDestino;
	private String dataExecutada;
	private int destinada;
	private String dataExecutadaHora;
	private int idOrs;
	private int idSubOrsAntiga;
	
	public int getIdOrs() {
		return idOrs;
	}
	public void setIdOrs(int idOrs) {
		this.idOrs = idOrs;
	}
	public int getIdSubOrsAntiga() {
		return idSubOrsAntiga;
	}
	public void setIdSubOrsAntiga(int idSubOrsAntiga) {
		this.idSubOrsAntiga = idSubOrsAntiga;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
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
	public String getDataExecutadaHora() {
		return dataExecutadaHora;
	}
	public void setDataExecutadaHora(String dataExecutadaHora) {
		this.dataExecutadaHora = dataExecutadaHora;
	}
	
	
	
	
}
