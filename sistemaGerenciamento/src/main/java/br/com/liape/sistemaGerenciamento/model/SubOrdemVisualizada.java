package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "SubOrdem_Visualizada")
public class SubOrdemVisualizada implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2111867249159541850L;
	@Coluna(nome="ID_SOR")
	@NotNull
	@Chave
	private int idSor;
	@Coluna(nome="LOGIN_USR")
	private String loginUsr;
	public int getIdSor() {
		return idSor;
	}
	public void setIdSor(int idSor) {
		this.idSor = idSor;
	}
	public String getLoginUsr() {
		return loginUsr;
	}
	public void setLoginUsr(String loginUsr) {
		this.loginUsr = loginUsr;
	}
	
}
