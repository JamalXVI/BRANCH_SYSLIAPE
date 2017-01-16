package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "Turno_Mural")
public class MuralTurno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6992572093354768100L;
	@Coluna(nome="ID_MUR")
	@NotNull
	@Chave
	private int idMur;
	@Coluna(nome="ID_TUR")
	@NotNull
	@Chave
	private int idTur;

	@Coluna(nome="ATIVO_TMU")
	private boolean ativo;
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getIdMur() {
		return idMur;
	}
	public void setIdMur(int idMur) {
		this.idMur = idMur;
	}
	public int getIdTur() {
		return idTur;
	}
	public void setIdTur(int idTur) {
		this.idTur = idTur;
	}
	
}
