package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Date;
import br.com.unaerp.jdbc.anotation.Tabela;
import br.com.unaerp.jdbc.anotation.Time;

@Tabela(nome="escalas")
public class Escala implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1212534181587428535L;
	@Chave
	@Coluna(nome="ID_ESC")
	private int id;
	@Coluna(nome="ENTRADA_ESC")
	@Time
	@Chave
	private LocalTime horaInicio;
	@Coluna(nome="SAIDA_ESC")
	@Time
	@Chave
	private LocalTime horaFim;
	@Coluna(nome="DIA_INI")
	@NotNull
	@Date
	@Chave
	private LocalDate dataIni;
	@Coluna(nome="DIA_FIM")
	@NotNull
	@Date
	@Chave
	private LocalDate dataFim;
	@Coluna(nome="ATIVO_ESC")
	private boolean ativo;
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public LocalDate getDataIni() {
		return dataIni;
	}
	public void setDataIni(LocalDate dataIni) {
		this.dataIni = dataIni;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
}
