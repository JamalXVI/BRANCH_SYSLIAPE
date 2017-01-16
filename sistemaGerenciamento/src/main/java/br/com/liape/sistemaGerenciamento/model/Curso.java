package br.com.liape.sistemaGerenciamento.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome="curso")
public class Curso {
	@Coluna(nome="CODIGO_CUR")
	@Chave
	@NotEmpty
	@Length(max=2)
	private String codigo;
	@Coluna(nome="NOME_CUR")
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
	
}
