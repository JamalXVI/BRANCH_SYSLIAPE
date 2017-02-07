package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;
import java.util.List;

public class EnviarEscala implements Serializable{
	private static final long serialVersionUID = -97756754810948904L;
	private String horaInicio;
	private String horaFim;
	private String dataInicio;
	private String dataFim;
	private List<String> usuarios;
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public List<String> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}
	
}
