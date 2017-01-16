package br.com.liape.sistemaGerenciamento.seguranca;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import br.com.liape.sistemaGerenciamento.model.Permissao;
import br.com.liape.sistemaGerenciamento.model.Usuario;

@SessionScoped
@Named
public class UsuarioLogado implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Calendar horario_login;

	public void fazLogin(Usuario usuario) {
		this.horario_login = Calendar.getInstance();
		this.usuario = usuario;
	}

	public void desloga() {
		this.usuario = null;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public boolean isLogado() {
		return this.usuario != null;
	}

	public List<Permissao> pegarAutorizacao() {
		return this.usuario.getGrupo().getPermissoes();
	}

	public Calendar getHorario_login() {
		return horario_login;
	}

	public void setHorario_login(Calendar horario_login) {
		this.horario_login = horario_login;
	}
}