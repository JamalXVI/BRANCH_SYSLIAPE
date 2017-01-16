package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.DateTime;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "SUB_ORDEM")
public class SubOrdem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3932781079722820555L;
	@Coluna(nome="ID_SOR")
	@NotNull
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="ID_ORS")
	@NotNull
	private int idOrs;
	@Coluna(nome="DATA_GERADA_SOR")
	@DateTime
	private LocalDateTime dataGerada;
	@Coluna(nome="DATA_PARA_SER_EXECUTADA_SOR")
	@DateTime
	private LocalDateTime dataParaSerExecutada;
	@Coluna(nome="DATA_EXECUTADA_SOR")
	@DateTime
	private LocalDateTime dataExecutada;
	@Coluna(nome="LOGIN_USR")
	private String login;
	@Coluna(nome="JUSTIFICATIVA_SOR")
	private String justificativa;
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
	public LocalDateTime getDataGerada() {
		return dataGerada;
	}
	public void setDataGerada(LocalDateTime dataGerada) {
		this.dataGerada = dataGerada;
	}
	public LocalDateTime getDataParaSerExecutada() {
		return dataParaSerExecutada;
	}
	public void setDataParaSerExecutada(LocalDateTime dataParaSerExecutada) {
		this.dataParaSerExecutada = dataParaSerExecutada;
	}
	public LocalDateTime getDataExecutada() {
		return dataExecutada;
	}
	public void setDataExecutada(LocalDateTime dataExecutada) {
		this.dataExecutada = dataExecutada;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	
	
	
}
