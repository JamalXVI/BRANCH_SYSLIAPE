package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Date;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome="anosemestre")
public class AnoSemestre implements Serializable {
	private static final long serialVersionUID = 1L;
	@Coluna(nome="ANO_ANS")
	@Chave
	private int ano;
	@Chave
	@Coluna(nome="SEMESTRE_ANS")
	private int semestre;
	@Date
	@Coluna(nome="DATAINI_ANS")
	private LocalDate DataIni;
	@Date
	@Coluna(nome="DATAFIM_ANS")
	private LocalDate DataFim;
	@Coluna(nome="ATIVO_ANS")
	private boolean ativo;
	
	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public LocalDate getDataIni() {
		return DataIni;
	}

	public void setDataIni(LocalDate dataIni) {
		DataIni = dataIni;
	}

	public LocalDate getDataFim() {
		return DataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		DataFim = dataFim;
	}

}
