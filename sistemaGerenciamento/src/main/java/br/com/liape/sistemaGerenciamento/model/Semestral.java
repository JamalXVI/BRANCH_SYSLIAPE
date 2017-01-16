package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;
import br.com.unaerp.jdbc.anotation.Time;

@Tabela(nome="semestral")
public class Semestral implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1024314715857557530L;
	@Coluna(nome="ID_RES")
	@NotNull
	private int idRes;
	@Coluna(nome="ID_SEM")
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="DIA_SEMANA_SEM")
	@NotNull
	private int diaSemana;
	@Time
	@Coluna(nome="HORA_INICIO_SEM")
	private LocalTime horaInicio;
	@Time
	@Coluna(nome="HORA_TERMINO_SEM")
	private LocalTime horaFim;
	@Coluna(nome="OBSERVACAO_SEM")
	private String observacao;
	@Coluna(nome="ID_SAL")
	@NotNull
	private int idSal;
	
	
	public int getIdSal() {
		return idSal;
	}
	public void setIdSal(int idSal) {
		this.idSal = idSal;
	}
	private Reserva reserva;
	
	@Coluna(nome="ATIVO_SEM")
	private boolean ativo;
	
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public int getIdRes() {
		return idRes;
	}
	public void setIdRes(int idRes) {
		this.idRes = idRes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
	
}
