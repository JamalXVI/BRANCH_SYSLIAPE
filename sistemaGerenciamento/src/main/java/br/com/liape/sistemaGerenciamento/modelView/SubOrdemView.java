package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;
import java.sql.Timestamp;

public class SubOrdemView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9034689861036286017L;
	private int id;
	private int idOrs;
	private String login;
	private Timestamp dataGerada;
	private Timestamp dataParaSerExecutada;
	private String justificativa;
	private String loginUsr;
	

	public String getLoginUsr() {
		return loginUsr;
	}

	public void setLoginUsr(String loginUsr) {
		this.loginUsr = loginUsr;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOrs() {
		return idOrs;
	}

	public void setIdOrs(int idOrs) {
		this.idOrs = idOrs;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

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

}
