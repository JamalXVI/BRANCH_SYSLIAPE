package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "sala")
public class Sala implements Serializable {
	private static final long serialVersionUID = -8019127453471882639L;
	@Chave
	@AutoIncrement
	@Coluna(nome = "ID_SAL")
	private int id;
	@Coluna(nome = "NOME_SAL")
	@NotNull
	@NotEmpty
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
