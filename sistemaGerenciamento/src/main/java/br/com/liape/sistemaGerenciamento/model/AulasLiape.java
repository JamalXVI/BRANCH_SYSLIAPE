package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;
import br.com.unaerp.jdbc.anotation.Time;

@Tabela(nome="aulas_liape")
public class AulasLiape implements Serializable{
	private static final long serialVersionUID = 7149936285030837143L;
	@Coluna(nome="ID_AUL")
	@Chave
	@AutoIncrement
	private int id;
	@Time
	@Coluna(nome="HORA_INICIO_AUL")
	private LocalTime horaInicio;
	@Time
	@Coluna(nome="HORA_FIM_AUL")
	private LocalTime horaFim;
	@Coluna(nome="ATIVO_AUL")
	private boolean ativo;
	@Coluna(nome="STATUS_AUL")
	private int status;
	@Coluna(nome="DESCRICAO_AUL")
	private String descricao;
	
	@Coluna(nome="SALA_AUL")
	private String sala;
	@Coluna(nome="DATA_AUL")
	private LocalDate data;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
