package br.comliape.sistemaGerenciamento.models;

import br.com.liape.sistemaGerenciamento.model.Pessoa;

public class UsuarioPessoa {
	private Pessoa pessoa;
	private String loginUSR;
	private String senhaUSR;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getLoginUSR() {
		return loginUSR;
	}

	public void setLoginUSR(String loginUSR) {
		this.loginUSR = loginUSR;
	}

	public String getSenhaUSR() {
		return senhaUSR;
	}

	public void setSenhaUSR(String senhaUSR) {
		this.senhaUSR = senhaUSR;
	}
}
