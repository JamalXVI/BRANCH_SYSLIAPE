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

@Tabela(nome="exporadico")
public class Exporadico implements Serializable{
	private static final long serialVersionUID = -2625958902238421677L;
	@Coluna(nome="ID_RES")
	@NotNull
	@Chave
	private int idRes;
	@Coluna(nome="DATA_MARCADA_EXP")
	@NotNull
	@Date
	@Chave
	private LocalDate data;
	@Coluna(nome="HORA_INICIO_EXP")
	@Time
	@Chave
	private LocalTime horaInicio;
	@Coluna(nome="HORA_FIM_EXP")
	@Time
	@Chave
	private LocalTime horaFim;
	@Coluna(nome="OBSERVACAO_EXP")
	private String observacao;
	
	private Reserva reserva;
	@Coluna(nome="ID_SAL")
	@NotNull
	@Chave
	private int idSal;
	
	@Coluna(nome="ATIVO_EXP")
	private boolean ativo;
	
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getIdSal() {
		return idSal;
	}
	public void setIdSal(int idSal) {
		this.idSal = idSal;
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
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
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
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	@Override
	public String toString() {
		return getData().toString()+" "+getHoraInicio().toString()+" - "+getHoraFim().toString()
				+" Reserva:"+getIdRes();
	}
}
