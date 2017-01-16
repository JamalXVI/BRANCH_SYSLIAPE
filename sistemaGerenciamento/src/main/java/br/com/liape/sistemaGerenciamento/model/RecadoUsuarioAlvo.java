package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;
@Tabela(nome="recados_usuario_alvo")
public class RecadoUsuarioAlvo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2625958902238421677L;
	@Coluna(nome="ID_REC")
	@NotNull
	@Chave
	private int idRec;
	@Coluna(nome="LOGIN_USR")
	@Chave
	private String login;

	@Coluna(nome="APAGADO_RUA")
	private boolean apagado;
	
	@Coluna(nome="VISUALIZADO_RUA")
	private boolean visualizado;

	public int getIdRec() {
		return idRec;
	}

	public void setIdRec(int idRec) {
		this.idRec = idRec;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isApagado() {
		return apagado;
	}

	public void setApagado(boolean apagado) {
		this.apagado = apagado;
	}

	public boolean isVisualizado() {
		return visualizado;
	}

	public void setVisualizado(boolean visualizado) {
		this.visualizado = visualizado;
	}
	
	
}
