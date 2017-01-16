package br.com.liape.sistemaGerenciamento.model;

import br.com.unaerp.jdbc.anotation.*;

@Tabela(nome="CHAMADO_SERVICO")
public class ChamadoServico {

	@Chave
	@Coluna(nome="NUMERO_CHM")
	private int numeroChamado;
	@Chave
	@Coluna(nome="ID_DEP")
	private int idDepartamento;
	
	public ChamadoServico() {
		this.numeroChamado = 0;
		this.idDepartamento = 0;
	}

	public int getNumeroChamado() {
		return numeroChamado;
	}

	public void setNumeroChamado(int numeroChamado) {
		this.numeroChamado = numeroChamado;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	
}
