package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;

public class NovaOrdemRedirecionada extends NovaPendencia implements Serializable{
	private static final long serialVersionUID = 7903539131104580507L;
	private String justificativa;
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
	
	
}
