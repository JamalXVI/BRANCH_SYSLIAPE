package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;
import java.sql.Timestamp;

public class EditarOrdemView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -890755815551037790L;
	private Timestamp dataParaSerExecutada;
	private boolean ehSala;
	private String idSala;
	private String idComputador;
	private boolean ehUsuario;
	private String alvo;
	private String descricao;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Timestamp getDataParaSerExecutada() {
		return dataParaSerExecutada;
	}
	public void setDataParaSerExecutada(Timestamp dataParaSerExecutada) {
		this.dataParaSerExecutada = dataParaSerExecutada;
	}
	public boolean isEhSala() {
		return ehSala;
	}
	public void setEhSala(boolean ehSala) {
		this.ehSala = ehSala;
	}
	public String getIdSala() {
		return idSala;
	}
	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}
	public String getIdComputador() {
		return idComputador;
	}
	public void setIdComputador(String idComputador) {
		this.idComputador = idComputador;
	}
	public boolean isEhUsuario() {
		return ehUsuario;
	}
	public void setEhUsuario(boolean ehUsuario) {
		this.ehUsuario = ehUsuario;
	}
	public String getAlvo() {
		return alvo;
	}
	public void setAlvo(String alvo) {
		this.alvo = alvo;
	}
	

}
