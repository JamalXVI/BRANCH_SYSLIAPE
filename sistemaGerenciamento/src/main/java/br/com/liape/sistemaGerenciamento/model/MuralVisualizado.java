package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.DateTime;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "Mural_Visualizado")
public class MuralVisualizado implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1558144020649700729L;
	@Coluna(nome="ID_MUR")
	@NotNull
	@Chave
	private int idMur;
	@Coluna(nome="LOGIN_USR")
	private String login;
	@Coluna(nome="APAGADO_MUV")
	private boolean apagado;
	@Coluna(nome="DATA_REGISTRO_MUV")
	@DateTime
	private LocalDateTime data;
	public int getIdMur() {
		return idMur;
	}
	public void setIdMur(int idMur) {
		this.idMur = idMur;
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
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
}
