package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "Computador")
public class Computador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8648576444010040139L;
	@Coluna(nome="ID_SAL")
	@NotNull
	@Chave
	private int idSal;
	@Coluna(nome="NUMERO_PC")
	@NotNull
	@Chave
	private int numeroPc;
	
	
	
}
