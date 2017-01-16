package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RecadoUsuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6267836127615081210L;
	private String titulo;
	private boolean visualizado;
	private String usuarioRemetente;
	private int idRec;
	private Timestamp data;
	
	
	
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public int getIdRec() {
		return idRec;
	}
	public void setIdRec(int idRec) {
		this.idRec = idRec;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public boolean isVisualizado() {
		return visualizado;
	}
	public void setVisualizado(boolean visualizado) {
		this.visualizado = visualizado;
	}
	public String getUsuarioRemetente() {
		return usuarioRemetente;
	}
	public void setUsuarioRemetente(String usuarioRemetente) {
		this.usuarioRemetente = usuarioRemetente;
	}
	
}
