package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Turno;
import br.com.liape.sistemaGerenciamento.model.UsuarioTurno;

public class TurnoUsuario implements Serializable {
	private static final long serialVersionUID = 281182974164464137L;
	private Turno turno;
	private List<UsuarioTurno> usuariosTurno;

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<UsuarioTurno> getUsuariosTurno() {
		return usuariosTurno;
	}

	public void setUsuariosTurno(List<UsuarioTurno> usuariosTurno) {
		this.usuariosTurno = usuariosTurno;
	}

}
