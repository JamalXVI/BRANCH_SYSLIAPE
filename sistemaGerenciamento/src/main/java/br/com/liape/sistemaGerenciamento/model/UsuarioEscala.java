package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome="usuario_escala")
public class UsuarioEscala implements Serializable{
	private static final long serialVersionUID = -168782161764777562L;
	@Coluna(nome="LOGIN_USR")
	@NotNull
	@Chave
	private String loginUsr;
	@Coluna(nome="ID_ESC")
	@NotNull
	@Chave
	private int idEsc;
	public int getIdEsc() {
		return idEsc;
	}
	public void setIdEsc(int idEsc) {
		this.idEsc = idEsc;
	}
	public String getLoginUsr() {
		return loginUsr;
	}
	public void setLoginUsr(String loginUsr) {
		this.loginUsr = loginUsr;
	}
	
}
