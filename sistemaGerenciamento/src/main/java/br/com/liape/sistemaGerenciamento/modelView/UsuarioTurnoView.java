package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;

import br.com.liape.sistemaGerenciamento.model.Usuario;

public class UsuarioTurnoView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9070378719336781883L;
	private int idTur;
	private String loginUsr;
	private Usuario usuario;
	public int getIdTur() {
		return idTur;
	}
	public void setIdTur(int idTur) {
		this.idTur = idTur;
	}
	public String getLoginUsr() {
		return loginUsr;
	}
	public void setLoginUsr(String loginUsr) {
		this.loginUsr = loginUsr;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
