package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "telefone")
public class Telefone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8370101270385218179L;
	@NotEmpty
	@Coluna(nome = "DDD_TEL")
	private String ddd;
	@NotEmpty
	@Coluna(nome = "TELEFONE_TEL")
	private String numero;
	@NotEmpty
	@Coluna(nome = "OPERADORA_TEL")
	private String operadora;
	@Coluna(nome = "ID_PES")
	private int idPes;
	@Coluna(nome="ATIVO_TEL")
	private boolean ativo;
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getIdPes() {
		return idPes;
	}

	public void setIdPes(int idPes) {
		this.idPes = idPes;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
}
