package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome="disciplina")
public class Disciplina implements Serializable, Comparable<Disciplina> {
	private static final long serialVersionUID = 5503781002895050651L;
	@Coluna(nome="CODIGO_DIS")
	@Chave
	@NotEmpty
	@Length(max=5)
	private String codigo;
	@Coluna(nome="NOME_DIS")
	@NotEmpty
	@NotNull
	private String nome;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public int compareTo(Disciplina dis) {
		return this.getCodigo().compareTo(dis.getCodigo());
	}
	
}
