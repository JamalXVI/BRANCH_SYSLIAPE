package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "Ordem_Servico")
public class OrdemServico implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6774695463496637370L;
	@Coluna(nome="ID_ORS")
	@NotNull
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="EXECUTADA_ORS")
	private boolean executada;
	@Coluna(nome="ATIVO_ORS")
	private boolean ativo;
	@Coluna(nome="DESCRICAO_ORS")
	private String descricao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isExecutada() {
		return executada;
	}
	public void setExecutada(boolean executada) {
		this.executada = executada;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
