package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;
import java.sql.Timestamp;

public class MuralUsuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6267836127615081210L;
	private String titulo;
	private boolean visualizado;
	private String turnoRemetente;
	private int idMur;
	private Timestamp data;
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public String getTurnoRemetente() {
		return turnoRemetente;
	}
	public void setTurnoRemetente(String turnoRemetente) {
		this.turnoRemetente = turnoRemetente;
	}
	public int getIdMur() {
		return idMur;
	}
	public void setIdMur(int idMur) {
		this.idMur = idMur;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	
	

	
}
