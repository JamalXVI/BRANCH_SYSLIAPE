package br.com.liape.sistemaGerenciamento.model;

import br.com.unaerp.jdbc.anotation.*;

public class ChamadoMonitor {
	
	@Chave
	@Coluna(nome="NUMERO_CHM")
	private int numeroChamado;
	@Chave
	@Coluna(nome="ID_SAL")
	private int idSala;
	@Chave
	@Coluna(nome="NUMERO_MON")
	private int numeroMonitor;
	
	public ChamadoMonitor() {
		this.numeroChamado = 0;
		this.idSala = 0;
		this.numeroChamado = 0;
	}

	public int getNumeroChamado() {
		return numeroChamado;
	}

	public void setNumeroChamado(int numeroChamado) {
		this.numeroChamado = numeroChamado;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public int getNumeroMonitor() {
		return numeroMonitor;
	}

	public void setNumeroMonitor(int numeroMonitor) {
		this.numeroMonitor = numeroMonitor;
	}

}
