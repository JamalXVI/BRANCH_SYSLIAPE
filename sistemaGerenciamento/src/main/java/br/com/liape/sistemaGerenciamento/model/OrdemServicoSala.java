package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "Ordem_Servico_Sala")
public class OrdemServicoSala implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2044556540865712620L;
	@Coluna(nome="ID_ORS")
	@NotNull
	@Chave
	private int idOrs;
	@Coluna(nome="ID_SAL")
	private int idSala;
	public int getIdOrs() {
		return idOrs;
	}
	public void setIdOrs(int idOrs) {
		this.idOrs = idOrs;
	}
	public int getIdSala() {
		return idSala;
	}
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	
}
