package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "Sub_Ordem_Turno")
public class SubOrdemTurno implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2111867249159541850L;
	@Coluna(nome="ID_SOR")
	@NotNull
	@Chave
	private int idSor;
	@Coluna(nome="ID_TUR")
	private int idTur;
	public int getIdSor() {
		return idSor;
	}
	public void setIdSor(int idSor) {
		this.idSor = idSor;
	}
	public int getIdTur() {
		return idTur;
	}
	public void setIdTur(int idTur) {
		this.idTur = idTur;
	}
	
}
