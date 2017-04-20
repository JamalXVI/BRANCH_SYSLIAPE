package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.DateTime;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome="movimento_arquivos")
public class MovimentoArquivos implements Serializable{
	private static final long serialVersionUID = 3014016748456835535L;
	@Coluna(nome="ID_MAR")
	@NotNull
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="ID_ARQ")
	@NotNull
	private int idArq;
	@Coluna(nome="OPERACAO_MAR")
	private int operacao;
	@Coluna(nome="LOGIN_USR")
	private String loginUsr;
	@Coluna(nome="DATA_MAR")
	@DateTime
	private LocalDateTime data;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdArq() {
		return idArq;
	}
	public void setIdArq(int idArq) {
		this.idArq = idArq;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public int getOperacao() {
		return operacao;
	}
	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}
	public String getLoginUsr() {
		return loginUsr;
	}
	public void setLoginUsr(String loginUsr) {
		this.loginUsr = loginUsr;
	}
	
	
	
}
