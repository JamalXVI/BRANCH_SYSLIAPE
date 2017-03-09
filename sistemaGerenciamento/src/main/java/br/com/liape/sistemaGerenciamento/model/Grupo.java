package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.util.List;
import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "grupo")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Chave
	@AutoIncrement
	@Coluna(nome = "ID_GRP")
	private int id;
	@Coluna(nome = "NOME_GRP")
	private String nome;
	@Coluna(nome = "ATIVO_GRP")
	private boolean ativo;
	@Coluna(nome="HIERARQUIA_GRP")
	private int hierarquia;
	/*
	 * Modelagem de Negócio
	 */
	private List<Usuario> usuarios;
	private List<Permissao> permissoes;
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

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

	public int getHierarquia() {
		return hierarquia;
	}

	public void setHierarquia(int hierarquia) {
		this.hierarquia = hierarquia;
	}
	
}
