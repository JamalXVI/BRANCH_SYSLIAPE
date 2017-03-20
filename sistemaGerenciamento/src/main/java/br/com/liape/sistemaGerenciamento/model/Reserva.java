package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "reserva")
public class Reserva implements Serializable {
	
	private static final long serialVersionUID = -646383921397057076L;
	
	@Chave
	@AutoIncrement
	@Coluna(nome = "ID_RES")
	private int id;
	@Coluna(nome = "CODIGO_DIS")
	@NotEmpty
	@NotNull
	private String codigoDis;
	@Coluna(nome = "CODIGO_PRO")
	@NotEmpty
	@NotNull
	private String codigoPro;
	@Coluna(nome = "LOGIN_USR")
	private String loginUsr;
	@Coluna(nome = "ANO_ANS")
	@NotNull
	private int anoAns;
	@Coluna(nome = "SEMESTRE_ANS")
	private int semestreAns;
	@Coluna(nome = "CODIGO_CUR")
	@NotNull
	@NotEmpty
	private String codigoCur;
	@Coluna(nome = "TURMA_RES")
	@Length(max = 1)
	private String turma;
	@Coluna(nome = "ATIVO")
	private boolean ativo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoDis() {
		return codigoDis;
	}

	public void setCodigoDis(String codigoDis) {
		this.codigoDis = codigoDis;
	}

	public String getCodigoPro() {
		return codigoPro;
	}

	public void setCodigoPro(String codigoPro) {
		this.codigoPro = codigoPro;
	}

	public String getLoginUsr() {
		return loginUsr;
	}

	public void setLoginUsr(String loginUsr) {
		this.loginUsr = loginUsr;
	}

	public int getAnoAns() {
		return anoAns;
	}

	public void setAnoAns(int anoAns) {
		this.anoAns = anoAns;
	}

	public int getSemestreAns() {
		return semestreAns;
	}

	public void setSemestreAns(int semestreAns) {
		this.semestreAns = semestreAns;
	}

	public String getCodigoCur() {
		return codigoCur;
	}

	public void setCodigoCur(String codigoCur) {
		this.codigoCur = codigoCur;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
