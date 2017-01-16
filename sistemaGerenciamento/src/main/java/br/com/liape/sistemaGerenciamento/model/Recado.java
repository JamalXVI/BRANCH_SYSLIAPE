package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.DateTime;
import br.com.unaerp.jdbc.anotation.Tabela;
@Tabela(nome="recados")
public class Recado implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2625958902238421677L;
	@Coluna(nome="ID_REC")
	@NotNull
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="DATA_REGISTRO_REC")
	@DateTime
	private LocalDateTime data;
	@Coluna(nome="DESCRICAO_REC")
	private String descricao;
	@Coluna(nome="LOGIN_USR")
	private String login;
	@Coluna(nome="TITULO_REC")
	private String titulo;
	@Coluna(nome="ATIVO_REC")
	private boolean ativo;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	

}
