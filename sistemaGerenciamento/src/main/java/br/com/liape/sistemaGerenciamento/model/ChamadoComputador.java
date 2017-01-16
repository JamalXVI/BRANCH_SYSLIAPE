package br.com.liape.sistemaGerenciamento.model;

import br.com.unaerp.jdbc.anotation.*;

public class ChamadoComputador {

	@Chave
	@Coluna(nome="NUMERO_CHM")
	private int numeroChamado;
	@Chave
	@Coluna(nome="ID_SAL")
	private int idSala;
	@Chave
	@Coluna(nome="NUMERO_PC")
	private int numeroPc;
	
	public ChamadoComputador() {
		this.numeroChamado = 0;
		this.idSala = 0;
		this.numeroPc = 0;
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

	public int getNumeroPc() {
		return numeroPc;
	}

	public void setNumeroPc(int numeroPc) {
		this.numeroPc = numeroPc;
	}
	
}
