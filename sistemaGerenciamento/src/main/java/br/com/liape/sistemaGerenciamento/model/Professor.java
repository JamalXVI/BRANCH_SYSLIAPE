package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "professor")
public class Professor implements Serializable, Comparable<Professor> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3115093295228902162L;
	@Coluna(nome = "ID_PES")
	private int idPes;
	@NotNull
	@NotEmpty
	@Length(min = 4, max = 5)
	@Coluna(nome = "CODIGO_PRO")
	@Chave
	private String codigo;
	/*
	 * Modelagem de Negócio
	 */
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getIdPes() {
		return idPes;
	}

	public void setIdPes(int idPes) {
		this.idPes = idPes;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public int compareTo(Professor profNov) {
		return profNov.getCodigo().compareTo(this.getCodigo());
	}
}
