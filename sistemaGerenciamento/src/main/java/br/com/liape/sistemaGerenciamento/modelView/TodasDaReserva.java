package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Curso;
import br.com.liape.sistemaGerenciamento.model.Disciplina;
import br.com.liape.sistemaGerenciamento.model.Exporadico;
import br.com.liape.sistemaGerenciamento.model.Professor;
import br.com.liape.sistemaGerenciamento.model.Reserva;
import br.com.liape.sistemaGerenciamento.model.Semestral;

public class TodasDaReserva implements Serializable{
	private static final long serialVersionUID = -3884575580502431434L;
	private Reserva reserva;
	private List<Semestral> semestrais;
	private List<Exporadico> exporadicos;
	
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public List<Semestral> getSemestrais() {
		return semestrais;
	}
	public void setSemestrais(List<Semestral> semestrais) {
		this.semestrais = semestrais;
	}
	public List<Exporadico> getExporadicos() {
		return exporadicos;
	}
	public void setExporadicos(List<Exporadico> exporadicos) {
		this.exporadicos = exporadicos;
	}
	
}
