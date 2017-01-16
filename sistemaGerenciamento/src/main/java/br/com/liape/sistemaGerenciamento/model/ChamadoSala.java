package br.com.liape.sistemaGerenciamento.model;

import br.com.unaerp.jdbc.anotation.*;

@Tabela(nome="CHAMADO_SALA")
public class ChamadoSala {
	
	@Chave
	@Coluna(nome="NUMERO_CHM")
	private int numeroChamado;
	@Chave
	@Coluna(nome="ID_SAL")
	private int idSala;
	
	public ChamadoSala() {
		this.numeroChamado = 0;
		this.idSala = 0;
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
	
}
