package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Date;
import br.com.unaerp.jdbc.anotation.Tabela;
import br.com.unaerp.jdbc.anotation.Time;

@Tabela(nome = "turno")
public class Turno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6128980603978984319L;
	@Chave
	@AutoIncrement
	@Coluna(nome="ID_TUR")
	private int id;
	@Coluna(nome="PERIODO_TUR")
	private int periodo;
	@Coluna(nome="HORA_INICIO_TUR")
	@Time
	private LocalTime horaInicio;
	@Coluna(nome="HORA_FIM_TUR")
	@Time
	private LocalTime horaFim;
	@Date
	@Coluna(nome="REGISTRO_TUR")
	private LocalDate registro;
	@Coluna(nome="ATIVO_TUR")
	private boolean ativo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalTime getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}
	public LocalDate getRegistro() {
		return registro;
	}
	public void setRegistro(LocalDate registro) {
		this.registro = registro;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
