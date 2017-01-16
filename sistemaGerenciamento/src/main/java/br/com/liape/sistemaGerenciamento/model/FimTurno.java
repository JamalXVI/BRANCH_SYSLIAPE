package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Date;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "fim_turno")
public class FimTurno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6128980603978984319L;
	@Chave
	@Coluna(nome="ID_TUR")
	private int idTur;
	@Date
	@Coluna(nome="DATA_FTU")
	private LocalDate registro;
	public int getIdTur() {
		return idTur;
	}
	public void setIdTur(int idTur) {
		this.idTur = idTur;
	}
	public LocalDate getRegistro() {
		return registro;
	}
	public void setRegistro(LocalDate registro) {
		this.registro = registro;
	}
	
}
