package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "usuario")
public class Usuario implements Serializable, Comparable<Usuario> {
	private static final long serialVersionUID = 1L;
	@Length(min = 5)
	@NotNull
	@NotEmpty
	@Chave
	@Coluna(nome = "LOGIN_USR")
	private String login;
	@Length(min = 5)
	@NotNull
	@NotEmpty
	@Coluna(nome = "SENHA_USR")
	private String senha;
	@Coluna(nome = "ID_PES")
	private int idPes;
	@Coluna(nome = "ID_GRP")
	private int idGrupo;
	/*
	 *  Modelagem de Negócio
	 */
	private Pessoa pessoa;
	private Grupo grupo;
	

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIdPes() {
		return idPes;
	}

	public void setIdPes(int idPes) {
		this.idPes = idPes;
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	@Override
	public int compareTo(Usuario usr) {
		return this.getPessoa().getNome().compareTo(usr.getPessoa().getNome());
	}
}
