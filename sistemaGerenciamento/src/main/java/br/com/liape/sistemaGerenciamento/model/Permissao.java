package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "permissao")
public class Permissao implements Serializable{
	private static final long serialVersionUID = 1288374377820253043L;
	@AutoIncrement
	@Chave
	@Coluna(nome = "ID_PER")
	private int id;
	@Coluna(nome = "NOME_PER")
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
