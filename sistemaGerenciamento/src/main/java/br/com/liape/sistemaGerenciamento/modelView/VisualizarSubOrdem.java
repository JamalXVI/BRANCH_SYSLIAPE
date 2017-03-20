package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;
import java.sql.Timestamp;

public class VisualizarSubOrdem implements Serializable {
	private static final long serialVersionUID = 4439213987021006244L;
	private Timestamp dataGerada;
	private Timestamp dataParaSerExecutada;
	private String login;
	private String descricao;
	private String justificativa;
	public Timestamp getDataGerada() {
		return dataGerada;
	}
	public void setDataGerada(Timestamp dataGerada) {
		this.dataGerada = dataGerada;
	}
	public Timestamp getDataParaSerExecutada() {
		return dataParaSerExecutada;
	}
	public void setDataParaSerExecutada(Timestamp dataParaSerExecutada) {
		this.dataParaSerExecutada = dataParaSerExecutada;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
}
