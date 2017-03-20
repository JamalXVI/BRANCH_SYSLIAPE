package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "usuario_turno")
public class UsuarioTurno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6128980603978984319L;
	@Chave
	@Coluna(nome="ID_TUR")
	private int idTur;
	@Chave
	@Coluna(nome="LOGIN_USR")
	private String loginUsr;
	public int getIdTur() {
		return idTur;
	}
	public void setIdTur(int idTur) {
		this.idTur = idTur;
	}
	public String getLoginUsr() {
		return loginUsr;
	}
	public void setLoginUsr(String loginUsr) {
		this.loginUsr = loginUsr;
	}
	@Override
	public String toString() {
		return String.valueOf(getIdTur()) + " / " + getLoginUsr();
	}
}
