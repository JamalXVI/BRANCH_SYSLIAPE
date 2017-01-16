package br.com.liape.sistemaGerenciamento.model;

import java.time.LocalDateTime;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "Login")
public class Login {
	@Chave
	@AutoIncrement
	@Coluna(nome = "ID_LOG")
	private int id;
	@Coluna(nome = "LOGIN_USR")
	private String loginUsr;
	@Coluna(nome = "DATA_LOG")
	private LocalDateTime data;

	public String getLoginUsr() {
		return loginUsr;
	}

	public void setLoginUsr(String loginUsr) {
		this.loginUsr = loginUsr;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
