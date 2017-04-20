package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "menu_usuario")
public class MenuUsuario implements Serializable {
	private static final long serialVersionUID = 6431566271925688323L;
	@Coluna(nome = "ID_MU")
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome = "LOGIN_USR")
	private String loginUsr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginUsr() {
		return loginUsr;
	}

	public void setLoginUsr(String loginUsr) {
		this.loginUsr = loginUsr;
	}

}
