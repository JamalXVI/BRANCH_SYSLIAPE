package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome="arquivos")
public class Arquivos implements Serializable{
	private static final long serialVersionUID = 3014016748456835535L;
	@Coluna(nome="ID_ARQ")
	@NotNull
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="CAMINHO_ARQ")
	private String caminho;
	@Coluna(nome="TITULO_ARQ")
	private String titulo;
	@Coluna(nome="DESCRICAO_ARQ")
	private String descricao;
	@Coluna(nome="ATIVO_ARQ")
	private boolean ativo;
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
